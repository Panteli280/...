package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Card> deck;
    protected int index = 0;

    Deck() {
        deck = new ArrayList<Card>();
        SUIT[] suit = SUIT.values();
        RANK[] rank = RANK.values();

        for (SUIT s : suit)  {
            for (RANK r : rank) {
                deck.add(new Card(s, r));
            }
        }

        Random rnd = new Random();
        for (int i = 1; i < deck.size(); i++) {
            int j = rnd.nextInt(i);
            Card temp = deck.get(i);
            deck.set(i, deck.get(j));
            deck.set(j, temp);

            int tr = rnd.nextInt(suit.length);
            SUIT trump = suit[tr];
        }
    }

    Card drawFromDeck(){
            Card drawedCard = deck.get(index);
            deck.remove(index);
            index++;
            return drawedCard;
    }

    int getSizeOfDeck() {
        return deck.size();
    }
}
