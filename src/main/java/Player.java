import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> hand = new ArrayList<>(2);
    private String combination;
    private int combinationStrength;

    Player(DeckOfCards deck){
        setHand(deck);

    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(DeckOfCards deck) {
        hand.add(deck.selectRandomCard());
        hand.add(deck.selectRandomCard());
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
