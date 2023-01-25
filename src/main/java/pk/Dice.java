package pk;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Dice{
    

    static protected Faces roll() {
        int howManyFaces = Faces.values().length;
        Random bag = new Random();
        return Faces.values()[bag.nextInt(howManyFaces)];
    }

    protected static HashMap<Faces, Integer> dice_frequency(List<Faces> rolls){
        HashMap<Faces, Integer> num_faces=new HashMap<Faces, Integer>();

        int num_gold= Collections.frequency(rolls, Faces.GOLD);
        int num_diamond= Collections.frequency(rolls, Faces.DIAMOND);
        int num_monkey= Collections.frequency(rolls, Faces.MONKEY);
        int num_parrot= Collections.frequency(rolls, Faces.PARROT);
        int num_saber= Collections.frequency(rolls, Faces.SABER);
        int num_skulls= Collections.frequency(rolls, Faces.SKULL);


        num_faces.put(Faces.GOLD, num_gold);
        num_faces.put(Faces.DIAMOND, num_diamond);
        num_faces.put(Faces.MONKEY, num_monkey);
        num_faces.put(Faces.PARROT, num_parrot);
        num_faces.put(Faces.SABER, num_saber);
        num_faces.put(Faces.SKULL, num_skulls);


        return num_faces;

    }
    

    static protected List<Faces> eightRoll(){
        List<Faces> turn_rolls=new ArrayList<>();

        for (int i=0; i<8; i++){
            turn_rolls.add(roll());
        }

        return turn_rolls;

    }
    
    
}
