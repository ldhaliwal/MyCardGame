import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private String[] ranks;
    private String[] suits;
    private int[] values;

    public Game() {
        this.ranks = new String[]{"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        this.suits = new String[]{"Spades", "Diamonds", "Hearts", "Clubs"};
        this.values = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
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
        Scanner s = new Scanner(System.in);

        //create a deck of cards
        Deck d = new Deck(ranks, suits, values);

        boolean gameWon = false;

        ArrayList<Card> playerHand = new ArrayList<>();

        //shuffle the deck
        d.shuffle();

        //deal two cards to the player and store in an arraylist called playerHand
        playerHand.add(d.deal());
        playerHand.add(d.deal());

        //while the game is still going
        while(!gameWon)
        {
            int handValue = 0;
            for(Card c : playerHand)
            {
                handValue += c.getPoint();
            }

            System.out.println("Would you like to stand or hit? (S or H) ");
            String response = s.nextLine();

        }
    }

    public boolean takeTurn(){
        return false;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.playGame();
    }
}


