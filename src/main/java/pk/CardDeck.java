package pk;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class CardDeck extends Card {

    //card deck is represented as a Queue.
    Queue<Card> deck=new ArrayDeque<Card>();

    public CardDeck(){

        //Creates each new card deck with 21 blank cards, 6 SeaBattle cards, 4 MonkeyBusiness cards and 4 Sorceress cards.

        for (int i=0; i<21; i++){
            deck.add(new Card(CardTypes.Empty, CardValues.NONE, 0));
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

        deck.add(new Card(CardTypes.Sorceress, CardValues.NONE, 0));
        deck.add(new Card(CardTypes.Sorceress, CardValues.NONE, 0));
        deck.add(new Card(CardTypes.Sorceress, CardValues.NONE, 0));
        deck.add(new Card(CardTypes.Sorceress, CardValues.NONE, 0));
    }

    public Card pickCard(){
        //Each card that is removed is added to bottom of queue (to cycle through).
        Card card=deck.remove();
        deck.add(card);
        return card;
    }

    public void shuffle(){
        //Uses shuffle method of ArrayList to shuffle queue.
        List<Card> list=new ArrayList<Card>(deck);
        Collections.shuffle(list);
        deck=new ArrayDeque<Card>(list);
    }

    public int Size(){
        return deck.size();
    }
}
