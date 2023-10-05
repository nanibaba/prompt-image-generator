package org.aubg;

import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Word2VecExample {

    // private static Logger log = LoggerFactory.getLogger(Word2VecExample.class);

    public static void main(String[] args) {

        // Sample data
        List<String> prompts = Arrays.asList(
                "draw a circle",
                "please draw a circle",
                "I want a circle",
                "draw a square",
                "please draw a square",
                "I want a square",
                "draw a triangle",
                "please draw a triangle",
                "I want a triangle"
        );

        // Convert prompts to numerical data (e.g., using Word2Vec)
        // Note: This is a basic representation. For real-world tasks, a more elaborate setup would be required.
        Word2Vec vec = new Word2Vec.Builder()
                .minWordFrequency(5)
                .iterations(1)
                .layerSize(100)
                .seed(42)
                .windowSize(5)
                .iterate(new SimpleSentenceIterator(prompts))
                .tokenizerFactory(new SimpleTokenizerFactory())
                .build();
        vec.fit();

        // Convert labels to one-hot encoded vectors
        INDArray labels = Nd4j.zeros(prompts.size(), 3);  // 3 classes: circle, square, triangle
        for (int i = 0; i < prompts.size(); i++) {
            String prompt = prompts.get(i);
            if (prompt.contains("circle")) labels.putRow(i, Nd4j.create(new float[]{1, 0, 0}));
            else if (prompt.contains("square")) labels.putRow(i, Nd4j.create(new float[]{0, 1, 0}));
            else labels.putRow(i, Nd4j.create(new float[]{0, 0, 1}));
        }

        // Prepare input data for the model
        INDArray input = Nd4j.zeros(prompts.size(), 100);
        for (int i = 0; i < prompts.size(); i++) {
            String[] words = prompts.get(i).split(" ");
            INDArray avgVec = Nd4j.zeros(100);
            int count = 0;
            for (String word : words) {
                if (vec.hasWord(word)) {
                    avgVec.addi(vec.getWordVectorMatrix(word));
                    count++;
                }
            }
            if (count > 0) {
                avgVec.divi(count); // Average the vectors
            }
            input.putRow(i, avgVec);
        }

        // Create a basic neural network model
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .weightInit(WeightInit.XAVIER)
                .activation(Activation.RELU)
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                .list()
                .layer(0, new DenseLayer.Builder().nIn(100).nOut(50).build())
                .layer(1, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                        .activation(Activation.SOFTMAX)
                        .nIn(50).nOut(3).build())
                .build();

        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();

        // Train the model
        DataSet dataSet = new DataSet(input, labels);
        model.fit(dataSet);

        Scanner promptInput = new Scanner(System.in); 

        System.out.print("Please enter a prompt: ");

        String newPrompt = promptInput.nextLine();

        // Tokenize and average the word vectors for the newPrompt
        INDArray newInput = Nd4j.zeros(1, 100);  // 1 row for one sentence, and 100 for the word vector size
        String[] words = newPrompt.split(" ");
        INDArray avgVec = Nd4j.zeros(100);
        int count = 0;
    
        for (String word : words) {
            if (vec.hasWord(word)) {
            avgVec.addi(vec.getWordVectorMatrix(word));
            count++;
        }
    }
        if (count > 0) {
            avgVec.divi(count); // Average the vectors
        }
    
        newInput.putRow(0, avgVec);
        
        INDArray prediction = model.output(newInput);
        int predictedClass = Nd4j.argMax(prediction, 1).getInt(0);
        String[] shapes = {"circle", "square", "triangle"};
        System.out.println("Predicted shape: " + shapes[predictedClass]);

        promptInput.close();
    }
}