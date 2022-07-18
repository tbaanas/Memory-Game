import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class FileManager {

    public List<Card> cardList = new LinkedList<>();
    public List<String> cardNames;

    public FileManager() {

    }


    private List<String> loadWordsToMap() throws FileNotFoundException {

        File file = new File("C:\\Users\\Tomek\\Desktop\\zadanie-REKRUT\\Words.txt");
        Scanner sc = new Scanner(file);
        cardNames = new ArrayList<>();
        while (sc.hasNextLine()) {
            cardNames.add(sc.nextLine());

        }
        return cardNames;
    }


    public List<Card> generateWordsToCard(int numb) throws FileNotFoundException {


        for (int i = 0; i < numb; i++) {
            Card card = new Card();
            card.setId(i);
            card.setName(loadWordsToMap().get(getRandomNumber()));
            cardList.add(card);

        }
        return cardList;
    }

    private int getRandomNumber() throws FileNotFoundException {
        return ThreadLocalRandom.current().nextInt(0, loadWordsToMap().size() - 1);
    }


}
