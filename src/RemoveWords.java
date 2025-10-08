import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RemoveWords {
 public static void main(String[] args) {

        try {
            File myObj = new File("C:/Users/jrad2/school/jworkshop/SemesterProject/articles/BO7/BO71.txt");
            Scanner myReader = new Scanner(myObj);

            // Use a StringBuilder to efficiently combine all lines
            StringBuilder article = new StringBuilder();

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                article.append(data).append(" ");
            }

            myReader.close();

            // Convert to a single string
            String BO71 = article.toString();
           
            // Replace punctuation
            BO71 = BO71.replaceAll("[^a-zA-Z0-9\\s]", "");
            String[] words = BO71.split("\\s+");
            ArrayList<String> wordList = new ArrayList<>(Arrays.asList(words));
            System.out.println("Words: " + wordList);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}