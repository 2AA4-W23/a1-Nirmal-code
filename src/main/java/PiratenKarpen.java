
import pk.Game;


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

        

        Game game= new Game(args[0], args[1]);

        game.finalUpdate();

    }
    
}
