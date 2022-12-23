import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class CombinationFinder {
    private List<Card> handAndTable = new ArrayList<>(7);
    Hashtable <String, Integer> values = new Hashtable<String, Integer>();

    CombinationFinder(Table table, Player player){
        setHandAndTable(table, player);
        System.out.printf(handAndTable.toString()+"\n");
        setValues();
        sortLowestToHighest();
        System.out.printf(handAndTable.toString());

    }

    public List<Card> getHandAndTable() {
        return handAndTable;
    }

    public void setHandAndTable(Table table, Player player) {
        for(int i=0;i<5;i++){
           // System.out.println(table.getCards().get(i));
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
}
