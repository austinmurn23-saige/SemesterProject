
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RemoveWords {

    // Load stop words from file and return them as a list
    public static ArrayList<String> loadStopWords(String stopWordsPath) throws FileNotFoundException {
        ArrayList<String> stopWords = new ArrayList<>();
        File stopWordsFile = new File(stopWordsPath);
        Scanner stopReader = new Scanner(stopWordsFile);
        while (stopReader.hasNextLine()) {
            stopWords.add(stopReader.nextLine().trim().toLowerCase());
        }
        stopReader.close();
        return stopWords;
    }

    // Process ALL topic folders inside /articles/ better system for all topics
    public static Map<String, ArrayList<String>> processAllTopics(String baseFolderPath, ArrayList<String> stopWords)
            throws FileNotFoundException {

        Map<String, ArrayList<String>> topicResults = new HashMap<>();

        File baseFolder = new File(baseFolderPath);
        File[] topicFolders = baseFolder.listFiles(File::isDirectory);

        if (topicFolders == null) {
            System.out.println(" No topic folders found in: " + baseFolderPath);
            return topicResults;
        }

        for (File topic : topicFolders) {
            File[] articleFiles = topic.listFiles((dir, name) -> name.endsWith(".txt"));
            if (articleFiles == null) continue;

            // Combine all article text for this topic
            StringBuilder combinedArticles = new StringBuilder();
            for (File article : articleFiles) {
                Scanner reader = new Scanner(article);
                while (reader.hasNextLine()) {
                    combinedArticles.append(reader.nextLine()).append(" ");
                }
                reader.close();
            }

            // Clean text (remove punctuation, lowercase, split)
            String allText = combinedArticles.toString()
                    .replaceAll("[^a-zA-Z0-9\\s]", "")
                    .toLowerCase();

            String[] words = allText.split("\\s+");

            // Remove stop words
            ArrayList<String> cleanWords = new ArrayList<>();
            for (String word : words) {
                if (!stopWords.contains(word) && !word.isEmpty()) {
                    cleanWords.add(word);
                }
            }

            topicResults.put(topic.getName(), cleanWords);
        }

        return topicResults;
    }
}