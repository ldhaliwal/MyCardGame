//Liliana Dhaliwal
//December 7, 2022

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private final String[] ranks;
    private final String[] suits;
    private final int[] values;
    Deck d;

    ArrayList<Card> playerHand;
    ArrayList<Card> dealerHand;

    public Game() {
        this.ranks = new String[]{"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        this.suits = new String[]{"Spades", "Diamonds", "Hearts", "Clubs"};
        this.values = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        this.d = new Deck(ranks, suits, values);
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
    }

    public void playGame() {
        //print instructions
        printInstructions();

        //shuffle the deck
        d.shuffle();

        //deal two cards to the player and store in an arraylist called playerHand
        playerHand.add(d.deal());
        playerHand.add(d.deal());

        printCards(playerHand);

        //deal two cards to the dealer and store in an arraylist called dealerHand
        dealerHand.add(d.deal());
        dealerHand.add(d.deal());

        printWinner(playerTurn(), dealerTurn());
    }

    public int playerTurn(){
        Scanner s = new Scanner(System.in);
        int playerValue = playerPoints();

        System.out.println("Your cards total to: " + playerValue);
        System.out.println();

        while(true) {
            if (playerValue > 21) {
                System.out.println("Bust!");
                break;
            }

            System.out.println("Would you like to stand or hit? (S or H) ");
            String response = s.nextLine();

            //if player chose to stand, end the player's turn
            if (response.equals("S")) {
                break;
            }
            //if player chose to hit, deal the player another card
            else if (response.equals("H")) {
                playerHand.add(d.deal());
                playerValue = playerPoints();
                System.out.println("You drew a " + playerHand.get(playerHand.size() - 1).toString());
                System.out.println("Your cards total to: " + playerValue);
                System.out.println();
            }
            else{
                System.out.println("Invalid input");
            }
        }
        System.out.println("------------------------------------");
        return playerValue;
    }

    public int dealerTurn(){
        int dealerValue = dealerPoints();
        System.out.println("The dealer's cards total to: " + dealerValue);
        System.out.println();

        while(true){
            if(dealerValue > 21)
            {
                System.out.println("The dealer went over 21, Bust!");
                break;
            }
            else if(dealerValue >= 17){
                System.out.println("The dealer chose to stand");
                break;
            }

            System.out.println("The dealer chose to hit");
            System.out.println();

            dealerHand.add(d.deal());
            dealerValue = dealerPoints();

            System.out.println("The dealer drew a " + dealerHand.get(dealerHand.size() - 1).toString());
            System.out.println("The dealer's cards total to: " + dealerValue);
        }
        System.out.println("------------------------------------");
        return dealerValue;
    }

    public void printWinner(int playerValue, int dealerValue){
        if ((dealerValue > 21 && playerValue > 21) || dealerValue == playerValue){
            System.out.println();
            System.out.println("You tied with the dealer! Good game!");
        }
        else if ((21 - dealerValue) < (21 - playerValue) || (dealerValue <= 21 && playerValue > 21)){
            System.out.println();
            System.out.println("You lost, better luck next time!");
        }
        else if ((21 - dealerValue) > (21 - playerValue) || (dealerValue > 21)){
            System.out.println();
            System.out.println("You win! Good game!");
        }
    }

    public int playerPoints()
    {
        int handValue = 0;
        for(Card c : playerHand)
        {
            handValue += c.getPoint();
        }
        return handValue;
    }

    public int dealerPoints()
    {
        int dealValue = 0;
        for(Card c : dealerHand)
        {
            dealValue += c.getPoint();
        }
        return dealValue;
    }

    public void printCards(ArrayList<Card> cards)
    {
        for(Card c : cards) {
            System.out.println("You drew a " + c.toString());
        }
    }

    public void printInstructions(){
        System.out.println("Welcome to Blackjack! the objective of the game is to get as");
        System.out.println("close as you can to 21 points without going over. Each turn, you will have the");
        System.out.println("choice to 'Stand' and keep the cards you have, or 'Hit' and draw another card.");
        System.out.println("Good luck!");
        System.out.println("------------------------------------");
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.playGame();
    }
}