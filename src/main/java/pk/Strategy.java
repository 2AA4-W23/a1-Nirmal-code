package pk;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class Strategy extends Player {



    protected boolean stratReroll(List<Faces> rolls, HashMap<Faces, Integer> num_faces){

        num_faces.remove(Faces.GOLD);
        num_faces.remove(Faces.DIAMOND);
        num_faces.remove(Faces.SKULL);


        int num_ones=Collections.frequency(num_faces.values(),1);
        int num_twos=Collections.frequency(num_faces.values(),2);

        if (num_ones>=2 | num_twos>0){

            for (int i=0; i<rolls.size(); i++){

                if (rolls.get(i)!=Faces.DIAMOND & rolls.get(i)!=Faces.GOLD & rolls.get(i)!=Faces.SKULL){

                    if (num_faces.get(rolls.get(i))==1 | num_faces.get(rolls.get(i))==2){
                        rolls.set(i, Dice.roll());
                    }

                }

            }

            logger.trace("reroll:"+rolls.toString());

        }else{

            logger.trace("Most dice have pairs. Don't want to risk.");
            {return false;}

        }

        return true;

    }

    


    protected void randomReroll(List<Faces> rolls){


        //selects which two die will be rerolled. Cannot be the same die, and cannot be skulls.

        int fixed_one=rand.nextInt(0, rolls.size());
        int fixed_two=rand.nextInt(0, rolls.size());

        while (fixed_one==fixed_two | rolls.get(fixed_one)==Faces.SKULL | rolls.get(fixed_two)==Faces.SKULL){
            if (rolls.get(fixed_one)==Faces.SKULL){
                fixed_one=rand.nextInt(0, rolls.size());
            }else if (rolls.get(fixed_two)==Faces.SKULL){
                fixed_two=rand.nextInt(0, rolls.size());
            }else{
                fixed_one=rand.nextInt(0,rolls.size());
            }
        }



        for (int i = 0; i < rolls.size(); i++) {

            if (i == fixed_one) {
                rolls.set(i, Dice.roll());
            } else if (i == fixed_two) {
                rolls.set(i, Dice.roll());
            } else {
                //If dice isn't fixed to be rerolled, this randomizes whether the dice should be rerolled.
                int reroll = rand.nextInt(0, 2);

                if (reroll == 1 & rolls.get(i)!=Faces.SKULL) {
                    rolls.set(i, Dice.roll());
                }
            }
        }

        logger.trace("reroll:"+rolls.toString());
    }
}
