
package Utility;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class PokeWriting {
    
    public void Writing(){
        try{
            Path path = Files.writeString(Path.of("C:/PokeLecture.txt"), "Hola", StandardOpenOption.CREATE_NEW);
            System.out.println(path);
            String inf = Files.readString(path);
            System.out.println(inf);
            
        }catch(Exception e){
            e.printStackTrace();
        } 
    }
}
