import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);
    Settings settings = new Settings();

    public static void main(String[] args) throws FileNotFoundException {
        Game game=new Game();
        game.generateGameMemory();

    }

    public void generateGameMemory() throws FileNotFoundException {
        while (true) {
            System.out.println("Press n new game, q exit");
            String nq = scanner.nextLine();
            if (nq.equals("q")) {
                System.out.println("The game ended...");
                break;
            } else if (nq.equals("n")) {
                settings.gameMenu();

                settings.printBoard();
                settings.checkInput();
                break;
            } else {
                System.out.println("Wrong sign");
            }

        }
    }

}
