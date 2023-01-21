package pk;

import java.util.Collections;


public class Strategy extends Player {

    void stratReroll(){
        while (num_skulls<2){

            dice_frequency();

            num_faces.remove(Faces.GOLD);
            num_faces.remove(Faces.DIAMOND);



            int num_ones=Collections.frequency(num_faces.values(),1);
            int num_twos=Collections.frequency(num_faces.values(),2);

            if (num_ones>=2 | num_twos>0){

                for (int i=0; i<rolls.size(); i++){

                    if (rolls.get(i)!=Faces.DIAMOND & rolls.get(i)!=Faces.GOLD){

                        if (num_faces.get(rolls.get(i))==1 | num_faces.get(rolls.get(i))==2){
                            rolls.set(i, myDice.roll());
                        }

                    }

                }

                logger.trace("reroll:"+rolls.toString());
                num_skulls+=Collections.frequency(rolls, Faces.SKULL);
                rolls.removeAll(Collections.singleton(Faces.SKULL));

            }else{

                logger.trace("Most dice have pairs. Don't want to risk.");
                return;

            }
        }
        if (num_skulls>=3){
            logger.trace("rolled 3 or more dice, no points");
        }else{
            logger.trace("2 skulls, don't want to risk it");
        }

    }
    


    void randomReroll(){

        //selects which two die will be rerolled from die left (at minimum).
        int fixed_one = rand.nextInt(0, rolls.size());
        int fixed_two;
        do{
            fixed_two = rand.nextInt(0, rolls.size());
        }while (fixed_two!=fixed_one);
        
        boolean cont=rand.nextBoolean();

        logger.trace("Player decides to reroll: "+cont);
        
        while (cont) {


            for (int i = 0; i < rolls.size(); i++) {

                if (i == fixed_one) {
                    rolls.set(i, myDice.roll());
                } else if (i == fixed_two) {
                    rolls.set(i, myDice.roll());
                } else {
                    //If dice isn't fixed to be rerolled, this randomizes whether the dice should be rerolled.
                    int reroll = rand.nextInt(0, 2);

                    if (reroll == 1) {
                        rolls.set(i, myDice.roll());
                    }
                }
            }

            logger.trace("reroll:"+rolls.toString());

            num_skulls+=Collections.frequency(rolls, Faces.SKULL);
            rolls.removeAll(Collections.singleton(Faces.SKULL));

            if (num_skulls>=3){
                logger.trace("No points, more than 3 skulls. ");
                curr_score=0;
                return;
            }else{
                cont=rand.nextBoolean();
                logger.trace("Player decides to reroll? "+cont);
            }

        }

    }
}
