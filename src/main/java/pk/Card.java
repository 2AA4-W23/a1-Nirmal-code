package pk;

public class Card {
    private CardTypes type;
    private CardValues val;
    private int points;

    public Card(){

    }

    public Card(CardTypes t, CardValues v, int p){
        this.type=t;
        this.val=v;
        this.points=p;
    }

}
