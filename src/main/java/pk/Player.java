package pk;

import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.List;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class Player {


    Random rand=new Random();

    public static Logger logger=LogManager.getLogger(Player.class);

    protected static List <Faces> rolls;
    protected static HashMap<Faces, Integer> num_faces=new HashMap<Faces, Integer>();

    private double wins;
    private int total_score;


    public Player(){
        this.wins=0;
        this.total_score=0;
    }


    protected int tallyScore(){

        int curr_score=0;

        num_faces=Dice.dice_frequency(rolls);

        if (num_faces.get(Faces.SKULL)>=3){
            return 0;
        }

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

        return curr_score;
    
    }



    private void Reroll(String strat, int num_skulls) {

        Strategy p_strategy=new Strategy();

        if (strat.equals("random")){

            boolean cont=rand.nextBoolean();
            logger.trace("Player decides to reroll: "+cont);

            while (cont) {
                p_strategy.randomReroll(rolls);
                num_skulls=Collections.frequency(rolls, Faces.SKULL);
                
                if (num_skulls<3){
                    cont=rand.nextBoolean();
                    logger.trace("Player decides to reroll: "+cont);
                }else{
                    logger.trace("More than 3 Skulls, no point!");
                    break;
                }
                
            }
            if (num_skulls<3){
                total_score+=tallyScore();
            }
            
        }else if (strat.equals("combo")){

            boolean cont=true;

            while (num_skulls<2 & cont){
                num_faces=Dice.dice_frequency(rolls);
                cont=p_strategy.stratReroll(rolls,num_faces);
                num_skulls=Collections.frequency(rolls, Faces.SKULL);
            }

            if (num_skulls>=3){
                logger.trace("rolled 3 or more dice, no points");
            }else{
                logger.trace("2 skulls, don't want to risk it");
                total_score+=tallyScore();
            }
        }
    }



    public void pTurn(String strat){

        //new roll each turn
        rolls =Dice.eightRoll();

        logger.trace("Player Initial Roll: "+rolls.toString());

        int num_skulls=Collections.frequency(rolls, Faces.SKULL);

        //checks if 3 die or more are not already skulls.
        if (num_skulls>=3){
            logger.trace("No points, more than 3 skulls. ");
        }else{
            Reroll(strat, num_skulls);
        }
    }

    public int getScore(){
        return total_score;
    }


    public static void winUpdate(Player p1, Player p2){

        logger.trace(String.format("Player 1: %d", p1.total_score));
        logger.trace(String.format("Player 2: %d", p2.total_score));

        if (p1.total_score > p2.total_score) {
            p1.wins += 1;
        } else if (p1.total_score < p2.total_score) {
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
