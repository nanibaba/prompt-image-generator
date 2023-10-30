package org.aubg;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.deeplearning4j.text.stopwords.StopWords;

public abstract class Prompter {
    private static Scanner promptInput = new Scanner(System.in); 

    private static final Pattern charsPunctuationPattern = Pattern.compile("[\\d:,\"\'\\`\\_\\|?!\n\r@;]+");

    static String inputPrompt() { 
        System.out.print("Please enter a prompt: ");
        String prompt = promptInput.nextLine();
        return prompt; 
    }

    static Collection<String> separatePromptWords(String prompt) {
        String input_text = charsPunctuationPattern.matcher(prompt.trim().toLowerCase()).replaceAll("");
        //replace text between {},[],() including them
        input_text = input_text.replaceAll("\\{.*?\\}", "");
        input_text = input_text.replaceAll("\\[.*?\\]", "");
        input_text = input_text.replaceAll("\\(.*?\\)", "");
        input_text = input_text.replaceAll("[^A-Za-z0-9(),!?@\'\\`\"\\_\n]", " ");
        input_text = input_text.replaceAll("[/]"," ");
        input_text = input_text.replaceAll(";"," ");
        
        //Collect all tokens into words collection.    
        Collection<String> words = Arrays.asList(input_text.split(" ")).parallelStream().filter(label->label.length()>0).collect(Collectors.toList());
        words = words.parallelStream().filter(label ->  !StopWords.getStopWords().contains(label.trim())).collect(Collectors.toList());

        return words;
    }

    static void closeInput() {
        promptInput.close();
    }
}
