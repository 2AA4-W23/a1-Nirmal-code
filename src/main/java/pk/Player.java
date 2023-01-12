package pk;

import java.util.Collections;
import java.util.Random;

import java.util.List;


public class Player {

    public int tally_score(List<Faces> final_rolls){
        int num_gold= Collections.frequency(final_rolls, Faces.GOLD);
        int num_diamond= Collections.frequency(final_rolls, Faces.GOLD);
        int score=(num_gold+num_diamond)*100;
        return score;
    }
    
}
