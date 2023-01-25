package pk;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class CardDeck extends Card {

    Queue<Card> deck=new ArrayDeque<Card>();

    public CardDeck(){

        for (int i=0; i<25; i++){
            deck.add(new Card(CardTypes.Unknown, CardValues.NONE, 0));
        }

        deck.add(new Card(CardTypes.SeaBattle, CardValues.TWO, 300));
        deck.add(new Card(CardTypes.SeaBattle, CardValues.TWO,300));
        deck.add(new Card(CardTypes.SeaBattle, CardValues.THREE,500));
        deck.add(new Card(CardTypes.SeaBattle, CardValues.THREE,500));
        deck.add(new Card(CardTypes.SeaBattle, CardValues.FOUR,1000));
        deck.add(new Card(CardTypes.SeaBattle, CardValues.FOUR,1000));

        deck.add(new Card(CardTypes.MonkeyBusiness, CardValues.NONE, 0));
        deck.add(new Card(CardTypes.MonkeyBusiness, CardValues.NONE, 0));
        deck.add(new Card(CardTypes.MonkeyBusiness, CardValues.NONE, 0));
        deck.add(new Card(CardTypes.MonkeyBusiness, CardValues.NONE, 0));
    }

    public Card pickCard(){
        Card card=deck.remove();
        deck.add(card);
        return card;
    }

    public void shuffle(){
        List<Card> list=new ArrayList<Card>(deck);
        Collections.shuffle(list);
        deck=new ArrayDeque<Card>(list);
    }

    public int Size(){
        return deck.size();
    }
}
