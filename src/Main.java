import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            // Load stop words
            System.out.println("Loading stop words...");
            ArrayList<String> stopWords = RemoveWords.loadStopWords("resources/stopwords.txt");

            // Process all topic folders
            System.out.println("Processing all topic folders...");
            Map<String, ArrayList<String>> topicData = RemoveWords.processAllTopics("articles", stopWords);

            // Analyze Word Frequency
            System.out.println("Analyzing word frequency...");
            TextAnalyzer.analyzeTopics(topicData);

            // Analyze Vocabulary Richness
            System.out.println("Analyzing vocabulary richness...");
            VocabRichness.analyzeVocabRichness(topicData);

            // Analyze sentiment
            System.out.println("Loading sentiment lexicon...");
            Map<String, Double> lexicon = SentimentAnalyzer.loadLexicon("resources/lexiconscores.txt");

            System.out.println("Calculating sentiment...");
            Map<String, Double> sentimentScores = SentimentAnalyzer.computeSentimentScores(topicData, lexicon);
            SentimentAnalyzer.displaySentimentResults(sentimentScores);

            System.out.println("Done! All topics processed successfully.");

        } catch (Exception e) {
            System.out.println("Error while running analysis:");
            e.printStackTrace();
        }
    }
}
