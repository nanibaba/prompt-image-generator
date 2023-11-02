package org.aubg;

import java.util.List;

// Abstract class named StopWords
public abstract class StopWords {

    // Static method to retrieve a list of stop words
    public static List<String> getStopWords() {
        // Calls the readObjectsFromFile method from the PatternRecognizer class
        // to read and return the list of stop words from a file
        return PatternRecognizer.readObjectsFromFile("src/main/resources/stopwords.txt");
    }

}
