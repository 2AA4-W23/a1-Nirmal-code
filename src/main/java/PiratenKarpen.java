
import pk.Player;



public class PiratenKarpen {

    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");
        System.out.println("I'm rolling a dice");

        Player p1=new Player();
        Player p2=new Player();



        for (int i=0; i<42; i++) {

            p1.score+= p1.turn();
            p2.score+= p2.turn();

            System.out.printf("Player 1: %d \n", p1.score);
            System.out.printf("Player 2: %d \n", p2.score);

            Player.win_update(p1,p2);


        }


        System.out.printf("Player 1 wins: %.0f ; score: %d ; win_percentage: %.3f%%\n", p1.wins, p1.score, (p1.wins/42)*100);
        System.out.printf("Player 2 wins: %.0f ; score: %d ; win_percentage: %.3f%%\n", p2.wins, p2.score, (p2.wins/42)*100);



    }
    
}
