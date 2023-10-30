package org.aubg;

import java.io.File;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;

public abstract class Word2VecLoader {

    static Word2Vec loadDictVec() {
        long startTime = System.currentTimeMillis();

        File gModel = new File("src/main/resources/GoogleNews-vectors-negative300.bin.gz");

        Word2Vec dictVec = WordVectorSerializer.readWord2VecModel(gModel);

        long loadTime = System.currentTimeMillis() - startTime;

        System.out.println("Time to load Google News Data: " + loadTime + "ms");

        return dictVec;
    }
}
