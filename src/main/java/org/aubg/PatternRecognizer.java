package org.aubg;

import org.deeplearning4j.models.word2vec.Word2Vec;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PatternRecognizer {
    // Function to calculate the Levenshtein Distance
    public static int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1] + 
                    (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1), 
                    dp[i - 1][j] + 1), dp[i][j - 1] + 1);
                }
            }
        }

        return dp[a.length()][b.length()];
    }

    // Function to find the closest object
    public static String findClosestObject(String word, List<String> objects, int maxDistance) {
        String closestObject = "";
        int minDistance = maxDistance;

        for (String object : objects) {
            int distance = levenshteinDistance(word, object);
            if (distance < minDistance) {
                minDistance = distance;
                closestObject = object;
            }
        }

        return closestObject;
    }

    public static List<String> readObjectsFromFile(String fileName) {
        List<String> objects = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] objectArray = line.split(", ");
                for (String object : objectArray) {
                    objects.add(object.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objects;
    }

    public static void computeTargetObject(
            String type, Collection<String> promptWords, 
            List<String> objects, Word2Vec dictVec) {
            int maxEditDistance = 2; // Max allowable edit distance for spell checking
            double cosSim = 0;
            double maxCosSim = cosSim;
            String targetObject = "";

            for (String word : promptWords) {
                // Spell check the word
                String correctedWord = findClosestObject(word, objects, maxEditDistance);
                System.out.println("Corrected word: " + correctedWord);

                for (String object : objects) {
                    cosSim = dictVec.similarity(correctedWord, object);
                    System.out.println("Cosine similarity: " + cosSim); 
                    if (cosSim >= maxCosSim) {
                        maxCosSim = cosSim;
                        targetObject = object;
                    }
                }

            }

            if (!targetObject.isEmpty()) {
                List<String> promptPhrases = new ArrayList<String>(promptWords);
                String selectedWord = findClosestObject(targetObject, promptPhrases, maxEditDistance);
                promptWords.remove(selectedWord);
                System.out.println("Target " + type + ": " + targetObject); 
            } else {
                System.out.println("No matching " + type + " found.");
            }
    }

    public static void main(String[] args) throws IOException {
        // Reading a file containing a list of possible shapes  
        List<String> shapes = readObjectsFromFile("src/main/resources/shapes.txt");
        // Reading a file containing a list of possible colors
        List<String> colors = readObjectsFromFile("src/main/resources/colors.txt");

        Word2Vec dictVec = Word2VecLoader.loadDictVec();
        String prompt = Prompter.inputPrompt(); 
        Collection<String> promptWords = Prompter.separatePromptWords(prompt);
        
        while (!prompt.equals("stop")) {
            // Output for target shape 
            computeTargetObject("shape", promptWords, shapes, dictVec);

            System.out.println("Remaining prompt words: " + promptWords);

            // Output for target color
            computeTargetObject("color", promptWords, colors, dictVec);

            System.out.println("Remaining prompt words: " + promptWords);

            prompt = Prompter.inputPrompt(); 
            promptWords = Prompter.separatePromptWords(prompt);
        }
        Prompter.closeInput();
    }
}