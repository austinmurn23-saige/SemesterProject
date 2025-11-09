# Semester Project - Text Analyzer

This project is a Java application designed to perform text analysis on a collection of articles. The application reads articles on various topics, preprocesses the text, and calculates key statistics, including word frequency, vocabulary richness, and sentiment analysis.

## Team Information

* **Team Member 1:** Julian Radossich
* **Team Member 2:** Austin Murn
* **Team Member 3:** Joseph Pelosi

---

## How the Project Works

The project has been refactored into multiple classes to ensure better modularity, maintainability, and ease of collaboration among team members. Instead of branching off and merging, we split the project into distinct, independent classes based on functionality. This approach aligns with software development best practices, reducing merge conflicts and improving the scalability of the project.

### `Main.java`
This file serves as the entry point for the application. The `main` method orchestrates the entire process by calling the necessary methods from other classes. It:
* Loads the stop words from the `stopwords.txt` file via the `RemoveWords` class.
* Processes articles in the `articles` directory, cleans the text, and prepares it for analysis using the `RemoveWords` class.
* Passes the cleaned data to the `TextAnalyzer` class for word frequency analysis and vocabulary richness calculation.
* Loads the sentiment lexicon, computes sentiment scores for each topic, and displays the results using the `SentimentAnalyzer` class.

### `RemoveWords.java`
This file is responsible for cleaning the text data.
* **`loadStopWords`**: Reads the `stopwords.txt` file to get a list of stopwords (such as "a", "the", "is", etc.) that should be excluded from analysis.
* **`processAllTopics`**: Goes through each topic folder in the `articles` directory, reads the articles, removes punctuation, converts text to lowercase, and eliminates stopwords. The method returns a cleaned list of words for each topic, which is passed to the next stage of analysis.

### `TextAnalyzer.java`
This file performs the analysis of word frequency and vocabulary richness.
* **`analyzeTopics`**: Takes the cleaned words from `RemoveWords` and calculates:
    * Total word count
    * Unique word count
    * Vocabulary richness (the ratio of unique words to total words)
    * Word frequency distribution (top 10 most frequent words)
    This information is printed to the console for each topic.

### `VocabRichness.java`
This new file calculates the **vocabulary richness** for each topic. It focuses on measuring how diverse the vocabulary is by calculating the ratio of unique words to the total number of words.
* **`calculateRichness`**: Calculates vocabulary richness for each topic.
* **`analyzeVocabRichness`**: Displays the vocabulary richness for each topic in the project.

### `SentimentAnalyzer.java`
This new file handles sentiment analysis of the articles.
* **`loadLexicon`**: Loads a sentiment lexicon from the `lexiconscores.txt` file. The lexicon maps words to sentiment scores (positive, negative, neutral).
* **`computeSentimentScores`**: Computes the sentiment score for each topic based on the presence of words from the lexicon in the topic’s articles.
* **`displaySentimentResults`**: Displays the sentiment score for each topic, categorizing them as positive, negative, or neutral based on the score.

---

## Why We Chose to Use Separate Classes Instead of Branching

### **Advantages of Separate Classes**:
1. **Reduced Merge Conflicts**: By creating independent classes for distinct functionalities (e.g., text cleaning, vocabulary richness, and sentiment analysis), each team member can work on their specific module without interfering with others. This avoids the common problem of merge conflicts when working on a shared file.
2. **Improved Collaboration**: In a multi-developer environment, it is easier to work on different parts of the system independently. This separation of concerns allows each developer to focus on their part without constantly merging and resolving conflicts.
3. **Easier Maintenance**: With separate classes, it’s easier to locate and fix issues, as each class has a single responsibility. If we need to change how we analyze sentiment, for example, we can do so in `SentimentAnalyzer.java` without affecting the other parts of the project.
4. **Better Modularity and Reusability**: Each class is modular and can be reused in other projects if needed. For instance, `VocabRichness.java` can be used in future projects that require similar vocabulary analysis.

### **Why We Avoided Branching and Merging**:
- Branching and merging is a common approach in version-controlled projects, but it can lead to problems in cases where team members are frequently modifying the same files, which can result in conflicts. By keeping different functionalities in separate classes, we avoid this issue.
- Instead of merging multiple branches of overlapping code, separating concerns allows each developer to work in parallel and integrate their changes seamlessly when required.

---

## Milestone 1 Architecture (UML Class Diagram)

This updated class structure reflects the new modular approach, with distinct classes for each responsibility. The UML diagram below illustrates how the classes interact.

```mermaid
classDiagram
    class Main {
        +--static-- main(String[] args)
    }

    class RemoveWords {
        +--static-- loadStopWords(String path) : ArrayList~String~
        +--static-- processAllTopics(String path, ArrayList~String~ stopWords) : Map~String, ArrayList~String~~
    }

    class TextAnalyzer {
        +--static-- analyzeTopics(Map~String, ArrayList~String~~ topicData)
    }

    class VocabRichness {
        +--static-- calculateRichness(ArrayList~String~ words) : double
        +--static-- analyzeVocabRichness(Map~String, ArrayList~String~~ topicData)
    }

    class SentimentAnalyzer {
        +--static-- loadLexicon(String filePath) : Map~String, Double~
        +--static-- computeSentimentScores(Map~String, ArrayList~String~~ topicWords, Map~String, Double~ lexiconScores) : Map~String, Double~
        +--static-- displaySentimentResults(Map~String, Double~ sentimentScores)
    }

    Main ..> RemoveWords : uses
    Main ..> TextAnalyzer : uses
    Main ..> VocabRichness : uses
    Main ..> SentimentAnalyzer : uses
    RemoveWords ..> TextAnalyzer : provides data to
    VocabRichness ..> TextAnalyzer : provides analysis
    SentimentAnalyzer ..> TextAnalyzer : provides analysis
