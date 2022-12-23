import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Card {
    private String faceName, suit;

    Card(){
    }

    public Card(String faceName, String suit) {
        setFaceName(faceName);
        setSuit(suit);
    }

    public String getFaceName() {
        return faceName;
    }

    public void setFaceName(String faceName) {
        List<String> validFaceNames = getValidFaceNames();
        faceName = faceName.toLowerCase();

        if (validFaceNames.contains(faceName)){
            this.faceName = faceName;
        } else throw new IllegalArgumentException("Invalid face name");

    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        List<String> validSuits = getValidSuits();
        suit = suit.toLowerCase();
        if (validSuits.contains(suit)){
            this.suit = suit;
        } else throw new IllegalArgumentException("Invalid suit");

    }

    public static List<String> getValidFaceNames(){
        return Arrays.asList("2","3","4","5","6","7","8","9","10","jack","queen","king","ace");
    }

    public static List<String> getValidSuits(){
        return Arrays.asList("hearts","diamonds","spades","clubs");
    }

    @Override
    public String toString() {
        return (faceName + " of " + suit);
    }
}
