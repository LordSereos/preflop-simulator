public class main {
    public static void main(String[] args) {
        DeckOfCards deck = new DeckOfCards();
        for (int i=0;i<10;i++){
            deck.shuffleDeck();
        }

        Card my1 = new Card("5", "spades");
        Card my2 = new Card("7", "clubs");

        Card c1 = new Card("9", "hearts");
        Card c2 = new Card("jack", "hearts");
        Card c3 = new Card("king", "diamonds");
        Card c4 = new Card("2", "hearts");
        Card c5 = new Card("queen", "hearts");

        Player p1 = new Player(deck, my1, my2);
        Player p2 = new Player(deck);
        System.out.println("My hand: "+"    "+p1.getHand().toString());
        Table table = new Table(deck,c1,c2,c3,c4,c5);
        Table table1 = new Table(deck);
        System.out.println("Table cards: " + table.getCards().toString());

        CombinationFinder cf = new CombinationFinder(table, p1);
    }
}