package pk;

import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.List;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class Player {


    static Random rand=new Random();

    public static Logger logger=LogManager.getLogger(Player.class);

    protected static List <Faces> rolls;
    private HashMap<Faces, Integer> num_faces;

    private double wins;
    private int total_score;

    public StrategyList p_strat;

    public Player(){
        this.wins=0;
        this.total_score=0;
    }


    public Player(String strat){
        if (strat.equals("random")){
            p_strat=StrategyList.RANDOM;
        }else if (strat.equals("combo")){
            p_strat=StrategyList.COMBO;
        }
    }


    protected int tallyScore(Card card){

        int curr_score=0;

        num_faces=Dice.dice_frequency(rolls);

        curr_score=((num_faces.get(Faces.DIAMOND)+num_faces.get(Faces.GOLD))*100);

        if (num_faces.get(Faces.SKULL)>=3){
            return 0;
        }

        if (card.getType()==CardTypes.SeaBattle & num_faces.get(Faces.SABER)>=card.getVal()){
            curr_score+=card.getPoints();
        }else if (card.getType()==CardTypes.SeaBattle){
            return 0;
        }else if (card.getType()==CardTypes.MonkeyBusiness){
            int num_monkey=num_faces.get(Faces.MONKEY);
            int num_parrot=num_faces.get(Faces.PARROT);
            num_faces.put(Faces.PARROT, num_monkey+num_parrot);
            num_faces.put(Faces.MONKEY, 0);
        }

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



    private void Reroll(Card card, int num_skulls) {


        boolean cont=true;
        int max=3;

        switch(p_strat){
            case RANDOM:
                cont=rand.nextBoolean();
                max=3;
                break;
            case COMBO:
                cont=true;
                max=2;
                break;
        }

        while (cont & num_skulls<max){
            num_faces=Dice.dice_frequency(rolls);

            if (p_strat==StrategyList.RANDOM){

                Strategy.randomReroll(rolls);
                cont=rand.nextBoolean();

            }else if (p_strat==StrategyList.COMBO){

                if (card.getType()==CardTypes.SeaBattle){
                    cont=Strategy.battleReroll(rolls,num_faces,card);
                }else if (card.getType()==CardTypes.MonkeyBusiness){
                    cont=Strategy.parrmonkReroll(rolls,num_faces,card);
                }else{
                    cont=Strategy.stratReroll(rolls,num_faces);
                }
            }
            
            num_skulls=Collections.frequency(rolls, Faces.SKULL);
        }

        if (num_skulls<3){
            logger.trace("Player decides to reroll: false");
            int added_score=tallyScore(card);
            total_score+=added_score;
        }else{
            logger.trace("3 or more skulls, 0 points, end turn.");
        }

    }



    public void pTurn(Card card){

        //new roll each turn

        logger.trace("Player Draws Card: "+card.getType()+card.getVal());
        rolls =Dice.eightRoll();

        logger.trace("Player Initial Roll: "+rolls.toString());

        int num_skulls=Collections.frequency(rolls, Faces.SKULL);

        //checks if 3 die or more are not already skulls.
        if (num_skulls>=3){
            logger.trace("No points, more than 3 skulls. ");
        }else{
            Reroll(card, num_skulls);
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
