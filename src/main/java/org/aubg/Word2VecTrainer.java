package org.aubg;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;

/*
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
*/

import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.IOException;

public class Word2VecTrainer {
    public static void main(String[] args) throws IOException {

        Scanner promptInput = new Scanner(System.in); 

        // Possible shapes list 
        List<String> shapes = Arrays.asList(
            "circle", "square", "triangle"
        );

        long startTime = System.currentTimeMillis();

        File gModel = new File("src/main/resources/GoogleNews-vectors-negative300.bin.gz");

        Word2Vec dictVec = WordVectorSerializer.readWord2VecModel(gModel);

        long loadTime = System.currentTimeMillis() - startTime;

        System.out.println("Time to load Google News Data: " + loadTime + "ms");

        System.out.print("Please enter a prompt: ");
        String prompt = promptInput.nextLine();
        String[] promptWords = prompt.split(" ");

        while (!prompt.equals("stop")) {
            
            double cosSim = 0;
            double maxCosSim = cosSim;
            String targetShape = ""; 
            
            for (String word : promptWords) {
                for (String shape : shapes) {
                    cosSim = dictVec.similarity(word, shape);
                    if (cosSim == 1.0) {
                        maxCosSim = cosSim;
                        targetShape = shape;
                        break; 
                    }
                    else if (cosSim >= maxCosSim) {
                        maxCosSim = cosSim;
                        targetShape = shape; 
                    }
                }
                
                if (cosSim == 1.0) {
                    break;
                }
            }

        System.out.println("Target shape: " + targetShape);

        System.out.print("Please enter a prompt: ");
        prompt = promptInput.nextLine();
        promptWords = prompt.split(" ");

    }
        promptInput.close();

        /* // Create a basic neural network model
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
    
        System.out.println("Predicted shape: " + shapes.toArray(possibleShapes)[predictedClass]); */

    }

    // Utility method to get the corresponding shape for a given prompt
   /*  private static String getShapeForPrompt(String prompt, List<String> shapes) {
        for (String shape : shapes) {
            if (prompt.contains(shape)) {
                return shape;
            }
        }
        return "";
    } */
}
