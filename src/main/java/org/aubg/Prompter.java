package org.aubg;

import java.util.Scanner;

import org.deeplearning4j.models.word2vec.Word2Vec;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class Prompter {

        INDArray generateInput(Word2Vec vec){
        Scanner promptInput = new Scanner(System.in); 

        System.out.print("Please enter a prompt: ");

        String prompt = promptInput.nextLine();

        // Tokenize and average the word vectors for the newPrompt
        INDArray input = Nd4j.zeros(1, 100);  // 1 row for one sentence, and 100 for the word vector size
        String[] words = prompt.split(" ");
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
    
        input.putRow(0, avgVec);

        promptInput.close();
        
        return input; 
    }

}
