package pk;

import java.util.Collections;
import java.util.Random;

import java.util.List;


public class Player {

    List <Faces> final_rolls;

    public int tally_score(List<Faces> final_rolls){
        int num_gold= Collections.frequency(final_rolls, Faces.GOLD);
        int num_diamond= Collections.frequency(final_rolls, Faces.DIAMOND);
        int score=(num_gold+num_diamond)*100;
        return score;
    }

    public int turn(List<Faces> starter_roll){
        int num_skulls=0;
        Dice myDice=new Dice();
        starter_roll.removeAll(Collections.singleton(Faces.SKULL));
        if (8-starter_roll.size()>=3){
            System.out.println(starter_roll.toString());
            return tally_score(starter_roll);
        }else{
            Random rand=new Random();
            while (8-starter_roll.size()<3){
                for (int i=0; i<starter_roll.size();i++){

                }
                starter_roll.removeAll(Collections.singleton(Faces.SKULL));
            }
            System.out.println(starter_roll.toString());
            return tally_score(starter_roll);
        }

    }

}
