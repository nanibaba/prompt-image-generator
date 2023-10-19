package org.aubg;

import org.deeplearning4j.text.tokenization.tokenizer.Tokenizer;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;


public class SimpleTokenizerFactory implements TokenizerFactory {

    private TokenPreProcess tokenPreProcessor;

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
    public void setTokenPreProcessor(TokenPreProcess tokenPreProcessor) {
        this.tokenPreProcessor = tokenPreProcessor;
    }

    @Override
    public TokenPreProcess getTokenPreProcessor() {
        return this.tokenPreProcessor;
    }

    public class SimpleTokenizer implements Tokenizer {

        private String[] tokens;
        private int currentIdx = 0;
        private TokenPreProcess tokenPreProcessor;

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
            String token = tokens[currentIdx++];
            if(tokenPreProcessor != null) {
                token = tokenPreProcessor.preProcess(token);
            }
            return token;
        }

        @Override
        public List<String> getTokens() {
            return Arrays.asList(tokens);
        }

        @Override
        public void setTokenPreProcessor(TokenPreProcess tokenPreProcessor) {
            this.tokenPreProcessor = tokenPreProcessor;
        }
    }
}
