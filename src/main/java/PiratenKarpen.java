import pk.Dice;
import pk.Faces;
import pk.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class PiratenKarpen {

    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");
        System.out.println("I'm rolling a dice");
        Dice myDice = new Dice();

        Player p1=new Player();
        Player p2=new Player();
        List<Faces> eight_rolls;

        double p1_wins=0;
        double p2_wins=0;


        for (int i=0; i<42; i++) {
            eight_rolls = myDice.eightRoll();
            int p1_score = p1.turn(eight_rolls);
            eight_rolls = myDice.eightRoll();
            int p2_score = p2.turn(eight_rolls);
            if (p1_score > p2_score) {
                p1_wins += 1;
            } else {
                p2_wins += 1;
            }

        }


        System.out.printf("Player 1 score: %.3f%%", (p1_wins/42)*100);
        System.out.println();
        System.out.printf("Player 2 score: %.3f%%", (p2_wins/42)*100);



    }
    
}
