package GameDesing;

import java.util.List;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public final class GenericDataBase {
    
    private GenericDataBase(){}
    
    private static final List<String> gdbTraderNameList = List.of("Chip", "Pixel", "Volt", "Perno", "Watts", "Gizmo", "Axel", "Sauce", "Musgo", "Raíz", "Baya", "Salvia", "Enebro", "Céfiro", "Aura", "Sly", "Murmullos", "Ganzúa", "Cuervo", "El Rata", "Nervos", "Sombra", "Twitch", "Marty", "Gus", "Timmy", "Tim", "Rick", "Stan", "Sal", "Leo", "Ken", "Bruno", "Marco", "Silas", "Balthazar", "Preston", "Cornelius", "Reginald (Reggie)", "Barney", "Mortimer", "Gideon", "Cyrus", "Orville", "Flint", "Jasper", "Milo", "Baxter", "Atlas", "Orion", "Rocco", "Spike", "Zane", "Griffin");
    private static final List<String> gdbPersonRegionList = List.of("Kanto", "Johto", "Hoenn", "Sinnoh", "Unova", "Kalos", "Alola", "Galar", "Hisui", "Paldea", "Orre", "Fiore", "Almia", "Oblivia", "Ransei", "Ferrum", "AEos", "Lental", "Pasio");
    private static final List<String> gdbPokemonNickNameList = List.of("Pika", "Ratata", "Soff", "BigGuy", "LilGuy", "TheDude", "ElJefe", "Jefe", "ElPutoAmo", "Red", "Blue", "Verde", "Tiny", "Smalls", "Biggy", "Fatty", "Tubs", "Jumbo", "Puffy", "Speedy", "Slowbro", "Zippy", "Heavy", "Lazy", "Shiny", "Zap", "Buzz", "Bzzzt", "Nova", "Comet", "Quark", "Pixel", "Vector", "Helix", "Alpha", "Omega", "Beta", "Sigma", "Delta", "Epsilon", "Cyro", "Pyro", "Geo", "Aero", "Static", "Volta", "Ion", "Neutron", "King", "Queen", "Prince", "Duke", "Baron", "Rex", "Kaiser", "Knight", "Rook", "Bishop", "Merlin", "Arthur", "Excalibur", "Galahad", "Lancelot", "Karma", "Zen", "Chaos", "Riot", "Jinx", "Lucky", "Hope", "Destiny", "Ace", "Glimmer", "Hazard", "Zero", "Void", "Echo", "Mirage", "Rogue", "Sparky", "Blaze", "Cinder", "Hydro", "Splash", "Bubbles", "Sprout", "Leafy", "Rocky", "Pebbles", "Stony", "Bolt", "Zappy", "Shadow", "Spooky", "Boo", "Scales", "Scythe", "Terra", "Gale", "Gusty", "Kevin", "Steve", "Bob", "Frank", "Gus", "Brenda", "Garry", "Larry", "Carl", "Chad", "Terry", "Susan", "Dave", "Greg", "Jerry", "Morty", "Rick", "Sal", "Ben", "Zeus", "Hades", "Thor", "Loki", "Ares", "Apollo", "Artemis", "Athena", "Poseidon", "Hercules", "Kratos", "Odin", "Anubis", "Ra", "Shiva", "Titan", "Goliath", "Beast", "Slayer", "Reaper", "Tank", "Juggernaut", "Crush", "Rage", "Spike", "Fang", "Claw", "Blade", "Edge", "Doom", "Rhino", "Brutus", "Goku", "Godzilla", "Hulk", "Yoshi", "Kirby", "Zelda", "Ganon", "Sephiroth", "Knuckles", "Sonic", "Sasuke", "Natsu", "Link", "Sheik", "Vader", "Buddy", "Pal", "Chispitas", "Cuddles", "Fluffy", "Puffles", "Muffin", "Peanut", "Waffles", "Biscuit", "Patitas", "Paws", "Whiskers", "Smokey", "Nugget", "Chicken", "Sushi", "Taco", "Mochi", "Meatball", "Porkchop", "Waddles", "Mitten", "Calcetín", "Sock", "Button", "Goober", "Bean");
    
    public static String getRndmTraderName(){
        return gdbTraderNameList.get(ThreadLocalRandom.current().nextInt(gdbTraderNameList.size()));
    }
    
    public static String getRndmPersonRegion(){
        return gdbPersonRegionList.get(ThreadLocalRandom.current().nextInt(gdbPersonRegionList.size()));
    }
    
    public static String getRndmPokemonNickName(){
        return gdbPokemonNickNameList.get(ThreadLocalRandom.current().nextInt(gdbPokemonNickNameList.size()));
    }
    
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