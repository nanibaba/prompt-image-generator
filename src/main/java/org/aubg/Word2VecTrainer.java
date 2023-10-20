package org.aubg;

import org.deeplearning4j.models.word2vec.Word2Vec;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.Arrays;
import java.util.List;

public class Word2VecTrainer {

    public static void main(String[] args) {

        // Possible shapes list 
        List<String> shapes = Arrays.asList(
            "circle", 
            "square", 
            "triangle"
        );

        // Convert shapes to numerical data
        Word2Vec vec = new Word2Vec.Builder()
                .minWordFrequency(1)
                .iterations(1)
                .layerSize(100)
                .seed(42)
                .windowSize(5)
                .iterate(new SimpleSentenceIterator(shapes))
                .tokenizerFactory(new SimpleTokenizerFactory())
                .build();
        vec.fit();

        INDArray shapeInput = Nd4j.zeros(shapes.size(), 100);
        
        for (int i = 0; i < shapes.size(); i++) {
            String shape = shapes.get(i);
            if (vec.hasWord(shape)) {
                INDArray shapeVec = vec.getWordVectorMatrix(shape);
                shapeInput.putRow(i, shapeVec);
            }
        }

        System.out.println(shapeInput); 
    }
}