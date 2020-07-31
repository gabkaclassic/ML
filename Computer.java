import java.util.*;

public class Computer extends Entrant {

    private Deque<int[]> shots;
    private Deque<int[]> luckyShots;
    private boolean verticalTarget;

    public Computer(final String name) {

        super(name);

        live = false;
        shots = getShots();
        luckyShots = new LinkedList<>();
    }

    public void setLuckyShots(final int x, final int y) {

        if(x == LOW_BORDER) {
            luckyShots.clear();
            return;
        }

        luckyShots.add(new int[]{x, y});

        if(luckyShots.size() == 2) analysis();

        else if(luckyShots.size() == 1) {

            shots.offerFirst(new int[] {(x - 1), y});
            shots.offerFirst(new int[] {(x + 1), y});
            shots.offerFirst(new int[] {x, (y - 1)});
            shots.offerFirst(new int[] {x, (y + 1)});
        }

        else if(luckyShots.size() > 2) {

            int[] arr1 = luckyShots.peekFirst();
            int[] arr2 = luckyShots.peekLast();
            int a1;
            int a2;
            int b;

            if(verticalTarget) {

                a1 = arr1[1];
                a2 = arr2[1];
                b = arr1[0];

                shots.offerFirst(new int[] {b, (a1 - 1)});
                shots.offerFirst(new int[] {b, (a1 + 1)});
                shots.offerFirst(new int[] {b, (a2 - 1)});
                shots.offerFirst(new int[] {b, (a2 + 1)});
            }
            else {

                    a1 = arr1[0];
                    a2 = arr2[0];
                    b = arr1[1];

                    shots.offerFirst(new int[] {(a1 - 1), b});
                    shots.offerFirst(new int[] {(a1 + 1), b});
                    shots.offerFirst(new int[] {(a2 - 1), b});
                    shots.offerFirst(new int[] {(a2 + 1), b});
            }
        }
    }

    private void analysis() {

            int[] arr1 = luckyShots.peekFirst();
            int[] arr2 = luckyShots.peekLast();
            verticalTarget = (arr1[0] == arr2[0]);
    }

    protected boolean getVertical(final int d) { return random.nextBoolean(); }

    protected int[] getCoordinates(final boolean v, final int d) {

        int[] arr = new int[4];

        if(d > 1) {

            if(d == 4) {

                if(v) {
                    arr[0] = arr[1] = (random.nextBoolean()) ? 1 : 10;
                    arr[2] = (random.nextBoolean()) ? 1 : 7;
                    arr[3] = arr[2] + 3;
                }
                else {
                    arr[2] = arr[3] = (random.nextBoolean()) ? 1 : 10;
                    arr[0] = (random.nextBoolean()) ? 1 : 7;
                    arr[1] = arr[0] + 3;
                }
            }
            else if(d == 3) {

                if(v) {
                    arr[0] = arr[1] = (random.nextBoolean()) ? 1 : 10;
                    arr[2] = 6 + random.nextInt(2);
                    arr[3] = arr[2] + 2;
                }
                else {
                    arr[2] = arr[3] = (random.nextBoolean()) ? 1 : 10;
                    arr[0] = 6 + random.nextInt(2);
                    arr[1] = arr[0] + 2;
                }
            }
            else if(d == 2) {

                if(v) {
                    arr[0] = arr[1] = (random.nextBoolean()) ? 1 : 10;
                    arr[2] = random.nextInt(8);
                    arr[3] = arr[2] + 1;
                }
                else {
                    arr[2] = arr[3] = (random.nextBoolean()) ? 1 : 10;
                    arr[0] = random.nextInt(8);
                    arr[1] = arr[0] + 1;
                }
            }
        }
        else {

            do {
                arr[0] = arr[1] = 3 + random.nextInt(5);
                arr[2] = arr[3] = 3 + random.nextInt(5);
            } while(field.containsKey(Ship.Deck.getAbsoluteCoordinates(arr[0], arr[2])));

        }

        return arr;
    }

    protected int[] getAnswers() { return shots.pollFirst(); }

    private Deque<int[]> getShots() {

        Deque<int[]> list = new LinkedList<>();

        int x;
        int y;
        int absStep = 4;
        int step = 4;

        while(absStep > 0) {

            do {

                step += 4;
                x = (LOW_BORDER + 1);
                y = (step > HIGH_BORDER) ? (HIGH_BORDER - 1) : step;

                for(;(x < HIGH_BORDER) && (y < HIGH_BORDER) && (x > LOW_BORDER) && (y > LOW_BORDER); x++, y--)
                    list.add(new int[]{x, y});

            } while(step < HIGH_BORDER);

            step = (absStep /= 2);
        }

        return list;
    }
}
