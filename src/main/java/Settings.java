import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Settings {

    private final FileManager fileManager = new FileManager();
    int chances;
    int userLevel = 0;
    Level userEasyOrHard = Level.EASY;
    public LocalDateTime date;
    public static String[][] cards = new String[10][10];
    public static String[][] board = new String[10][10];

    public Settings() {

    }

    public void printBoard() {

        System.out.println(" —-----------------------------------");
        System.out.println("       Level: " + userEasyOrHard);
        System.out.println("       Guess chances: " + chances);
        System.out.print("     ");
        for (int i = 0; i < userLevel; i++) {

            System.out.print(i + 1 + " | ");
        }
        System.out.println();
        for (int i = 0; i < 2; i++) {

            if (i == 0) System.out.print("A ");
            else System.out.print("B ");
            System.out.print(" | ");
            for (int j = 0; j < userLevel; j++) {
                System.out.print(board[i][j]);
                System.out.print(" | ");
            }
            System.out.println();

        }
        System.out.println(" —-----------------------------------");
    }


    public void gameMenu() throws FileNotFoundException {

        System.out.println("Welcome to the memory game\n");


        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose level");
        System.out.println("1. EASY");
        System.out.println("2. HARD");
        int numb = 0;
        try {
            numb = scanner.nextInt();

        } catch (InputMismatchException exception) {
            System.out.println("Enter the number 1 or 2");
        }
        if (numb == 1) {

            userLevel = 4;
            userEasyOrHard = Level.EASY;
            generateMemory(userEasyOrHard);

        }
        if (numb == 2) {

            userLevel = 8;
            userEasyOrHard = Level.HARD;
            generateMemory(userEasyOrHard);

        }


    }

    public void generateMemory(Level level) throws FileNotFoundException {

        for (int i = 0; i < userLevel; i++) {
            for (int j = 0; j < userLevel; j++) {
                board[i][j] = "X";
            }
        }
        if (level.equals(Level.EASY)) {
            chances = 10;
            List<Card> cardList = fileManager.generateWordsToCard(4);
            namesToStringTab(cardList);
        } else if (level.equals(Level.HARD)) {
            List<Card> cardList = fileManager.generateWordsToCard(8);
            namesToStringTab(cardList);
            chances = 15;
        } else {
            System.out.println("Error, something went wrong :/ ");
        }
    }

    private void namesToStringTab(List<Card> cardList) {

        for (int i = 0; i < cardList.size(); i++) {

            cards[0][i] = cardList.get(i).toString();


        }
        int p = cardList.size() - 1;
        for (int i = 0; i < cardList.size(); i++) {
            cards[1][i] = cardList.get(p).toString();
            p -= 1;
        }

    }


    public void checkInput() throws FileNotFoundException {
        date = LocalDateTime.now();
        int secondsStartGame = date.toLocalTime().toSecondOfDay();

        Scanner scanner = new Scanner(System.in);
        int correctWords = 0;
        while (true) {
            if (!gameOver()) {
                System.out.println("Enter coordinates ex. A1");

                String string = scanner.nextLine();

                String[] parts = string.split("");
                boolean badError = true;
                while (badError) {
                    if (stringToNumber(parts[0]) == 99 || Integer.parseInt(parts[1]) > userLevel || Integer.parseInt(parts[1]) < 0) {
                        System.out.println("Wrong format, re-enter data: ");
                        string = scanner.nextLine();
                        parts = string.split("");
                    } else badError = false;
                }
                int row1 = stringToNumber(parts[0]);
                int ab1 = Integer.parseInt(parts[1]);
                if (!board[row1 - 1][ab1 - 1].equals("X")) {
                    System.out.println("Already introduced!!");
                    System.out.println();
                    board[row1 - 1][ab1 - 1] = " " + cards[row1 - 1][ab1 - 1] + " ";
                    printBoard();
                    continue;
                } else {
                    board[row1 - 1][ab1 - 1] = " " + cards[row1 - 1][ab1 - 1] + " ";
                    printBoard();
                }

                System.out.println("Enter second coordinates ex. B1");

                String string2 = scanner.nextLine();

                String[] parts2 = string2.split("");
                boolean badError2 = true;
                while (badError2) {
                    if (stringToNumber(parts2[0]) == 99 || Integer.parseInt(parts2[1]) > userLevel || Integer.parseInt(parts2[1]) < 0) {
                        System.out.println("Wrong format, re-enter data: ");
                        string2 = scanner.nextLine();
                        parts2 = string2.split("");
                    } else badError2 = false;
                }

                int row2 = stringToNumber(parts2[0]);
                int ab2 = Integer.parseInt(parts2[1]);






                if (!board[row2 - 1][ab2 - 1].equals("X")) {
                    System.out.println("Already introduced!!");
                    board[row2 - 1][ab2 - 1] = "X";
                    //  board[row1 - 1][ab1 - 1] = " " + cards[row1 - 1][ab1 - 1] + " ";
                    System.out.println();

                    printBoard();
                } else {
                    board[row2 - 1][ab2 - 1] = " " + cards[row2 - 1][ab2 - 1] + " ";
                    System.out.println();
                    printBoard();

                    if (board[row1 - 1][ab1 - 1].equals(board[row2 - 1][ab2 - 1])) {
                        System.out.println();
                        printBoard();
                        System.out.println("Correct!!\n");
                        correctWords += 1;
                        if (correctWords == userLevel) {
                            LocalDateTime dateEndGame = LocalDateTime.now();
                            int secondsWinGame = dateEndGame.toLocalTime().toSecondOfDay() - secondsStartGame;

                            System.out.println("!! Win !!");
                            System.out.println("Chances remaining: " + chances);
                            System.out.println("Your playing time is: " + secondsWinGame + " Seconds");
                            System.out.println("Do you want to play again?");
                            System.out.println("1 - Yes");
                            System.out.println("2 - No");

                            if (scanner.nextInt() == 1) {

                                Game.main(new String[]{"start"});
                            }
                            break;
                        }

                    } else {
                        printBoard();
                        chances -= 1;
                        if (chances == 0) {
                            System.out.println("You've lost! You've lost all your chances");
                            System.out.println("Do you want to play again?");
                            System.out.println("1 - Yes");
                            System.out.println("2 - No");

                            if (scanner.nextInt() == 1) {
                                Game.main(new String[]{"start"});
                            }
                            break;
                        }
                        System.out.println("False!\n");
                        board[row1 - 1][ab1 - 1] = "X";
                        board[row2 - 1][ab2 - 1] = "X";
                        System.out.println();
                        printBoard();
                    }
                }

            } else {
                System.out.println("Game Over");
                break;
            }

        }
    }

    private int stringToNumber(String text) {

        return switch (text) {
            case "A" -> 1;
            case "B" -> 2;
            default -> 99;
        };
    }

    public boolean gameOver() {
        for (int i = 0; i < userLevel; i++) {
            for (int j = 0; j < userLevel; j++) {
                if (board[i][j].equals("X")) {
                    return false;
                }
            }

        }
        return true;
    }

}
