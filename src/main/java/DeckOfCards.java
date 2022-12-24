import java.util.*;

public class DeckOfCards {
    private ArrayList<Card> deck;
    private List<String> suits = Card.getValidSuits();
    private List<String> faceNames = Card.getValidFaceNames();

    DeckOfCards(){
        deck = new ArrayList<>();
        for (String suit:suits){
            for (String faceName:faceNames){
                deck.add(new Card(faceName, suit));
            }
        }
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void displayDeck(){
        for (Card card:deck){
            System.out.println(card.getFaceName() + " of " + card.getSuit());
        }
    }

    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    public Card selectRandomCard(){
        Random rand = new Random();
        int randomIndex = rand.nextInt(deck.size());
        Card selectedCard = deck.get(randomIndex);
        removeCard(randomIndex);
        return selectedCard;
    }

    public void removeCard(int index){
        deck.remove(index);
    }

    public void removeCardByProperty(Card card){

        for (int i=0;i<deck.size();i++){
            if (deck.get(i).getFaceName().equals(card.getFaceName()) && deck.get(i).getSuit().equals(card.getSuit())){
                deck.remove(i);
                break;
            }
        }
    }
}
