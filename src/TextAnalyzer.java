import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class TextAnalyzer {

    // Analyze and print statistics for each topic
    public static void analyzeTopics(Map<String, ArrayList<String>> topicData) {
        for (String topic : topicData.keySet()) {
            ArrayList<String> words = topicData.get(topic);

            // Word counts
            int totalWords = words.size();
            int uniqueWords = new HashSet<>(words).size();

            // Frequency map so it can run through all topics in articles and be organized
            Map<String, Integer> freq = new HashMap<>();
            for (String w : words) {
                freq.put(w, freq.getOrDefault(w, 0) + 1);
            }

            // Sort by frequency
            List<Map.Entry<String, Integer>> sorted = new ArrayList<>(freq.entrySet());
            sorted.sort((a, b) -> b.getValue().compareTo(a.getValue()));

            // Print results
            System.out.println("======================================");
            System.out.println(" Topic: " + topic);
            System.out.println("Total words: " + totalWords);
            System.out.println("Unique words: " + uniqueWords);
            System.out.println("Top 10 most frequent words:");
            for (int i = 0; i < Math.min(10, sorted.size()); i++) {
                System.out.println("   " + sorted.get(i).getKey() + " = " + sorted.get(i).getValue());
            }
            System.out.println("======================================\n");
        }
    }
}