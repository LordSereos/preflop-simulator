import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void inputCorrectCard(){
        var card = new Card();
        card.setSuit("spades");
        card.setFaceName("queen");
        assertEquals("queen of spades",card.toString());
    }
    @Test
    void inputIncorrectCard(){
        var card = new Card();
        card.setSuit("spades");
        card.setFaceName("boat");
        assertEquals("boat of spades",card.toString());
    }

}