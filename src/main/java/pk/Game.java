package pk;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Game {

    Player p1;
    Player p2;
    CardDeck deck;

    //counts the number of simulations that take place in game.
    int num_sim;
    

    public static Logger logger=LogManager.getLogger(Game.class);  
    

    public Game(String arg1, String arg2){


        p1=new Player(arg1);
        p2=new Player(arg2);

        deck=new CardDeck();

        //shuffle card at beginning of each game.
        deck.shuffle();

        num_sim=0;

        while (p1.getScore()<6000 & p2.getScore()<6000){

            logger.trace(String.format("Round %d:", num_sim+1));


            //runs the simulation for each player's turn.
            Sim();

            num_sim+=1;

            //shuffles the deck if all cards are used.
            if (num_sim%35==0){
                deck.shuffle();
            }

        }

        //Redemption feature for player with lesser points.
        Redemption();

    }

    public void Sim(){


        logger.trace("Player 1 Turn:");
        
        //Player picks a card each turn.
        p1.pTurn(deck.pickCard());
        logger.trace("Player 1 turn ended.");


        logger.trace("Player 2 Turn:");
        p2.pTurn(deck.pickCard());
        logger.trace("Player 2 turn ended.");

        //Updates who won each turn.
        Player.winUpdate(p1,p2);

    }

    public void Redemption(){
        //Player 1 gets redemption if Player 2 has more points and vice versa.
        if (p1.getScore()>=6000){
            logger.trace("Player 2 Redemption:");
            p2.pTurn(deck.pickCard());
        }else{
            logger.trace("Player 1 Redemption:");
            p1.pTurn(deck.pickCard());
        }
    }

    public void finalUpdate(){

        int p1_score=p1.getFinalPoints();
        int p2_score=p2.getFinalPoints();

        int p1_wins=p1.getWins();
        int p2_wins=p2.getWins();

        System.out.printf("Final Scores: \nPlayer 1: %d, Player 2: %d\n", p1_score, p2_score);

        if (p1_score>p2_score){
            System.out.println("Player 1 Wins!");
        }else if (p1_score>p2_score){
            System.out.println("Player 2 Wins!");
        }else{
            System.out.println("Tie Game!");
        }

        System.out.printf("Player 1 win percentage: %.3f%%\n",((double)p1_wins/num_sim)*100);
        System.out.printf("Player 2 win percentage: %.3f%%\n", ((double)p2_wins/num_sim)*100);
        System.out.printf("Tie percentage: %.3f%%\n", ((double)(num_sim-(p1_wins+p2_wins))/num_sim)*100);

    }


}
