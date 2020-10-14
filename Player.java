package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    private int id;
    private static int nextId = 1;

    protected int status = 0;
    protected ArrayList<Card> hand;

    Player(Deck deck) {
        this.setId();
        this.hand = new ArrayList<Card>();

        for (int i = 0; i <= 5; i++) {
            this.hand.add(deck.drawFromDeck());
        }
    }

    void setId() {
        id = nextId;
        nextId++;
    }

    int getId() {
        return id;
    }

    void changeStatus(){
        this.status++;
    }

    void showCardsInHand() {
        System.out.println("\nCards of player #" + this.getId() + ": ");
        for (Card c : hand) {
            System.out.println(c.toString() + "; ");
        }
    }

    void addCard(Board boards) {
        Scanner scan = new Scanner(System.in);

        if (boards.board.size() == 0) {
            System.out.println("\nPlayer #" + this.getId() + " Choose a card");
            int choice = scan.nextInt()-1;
            boards.defBoard.add(hand.get(choice));
            put(hand, boards.board, choice);
        } else {
            System.out.println("\nPlayer #" + this.getId()
                    + " Do you want to put one more card? 'Y', 'N' or 'S'...");
            String userAnswer = scan.next();

            switch (userAnswer.toUpperCase()) {

                case "Y":
                    System.out.println("Choose a card");
                    int choice = scan.nextInt()-1;
                    for (int i = 0; i < boards.board.size(); i++) {
                        if (hand.get(choice).getRank() == boards.board.get(i).getRank()) {
                            boards.defBoard.add(hand.get(choice));
                            put(hand, boards.board, choice);
                            break;
                        }
                        if (i == boards.board.size()) {
                            System.out.println("Incorrect input. Try again.");
                            addCard(boards);
                        }
                    }
                    break;

                case "N":
                    changeStatus();
                    break;

                case "S":
                    boards.board.clear();
                    changeStatus();
                    break;

                default:
                    System.out.println("Incorrect input. Try again.");
                    addCard(boards);
            }
        }
    }

    void defCard(Board boards) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nPlayer #" + this.getId()
                + " Do you want to def? 'Y' or 'N'...");
        String userAnswer = scan.next();

        switch (userAnswer.toUpperCase()) {

            case "Y":
                System.out.println("Choose a card");
                int choice = scan.nextInt()-1;
                for (int i = 0; i < boards.board.size(); i++) {
                    int index = compare(boards.defBoard, hand.get(choice));
                    if (index != -1) {
                        boards.defBoard.remove(index);
                        put(hand, boards.board, choice);
                        break;
                    }
                }
                break;

            case "N":
                for (int j = 0; j <= boards.board.size(); j++) {
                    put(boards.board, hand, j);
                }
                boards.defBoard.clear();
                break;

            default:
                System.out.println("Incorrect input. Try again.");
                defCard(boards);
        }
    }

    void put(ArrayList<Card> from, ArrayList<Card> to, int choice) {
        to.add(from.get(choice));
        from.remove(choice);
    }

    int compare(ArrayList<Card> defBoard, Card card) {
        for (int index = 0; index < defBoard.size(); index++) {
            if ((card.getRank().getValue() > defBoard.get(index).getRank().getValue()) && (card.getSuit() == defBoard.get(index).getSuit())) {
                return index;
            }
        }
        return -1;
    }
}



