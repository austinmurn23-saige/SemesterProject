import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SentimentAnalyzer {

    // Load lexiconscores.txt: word -> sentiment value
    public static Map<String, Double> loadLexicon(String filePath) throws FileNotFoundException {
    Map<String, Double> lexicon = new HashMap<>();

    File file = new File(filePath);
    Scanner reader = new Scanner(file);

    while (reader.hasNextLine()) {
        String line = reader.nextLine().trim();
        if (line.isEmpty()) continue;

        // Allows space OR tab separated lexicon files
        String[] parts = line.split("\\s+");

        if (parts.length >= 2) {
            String word = parts[0]
                    .toLowerCase()
                    .replaceAll("[^a-zA-Z0-9\\s]", "");

            try {
                double score = Double.parseDouble(parts[1]);
                if (!word.isEmpty()) {
                    lexicon.put(word, score);
                }
            } catch (NumberFormatException e) {
                // Skip rows that don't contain valid numeric scores
                continue;
            }
        }
    }

    reader.close();
    return lexicon;
}

    // Compute sentiment score per topic
    public static Map<String, Double> computeSentimentScores(
            Map<String, ArrayList<String>> topicWords,
            Map<String, Double> lexiconScores) {

        Map<String, Double> topicSentimentTotals = new HashMap<>();

        for (String topic : topicWords.keySet()) {
            double totalScore = 0.0;

            for (String word : topicWords.get(topic)) {
                if (lexiconScores.containsKey(word)) {
                    totalScore += lexiconScores.get(word);
                }
            }

            topicSentimentTotals.put(topic, totalScore);
        }

        return topicSentimentTotals;
    }

    // Display results
    public static void displaySentimentResults(Map<String, Double> sentimentScores) {
    System.out.println("\n--- Sentiment Analysis Results ---");

    for (String topic : sentimentScores.keySet()) {
        double score = sentimentScores.get(topic);

        // Format score to 2 decimal places
        String formatted = String.format("%.2f", score);

        System.out.println("\nTopic: " + topic);
        System.out.println("Total Sentiment Score: " + formatted);

        if (score > 0) {
            System.out.println("Overall Sentiment: POSITIVE (Score: " + formatted + ")");
        } else if (score < 0) {
            System.out.println("Overall Sentiment: NEGATIVE (Score: " + formatted + ")");
        } else {
            System.out.println("Overall Sentiment: NEUTRAL (Score: " + formatted + ")");
        }
    }
}
}
