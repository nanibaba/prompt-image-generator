package org.aubg;

import java.io.File;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;

// Abstract class named Word2VecLoader
public abstract class Word2VecLoader {

    // Static method to load the Word2Vec model
    static Word2Vec loadDictVec() {
        // Logging at the beginning of the loading process
        System.out.println("Loading Word2vec model...");

        // Record the start time
        long startTime = System.currentTimeMillis();

        // Specify the file location of the Google News Word2Vec model
        File gModel = new File("src/main/resources/GoogleNews-vectors-negative300.bin.gz");

        // Load the Word2Vec model from the specified file
        Word2Vec dictVec = WordVectorSerializer.readWord2VecModel(gModel);

        // Calculate the time taken to load the model
        long loadTime = System.currentTimeMillis() - startTime;

        // Print the time taken to load the Google News Data
        System.out.println("Time to load Google News Data: " + loadTime + "ms");

        // Return the loaded Word2Vec model
        return dictVec;
    }
}

