package Utility.Lecture;

import GameDesing.Game;
import Persons.NurseJoy;
import Persons.Trader;
import Persons.Trainer;
import Pokemons.Logic.Items.Item;
import Pokemons.Pokemon;
import Utility.DataBase.ItemsDataBase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;

public class SaveLoadManager {

    private static final String SAVE_FILE = "pokepository_save.txt";

    private static class SaveData {
        Trainer trainer1;
        Trainer trainer2;
        Trader trader;
        NurseJoy nurseJoy;
    }

    // --- ADAPTER 1: Para LocalDate ---
    private static class LocalDateAdapter extends TypeAdapter<LocalDate> {
        @Override
        public void write(JsonWriter out, LocalDate value) throws IOException {
            if (value == null) out.nullValue();
            else out.value(value.toString());
        }
        @Override
        public LocalDate read(JsonReader in) throws IOException {
            if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            return LocalDate.parse(in.nextString());
        }
    }

    // --- ADAPTER 2: Para Items (Soluciona el error del Trader) ---
    private static class ItemAdapter extends TypeAdapter<Item> {
        @Override
        public void write(JsonWriter out, Item item) throws IOException {
            if (item == null) {
                out.nullValue();
            } else {
                // Guardamos solo el nombre del ítem
                out.value(item.getItName());
            }
        }

        @Override
        public Item read(JsonReader in) throws IOException {
            if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            String name = in.nextString();
            // Buscamos el ítem original en la base de datos por su nombre
            for (Item item : ItemsDataBase.pkItemsList) {
                if (item.getItName().equals(name)) {
                    return item;
                }
            }
            return null; // Si no se encuentra (ej. versión antigua), devuelve null
        }
    }

    // --- ADAPTER 3: Para la clase Abstracta Pokemon ---
    private static class PokemonAdapter implements JsonSerializer<Pokemon>, JsonDeserializer<Pokemon> {
        @Override
        public JsonElement serialize(Pokemon src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject result = context.serialize(src).getAsJsonObject();
            result.addProperty("type", src.getClass().getSimpleName());
            return result;
        }

        @Override
        public Pokemon deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            if (!jsonObject.has("type")) {
                throw new JsonParseException("Falta el tipo de Pokémon.");
            }
            String type = jsonObject.get("type").getAsString();
            try {
                return context.deserialize(jsonObject, Class.forName("Pokemons." + type));
            } catch (ClassNotFoundException cnfe) {
                throw new JsonParseException("Clase desconocida: " + type, cnfe);
            }
        }
    }

    // --- CONFIGURACIÓN GSON ---
    private static Gson createGson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .enableComplexMapKeySerialization() // Ayuda extra para mapas complejos
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(Pokemon.class, new PokemonAdapter())
                .registerTypeAdapter(Item.class, new ItemAdapter()) // ¡REGISTRAMOS EL NUEVO ADAPTER!
                .setExclusionStrategies(new com.google.gson.ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(com.google.gson.FieldAttributes f) {
                        return f.getDeclaredClass().getName().contains("java.awt.image.BufferedImage") ||
                               f.getDeclaredClass().getName().contains("javax.swing.JPanel") ||
                               f.getDeclaredClass().getName().contains("GameDesing.Graphics");
                    }
                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) { return false; }
                })
                .create();
    }

    // --- GUARDAR Y CARGAR ---

    public static void saveGame(Game game) {
        SaveData data = new SaveData();
        data.trainer1 = game.getgTrainer1();
        data.trainer2 = game.getgTrainer2();
        data.trader = game.getgTrader();
        data.nurseJoy = game.getgNurseJoy();

        Gson gson = createGson();
        String jsonString = gson.toJson(data);

        try (FileWriter writer = new FileWriter(SAVE_FILE)) {
            writer.write(jsonString);
            System.out.println("Partida guardada en " + SAVE_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean loadGame(Game game) {
        StringBuilder jsonString = new StringBuilder();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
        } catch (IOException e) {
            System.out.println("No se encontró archivo de guardado.");
            return false;
        }

        try {
            Gson gson = createGson();
            SaveData data = gson.fromJson(jsonString.toString(), SaveData.class);
    
            game.setgTrainer1(data.trainer1);
            game.setgTrainer2(data.trainer2);
            game.setgTrader(data.trader);
            game.setgNurseJoy(data.nurseJoy);
            
            System.out.println("Partida cargada con éxito.");
            return true;
        } catch (Exception e) {
            System.err.println("Error al leer el archivo de guardado: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}