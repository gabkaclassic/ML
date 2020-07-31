import java.util.Arrays;
import java.util.List;

public class Ship {

    public static final int LOW_BORDER = 0;
    public static final int HIGH_BORDER = 11;

    private final List<Deck> decks;

    public Ship(List<Deck> decks) {

        for(Deck d: decks) d.ship = this;

        this.decks = decks;
    }

    public boolean getLive() { return decks.stream().anyMatch(d -> d.live); }

    public int getCountDecks() { return decks.size(); }

    static class Deck {

        private final int x;
        private final int y;
        private final int absoluteCoordinates;
        private Ship ship;
        private boolean live;

        Deck(final int X, final int Y) {

            x = X;
            y = Y;
            absoluteCoordinates = getAbsoluteCoordinates(x, y);
            live = true;
        }

        public static int getAbsoluteCoordinates(int x, int y) { return Arrays.hashCode(new int[] {x, y}); }

        public int getX() { return x; }

        public int getY() { return y; }

        public int getAbsoluteCoordinates() { return absoluteCoordinates; }

        public Ship getShip() { return ship; }

        public void destroy() { live = false; }
    }
}