import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;
    private int cardsLeft = 0;

    public Deck(String[] ranks, String[] suits, int[] values) {
        this.cards = new ArrayList<Card>();

        for(int i = 0; i < ranks.length; i++)
        {
            for(int j = 0; j < suits.length; j++)
            {
                Card c = new Card(ranks[i], suits[j], values[i]);
                cards.add(c);
            }
        }
        this.cardsLeft = cards.size();
    }

    public boolean isEmpty(){
        return cardsLeft <= 0;
    }

    public int getCardsLeft(){
        return cardsLeft;
    }

    public Card deal(){
        if (isEmpty())
        {
            return null;
        }
        cardsLeft--;
        return cards.remove(cardsLeft);
    }

    public void shuffle(){
        for(int i = cards.size() - 1; i > 0; i--)
        {
            int randomIndex = (int) Math.random() * i;
            Card temp = cards.get(i);
            cards.set(i, cards.get(randomIndex));
            cards.set(randomIndex, temp);
        }
    }
}
