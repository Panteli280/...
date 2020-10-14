package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Game {

    public static void main(String[] args) {

        Deck deck = new Deck();

       Board boards = new Board();

        Player hand1 = new Player(deck);
        Player hand2 = new Player(deck);

        Random rnd = new Random();
        int i = rnd.nextInt(2);
        if (hand1.getId() == i)
            hand1.changeStatus();
        else hand2.changeStatus();

            while (Continue(hand1, hand2)) {
                if (hand1.status == 1) turn(hand1, hand2, boards);
                else turn(hand2, hand1, boards);
                if (hand1.status == 1) getAdditionalCards(hand1, hand2,  deck);
                else getAdditionalCards(hand2, hand1,  deck);
            }

        if (hand1.hand.size() == 0)  System.out.println("\nPlayer #1 is winner");
        else  System.out.println("\nPlayer #2 is winner");
    }

    static void turn(Player attacker, Player defender, Board boards) {
        attacker.showCardsInHand();
        boards.showCardsOnBoard();
        attacker.addCard(boards);
        while (boards.board.size() != 0) {
            resetStatus(attacker, defender);
            while (attacker.status == 1) {
                attacker.showCardsInHand();
                boards.showCardsOnBoard();
                attacker.addCard(boards);
            }
            while ((defender.status == 0)&&(boards.defBoard.size() != 0)) {
                defender.showCardsInHand();
                boards.showCardsOnDefBoard();
                defender.defCard(boards);
            }
        }
        if (defender.status == 0) resetStatus(defender, attacker);
        else resetStatus(attacker, defender);
    }

    static boolean Continue(Player hand1, Player hand2) {
        if (hand1.hand.size() == 0) return false;
        if (hand2.hand.size() == 0) return false;
        return true;
    }

    static void resetStatus(Player attacker, Player defender){
        attacker.status = 1;
        defender.status = 0;
    }

    static void getAdditionalCards(Player player1, Player player2, Deck deck) {
            while ((player1.hand.size() < 6) && (deck.index != deck.getSizeOfDeck()))
                player1.hand.add(deck.drawFromDeck());
            while ((player2.hand.size() < 6) && (deck.index != deck.getSizeOfDeck()))
                player2.hand.add(deck.drawFromDeck());

    }
}
