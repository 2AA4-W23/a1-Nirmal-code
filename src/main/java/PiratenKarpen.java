
import pk.Player;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class PiratenKarpen {

    private static Logger logger=LogManager.getLogger(PiratenKarpen.class);



    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");
        System.out.println("I'm rolling a dice");

        Player p1=new Player();
        Player p2=new Player();



        for (int i=0; i<42; i++) {

            logger.trace(String.format("Round %d:", i+1));


            logger.trace("Player 1 Turn:");
            p1.score+= p1.turn();
            logger.trace("Player 1 turn ended.");


            logger.trace("Player 2 Turn:");
            p2.score+= p2.turn();
            logger.trace("Player 2 turn ended.");



            logger.trace(String.format("Player 1: %d", p1.score));
            logger.trace(String.format("Player 2: %d", p2.score));

            Player.win_update(p1,p2);

        }

        logger.trace(String.format("Final Wins: Player 1: %.0f, Player 2: %.0f", p1.wins,p2.wins));


        System.out.printf("Player 1 win percentage: %.3f%%\n",(p1.wins/42)*100);
        System.out.printf("Player 2 win percentage: %.3f%%\n", (p2.wins/42)*100);



    }
    
}
