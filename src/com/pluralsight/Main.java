package com.pluralsight;

import java.util.Scanner;
import java.util.Random;

public class Main {

/*
    https://programmingbydoing.com/

    Nim - Assignment 63c (Bonus #5)
    Format modified to include classes and methods
*/

    public static void main(String[] args) {
        // write your code here

        Scanner keyboard = new Scanner(System.in);
        Random r = new Random();

//Create variables and objects for players
        System.out.println("Player 1, enter your name:");
        Player player1 = new Player(keyboard.next());
        System.out.println();

        System.out.println("Player 2, is the computer");
        Player player2 = new Player("Computer");
        System.out.println();

        Player currentPlayer = player1;
        String currentPlayerName = player1.getName();
        String otherPlayerName;

//Create variables and objects for piles
        Piles[] pile = new Piles[3];
        pile[0] = new Piles("A", 3);
        pile[1] = new Piles("B", 4);
        pile[2] = new Piles("C", 5);
        int total = pile[0].getCount() + pile[1].getCount() + pile[2].getCount();
        int reduce;
        int i = 0;
        int currentLine = 5;
        String currentPile;

        boolean valid;

//Play game until no counters left
        while (total > 1) {
//Loop through to display appropriate number of *'s next to each pile letter in columns
            while (currentLine != 0) {
                System.out.print("  ");
                while (i < pile.length) {
                    pile[i].getImage(currentLine);
                    i++;
                }
                currentLine--;
                System.out.println();
                i = 0;
            }
            System.out.print("  ");
            System.out.println(pile[0].getDisplayLetter() + pile[1].getDisplayLetter() + pile[2].getDisplayLetter());

            System.out.println();
            System.out.println(currentPlayerName + ", choose a pile:");

//If computer's turn, randomly choose a pile
            if (currentPlayer == player2) {
                int x = r.nextInt(3);
                currentPile = pile[x].getDisplayLetter();
            }
//If human player's turn, prompt for pile choice
//FIRST VALIDITY CHECK ON PILE SELECTION
            else {
                currentPile = keyboard.next();
                while (!(currentPile.equalsIgnoreCase("A") || currentPile.equalsIgnoreCase("B") || currentPile.equalsIgnoreCase("C"))){
                    System.out.println("Entry must be \"A\" , \"B\" , or \"C\"");
                    currentPile = keyboard.next();
                }
            }

            valid = false;
//Determine if the chosen pile is valid (isn't a counter of 0 and can be reduced)
//SECOND VALIDITY CHECK ON PILE SELECTION
            while (valid == false) {
                i = 0;
                while (i < pile.length && valid == false) {
                    valid = pile[i].validNotZero(currentPile);
                    i++;
                }
                if (valid == false) {

//If computer's turn, randomly re-choose a pile
                    if (currentPlayer == player2) {
                        int x = r.nextInt(3);
                        currentPile = pile[x].getDisplayLetter();
                    }

//If human player's turn, prompt for re-selection
                    else {
                        System.out.println("Nice try, " + currentPlayerName + " That pile is empty. Choose again:");
                        currentPile = keyboard.next();
                    }
                }
            }

//Display computers choice
            if (valid == true && currentPlayer == player2) {
                System.out.println("Computer chooses " + currentPile);
            }

            System.out.println("How many to remove from pile " + currentPile + " ?");
//If computer's turn, randomly choose a value
            if (currentPlayer == player2) {
                reduce = 1 + r.nextInt(5);
            }
//If human player's turn, prompt for value choice
            else {
                reduce = keyboard.nextInt();
            }

            valid = false;
            while (valid == false) {
//FIRST VALIDITY CHECK ON NUMBER
//Determine if the reduction value is valid (isn't greater than the counter)
                while (valid == false) {
                    i = 0;
                    while (i < pile.length && valid == false) {
                        valid = pile[i].validNotTooMuch(currentPile, reduce);
                        i++;
                    }
                    if (valid == false) {
                        //If computer's turn, randomly re-choose a value
                        if (currentPlayer == player2) {
                            reduce = 1 + r.nextInt(5);
                        }
                        //If human player's turn, prompt for pile choice
                        else {
                            System.out.println("Pile " + currentPile + " doesn't have that many. Try again:");
                            reduce = keyboard.nextInt();
                        }
                    }
                }

                if (valid == true){
//SECOND VALIDITY CHECK ON NUMBER
//Determine if the reduction value is valid (isn't negative)
                    while (reduce <= 0) {
                        valid = false;
                        System.out.println("You must choose at least 1. Try again:");
                        reduce = keyboard.nextInt();
                    }
                }

            }


//Display computers choice
            if (valid == true && currentPlayer == player2) {
                System.out.println("Computer chooses " + reduce);
            }

//Determine pile to be reduced and proceed
            valid = false;
            while (valid == false) {
                i = 0;
                while (i < pile.length && valid == false) {
                    valid = pile[i].setCount(currentPile, reduce);
                    i++;
                }
            }
//Reset total, values in each count, object counter and display line
            total = pile[0].getCount() + pile[1].getCount() + pile[2].getCount();
            i = 0;
            currentLine = 5;

//Switch to next player
            if (currentPlayer == player1) {
                currentPlayer = player2;
                currentPlayerName = player2.getName();
                otherPlayerName = player1.getName();
            } else {
                currentPlayer = player1;
                currentPlayerName = player1.getName();
                otherPlayerName = player2.getName();
            }

//Determine if there is only 1 counter remaining and declare the winner one step early
            if (total == 1) {
                System.out.println(currentPlayerName + ", you must take the last remaining counter, so you lose. " +
                        otherPlayerName + " wins!");
            }
        }

//End the game and declare the winner
        if (total == 0) {
            System.out.println(currentPlayerName + ", there are no counters left, so you win");
        }


    }
}

