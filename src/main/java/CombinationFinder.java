import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class CombinationFinder {
    private List<Card> handAndTable = new ArrayList<>(7);
    Hashtable <String, Integer> values = new Hashtable<String, Integer>();

    private boolean spadesFlush = false;
    private boolean heartsFlush = false;
    private boolean diamondsFlush = false;
    private boolean clubsFlush = false;

    private boolean isStraight = false;
    private boolean isStraightFlush = false;
    private boolean isRoyalFlush = false;
    private boolean areQuads = false;
    private boolean areTrips = false;
    private String tripsFace;
    private boolean arePairs = false;
    private String firstPairsFace;
    private String secondPairsFace;
    private String highCard;

    CombinationFinder(Table table, Player player){
        setHandAndTable(table, player);
       // System.out.printf(handAndTable.toString()+"\n");
        setValues();
        sortLowestToHighest();
       // System.out.printf(handAndTable.toString());
        checkIfFlush();

        if (spadesFlush || heartsFlush || diamondsFlush || clubsFlush) {
            reduceFor5CardsFlush();
            checkIfStraight();
            if (isStraight){
                checkIfRoyal();
                if (isRoyalFlush){
                    System.out.println("\nIS ROYAL");
                } else System.out.println("\nIS STRAIGHT FLUSH");
            }
            else System.out.println("\nIS FLUSH");
        }
        else {
            checkIfStraight();
            if (isStraight){
                System.out.println("\nIS STRAIGHT");
            }
            else {
                checkIfQuads();
                if (areQuads){
                    System.out.println("\nARE QUADS");
                }
                else {
                    checkIfTrips();
                    if (areTrips){
                        reduce3FoundTrips();
                        checkIfPairs();
                        if (arePairs){
                            System.out.println("\nFULL HOUSE");
                            System.out.println("\nTrips: " + tripsFace);
                            System.out.println("\nPair: " + firstPairsFace);
                        }
                        else System.out.println("\nARE TRIPS");
                    }
                    else {
                        checkIfPairs();
                        if (arePairs){
                            arePairs=false;
                            reduce2FoundPairs();
                            checkIfSecondPairs();
                            if (arePairs){
                                System.out.println("\nARE TWO PAIRS");
                                System.out.println("\nBigger pair: " + firstPairsFace);
                                System.out.println("\nSmaller pair: " + secondPairsFace);
                            }
                            else System.out.println("\nARE PAIRS");
                        }
                        else {
                            findHighCard();
                            System.out.println("\nHIGH CARD " + highCard);
                        }
                    }
                }
            }
        }
    }

    public List<Card> getHandAndTable() {
        return handAndTable;
    }

    public void setHandAndTable(Table table, Player player) {
        for(int i=0;i<5;i++){
            handAndTable.add(table.getCards().get(i));
        }
        handAndTable.add(player.getHand().get(0));
        handAndTable.add(player.getHand().get(1));
    }

    public void setValues(){
        values.put("2", 2);
        values.put("3", 3);
        values.put("4", 4);
        values.put("5", 5);
        values.put("6", 6);
        values.put("7", 7);
        values.put("8", 8);
        values.put("9", 9);
        values.put("10", 10);
        values.put("jack", 11);
        values.put("queen", 12);
        values.put("king", 13);
        values.put("ace", 14);
    }

    public void sortLowestToHighest(){

        for (int i=0;i<handAndTable.size()-1;i++){
           for (int j=i+1;j<handAndTable.size();j++){
               if (values.get(handAndTable.get(i).getFaceName()) >= values.get(handAndTable.get(j).getFaceName())){
                   Card c = new Card();
                   c.setFaceName(handAndTable.get(i).getFaceName());
                   c.setSuit(handAndTable.get(i).getSuit());

                   handAndTable.get(i).setFaceName(handAndTable.get(j).getFaceName());
                   handAndTable.get(i).setSuit(handAndTable.get(j).getSuit());

                   handAndTable.get(j).setFaceName(c.getFaceName());
                   handAndTable.get(j).setSuit(c.getSuit());
               }
           }
        }
    }

    public void checkIfFlush(){
        int countDiamonds = 0;
        int countClubs = 0;
        int countHearts = 0;
        int countSpades = 0;

        for (int i=0;i<7;i++){
            if (handAndTable.get(i).getSuit().equals("diamonds")){
                countDiamonds++;
            }
            else if (handAndTable.get(i).getSuit().equals("clubs")){
                countClubs++;
            }
            else if (handAndTable.get(i).getSuit().equals("hearts")){
                countHearts++;
            }
            else  {
                countSpades++;
            }
        }
        if (countClubs >=5 ){
            clubsFlush=true;
        }
        if (countDiamonds >=5 ){
            diamondsFlush=true;
        }
        if (countHearts >=5 ){
            heartsFlush=true;
        }
        if (countSpades >=5 ){
            spadesFlush=true;
        }
    }

    public void reduceFor5CardsFlush(){
        List<Card> hat = new ArrayList<>(5);
        int k=0;
        if (spadesFlush) {
            for (int i=handAndTable.size()-1;i>=0;i--){
                if (handAndTable.get(i).getSuit().equals("spades") && k<5){
                    hat.add(handAndTable.get(i));
                    k++;
                }
            }
        }
        else if (diamondsFlush) {
            for (int i=handAndTable.size()-1;i>=0;i--){
                if (handAndTable.get(i).getSuit().equals("diamonds") && k<5){
                    hat.add(handAndTable.get(i));
                    k++;
                }
            }
        }
        else if (clubsFlush) {
            for (int i=handAndTable.size()-1;i>=0;i--){
                if (handAndTable.get(i).getSuit().equals("clubs") && k<5){
                    hat.add(handAndTable.get(i));
                    k++;
                }
            }
        }
        else if (heartsFlush) {
            for (int i=handAndTable.size()-1;i>=0;i--){
                if (handAndTable.get(i).getSuit().equals("hearts") && k<5){
                    hat.add(handAndTable.get(i));
                    k++;
                }
            }
        }

        handAndTable.removeAll(handAndTable);
        for (int i=hat.size()-1;i>=0;i--){
            handAndTable.add(hat.get(i));
        }
        System.out.printf("\n"+handAndTable.toString());
    }

    public void checkIfStraight(){
        if (isAceInHand()){
            if (is2345InHand()){
                isStraight = true;
            }
        }
        else {
            for (int i=handAndTable.size()-1;i>0;i--){
                int q=1;
                int sequenceCounter = 0;
                for (int j=i-1;j>=0;j--){
                    if (values.get(handAndTable.get(i).getFaceName()) - values.get(handAndTable.get(j).getFaceName()) == q){
                        sequenceCounter++;
                        q++;
                    }
                    if (sequenceCounter==4){
                        isStraight = true;
                        break;
                    }
                }
                if (sequenceCounter==4) break;
            }
        }

    }

    public void checkIfRoyal(){
        if (handAndTable.get(0).getFaceName().equals("10") && handAndTable.get(1).getFaceName().equals("jack") &&
                handAndTable.get(2).getFaceName().equals("queen") && handAndTable.get(3).getFaceName().equals("king") && handAndTable.get(4).getFaceName().equals("ace")){
            isRoyalFlush=true;
        }
    }

    public void checkIfQuads(){

        for (int i=0;i<handAndTable.size()-1;i++){
            int counter = 0;
            for (int j=i+1;j<handAndTable.size();j++){
                if (handAndTable.get(j).getFaceName().equals(handAndTable.get(i).getFaceName())){
                    counter++;
                }
                if (counter==3){
                    areQuads = true;
                    break;
                }
            }
            if (areQuads) break;
        }
    }

    public void checkIfTrips(){

        for (int i=0;i<handAndTable.size()-1;i++){
            int counter = 0;
            for (int j=i+1;j<handAndTable.size();j++){
                if (handAndTable.get(j).getFaceName().equals(handAndTable.get(i).getFaceName())){
                    counter++;
                }
                if (counter==2){
                    areTrips = true;
                    tripsFace = handAndTable.get(i).getFaceName();
                    break;
                }
            }
        }
    }

    public void reduce3FoundTrips(){
        for (int i=0;i<handAndTable.size();i++){
            if (handAndTable.get(i).getFaceName().equals(tripsFace)){
                handAndTable.remove(handAndTable.get(i));
                i--;
            }
        }
    }

    public void checkIfPairs(){

        for (int i=0;i<handAndTable.size()-1;i++){
            for (int j=i+1;j<handAndTable.size();j++){
                if (handAndTable.get(j).getFaceName().equals(handAndTable.get(i).getFaceName())){
                    arePairs = true;
                    firstPairsFace = handAndTable.get(i).getFaceName();
                    break;
                }
            }
        }
    }

    public void checkIfSecondPairs(){

        for (int i=0;i<handAndTable.size()-1;i++){
            for (int j=i+1;j<handAndTable.size();j++){
                if (handAndTable.get(j).getFaceName().equals(handAndTable.get(i).getFaceName())){
                    arePairs = true;
                    secondPairsFace = handAndTable.get(i).getFaceName();
                    break;
                }
            }
        }
    }

    public void reduce2FoundPairs(){
        for (int i=0;i<handAndTable.size();i++){
            if (handAndTable.get(i).getFaceName().equals(firstPairsFace)){
                handAndTable.remove(handAndTable.get(i));
                i--;
            }
        }
        for (int i=0;i<handAndTable.size()-1;i++){
            for (int j=i+1;j<handAndTable.size();j++){
                if (handAndTable.get(j).getFaceName().equals(handAndTable.get(i).getFaceName())){
                    arePairs = true;
                    secondPairsFace = handAndTable.get(i).getFaceName();
                    break;
                }
            }
        }


    }

    public void findHighCard(){
        Card max = handAndTable.get(0);
        for (int i=0;i<handAndTable.size();i++){
            if (values.get(handAndTable.get(i).getFaceName())>=values.get(max.getFaceName())){
                max = handAndTable.get(i);
            }
        }
        highCard = max.getFaceName();
    }

    public boolean isAceInHand(){
        for (Card c:handAndTable){
            if (c.getFaceName().equals("ace")){
                return true;
            }
        }
        return false;
    }

    public boolean is2345InHand(){
        boolean two = false;
        boolean three = false;
        boolean four = false;
        boolean five = false;
        for (Card c:handAndTable){
            if (c.getFaceName().equals("2")){
                two = true;
            }
        }
        for (Card c:handAndTable){
            if (c.getFaceName().equals("3")){
                three = true;
            }
        }
        for (Card c:handAndTable){
            if (c.getFaceName().equals("4")){
                four = true;
            }
        }
        for (Card c:handAndTable){
            if (c.getFaceName().equals("5")){
                five = true;
            }
        }
        if (two && three && four && five) return true;
        else return false;
    }
}
