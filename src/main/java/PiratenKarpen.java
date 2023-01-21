
import pk.Player;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;




public class PiratenKarpen {

    public static Logger logger=LogManager.getLogger(PiratenKarpen.class);    

    public static void main(String[] args) {
        
        System.out.println("Welcome to Piraten Karpen Simulator!");
        System.out.println("I'm rolling a dice");


        if ((System.getProperty("my.trace")=="true")) {
            Configurator.setRootLevel(Level.ALL);
        }else if ((System.getProperty("my.trace")==null)){
            Configurator.setRootLevel(Level.OFF);
        }


        Player p1=new Player();
        Player p2=new Player();

        int num_sim=0;


        while (p1.getScore()<6000 & p2.getScore()<6000){

            logger.trace(String.format("Round %d:", num_sim+1));


            logger.trace("Player 1 Turn:");
            p1.pTurn(args[0]);
            logger.trace("Player 1 turn ended.");


            logger.trace("Player 2 Turn:");
            p2.pTurn(args[1]);
            logger.trace("Player 2 turn ended.");


            Player.winUpdate(p1,p2);
            num_sim+=1;

        }


        Player.finalUpdate(p1, p2, num_sim);


    }
    
}
