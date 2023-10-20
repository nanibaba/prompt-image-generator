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
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.lossfunctions.LossFunctions;

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

        // Possible prompts list
        List<String> prompts = Arrays.asList(
            "draw a circle",
            "sketch a circle for me",
            "could you draw a circle?",
            "I'd like to see a circle",
            "please draw a circle",
            "show me a circle drawing",
            "circle please",
            "I want a circle",
            "make a circle on the screen",
    
            "draw a square",
            "sketch a square for me",
            "could you draw a square?",
            "I'd like to see a square",
            "please draw a square",
            "show me a square drawing",
            "square please",
            "I want a square",
            "make a square on the screen",
    
            "draw a triangle",
            "sketch a triangle for me",
            "could you draw a triangle?",
            "I'd like to see a triangle",
            "please draw a triangle",
            "show me a triangle drawing",
            "triangle please",
            "I want a triangle",
            "make a triangle on the screen"
        );

        // Convert shapes to numerical data
        Word2Vec shapeVec = new Word2Vec.Builder()
                .minWordFrequency(1)
                .iterations(1)
                .layerSize(50)
                .seed(42)
                .windowSize(5)
                .iterate(new SimpleSentenceIterator(shapes))
                .tokenizerFactory(new SimpleTokenizerFactory())
                .build();
        shapeVec.fit();

        INDArray shapeInput = Nd4j.zeros(shapes.size(), 50);
        
        for (int i = 0; i < shapes.size(); i++) {
            String shape = shapes.get(i);
            if (shapeVec.hasWord(shape)) {
                INDArray avgShapeVec = shapeVec.getWordVectorMatrix(shape);
                shapeInput.putRow(i, avgShapeVec);
            }
        }

        System.out.println(shapeInput);
        
         // Convert shapes to numerical data
        Word2Vec promptsVec = new Word2Vec.Builder()
                .minWordFrequency(1)
                .iterations(1)
                .layerSize(50)
                .seed(42)
                .windowSize(5)
                .iterate(new SimpleSentenceIterator(prompts))
                .tokenizerFactory(new SimpleTokenizerFactory())
                .build();
        promptsVec.fit();

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

        INDArray input = Nd4j.zeros(prompts.size(), 50);
        INDArray labels = Nd4j.zeros(prompts.size(), shapes.size());

        for (int i = 0; i < prompts.size(); i++) {
            String prompt = prompts.get(i);
            if (promptsVec.hasWord(prompt)) {
                INDArray avgPromptVec = promptsVec.getWordVectorMatrix(prompt);
                input.putRow(i, avgPromptVec);
            }
            
            int idx = shapes.indexOf(getShapeForPrompt(prompt));
            labels.putScalar(new int[]{i, idx}, 1.0);
        }
        
        // Train the model
        for (int i = 0; i < 1000; i++) { // train for 1000 epochs as an example
            model.fit(input, labels);
        }
    }

    // Utility method to get the corresponding shape for a given prompt
    private static String getShapeForPrompt(String prompt) {
        if (prompt.contains("circle")) {
            return "circle";
        } else if (prompt.contains("square")) {
            return "square";
        } else if (prompt.contains("triangle")) {
            return "triangle";
        }
        return "";
    }
}
