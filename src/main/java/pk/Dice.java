package pk;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dice {

    public Faces roll() {
        int howManyFaces = Faces.values().length;
        Random bag = new Random();
        return Faces.values()[bag.nextInt(howManyFaces)];
    }

    public List<Faces> eightRoll(){
        List<Faces> turn_rolls=new ArrayList<>();

        for (int i=0; i<8; i++){
            turn_rolls.add(roll());
        }

        return turn_rolls;

    }
    
}
