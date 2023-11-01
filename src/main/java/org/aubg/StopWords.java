package org.aubg;

import java.util.List;

public abstract class StopWords {

    public static List<String> getStopWords() {
        return PatternRecognizer.readObjectsFromFile("src/main/resources/stopwords.txt");
    }

}