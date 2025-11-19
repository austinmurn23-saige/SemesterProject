import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(System.in);

            // load system resources
            //init
            System.out.println("Loading system resources...");
            ArrayList<String> stopWords = RemoveWords.loadStopWords("resources/stopwords.txt");
            Map<String, ArrayList<String>> allTopicsData = RemoveWords.processAllTopics("articles", stopWords);
            Map<String, Double> lexicon = SentimentAnalyzer.loadLexicon("resources/lexiconscores.txt");

            boolean running = true;
            while (running) {
                System.out.println("\n=========================================");
                System.out.println("      TEXT ANALYSIS DASHBOARD (no dynamic selections)");
                System.out.println("=========================================");
                System.out.println("Library Contents:");

                // static menu
                System.out.println("  [1] SPACE");
                System.out.println("  [2] NVDA");
                System.out.println("  [3] BO7");
                System.out.println("  [0] Exit");
                System.out.print("\nSelect a topic to analyze (Enter ID): ");

                String choice = input.nextLine();
                String selectedTopicName;

                if (choice.equals("0")) {
                    running = false;
                    System.out.println("Exiting...");
                } else {
                    // map selection to topic name
                    switch (choice) {
                        case "1":
                            selectedTopicName = "SPACE";
                            break;
                        case "2":
                            selectedTopicName = "NVDA";
                            break;
                        case "3":
                            selectedTopicName = "BO7";
                            break;
                        default:
                            System.out.println("Invalid selection.");
                            continue;
                    }

                    // run analysis if topic exists
                    if (allTopicsData.containsKey(selectedTopicName)) {

                        ArrayList<String> topicWords = allTopicsData.get(selectedTopicName);
                        Map<String, ArrayList<String>> selectedTopicData = new HashMap<>();
                        selectedTopicData.put(selectedTopicName, topicWords);

                        System.out.println("\n>>> PROCESSING: " + selectedTopicName + " <<<\n");

                        // run the analyzers
                        TextAnalyzer.analyzeTopics(selectedTopicData);

                        VocabRichness.analyzeVocabRichness(selectedTopicData);

                        Map<String, Double> sentimentScores = SentimentAnalyzer.computeSentimentScores(selectedTopicData, lexicon);
                        SentimentAnalyzer.displaySentimentResults(sentimentScores);

                        System.out.println("\n(Press Enter to continue)");
                        input.nextLine();
                    } else {
                        System.out.println("Error: Topic not found.");
                    }
                }
            }
            input.close();

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}