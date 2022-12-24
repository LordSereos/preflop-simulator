import java.util.ArrayList;
import java.util.List;

public class Table {
    private List<Card> cards = new ArrayList<>(5);
    Table(DeckOfCards deck){
        setCards(deck);
    }
    Table(DeckOfCards deck, Card c1, Card c2, Card c3, Card c4, Card c5){
        setParticularCards(deck, c1, c2, c3, c4, c5);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(DeckOfCards deck) {
        for (int i=0;i<5;i++){
            cards.add(deck.selectRandomCard());
        }
    }
    public void setParticularCards(DeckOfCards deck, Card c1, Card c2, Card c3, Card c4, Card c5) {
        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        cards.add(c5);
        deck.removeCardByProperty(c1);
        deck.removeCardByProperty(c2);
        deck.removeCardByProperty(c3);
        deck.removeCardByProperty(c4);
        deck.removeCardByProperty(c5);
    }
}
