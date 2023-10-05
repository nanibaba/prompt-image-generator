package org.aubg;

import org.deeplearning4j.text.tokenization.tokenizer.Tokenizer;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;


public class SimpleTokenizerFactory implements TokenizerFactory {
    @Override
    public Tokenizer create(String toTokenize) {
        return new SimpleTokenizer(toTokenize);
    }

    @Override
    public Tokenizer create(InputStream toTokenize) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(toTokenize, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append(" ");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading from InputStream", e);
        }
        return create(sb.toString());
    }

    @Override
    public void setTokenPreProcessor(org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess tokenPreProcessor) {
        // Implement if required.
    }

    @Override
    public org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess getTokenPreProcessor() {
        return null; 
    }

    public class SimpleTokenizer implements Tokenizer {

        private String[] tokens;
        private int currentIdx = 0;

        public SimpleTokenizer(String toTokenize) {
            this.tokens = toTokenize.split(" ");
        }

        @Override
        public boolean hasMoreTokens() {
            return currentIdx < tokens.length;
        }

        @Override
        public int countTokens() {
            return tokens.length;
        }

        @Override
        public String nextToken() {
            return tokens[currentIdx++];
        }

        @Override
        public List<String> getTokens() {
            return Arrays.asList(tokens);
        }

        @Override
        public void setTokenPreProcessor(org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess tokenPreProcessor) {
            // This can be expanded for token pre-processing.
        }
    }
}
