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

    public String[] getRanks() {
        return ranks;
    }

    public String[] getSuits() {
        return suits;
    }

    public int[] getValues() {
        return values;
    }

    public void playGame() {
        //print instructions
        System.out.println(printInstructions());

        //shuffle the deck
        d.shuffle();

        //deal two cards to the player and store in an arraylist called playerHand
        playerHand.add(d.deal());
        playerHand.add(d.deal());

        //deal two cards to the dealer and store in an arraylist called dealerHand
        dealerHand.add(d.deal());
        dealerHand.add(d.deal());

        printWinner(dealerTurn(), playerTurn());
    }

    public int playerTurn(){
        Scanner s = new Scanner(System.in);
        int playerValue = playerPoints();

        System.out.println("Your cards total to: " + playerValue);

        while(true) {
            if (playerValue >= 21) {
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
            }
            else{
                System.out.println("Invalid input");
            }
        }
        return playerValue;
    }

    public int dealerTurn(){
        int dealerValue = dealerPoints();

        while(true){
            if(dealerValue >= 17){
                break;
            }
            dealerHand.add(d.deal());
            dealerValue = dealerPoints();
        }
        return dealerValue;
    }


    public void printWinner(int dealerValue, int playerValue){
        if ((dealerValue > 21 && playerValue > 21) || (dealerValue == 21 && playerValue == 21)){
            System.out.println("You tied with the dealer! Good game!");
        }
        else if ((21 - dealerValue) < (21 - playerValue)) {
            System.out.println("You lost, better luck next time!");
        }
        else if ((21 - dealerValue) > (21 - playerValue)){
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

    public String printInstructions(){
        return " ";
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.playGame();
    }
}


