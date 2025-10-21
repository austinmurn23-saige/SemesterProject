# Semester Project - Text Analyzer

This project is a Java application designed to perform text analysis on a collection of articles. The application reads articles on various topics, preprocesses the text, and calculates key statistics, including word frequency.

## Team Information

* **Team Member 1:** Julian Radossich
* **Team Member 2:** Austin Murn
* **Team Member 3:** Joseph Pelosi

---

## How the Project Works

The project is split into three files.

### `Main.java`
This is the file that starts everything. The `main` method here is the first thing that runs and it controls the whole process.
* It tells the `RemoveWords` file to load a list of common words to ignore.
* Then, it tells `RemoveWords` to read all of the articles and clean up the text.
* Finally, it takes the clean words and hands them over to the `TextAnalyzer` file to be counted and printed.

### `RemoveWords.java`
This file is a helper that does all the text cleaning.
* **`loadStopWords`**: This method reads the `stopwords.txt` file to get a list of boring words (like "a", "the", "is", etc.). We do this so we can ignore them later.
* **`processAllTopics`**: This method does most of the work. It goes into the `articles` folder, reads all the text files for a topic, and gets them ready. It removes punctuation, makes all words lowercase, and then removes all the boring stop words. It gives back a clean list of important words for each topic.

### `TextAnalyzer.java`
This file is another helper that does all the counting and shows the results.
* **`analyzeTopics`**: This method takes the clean list of words from `RemoveWords`. It first counts the total number of words and how many of them are unique. Then, it counts how many times each individual word appears in the list. Finally, it sorts the words from most common to least common and prints out a "Top 10" list to the screen.
    
## Milestone 1 Architecture (UML Class Diagram)

This diagram shows the initial class structure for the project.
    
```mermaid
classDiagram
    class Main {
        +--static-- main(String[] args)
    }

    class RemoveWords {
        +--static-- loadStopWords(String path) : Set~String~
        +--static-- processAllTopics(String path, Set~String~ stopWords) : Map~String, ArrayList~String~~
    }

    class TextAnalyzer {
        +--static-- analyzeTopics(Map~String, ArrayList~String~~ topicData)
    }

    Main ..> RemoveWords : uses
    Main ..> TextAnalyzer : uses


