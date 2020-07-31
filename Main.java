import java.io.PrintStream;
import java.util.*;

public class Main {

    private static final String DEFAULT_NAME_PLAYER = "Player";
    private static final String DEFAULT_NAME_COMPUTER = "Computer";

    private static final Scanner scanner = new Scanner(System.in);
    
    private static Entrant player;
    private static Entrant computer;

    private static boolean nextGame = true;

    private Main() { this(DEFAULT_NAME_PLAYER, DEFAULT_NAME_COMPUTER); }

    private Main(String namePlayer, String nameComputer) {

        player = new Player(namePlayer);
        computer = new Computer(nameComputer);

        PrintStream output = System.out;
       // System.setOut(new PrintStream(OutputStream.nullOutputStream()));
        player.setEnemyField(computer.allocation());
        System.setOut(output);
        computer.setEnemyField(player.allocation());
    }

    public static void main(String[] args) {

        String namePlayer;
        String nameComputer;

        System.out.println("Yo-ho-hoo! You are a pirate!");
        System.out.println("How are you? ");
        namePlayer = scanner.nextLine();
        System.out.println("How are your enemy? ");
        nameComputer = scanner.nextLine();

        if((namePlayer == null) || (nameComputer == null)) while(nextGame) new Main().start();
        else while(nextGame) new Main(namePlayer, nameComputer).start();

        System.out.println("Nice to see you, play with you again later!");
    }

    private void start() {

        Entrant currentEntrant;

        while(true) {

            System.out.println(player.getName() + "'s move");
            currentEntrant = move(player);
            if(currentEntrant != null) break;

            System.out.println(computer.getName() + "'s move");
            currentEntrant = move(computer);
            if(currentEntrant != null) break;
        }

        System.out.println(currentEntrant.getName() + " win!");

        System.out.println("Good brawl! Play again? ");

        nextGame = new Scanner(System.in).nextLine().toLowerCase().equals("yes");
    }

    private Entrant move(final Entrant fighter) {

        int enemyLivesBeforeMove;

        do {

            enemyLivesBeforeMove = fighter.getEnemyField().size();
            fighter.move();
            if(fighter.getEnemyField().isEmpty()) return fighter;

        } while(enemyLivesBeforeMove >= fighter.getEnemyField().size());

        return null;
    }

}