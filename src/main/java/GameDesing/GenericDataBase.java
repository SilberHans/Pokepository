package GameDesing;

import java.util.List;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public final class GenericDataBase {
    
    private GenericDataBase(){}
    
    private static final List<String> gdbTraderNameList = List.of("Chip", "Pixel", "Volt", "Perno", "Watts", "Gizmo", "Axel", "Sauce", "Musgo", "Raíz", "Baya", "Salvia", "Enebro", "Céfiro", "Aura", "Sly", "Murmullos", "Ganzúa", "Cuervo", "El Rata", "Nervos", "Sombra", "Twitch", "Marty", "Gus", "Timmy", "Tim", "Rick", "Stan", "Sal", "Leo", "Ken", "Bruno", "Marco", "Silas", "Balthazar", "Preston", "Cornelius", "Reginald (Reggie)", "Barney", "Mortimer", "Gideon", "Cyrus", "Orville", "Flint", "Jasper", "Milo", "Baxter", "Atlas", "Orion", "Rocco", "Spike", "Zane", "Griffin");
    private static final List<String> gdbPersonRegionList = List.of("Kanto", "Johto", "Hoenn", "Sinnoh", "Unova", "Kalos", "Alola", "Galar", "Hisui", "Paldea", "Orre", "Fiore", "Almia", "Oblivia", "Ransei", "Ferrum", "AEos", "Lental", "Pasio");
    //private static final List<String> gdbPokemonNickNameList = List.of("Alakazam", "Electrode", "Arcanine", "Bulbasaur","Gyarados", "Charmander", "Dodrio", "Dugtrio","Hitmonlee","Victreebel", "Squirtle", "Pikachu");
    
    public static String getRndmTraderName(){
        return gdbTraderNameList.get(ThreadLocalRandom.current().nextInt(gdbTraderNameList.size()));
    }
    
    public static String getRndmPersonRegion(){
        return gdbPersonRegionList.get(ThreadLocalRandom.current().nextInt(gdbPersonRegionList.size()));
    }
    
    //public static String getRndmPokemonNickName(){
    //    return gdbPokemonNickNameList.get(ThreadLocalRandom.current().nextInt(gdbPokemonNickNameList.size()));
    //}
    
    public static String genRndmPersonID(int gdbPersonType, String gdbPersonRegion){
        switch(gdbPersonType){
            case 0 -> {return "TNR-" + gdbPersonRegion + "-" + String.format("%03d", ThreadLocalRandom.current().nextInt(1, 1000));}
            case 1 -> {return "NJ-" + gdbPersonRegion + "-" + String.format("%03d", ThreadLocalRandom.current().nextInt(1, 1000));}
            case 2 -> {return "TRD-" + gdbPersonRegion + "-" + String.format("%03d", ThreadLocalRandom.current().nextInt(1, 1000));}
            default -> {return "Uhh...";}
        }
    }
    
    public static LocalDate genRndmDateByCrrntYears(int gdbMinCrrntYears, int gdbMaxCrrntYears){
        LocalDate gdbLatestPossibleDate = LocalDate.now().minusYears(gdbMinCrrntYears);
        LocalDate gdbEarliestPossibleDate = LocalDate.now().minusYears(gdbMaxCrrntYears);
        long gdbMinDays = gdbEarliestPossibleDate.toEpochDay();
        long gdbMaxDays = gdbLatestPossibleDate.toEpochDay();
        Long gdbRndmDays = ThreadLocalRandom.current().nextLong(gdbMinDays, gdbMaxDays + 1);
        return LocalDate.ofEpochDay(gdbRndmDays);
    }
}