package pk;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class Strategy extends Player {



    //Strategic reroll method. It is used as the basis for any strategic reroll by program.
    protected static boolean stratReroll(List<Faces> rolls, HashMap<Faces, Integer> num_faces, CardTypes card){

        //Discounts the gold and diamond, since we don't want to reroll those.
        num_faces.remove(Faces.GOLD);
        num_faces.remove(Faces.DIAMOND);

        //Used to check if SeaBattle card was pulled (SeaBattle uses stratReroll)
        Boolean battle=false;

        //Used to check if Sorceress card was pulled (Sorceress uses stratReroll). 
        Boolean sorc=false;

        //Monkey Card isn't here since it doesn't use stratReroll.


        if (card==CardTypes.SeaBattle){
            //If card is sea battle, we want to discount the skull and sabers since we don't want to reroll them.
            num_faces.remove(Faces.SABER);
            num_faces.remove(Faces.SKULL);
            battle=true;
        }else if (card==CardTypes.Sorceress){
            sorc=true;
        }else{
            //If card is anything besides Sorceress, we want to discount skulls too.
            num_faces.remove(Faces.SKULL);
        }


        //Calculates how many 1 and 2 pairs there are.
        //Strategy is to reroll those since they give no points.
        int num_ones=Collections.frequency(num_faces.values(),1);
        int num_twos=Collections.frequency(num_faces.values(),2);

        //Must reroll a minimum of 2 so this checks if the num_ones is >=2.
        if (num_ones>=2 | num_twos>0){
            logger.trace("Player decides to reroll:true");

            //Goes through each dice in the roll.
            for (int i=0; i<rolls.size(); i++){

                //If dice is a skull, then we only want to reroll if the card is sorceress.
                //Reroll in Player ensures that this only happens once though.
                if (rolls.get(i)==Faces.SKULL){
                    if (sorc){
                        logger.trace("Player decides to use sorceress card");
                        rolls.set(i, Dice.roll());
                        sorc=false;
                    }

                //Rerolls any other die that isn't gold, diamond or saber (only if it is SeaBattle). 
                //Also ensures that the die only has pair of 1 or 2.
                }else if (rolls.get(i)!=Faces.DIAMOND & rolls.get(i)!=Faces.GOLD & (battle?rolls.get(i)!=Faces.SABER:true)){
                    if (num_faces.get(rolls.get(i))==1 | num_faces.get(rolls.get(i))==2){
                        rolls.set(i, Dice.roll());
                    }
                }

            }
            logger.trace("reroll:"+rolls.toString());

            //Returns true to cont (strategy wants to reroll again.)
            return true;

        }else{
            //Returns false to cont (strategy doens't want to reroll again)
            return false;

        }

    }



    protected static boolean battleReroll(List<Faces> rolls, HashMap<Faces, Integer> num_faces, Card card){


        int num_saber=num_faces.get(Faces.SABER);
        num_faces.remove(Faces.SKULL);

        //Determines whether the SeaBattle card's condition is met.
        boolean got_req=num_saber>=card.getVal();

        //If it is met, we want to run stratReroll as normal.
        if (got_req){
            //Returns whatever stratReroll() says to cont.
            return stratReroll(rolls, num_faces, card.getType());
        }else{
            //If it is not met, we want to reroll only the die that aren't skull or saber.
            //Indirectly ensures that 2 dies are rerolled at minimum since max saber is 4, and max skull is 2. So if program entered this section, its only if two others aren't skull or saber.
            logger.trace("Player decides to reroll:true");
            for (int i=0; i<rolls.size(); i++){
                if (rolls.get(i)!=Faces.SABER & rolls.get(i)!=Faces.SKULL){
                    rolls.set(i, Dice.roll());
                }
            }
            logger.trace("reroll:"+rolls.toString());
            //Returns true to cont (we want to reoll again since condition isn't met)
            return true;
        }

    }





    protected static boolean parrmonkReroll(List<Faces> rolls, HashMap<Faces, Integer> num_faces, Card card){

        //Strategy is to just reroll all sabers. Since gold, diamond, parrot and monkey all provide value. Also, cannot reroll skulls.
        int num_saber=num_faces.get(Faces.SABER);

        //Ensures that we can reroll minium of 2 die.
        if (num_saber>=2){
            logger.trace("Player decides to reroll:true");

            for (int i=0; i<rolls.size(); i++){

                if (rolls.get(i)==Faces.SABER){
                    rolls.set(i, Dice.roll());
                }

            }
            logger.trace("reroll:"+rolls.toString());

            //Returns true to cont (want to reroll die again)
            return true;

        }else{
            //Return false to cont (we don't want to reroll die again.)
            return false;

        }

    }

    


    

    protected static void randomReroll(List<Faces> rolls){


        logger.trace("Player decides to reroll: true");

        //Selects which two dies will be rerolled. Cannot be the same die, and cannot be skulls.

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

            //If die is one of the select 2, it rerolls that die.
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

