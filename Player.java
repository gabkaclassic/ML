import java.util.*;

public class Player extends Entrant {

    private static Scanner scanner;

    public Player(final String name) {

        super(name);

        scanner = new Scanner(System.in);
        live = true;
    }

    protected boolean getVertical(final int d) {

        System.out.println("How do you want to put a " + d + "-deck ship? \n");

        return scanner.nextLine().toLowerCase().equals("vertically");
    }

    protected int[] getCoordinates(final boolean v, final int c) {

        int[] arr = new int[4];

        if(c > 1) {

            if (v) {

                System.out.print("Enter X: ");
                arr[0] = arr[1] = scanner.nextInt();
                System.out.print("Enter Y1: ");
                arr[2] = scanner.nextInt();
                System.out.print("Enter Y2: ");
                arr[3] = scanner.nextInt();
            } else {

                System.out.print("Enter X1: ");
                arr[0] = scanner.nextInt();
                System.out.print("Enter X2: ");
                arr[1] = scanner.nextInt();
                System.out.print("Enter Y: ");
                arr[2] = arr[3] = scanner.nextInt();
            }
        }

        else {

            System.out.print("Enter X: ");
            arr[0] = arr[1] = scanner.nextInt();
            System.out.print("Enter Y: ");
            arr[2] = arr[3] = scanner.nextInt();
        }

        return arr;
    }

    protected int[] getAnswers() {

        int[] arr = new int[2];

        System.out.println("Enter X: ");
        arr[0] = scanner.nextInt();
        System.out.println("Enter Y: ");
        arr[1] = scanner.nextInt();

        return arr;
    }

}