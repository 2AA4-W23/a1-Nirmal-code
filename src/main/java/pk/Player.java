package pk;

import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.List;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class Player {


    Dice myDice=new Dice();
    Random rand=new Random();

    protected static Logger logger=LogManager.getLogger(Player.class);

    protected static List <Faces> rolls;
    protected static HashMap<Faces, Integer> num_faces=new HashMap<Faces, Integer>();

    private double wins;
    private int total_score;
    protected static int curr_score;
    protected static int num_skulls;



    public Player(){
        wins=0;
        total_score=0;
        num_skulls=0;
        curr_score=0;
    }


    protected void dice_frequency(){



        int num_gold= Collections.frequency(rolls, Faces.GOLD);
        int num_diamond= Collections.frequency(rolls, Faces.DIAMOND);
        int num_monkey= Collections.frequency(rolls, Faces.MONKEY);
        int num_parrot= Collections.frequency(rolls, Faces.PARROT);
        int num_saber= Collections.frequency(rolls, Faces.SABER);

        num_faces.put(Faces.GOLD, num_gold);
        num_faces.put(Faces.DIAMOND, num_diamond);
        num_faces.put(Faces.MONKEY, num_monkey);
        num_faces.put(Faces.PARROT, num_parrot);
        num_faces.put(Faces.SABER, num_saber);

    }


    protected void tallyScore(){

        dice_frequency();

        curr_score=((num_faces.get(Faces.DIAMOND)+num_faces.get(Faces.GOLD))*100);

        for (int i:num_faces.values()){
            switch(i){
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
    
    }





    private void Reroll(String strat) {
        Strategy rando=new Strategy();

        if (strat.equals("random")){
            rando.randomReroll();
            tallyScore();
        }else{
            rando.stratReroll();
            tallyScore();
        }
    }



    public void pTurn(String strat){

        //new roll each turn
        rolls =myDice.eightRoll();

        logger.trace("Player Initial Roll: "+rolls.toString());

        num_skulls+=Collections.frequency(rolls, Faces.SKULL);


        rolls.removeAll(Collections.singleton(Faces.SKULL));


        //checks if 3 die or more are not already skulls.
        if (num_skulls>=3){
            logger.trace("No points, more than 3 skulls. ");
        }else{
            Reroll(strat);
            total_score+=curr_score;
            curr_score=0;
        }

        num_skulls=0;

    }

    public int getScore(){
        return total_score;
    }




    public static void winUpdate(Player p1, Player p2){

        logger.trace(String.format("Player 1: %d", p1.total_score));
        logger.trace(String.format("Player 2: %d", p2.total_score));

        if (p1.total_score >= p2.total_score) {
            p1.wins += 1;
        } else {
            p2.wins += 1;
        }

    }



    public static void finalUpdate(Player p1, Player p2, int num_sim){

        System.out.printf("Final Scores: \nPlayer 1: %d, Player 2: %d\n", p1.total_score, p2.total_score);

        if (p1.total_score>p2.total_score){
            System.out.println("Player 1 Wins!");
        }else if (p1.total_score<p2.total_score){
            System.out.println("Player 2 Wins!");
        }else{
            System.out.println("Tie Game!");
        }

        System.out.printf("Player 1 win percentage: %.3f%%\n",(p1.wins/num_sim)*100);
        System.out.printf("Player 2 win percentage: %.3f%%\n", (p2.wins/num_sim)*100);
    }

}
