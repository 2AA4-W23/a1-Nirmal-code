package pk;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class Strategy extends Player {



    protected static boolean stratReroll(List<Faces> rolls, HashMap<Faces, Integer> num_faces, CardTypes card){

        num_faces.remove(Faces.GOLD);
        num_faces.remove(Faces.DIAMOND);

        Boolean battle=false;
        Boolean sorc=false;

        if (card==CardTypes.SeaBattle){
            num_faces.remove(Faces.SABER);
            num_faces.remove(Faces.SKULL);
            battle=true;
        }else if (card==CardTypes.Sorceress){
            sorc=true;
        }else{
            num_faces.remove(Faces.SKULL);
        }


        int num_ones=Collections.frequency(num_faces.values(),1);
        int num_twos=Collections.frequency(num_faces.values(),2);

        if (num_ones>=2 | num_twos>0){
            logger.trace("Player decides to reroll:true");

            for (int i=0; i<rolls.size(); i++){

                if (rolls.get(i)==Faces.SKULL){
                    if (sorc){
                        logger.trace("Player decides to use sorceress card");
                        rolls.set(i, Dice.roll());
                        sorc=false;
                    }

                }else if (rolls.get(i)!=Faces.DIAMOND & rolls.get(i)!=Faces.GOLD & (battle?rolls.get(i)!=Faces.SABER:true)){
                    if (num_faces.get(rolls.get(i))==1 | num_faces.get(rolls.get(i))==2){
                        rolls.set(i, Dice.roll());
                    }
                }

            }
            logger.trace("reroll:"+rolls.toString());

            return true;

        }else{
            {return false;}

        }

    }






    protected static boolean battleReroll(List<Faces> rolls, HashMap<Faces, Integer> num_faces, Card card){


        int num_saber=num_faces.get(Faces.SABER);
        num_faces.remove(Faces.SKULL);

        boolean got_req=num_saber>=card.getVal();

        if (got_req){
            return stratReroll(rolls, num_faces, card.getType());
        }else{
            logger.trace("Player decides to reroll:true");
            for (int i=0; i<rolls.size(); i++){
                if (rolls.get(i)!=Faces.SABER & rolls.get(i)!=Faces.SKULL){
                    rolls.set(i, Dice.roll());
                }
            }
            logger.trace("reroll:"+rolls.toString());
            return true;
        }

    }





    protected static boolean parrmonkReroll(List<Faces> rolls, HashMap<Faces, Integer> num_faces, Card card){

        int num_saber=num_faces.get(Faces.SABER);

        if (num_saber>=2){
            logger.trace("Player decides to reroll:true");

            for (int i=0; i<rolls.size(); i++){

                if (rolls.get(i)==Faces.SABER){
                    rolls.set(i, Dice.roll());
                }

            }
            logger.trace("reroll:"+rolls.toString());

            return true;

        }else{
            return false;

        }

    }

    


    

    protected static void randomReroll(List<Faces> rolls){


        //selects which two die will be rerolled. Cannot be the same die, and cannot be skulls.

        logger.trace("Player decides to reroll: true");

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

