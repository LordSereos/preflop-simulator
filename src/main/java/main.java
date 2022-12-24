public class main {
    public static void main(String[] args) {
        DeckOfCards deck = new DeckOfCards();
        for (int i=0;i<10;i++){
            deck.shuffleDeck();
        }

        System.out.println("Shuffled, size is " + deck.getDeck().size());
        Player p1 = new Player(deck, new Card("2","diamonds"), new Card("5","hearts"));
        System.out.println("After p1, size is " + deck.getDeck().size());
        System.out.println(p1.getHand().toString());

        Card c1 = new Card("king", "spades");
        Card c2 = new Card("9", "clubs");
        Card c3 = new Card("4", "diamonds");
        Card c4 = new Card("ace", "spades");
        Card c5 = new Card("8", "hearts");

        Table table = new Table(deck,c1,c2,c3,c4,c5);
        System.out.println("After table, size is " + deck.getDeck().size());
        CombinationFinder cf = new CombinationFinder(table, p1);
        System.out.println(cf.getHandAndTable().toString());
    }
}