import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RemoveWords {
 public static void main(String[] args) {

        try {
            File myObj = new File("articles\\BO7\\BO71.txt");
            Scanner myReader = new Scanner(myObj);

            // Use a StringBuilder to efficiently combine all lines
            StringBuilder article = new StringBuilder();

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                article.append(data).append(" ");
            }

            myReader.close();

            // Convert to a single string
            String fullArticle = article.toString();
           
            // Replace punctuation
            fullArticle = fullArticle.replaceAll("[^a-zA-Z0-9\\s]", "");
            String[] words = fullArticle.split("\\s+");
            ArrayList<String> wordList = new ArrayList<>(Arrays.asList(words));
            System.out.println("Words: " + wordList);

            //Remove stop words
            

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}