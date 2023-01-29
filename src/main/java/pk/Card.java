package pk;

public class Card {

    //Each card has type, val and point attribute.
    private CardTypes type;
    private CardValues val;
    private int points;

    public Card(){

    }

    public Card(CardTypes t, CardValues v, int p){
        //Each creation of card requires type, val and points.
        this.type=t;
        this.val=v;
        this.points=p;
    }

    public int getVal(){
        //Returns numerical value for each card.
        if (this.val==CardValues.ONE){
            return 1;
        }else if (this.val==CardValues.TWO){
            return 2;
        }else if (this.val==CardValues.THREE){
            return 3;
        }else if (this.val==CardValues.FOUR){
            return 4;
        }else{
            return 0;
        }
    }

    public CardTypes getType(){
        return this.type;
    }

    public int getPoints(){
        return this.points;
    }
}
