package Utility.Validations;

import Pokemons.Logic.Movements.Move;
import Utility.DataBase.MovementsDataBase;
import Utility.Constants.PkMovementTypeEnum;
import Utility.Constants.PkTypeEnum;
import java.util.ArrayList;
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
    
    public static ArrayList<Move> pkMovemntsSelection(PkTypeEnum pkType1, PkTypeEnum pkType2){
        ArrayList<Move> pkMovements = new ArrayList<>();
        ArrayList<Move> pkSelectableMovementsByPkType = new ArrayList<>();
        ArrayList<Move> pkSelectablePhysicalMovements = new ArrayList<>();
        ArrayList<Move> pkSelectableSpecialMovements = new ArrayList<>();
        ArrayList<Move> pkSelectableStatusMovements = new ArrayList<>();
        for(Move tryMove: MovementsDataBase.pkMovementsList){
            if(tryMove.getMvPkType() == pkType1 || tryMove.getMvPkType() == pkType2 || tryMove.getMvPkType() == PkTypeEnum.Normal){
                pkSelectableMovementsByPkType.add(tryMove);
            }
        }
        for(Move tryMove: pkSelectableMovementsByPkType){
            if(tryMove.getMvType() == PkMovementTypeEnum.Physical){
                pkSelectablePhysicalMovements.add(tryMove);
            }
            if(tryMove.getMvType() == PkMovementTypeEnum.Special){
                pkSelectableSpecialMovements.add(tryMove);
            }
            if(tryMove.getMvType() == PkMovementTypeEnum.Status){
                pkSelectableStatusMovements.add(tryMove);
            }
        }
        if(ThreadLocalRandom.current().nextInt(2) == 0){
            do{
                pkMovements.add(pkSelectablePhysicalMovements.get(ThreadLocalRandom.current().nextInt(pkSelectablePhysicalMovements.size())));
                pkMovements.add(pkSelectablePhysicalMovements.get(ThreadLocalRandom.current().nextInt(pkSelectablePhysicalMovements.size())));
                if(pkMovements.get(0) == pkMovements.get(1)){
                    pkMovements.set(0, null);
                    pkMovements.set(1, null);
                }
            }while(pkMovements.get(0) == pkMovements.get(1));
            pkMovements.add(pkSelectableSpecialMovements.get(ThreadLocalRandom.current().nextInt(pkSelectableSpecialMovements.size())));
            pkMovements.add(pkSelectableStatusMovements.get(ThreadLocalRandom.current().nextInt(pkSelectableStatusMovements.size())));
            return pkMovements;
        }
        do{
            pkMovements.add(pkSelectableSpecialMovements.get(ThreadLocalRandom.current().nextInt(pkSelectableSpecialMovements.size())));
            pkMovements.add(pkSelectableSpecialMovements.get(ThreadLocalRandom.current().nextInt(pkSelectableSpecialMovements.size())));
            if(pkMovements.get(0) == pkMovements.get(1)){
                    pkMovements.set(0, null);
                    pkMovements.set(1, null);
                }
        }while(pkMovements.get(0) == pkMovements.get(1));
        pkMovements.add(pkSelectablePhysicalMovements.get(ThreadLocalRandom.current().nextInt(pkSelectablePhysicalMovements.size())));
        pkMovements.add(pkSelectableStatusMovements.get(ThreadLocalRandom.current().nextInt(pkSelectableStatusMovements.size())));
        return pkMovements;
    }
}