package pk;

import java.util.ArrayList;
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


    int[] num_dice=new int[5];


    Random rand = new Random();





    

    private static Logger logger=LogManager.getLogger(Player.class);




    public Player(){
        wins=0;
        score=0;
    }

    void dice_frequency(){
        int num_gold= Collections.frequency(rolls, Faces.GOLD);
        int num_diamond= Collections.frequency(rolls, Faces.DIAMOND);
        int num_monkey= Collections.frequency(rolls, Faces.MONKEY);
        int num_parrot= Collections.frequency(rolls, Faces.PARROT);
        int num_saber= Collections.frequency(rolls, Faces.SABER);

        num_dice[0]=num_gold;
        num_dice[1]=num_diamond;
        num_dice[2]=num_monkey;
        num_dice[3]=num_parrot;
        num_dice[4]=num_saber;



    }

    int tally_score(){

        dice_frequency();
        int curr_score=0;

        curr_score=((num_dice[0]+num_dice[1])*100);


        for (int i=0; i<num_dice.length; i++){
            switch(num_dice[i]){
                case 3:
                    curr_score+=100;
                    break;
                case 4:
                    curr_score+=200;
                    break;
                case 5:
                    curr_score+=500;
                    break;
                case 6:
                    curr_score+=1000;
                    break;
                case 7:
                    curr_score+=2000;
                    break;
                case 8:
                    curr_score+=4000;
                    break;
            }
        }

        logger.trace("result: "+rolls.toString());
        logger.trace(String.format("Added points:%d", curr_score));

        return curr_score;
    }

    void strat_rerolls(){
        dice_frequency();



    }

    int random_rerolls() {


        //selects which two die will be rerolled from die left (at minimum).
        int fixed_one = rand.nextInt(0, rolls.size());
        int fixed_two;
        do{
            fixed_two = rand.nextInt(0, rolls.size());
        }while (fixed_two!=fixed_one);
        
        boolean cont=rand.nextBoolean();

        logger.trace("Player decides to reroll:"+cont);
        
        //condition: true while there are less than 3 dies removed.
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
            rolls.removeAll(Collections.singleton(Faces.SKULL));

            if (8-rolls.size()>=3){
                logger.trace("No points, more than 3 skulls. ");
                return 0;
            }

            cont=rand.nextBoolean();
            logger.trace("Player decides to reroll? "+cont);

        }
        return tally_score();
    }

    public int turn(){

        //new roll each turn
        rolls =myDice.eightRoll();

        logger.trace("Player rolls"+rolls.toString());

        rolls.removeAll(Collections.singleton(Faces.SKULL));

        int curr_score=0;

        //checks if 3 die or more are not already skulls.
        if (8-rolls.size()>=3){
            logger.trace("No points, more than 3 skulls. ");
            return 0;
        }else{
            //if less than 3 are skulls, this rerolls.
            curr_score=random_rerolls();
            return curr_score;
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
