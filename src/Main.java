import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class
Main {

    private static final String ARTICLES_DIR = "articles";

    public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {

            System.out.println("Loading system resources...");

            ArrayList<String> stopWords =
                    RemoveWords.loadStopWords("resources/stopwords.txt");

            Map<String, Double> lexicon =
                    SentimentAnalyzer.loadLexicon("resources/lexiconscores.txt");

            boolean running = true;

            while (running) {

                Map<String, ArrayList<String>> allTopicsData =
                        RemoveWords.processAllTopics(ARTICLES_DIR, stopWords);

                if (allTopicsData.isEmpty()) {
                    System.out.println("No topics found in /articles directory.");
                }

                System.out.println("\n=========================================");
                System.out.println("        TEXT ANALYSIS DASHBOARD");
                System.out.println("=========================================");
                System.out.println("Available Topics:");

                ArrayList<String> topicNames =
                        new ArrayList<>(allTopicsData.keySet());

                for (int i = 0; i < topicNames.size(); i++) {
                    System.out.println("  [" + (i + 1) + "] " + topicNames.get(i));
                }

                System.out.println("\nOptions:");
                System.out.println("  [A] Add new article");
                System.out.println("  [0] Exit");

                System.out.print("\nEnter selection: ");
                String choice = input.nextLine().trim();

                if (choice.equals("0")) {
                    running = false;
                    System.out.println("Exiting...");
                    continue;
                }

                if (choice.equalsIgnoreCase("A")) {
                    addNewArticle(input);
                    continue;
                }

                int index;
                try {
                    index = Integer.parseInt(choice) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.");
                    continue;
                }

                if (index < 0 || index >= topicNames.size()) {
                    System.out.println("Invalid selection.");
                    continue;
                }

                String selectedTopic = topicNames.get(index);

                Map<String, ArrayList<String>> selectedTopicData = new HashMap<>();
                selectedTopicData.put(
                        selectedTopic,
                        allTopicsData.get(selectedTopic)
                );

                System.out.println("\n>>> ANALYZING TOPIC: " + selectedTopic + " <<<\n");

                TextAnalyzer.analyzeTopics(selectedTopicData);
                VocabRichness.analyzeVocabRichness(selectedTopicData);

                Map<String, Double> sentimentScores =
                        SentimentAnalyzer.computeSentimentScores(
                                selectedTopicData, lexicon
                        );

                SentimentAnalyzer.displaySentimentResults(sentimentScores);

                System.out.println("\nPress Enter to return to menu...");
                input.nextLine();
            }

        } catch (Exception e) {
            System.err.println("Fatal error: " + e.getMessage());
        }
    }

    // ================= ADD ARTICLE =================

    private static void addNewArticle(Scanner input) {

        try {
            System.out.print("\nEnter topic name: ");
            String topic = input.nextLine().trim();

            if (topic.isEmpty()) {
                System.out.println("Topic name cannot be empty.");
                return;
            }

            File topicDir = new File(ARTICLES_DIR + "/" + topic);
            if (!topicDir.exists()) {
                topicDir.mkdirs();
            }

            String filename = "article_" + System.currentTimeMillis() + ".txt";
            File articleFile = new File(topicDir, filename);

            System.out.println("\nPaste article text below.");
            System.out.println("Press ENTER on an empty line to finish:\n");

            StringBuilder content = new StringBuilder();

            while (true) {
                String line = input.nextLine();
                if (line.isEmpty()) break;
                content.append(line).append("\n");
            }

            try (FileWriter writer = new FileWriter(articleFile)) {
                writer.write(content.toString());
            }

            System.out.println("\nArticle saved to: " + articleFile.getPath());

        } catch (Exception e) {
            System.err.println("Failed to add article: " + e.getMessage());
        }
    }
}
