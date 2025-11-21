package Pokemons.Logic.Items;

import Utility.Constants.PkEffectsEnum;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO; 
import java.io.IOException; 

public class Item {
    private final String itName;
    private final String itDescription;
    private final int itPrice;
    private final PkEffectsEnum itEffect;
    private final int itEffectValue;
    private BufferedImage img; 

    // Modifica el constructor
    public Item(String itName, String itDescription, int itPrice, PkEffectsEnum itEffect, int itEffectValue){
        this.itName = itName;
        this.itDescription = itDescription; 
        this.itPrice = itPrice;
        this.itEffect = itEffect;
        this.itEffectValue = itEffectValue;
        loadImg();
    }
    
    // Getters
    public String getItName(){
        return itName;
    }
    public int getItPrice(){
        return itPrice;
    }
    public PkEffectsEnum getItEffect(){
        return itEffect;
    }
    public int getItEffectValue(){
        return itEffectValue;
    }

    // --- MÉTODOS AÑADIDOS DE TU VERSIÓN GRÁFICA ---

    public String getItDescription(){ // <-- MÉTODO AÑADIDO
        return this.itDescription;
    }
    
    public BufferedImage getImg() { // <-- MÉTODO AÑADIDO
        return this.img;
    }

    // Método para cargar la imagen (¡asume que las imágenes están en /items/!)
    private void loadImg() { // <-- MÉTODO AÑADIDO
        try {
            // Asume que tienes las imágenes de los items en /resources/items/Potion.png, /items/Antidote.png, etc.
            // Si la ruta es diferente, ajústala aquí.
            String path = "/items/" + this.itName + ".png";
            this.img = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error cargando imagen para item: " + this.itName);
            this.img = null; // Deja la imagen como null si no se encuentra
        }
    }
    // --- FIN DE MÉTODOS AÑADIDOS ---
    
    @Override
    public String toString(){
        // (toString original de la lógica)
        return "\nName:\t" + this.getItName() + "\nPrice:\t" + this.getItPrice() + "\nEffects:\t" + this.getItEffect() + "\nValue:\t" + this.getItEffectValue();
    }
}