package Utility.Validations;

import Pokemons.Logic.Movements.Move;
import Utility.DataBase.MovementsDataBase;
import Utility.Constants.PkMovementTypeEnum;
import Utility.Constants.PkTypeEnum;
import java.util.ArrayList;
import java.util.Collections; // ¡Importante!
import java.util.HashSet; // ¡Importante!
import java.util.Set; // ¡Importante!
import java.util.concurrent.ThreadLocalRandom;

public final class PokemonValidations{
    private PokemonValidations(){}
    
    public static String valPkNickNames(String pkNickName){
        if(pkNickName == null){
            throw new IllegalArgumentException("NickName cannot be empty...");
        }
        if(pkNickName.isBlank()){
            throw new IllegalArgumentException("NickName cannot be empty...");
        }
        if(!pkNickName.matches("[A-Z a-z]+")){
            throw new IllegalArgumentException("NickName can only contain letters...");
        }
        if(pkNickName.length() > 20){
            throw new IllegalArgumentException("Please choose a shorter Name");
        }
        return pkNickName;
    }
    
    /**
     * Lógica de selección de movimientos corregida para prevenir bucles infinitos.
     */
    public static ArrayList<Move> pkMovemntsSelection(PkTypeEnum pkType1, PkTypeEnum pkType2){
        ArrayList<Move> pkMovements = new ArrayList<>(4); // Lista final
        
        // 1. Obtener todos los movimientos válidos (por tipo)
        ArrayList<Move> pkSelectableMovementsByPkType = new ArrayList<>();
        for(Move tryMove: MovementsDataBase.pkMovementsList){
            if(tryMove.getMvPkType() == pkType1 || tryMove.getMvPkType() == pkType2 || tryMove.getMvPkType() == PkTypeEnum.Normal){
                pkSelectableMovementsByPkType.add(tryMove);
            }
        }

        // 2. Separar por categoría
        ArrayList<Move> pkSelectablePhysicalMovements = new ArrayList<>();
        ArrayList<Move> pkSelectableSpecialMovements = new ArrayList<>();
        ArrayList<Move> pkSelectableStatusMovements = new ArrayList<>();
        
        for(Move tryMove: pkSelectableMovementsByPkType){
            switch (tryMove.getMvType()) {
                case Physical:
                    pkSelectablePhysicalMovements.add(tryMove);
                    break;
                case Special:
                    pkSelectableSpecialMovements.add(tryMove);
                    break;
                case Status:
                    pkSelectableStatusMovements.add(tryMove);
                    break;
            }
        }

        // 3. --- LÓGICA DE SELECCIÓN CORREGIDA ---
        // Usamos un Set para garantizar que los 4 movimientos sean únicos
        Set<Move> moveSet = new HashSet<>();

        // 4. Barajamos las listas para aleatoriedad
        Collections.shuffle(pkSelectablePhysicalMovements);
        Collections.shuffle(pkSelectableSpecialMovements);
        Collections.shuffle(pkSelectableStatusMovements);

        // 5. Intentamos añadir movimientos (ej. 2 físicos, 1 especial, 1 de estado)
        
        // Añadimos 2 Físicos (si los hay)
        for (int i = 0; i < 2 && i < pkSelectablePhysicalMovements.size(); i++) {
            moveSet.add(pkSelectablePhysicalMovements.get(i));
        }

        // Añadimos 1 Especial (si lo hay)
        if (pkSelectableSpecialMovements.size() > 0) {
             moveSet.add(pkSelectableSpecialMovements.get(0));
        }
       
        // Añadimos 1 de Estado (si lo hay)
        if (pkSelectableStatusMovements.size() > 0) {
            moveSet.add(pkSelectableStatusMovements.get(0));
        }

        // 6. Si no tenemos 4 movimientos (ej. un Pokémon solo tiene movimientos de estado),
        // rellenamos con lo que quede de la lista general (barajada).
        Collections.shuffle(pkSelectableMovementsByPkType);
        if (moveSet.size() < 4) {
            for (Move move : pkSelectableMovementsByPkType) {
                moveSet.add(move); // add() de un Set ignora duplicados automáticamente
                if (moveSet.size() >= 4) break; // Paramos cuando tengamos 4
            }
        }

        // 7. Convertimos el Set de vuelta a un ArrayList y lo devolvemos
        pkMovements.addAll(moveSet);
        
        // Aseguramos que solo devuelva 4 movimientos como máximo
        if (pkMovements.size() > 4) {
            return new ArrayList<>(pkMovements.subList(0, 4));
        }
        
        return pkMovements;
    }
}