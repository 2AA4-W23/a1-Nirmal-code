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

    //Keeps track of how many instances of each face there are.
    private HashMap<Faces, Integer> num_faces;

    //Keeps track of how many times player won.
    private int wins;

    //Keeps track of total points each game.
    private int total_score;

    //Keeps track of the points each game.
    private int curr_score;

    //Selection between random or combo methods.
    private StrategyList p_strat;


    public Player(){

        this.wins=0;
        this.total_score=0;
        this.curr_score=0;

    }





    public Player(String strat){

        //Each player either uses random or combo method.

        if (strat.equals("random")){

            p_strat=StrategyList.RANDOM;

        }else if (strat.equals("combo")){

            p_strat=StrategyList.COMBO;
        }


    }





    protected int tallyScore(Card card){


        curr_score=0;

        num_faces=Dice.dice_frequency(rolls);

        //Each gold and diamond card counts as 100 points.
        curr_score=((num_faces.get(Faces.DIAMOND)+num_faces.get(Faces.GOLD))*100);

        //SeaBattle card changes how points are calculated. This checks if they got the number of sabers required, else it subtracts the value of the saber card from total points.
        if (card.getType()==CardTypes.SeaBattle){


            if (num_faces.get(Faces.SABER)>=card.getVal() & num_faces.get(Faces.SKULL)<3){

                curr_score+=card.getPoints();

            }else if (num_faces.get(Faces.SABER)>=card.getVal()){

                curr_score=0;
                return curr_score;

            }else{
                
                logger.trace("Deducted points: "+card.getPoints());
                curr_score=-1*card.getPoints();
                return (curr_score);

            }


        }else if (card.getType()==CardTypes.MonkeyBusiness){

            //If card is MonkeyBusiness, then this combines the parrots and monkeys into one die (since they will be calculated together anyways.)

            int num_monkey=num_faces.get(Faces.MONKEY);
            int num_parrot=num_faces.get(Faces.PARROT);
            num_faces.put(Faces.PARROT, num_monkey+num_parrot);
            num_faces.put(Faces.MONKEY, 0);


        }



        //Goes through all values and checks how many points to give each die.
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

        //cont will be used to check a condition that is true to each card type.
        //Boolean will be returned from each card strategy and will update this according to individual strategies.
        boolean cont=true;

        //max is how many skulls the program should break reroll at. Different for various strategies.
        int max=3;

        //Used for the sorceress card to see if they rerolled a skull.
        boolean skull_reroll=false;


        switch(p_strat){
            case RANDOM:
                cont=rand.nextBoolean();
                max=3;
                break;
            case COMBO:
                if (card.getType()==CardTypes.Sorceress){
                    //Sorceress has initial max of 4 since player an reroll one skull, so it initially doesn't matter if they got 3.
                    max=4;
                    cont=true;
                }else{
                    cont=true;
                    //Every other combo has max 2 since we don't want to risk 3 skulls.
                    max=2;
                }
                break;
        }



        while (cont & num_skulls<max){
            

            num_faces=Dice.dice_frequency(rolls);

            //outer if statement checks whether the strategy is random or combo.
            if (p_strat==StrategyList.RANDOM){

                Strategy.randomReroll(rolls);
                cont=rand.nextBoolean();

            }else if (p_strat==StrategyList.COMBO){

                //Nested if statement checks which card type was drawn, and redirects code to strategy method accordingly.

                if (card.getType()==CardTypes.SeaBattle){

                    cont=Strategy.battleReroll(rolls,num_faces,card);

                }else if (card.getType()==CardTypes.MonkeyBusiness){

                    cont=Strategy.parrmonkReroll(rolls,num_faces,card);

                }else if (card.getType()==CardTypes.Sorceress){

                    //max is now 2 since if they had 3 skulls, they would've rerolled by now.
                    max=2;

                    if (skull_reroll | num_skulls==0){
                        //If they rerolled, we want to proceed with stratReroll as usual.
                        cont=Strategy.stratReroll(rolls, num_faces, CardTypes.Empty);
                    }else{
                        cont=Strategy.stratReroll(rolls,num_faces,card.getType());
                        //If there is a skull, then we know program will automatically reroll it.
                        skull_reroll=true;
                    }

                }else{

                    cont=Strategy.stratReroll(rolls, num_faces, card.getType());

                }
            }
            //updates the number of skulls.
            num_skulls=Collections.frequency(rolls, Faces.SKULL);
        }


        if (num_skulls<3){

            logger.trace("Player decides to reroll: false");
            //If in the end, they have less than 3 skulls, then we want to update the total score.
            int added_score=tallyScore(card);
            total_score+=added_score;

        }else{

            if (card.getType()==CardTypes.SeaBattle){
                //If they got more than 3 skulls, and sea battle card, we want to update the total point (either subtract by card point, or add no points)
                int sub_score=tallyScore(card);
                total_score+=sub_score;
            }else{
                logger.trace("3 or more skulls, 0 points, end turn.");
            }

        }

    }





    public void pTurn(Card card){

        logger.trace("Player Draws Card: "+card.getType()+" with value "+card.getVal());

        //new roll each turn
        rolls =Dice.eightRoll();

        logger.trace("Player Initial Roll: "+rolls.toString());

        //Calculates the initial number of skulls.
        int num_skulls=Collections.frequency(rolls, Faces.SKULL);

        //Reroll will check whether to reroll or not, and will perform it accordingly.
        Reroll(card, num_skulls);

    }




    public int getScore(){
        return total_score;
    }




    public static void winUpdate(Player p1, Player p2){

        logger.trace(String.format("Player 1: %d", p1.total_score));
        logger.trace(String.format("Player 2: %d", p2.total_score));

        //Updates the number of wins depending on current score for each round (assumption).

        if (p1.curr_score > p2.curr_score) {
            p1.wins += 1;
        } else if (p1.curr_score < p2.curr_score) {
            p2.wins += 1;
        }

    }



    public int getCurrScore(){
        return this.curr_score;
    }



    public int getFinalPoints(){
        return this.total_score;
    }



    public int getWins(){
        return this.wins;
    }





}
