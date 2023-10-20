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

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Word2VecTrainer {

    public static void main(String[] args) {

        // Possible shapes list 
        List<String> shapes = Arrays.asList(
            "circle", "square", "triangle", "oval", "pentagon", 
            "hexagon", "octagon", "star", "diamond", "heart", 
            "crescent", "parallelogram", "rhombus", "ellipse"
        );

    // Possible prompts list
    List<String> prompts = new ArrayList<>();
    for(String shape : shapes) {
        prompts.addAll(Arrays.asList(
            "Please draw a " + shape, 
            "draw a " + shape,
            shape + " please",
            "paint a " + shape,
            "illustrate a " + shape,
            "depict a " + shape,
            "show me a " + shape,
            "lemme see a " + shape,
            "could you possibly draw a " + shape + "?",
            "can you try to sketch a " + shape + "?",
            "would you mind sketching a " + shape + "?",
            "how about drawing a big " + shape + "?",
            "think you can paint a small " + shape + "?",
            "any chance you can illustrate a colored " + shape + "?",
            "do you know how to draw a " + shape + "?",
            "ever tried drawing a " + shape + "?",
            "hey! draw a " + shape + " for me.",
            "wow, show me a giant " + shape + "!",
            "by the way, can you draw a tiny " + shape + "?",
            "oh, and sketch a " + shape + ".",
            "while you're at it, depict a " + shape + "."
        ));
    }

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
        
         // Convert prompts to numerical data
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
                .layer(0, new DenseLayer.Builder().nIn(50).nOut(50).build())
                .layer(1, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                        .activation(Activation.SOFTMAX)
                        .nIn(50).nOut(shapes.size()).build())
                .build();

        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();

        INDArray input = Nd4j.zeros(prompts.size(), 50);
        INDArray labels = Nd4j.zeros(prompts.size(), shapes.size());

        for (int i = 0; i < prompts.size(); i++) {
            String prompt = prompts.get(i);
            INDArray avgPromptVec = promptsVec.getWordVectorMatrix(prompt);
            if (avgPromptVec != null) {
                input.putRow(i, avgPromptVec);
            }
            
            int idx = shapes.indexOf(getShapeForPrompt(prompt, shapes));
            labels.putScalar(new int[]{i, idx}, 1.0);
        }
        
        // Train the model
        for (int i = 0; i < 10000; i++) { 
            model.fit(input, labels);
        }

        Scanner promptInput = new Scanner(System.in); 
        System.out.print("Please enter a prompt: ");
        String prompt = promptInput.nextLine();

        // Tokenize and average the word vectors for the user prompt
        INDArray userPromptInput = Nd4j.zeros(1, 50); 
        String[] userPromptWords = prompt.split(" ");
        INDArray userPromptAvgVec = Nd4j.zeros(50);
        int count = 0;

        for (String word : userPromptWords) {
            if (promptsVec.hasWord(word)) {
            userPromptAvgVec.addi(promptsVec.getWordVectorMatrix(word));
            count++;
        }
    }
        if (count > 0) {
            userPromptAvgVec.divi(count); // Average the vectors
        }
    
        userPromptInput.putRow(0, userPromptAvgVec);

        promptInput.close();

        // Use the neural network to predict the shape
        INDArray prediction = model.output(userPromptInput);
        int predictedClass = Nd4j.argMax(prediction, 1).getInt(0);
    
        String[] possibleShapes = new String[shapes.size()];
    
        System.out.println("Predicted shape: " + shapes.toArray(possibleShapes)[predictedClass]);

    }

    // Utility method to get the corresponding shape for a given prompt
    private static String getShapeForPrompt(String prompt, List<String> shapes) {
        for (String shape : shapes) {
            if (prompt.contains(shape)) {
                return shape;
            }
        }
        return "";
    }
}
