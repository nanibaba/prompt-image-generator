package org.aubg;

import org.deeplearning4j.models.word2vec.Word2Vec;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class PatternRecognizer {
    
    // Function to calculate the Levenshtein Distance between two strings
    public static int levenshteinDistance(String a, String b) {
        // Initialize a 2D array for dynamic programming
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    // Calculate the minimum number of operations needed
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1] + 
                    (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1), 
                    dp[i - 1][j] + 1), dp[i][j - 1] + 1);
                }
            }
        }

        return dp[a.length()][b.length()];
    }

    // Function to find the closest object to a given word based on Levenshtein Distance
    public static String findClosestObject(String word, List<String> objects, int maxDistance) {
        String closestObject = "";
        int minDistance = maxDistance;

        // Iterate through the objects and find the one with the minimum distance
        for (String object : objects) {
            int distance = levenshteinDistance(word, object);
            if (distance < minDistance) {
                minDistance = distance;
                closestObject = object;
            }
        }

        return closestObject;
    }

    // Function to read objects from a file and return them as a list
    public static List<String> readObjectsFromFile(String fileName) {
        List<String> objects = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            // Read each line from the file
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

    // Function to compute the target object from a collection of prompt words
    public static String computeTargetObject(
            String type, 
            Collection<String> promptWords, 
            ClusterInfo cluster, 
            List<String> objects, 
            Word2Vec dictVec
            ) {
            int maxEditDistance = 2; // Max allowable edit distance for spell checking
            double maxCosSim = Double.NEGATIVE_INFINITY;
            String targetObject = "";

            for (String word : promptWords) {
                // Spell check the word
                String correctedWord = findClosestObject(word, objects, maxEditDistance);
                System.out.println("Corrected word: " + correctedWord);

                // Find the most similar object within the cluster for each word
                double cosSim = cluster.similarity(correctedWord);
                if (cosSim > maxCosSim) {
                    maxCosSim = cosSim;
                    targetObject = correctedWord;
                }
            }

            // If a target object is found, remove the closest word from the prompt words
            if (!targetObject.isEmpty()) {
                List<String> promptPhrases = new ArrayList<String>(promptWords);
                String selectedWord = findClosestObject(targetObject, promptPhrases, maxEditDistance);
                promptWords.remove(selectedWord);
                System.out.println("Target " + type + ": " + targetObject); 
            } else {
                System.out.println("No matching " + type + " found.");
            }

            return targetObject;
    }
}
