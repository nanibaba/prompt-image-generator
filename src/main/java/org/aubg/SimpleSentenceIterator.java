package org.aubg;

import org.deeplearning4j.text.sentenceiterator.BaseSentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;

import java.util.List;

public class SimpleSentenceIterator extends BaseSentenceIterator {
    private List<String> sentences;
    private int currentIdx = 0;

    public SimpleSentenceIterator(List<String> sentences) {
        this.sentences = sentences;
    }

    @Override
    public String nextSentence() {
        // Ensure there's a next sentence to prevent out of bounds exceptions
        if (hasNext()) {
            return sentences.get(currentIdx++);
        } else {
            return null; // or you can throw an exception based on your requirements
        }
    }

    @Override
    public boolean hasNext() {
        return currentIdx < sentences.size();
    }

    @Override
    public void reset() {
        currentIdx = 0;
    }

    @Override
    public void finish() {
        // No resources to close in this implementation.
        // If future modifications involve resources, close/cleanup them here.
    }

    @Override
    public SentenceIterator clone() {
        // This can be improved upon depending on how you need to utilize cloning
        return new SimpleSentenceIterator(sentences);
    }
}
