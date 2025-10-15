import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RemoveWords {
    public static void main(String[] args) {

        // All files that will be processed
        String[] articlePaths = {
            "articles\\BO7\\BO71.txt",
            "articles\\BO7\\BO72.txt",
            "articles\\BO7\\BO73.txt"
        };

        // Read the stopwords
        ArrayList<String> stopWords = new ArrayList<>();
        try {
            File stopWordsFile = new File("resources\\stopwords.txt");
            Scanner stopReader = new Scanner(stopWordsFile);
            while (stopReader.hasNextLine()) {
                stopWords.add(stopReader.nextLine().trim().toLowerCase());
            }
            stopReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Stopwords file not found!");
            e.printStackTrace();
            return;
        }

        // Combine all articles into one StringBuilder
        StringBuilder combinedArticles = new StringBuilder();

        for (String path : articlePaths) {
            try {
                File file = new File(path);
                Scanner reader = new Scanner(file);
                while (reader.hasNextLine()) {
                    combinedArticles.append(reader.nextLine()).append(" ");
                }
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("Error: Could not read " + path);
                e.printStackTrace();
            }
        }

        // Convert to string
        String allText = combinedArticles.toString();

        // Remove punctuation and lowercase
        allText = allText.replaceAll("[^a-zA-Z0-9\\s]", "").toLowerCase();

        // Split into words
        String[] words = allText.split("\\s+");

        // Remove stop words
        ArrayList<String> cleanWords = new ArrayList<>();
        for (String word : words) {
            if (!stopWords.contains(word)) {
                cleanWords.add(word);
            }
        }

        // Print output
        System.out.println(cleanWords);
    }
}