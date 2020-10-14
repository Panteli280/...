package com.company;

import java.util.ArrayList;

public class Board {

    protected ArrayList<Card> board;
    protected ArrayList<Card> defBoard;

    Board(){
        this.board = new ArrayList<Card>();
        this.defBoard = new ArrayList<Card>();
    }

    void showCardsOnBoard() {
        System.out.println("\nCards board : ");
        for (Card c : board) {
            System.out.println(c.toString() + "; ");
        }
    }

    void showCardsOnDefBoard() {
        System.out.println("\nCards defBoard : ");
        for (Card c : defBoard) {
            System.out.println(c.toString() + "; ");
        }
    }
}
