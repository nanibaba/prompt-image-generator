package org.aubg;

import org.deeplearning4j.models.word2vec.Word2Vec;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.ops.transforms.Transforms;

import java.util.List;

public class ClusterInfo {
    private int id; // Unique identifier for the cluster
    private INDArray centroid; // The centroid vector of the cluster
    private List<String> objects; // The list of object names in the cluster

    public ClusterInfo(int id, INDArray centroid, List<String> objects) {
        this.id = id;
        this.centroid = centroid;
        this.objects = objects;
    }

    public int getId() {
        return id;
    }

    public INDArray getCentroid() {
        return centroid;
    }

    public List<String> getObjects() {
        return objects;
    }

    // Given a word and the Word2Vec model, find the most similar object within the cluster
    public String findMostSimilarObject(String word, Word2Vec dictVec) {
        double maxCosSim = Double.NEGATIVE_INFINITY;
        String mostSimilarObject = null;
        INDArray wordVector = dictVec.getWordVectorMatrix(word);

        for (String object : objects) {
            INDArray objectVector = dictVec.getWordVectorMatrix(object);
            double cosSim = Transforms.cosineSim(wordVector, objectVector);

            if (cosSim > maxCosSim) {
                maxCosSim = cosSim;
                mostSimilarObject = object;
            }
        }

        return mostSimilarObject;
    }
}

