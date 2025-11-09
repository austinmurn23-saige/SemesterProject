import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class VocabRichness {

    // Method to calculate vocabulary richness
    public static double calculateRichness(ArrayList<String> words) {
        int totalWords = words.size();
        HashSet<String> uniqueWordsSet = new HashSet<>(words);
        int uniqueWords = uniqueWordsSet.size();

        if (totalWords == 0) {
            return 0.0;
        }

        // Calculate vocabulary richness as the ratio of unique words to total words
        return (double) uniqueWords / totalWords;
    }

    // Method to analyze and display vocab richness for each topic
    public static void analyzeVocabRichness(Map<String, ArrayList<String>> topicData) {
        for (String topic : topicData.keySet()) {
            ArrayList<String> words = topicData.get(topic);

            // Calculate vocabulary richness
            double richness = calculateRichness(words);

            // Output results
            System.out.println("======================================");
            System.out.println(" Topic: " + topic);
            System.out.println("Vocabulary Richness: " + richness);
            System.out.println("======================================\n");
        }
    }
}
