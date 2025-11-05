import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(" Loading stop words...");
            ArrayList<String> stopWords = RemoveWords.loadStopWords("resources/stopwords.txt");

            System.out.println(" Processing all topic folders...");
            Map<String, ArrayList<String>> topicData = RemoveWords.processAllTopics("articles", stopWords);

            System.out.println(" Analyzing results...");
            TextAnalyzer.analyzeTopics(topicData);

            System.out.println(" Loading sentiment lexicon...");
            Map<String, Double> lexicon = SentimentAnalyzer.loadLexicon("resources/lexiconscores.txt");

            System.out.println(" Calculating sentiment...");
            Map<String, Double> sentimentScores = SentimentAnalyzer.computeSentimentScores(topicData, lexicon);
            SentimentAnalyzer.displaySentimentResults(sentimentScores);
            
            System.out.println(" Done! All topics processed successfully.");

        } catch (Exception e) {
            System.out.println(" Error while running analysis:");
            e.printStackTrace();
        }
    }
}
