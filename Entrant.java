import java.util.*;

public abstract class Entrant {

    protected static final int LOW_BORDER = Ship.LOW_BORDER;
    protected static final int HIGH_BORDER = Ship.HIGH_BORDER;
    protected final String name;

    protected static Random random;

    protected boolean live;

    protected Map<Integer, Ship.Deck> field;
    protected Map<Integer, Ship.Deck> enemyField;
    protected Queue<Integer> moves;

    Entrant(String name) {

        field = new HashMap<>(20);
        moves = new ArrayDeque<>(100);
        this.name = name;
        random = new Random();
    }

    public void move() {

        int[] answers;
        int x;
        int y;

        while(true) {

            answers = getAnswers();

            x = answers[0];
            y = answers[1];

            if(checkAnswer(x, y)) {
                System.out.printf("%s do shot: X- %d Y- %d \n", name, x, y);
                break;
            }
            else System.out.println("Selected the wrong coordinates!");
        }

        Ship.Deck target = enemyField.get(Ship.Deck.getAbsoluteCoordinates(x, y));

        if(target != null) {

            System.out.printf("%s got! \n", name);
            target.destroy();

            if(target.getShip().getLive()) setLuckyShots(x, y);
            else {
                System.out.printf("%s killed %d-decks! \n", name, target.getShip().getCountDecks());
                wrapped();
                setLuckyShots(0, 0);
            }
        }
    }

    protected void setLuckyShots(int x, int y) {}

    public Map<Integer, Ship.Deck> allocation() {

        int x1;
        int x2;
        int y1;
        int y2;

        List<Ship.Deck> decks;

        for(int d = 4; d > 0; d--) {

            decks = new ArrayList<>(d);

            if(d > 1) {

                for (int c = 0; c < (5 - d); c++) {

                    boolean vertical = getVertical(d);

                    if (vertical) {

                        while (true) {

                            int[] coordinates = getCoordinates(vertical, d);

                            x1 = coordinates[0];
                            x2 = coordinates[1];
                            y1 = coordinates[2];
                            y2 = coordinates[3];

                            if(checkCord(x1, x2, y1, y2, vertical, d)) break;
                            else System.out.printf("Wrong coordinates: %d, %d, %d, %d : %d \n", x1, x2, y1, y2, d);

                            vertical = getVertical(d);
                        }

                        int step = (y1 < y2) ? 1 : (-1);

                        for(int i = y1; i != (y2 + step); i += step) {

                            Ship.Deck deck = new Ship.Deck(x1, i);
                            decks.add(deck);
                            field.put(deck.getAbsoluteCoordinates(), deck);
                        }

                    }
                    else {

                        while (true) {

                            int[] coordinates = getCoordinates(vertical, d);

                            x1 = coordinates[0];
                            x2 = coordinates[1];
                            y1 = coordinates[2];
                            y2 = coordinates[3];

                            if(checkCord(x1, x2, y1, y2, vertical, d)) break;
                            else System.out.printf("Wrong coordinates: %d, %d, %d, %d : %d \n", x1, x2, y1, y2, d);

                            vertical = getVertical(d);
                        }

                        int step = (x1 < x2) ? 1 : (-1);

                        for(int i = x1; i != (x2 + step); i += step) {

                            Ship.Deck deck = new Ship.Deck(i, y1);
                            decks.add(deck);
                            field.put(deck.getAbsoluteCoordinates(), deck);
                        }

                    }
                    System.out.printf("COORDINATES: %d, %d, %d, %d \n", x1, x2, y1, y2);
                }

            }
            else {

                for (int i = 0; i < 4; i++) {
                    while (true) {

                        int[] coordinates = getCoordinates(false, d);

                        x1 = coordinates[0];
                        x2 = coordinates[1];
                        y1 = coordinates[2];
                        y2 = coordinates[3];

                        if (checkCord(x1, y1)) break;
                        else System.out.printf("Wrong coordinates: %d, %d, %d, %d : %d \n", x1, x2, y1, y2, d);
                    }

                    Ship.Deck deck = new Ship.Deck(x1, y1);
                    decks.add(deck);
                    field.put(deck.getAbsoluteCoordinates(), deck);

                    System.out.printf("COORDINATES: %d, %d, %d, %d \n", x1, x2, y1, y2);
                }

            }

            new Ship(decks);
        }

        return field;
    }

    protected abstract boolean getVertical(int d);

    protected abstract int[] getCoordinates(final boolean v, final int c);

    protected abstract int[] getAnswers();

    public Map<Integer, Ship.Deck> getEnemyField() { return enemyField; }

    public void setEnemyField(final Map<Integer, Ship.Deck> enemyField) { this.enemyField = enemyField; }

    public String getName() { return name; }

    protected boolean checkAnswer(final int answerX, final int answerY) {

        return (answerX < HIGH_BORDER) && (answerX > LOW_BORDER)
               && (answerY < HIGH_BORDER) && (answerY > LOW_BORDER)
               && (!moves.contains(Ship.Deck.getAbsoluteCoordinates(answerX, answerY)));
    }

    private void wrapped() {

        field.values().stream()
                .filter(deck -> (deck.getShip().getLive()))
                .map(deck -> {
            for(int i = (deck.getX() - 1); i <= (deck.getX() + 1); i++)
                for(int j = (deck.getY() - 1); j <= (deck.getY() + 1); j++)
                    moves.add(Ship.Deck.getAbsoluteCoordinates(i, j));
            return null;
        }).findFirst();
    }

    protected boolean checkCord(final int a, final int b) {

        for(int x = (a - 1); x < (a + 2); x++)
            for(int y = (b - 1); y < (b + 2); y++)
                if(field.containsKey(Ship.Deck.getAbsoluteCoordinates(x, y))) return false;

        return (a < HIGH_BORDER) && (a > LOW_BORDER)
                && (b < HIGH_BORDER) && (b > LOW_BORDER);
    }

    protected boolean checkCord(int x1, int x2, int y1, int y2, final boolean v, final int r) {

       if((v && (Math.abs(y1 - y2) != (r - 1))) || (!v && (Math.abs(x1 - x2) != (r - 1))))  return false;

       if(v) {

           if(y1 > y2) {
               y1 += y2;
               y2 = (y1 - y2);
               y1 -= y2;
           }

           for(int i = (y1 - 1); i < (y2 + 2); i++)
               for(int j = (x1 - 1); j < (x1 + 2); j++)
                   if(field.containsKey(Ship.Deck.getAbsoluteCoordinates(j, i))) return false;
       }
       else {

           if(x1 > x2) {
               x1 += x2;
               x2 = (x1 - x2);
               x1 -= x2;
           }

           for(int i = (x1 - 1); i < (x2 + 2); i++)
               for(int j = (y1 - 1); j < (y1 + 2); j++)
                   if(field.containsKey(Ship.Deck.getAbsoluteCoordinates(i, j))) return false;
       }

        return (x1 < HIGH_BORDER) && (x1 > LOW_BORDER)
                && (x2 < HIGH_BORDER) && (x2 > LOW_BORDER)
                && (y1 < HIGH_BORDER) && (y1 > LOW_BORDER)
                && (y2 < HIGH_BORDER) && (y2 > LOW_BORDER);
    }
}