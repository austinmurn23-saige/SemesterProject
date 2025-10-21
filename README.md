# Semester Project - Text Analyzer

This project analyzes text files to compare articles on the same topic.

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
