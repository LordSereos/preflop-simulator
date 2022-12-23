public class main {
    public static void main(String[] args) {
        DeckOfCards deck = new DeckOfCards();
        for (int i=0;i<10;i++){
            deck.shuffleDeck();
        }

        System.out.println("Shuffled, size is " + deck.getDeck().size());
        Player p1 = new Player(deck);
        System.out.println("After p1, size is " + deck.getDeck().size());
        System.out.println(p1.getHand().toString());
        Table table = new Table(deck);
        System.out.println("After table, size is " + deck.getDeck().size());
        System.out.println(table.getCards().toString());
        CombinationFinder cf = new CombinationFinder(table, p1);
   //     System.out.println(cf.getHandAndTable().toString());
    }
}