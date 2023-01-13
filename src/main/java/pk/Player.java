package pk;

import java.util.Collections;
import java.util.Random;

import java.util.List;


public class Player {

    List <Faces> final_rolls;

    public int tally_score(List<Faces> final_rolls){
        int num_gold= Collections.frequency(final_rolls, Faces.GOLD);
        int num_diamond= Collections.frequency(final_rolls, Faces.DIAMOND);
        int num_monkey= Collections.frequency(final_rolls, Faces.MONKEY);
        int num_parrot= Collections.frequency(final_rolls, Faces.PARROT);
        int num_saber= Collections.frequency(final_rolls, Faces.SABER);

        int score=((num_gold+num_diamond)*100)+(num_gold/3)*100+(num_diamond/3)*100+(num_monkey/3)*100+(num_parrot/3)*100+(num_saber/3)*100;

        return score;
    }

    public int turn(List<Faces> starter_roll){
        int num_skulls=0;
        Dice myDice=new Dice();
        starter_roll.removeAll(Collections.singleton(Faces.SKULL));
        if (8-starter_roll.size()>=3){
            return tally_score(starter_roll);
        }else{
            Random rand=new Random();
            while (8-starter_roll.size()<3){
                for (int i=0; i<starter_roll.size();i++){
                    int reroll=rand.nextInt(0,2);
                    if (reroll==1){
                        starter_roll.set(i,myDice.roll());
                    }
                }
                starter_roll.removeAll(Collections.singleton(Faces.SKULL));
            }
            return tally_score(starter_roll);
        }

    }

}
