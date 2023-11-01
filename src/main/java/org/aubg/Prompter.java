package org.aubg;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.deeplearning4j.models.word2vec.Word2Vec;
public class Prompter {
    
    private static final Pattern charsPunctuationPattern = Pattern.compile("[\\d:,\"\'\\`\\_\\|?!\n\r@;]+");
    private JFrame frame;
    private JTextField promptField;
    private JLabel promptLabel;
    private JButton submitButton;
    private final String placeholderText = "Please enter a prompt";

    public Prompter() {
        initialize();
    }

    private void initialize() {

        // Reading a file containing a list of possible shapes  
        List<String> shapes = PatternRecognizer.readObjectsFromFile("src/main/resources/shapes.txt");

        // Reading a file containing a list of possible colors
        List<String> colors = PatternRecognizer.readObjectsFromFile("src/main/resources/colors.txt");

        Word2Vec dictVec = Word2VecLoader.loadDictVec();

        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        promptLabel = new JLabel("Prompt");
        promptLabel.setBounds(10, 10, 80, 25);
        frame.add(promptLabel);

        promptField = new JTextField();
        promptField.setForeground(Color.GRAY);
        promptField.setBounds(100, 10, 200, 25);
        promptField.setText(placeholderText); 
        frame.add(promptField);

        DrawingPanel drawingPanel = new DrawingPanel();
        drawingPanel.setBounds(10, 90, 300, 150);
        drawingPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.add(drawingPanel);

        promptField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (promptField.getText().equals(placeholderText)) {
                    promptField.setText("");
                    promptField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (promptField.getText().isEmpty()) {
                    promptField.setForeground(Color.GRAY);
                    promptField.setText(placeholderText);
                }
            }
        });

        submitButton = new JButton("Submit");
        submitButton.setBounds(100, 50, 80, 25);
        frame.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String promptText = promptField.getText();
                String targetShape = "";
                String targetColor = "";

                if (promptText.equals(placeholderText)) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid prompt", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                else {
                    Collection<String> promptWords = separatePromptWords(promptText);

                    // Output for target shape 
                    targetShape = PatternRecognizer.computeTargetObject("shape", promptWords, shapes, dictVec);
                    System.out.println("Remaining prompt words: " + promptWords);

                    if (targetShape.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "No matching shape found.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    else {
                        // Set drawing shape 
                        drawingPanel.setDrawingShape(targetShape);
                        // Output for target color
                        targetColor = PatternRecognizer.computeTargetObject("color", promptWords, colors, dictVec);
                        System.out.println("Remaining prompt words: " + promptWords);

                        if (!targetColor.isEmpty()) {
                            ColorCodeGenerator colorCodeGenerator = new ColorCodeGenerator(targetColor);
                            int r = colorCodeGenerator.redValue;
                            int g = colorCodeGenerator.greenValue;
                            int b = colorCodeGenerator.blueValue;
                            drawingPanel.setDrawingColor(new Color(r, g, b));
                        }

                        drawingPanel.drawShape();
                    }
                }
            }
        });
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

    public void show() {
        frame.setVisible(true);
    }
   
    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Prompter window = new Prompter();
                    window.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

