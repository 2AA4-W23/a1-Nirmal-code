package pk;

import java.util.Collections;
import java.util.Random;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class Player {


    Dice myDice=new Dice();

    List <Faces> rolls;
    public double wins;
    public int score;

    private static Logger logger=LogManager.getLogger(Player.class);




    public Player(){
        wins=0;
        score=0;
    }

    int tally_score(){
        int num_gold= Collections.frequency(rolls, Faces.GOLD);
        int num_diamond= Collections.frequency(rolls, Faces.DIAMOND);
        int num_monkey= Collections.frequency(rolls, Faces.MONKEY);
        int num_parrot= Collections.frequency(rolls, Faces.PARROT);
        int num_saber= Collections.frequency(rolls, Faces.SABER);

        score=((num_gold+num_diamond)*100)+(num_gold/3)*100+(num_diamond/3)*100+(num_monkey/3)*100+(num_parrot/3)*100+(num_saber/3)*100;

        logger.trace("result: "+rolls.toString());
        logger.trace(String.format("Added points:%d", score));

        return score;
    }

    void rerolls() {
        Random rand = new Random();

        //selects which two die will be rerolled from die left (at minimum).
        int fixed_one = rand.nextInt(0, rolls.size());
        int fixed_two;
        do{
            fixed_two = rand.nextInt(0, rolls.size());
        }while (fixed_two!=fixed_one);

        //condition: true while there are less than 3 dies removed.
        while (8 - rolls.size() < 3) {


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
            rolls.removeAll(Collections.singleton(Faces.SKULL));
        }
    }

    public int turn(){

        //new roll each turn
        rolls =myDice.eightRoll();

        logger.trace("Player rolls"+rolls.toString());

        rolls.removeAll(Collections.singleton(Faces.SKULL));

        //checks if 3 die or more are not already skulls.
        if (8-rolls.size()>=3){
            tally_score();
            return score;
        }else{
            //if less than 3 are skulls, this rerolls.
            rerolls();
            tally_score();
            return score;
        }

    }


    public static void win_update(Player p1, Player p2){

        if (p1.score >= p2.score) {
            p1.wins += 1;
        } else {
            p2.wins += 1;
        }
    }

}
