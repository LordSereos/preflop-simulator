import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> hand = new ArrayList<>(2);
    private String combination;
    private int combinationStrength;

    Player(DeckOfCards deck){
        setHand(deck);
    }
    Player(DeckOfCards deck, Card c1, Card c2){
        setParticularHand(deck, c1, c2);
    }



    public List<Card> getHand() {
        return hand;
    }

    public void setHand(DeckOfCards deck) {
        hand.add(deck.selectRandomCard());
        hand.add(deck.selectRandomCard());
    }
    public void setParticularHand(DeckOfCards deck,Card c1, Card c2) {
        hand.add(c1);
        hand.add(c2);
        deck.removeCardByProperty(c1);
        deck.removeCardByProperty(c2);
    }

    public String getCombination() {
        return combination;
    }

    public void setCombination(String combination) {
        this.combination = combination;
    }

    public int getCombinationStrength() {
        return combinationStrength;
    }

    public void setCombinationStrength(int combinationStrength) {
        this.combinationStrength = combinationStrength;
    }
}
