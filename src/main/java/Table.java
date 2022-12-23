import java.util.ArrayList;
import java.util.List;

public class Table {
    private List<Card> cards = new ArrayList<>(5);
    Table(DeckOfCards deck){
        setCards(deck);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(DeckOfCards deck) {
        for (int i=0;i<5;i++){
            cards.add(deck.selectRandomCard());
        }
    }
}
