import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RemoveWords {
    public static void main(String[] args) {

        try {
            // Read the article
            // To change the article it converts, you only need to change the path directly below this line
            File myObj = new File("articles\\BO7\\BO71.txt");
            Scanner myReader = new Scanner(myObj);

            StringBuilder article = new StringBuilder();
            while (myReader.hasNextLine()) {
                article.append(myReader.nextLine()).append(" ");
            }
            myReader.close();

            String fullArticle = article.toString();

            // Read the stopwords
            File stopWordsFile = new File("resources\\stopwords.txt");
            Scanner thisReader = new Scanner(stopWordsFile);
            ArrayList<String> stopWords = new ArrayList<>();

            while (thisReader.hasNextLine()) {
                stopWords.add(thisReader.nextLine().trim().toLowerCase());
            }
            thisReader.close();

            // Remove punctuation
            fullArticle = fullArticle.replaceAll("[^a-zA-Z0-9\\s]", "").toLowerCase();

            // Split into words
            String[] words = fullArticle.split("\\s+");

            // Remove stop words
            ArrayList<String> cleanArticle = new ArrayList<>();
            for (String word : words) {
                if (!stopWords.contains(word)) {
                    cleanArticle.add(word);
                }
            }

            System.out.println(cleanArticle);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}