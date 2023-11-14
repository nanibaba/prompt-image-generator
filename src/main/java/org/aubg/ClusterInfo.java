package org.aubg;

import org.deeplearning4j.models.word2vec.Word2Vec;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.ops.transforms.Transforms;

import java.util.List;

public class ClusterInfo {
    private int id; // Unique identifier for the cluster
    private List<String> objects; // The list of object names in the cluster
    private Word2Vec dictVec; // The dictionary vector model used by the cluster
    private INDArray centroid; // The centroid vector of the cluster

    public ClusterInfo(
        int id, 
        Word2Vec dictVec,
        List<String> objects
        ) {
        this.id = id;
        this.objects = objects;
        this.dictVec = dictVec;

        // Initialize an array to store the sum of all object vectors
        INDArray centroidSum = Nd4j.create(dictVec.getLayerSize());

        // Sum the vectors of all objects
        for (String object : objects) {
            if (dictVec.hasWord(object)) {
                INDArray objectVector = dictVec.getWordVectorMatrix(object);
                centroidSum.addi(objectVector);
            } else {
                System.out.println("Word not in Word2Vec model: " + object);
            }
        }

        // Calculate the centroid by averaging the sum vector
        this.centroid = centroidSum.div(objects.size());
    }

    public int getId() {
        return id;
    }

    public List<String> getObjects() {
        return objects;
    }

     public INDArray getCentroid() {
        return centroid;
    }

    // Find the cosine similarity of a prompt word and the cluster's centroid 
    public double similarity (String word) {
        INDArray wordVector = dictVec.getWordVectorMatrix(word);
        double cosSim = Transforms.cosineSim(wordVector, centroid);

        System.out.println("Cosine similarity: " + cosSim);

        return cosSim;
    }
}

