package org.aubg;

// Import necessary libraries and classes
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

// Class definition for Prompter
public class Prompter {
    
    // Pattern to match characters, punctuation, and other symbols
    private static final Pattern charsPunctuationPattern = Pattern.compile("[\\d:,\"\'\\`\\_\\|?!\n\r@;]+");

    // GUI components
    private JFrame frame;
    private JTextField promptField;
    private JLabel promptLabel;
    private JButton submitButton;
    private final String placeholderText = "Please enter a prompt";

    public Prompter() {
        initialize();
    }

    // Method to initialize the GUI and its components
    private void initialize() {

        // Read shapes and colors from files
        List<String> shapes = PatternRecognizer.readObjectsFromFile("src/main/resources/shapes.txt");
        List<String> colors = PatternRecognizer.readObjectsFromFile("src/main/resources/colors.txt");

        // Load the Google News vector dictionary
        Word2Vec dictVec = Word2VecLoader.loadDictVec();

        // Create shape and color clusters
        ClusterInfo shapeCluster = new ClusterInfo(1, dictVec, shapes);
        ClusterInfo colorCluster = new ClusterInfo(2, dictVec, colors);

        // Set up the main frame
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Set up the prompt label
        promptLabel = new JLabel("Prompt");
        promptLabel.setBounds(10, 10, 80, 25);
        frame.add(promptLabel);

        // Set up the prompt field with placeholder text
        promptField = new JTextField();
        promptField.setForeground(Color.GRAY);
        promptField.setBounds(100, 10, 200, 25);
        promptField.setText(placeholderText); 
        frame.add(promptField);

        // Set up the drawing panel
        DrawingPanel drawingPanel = new DrawingPanel();
        drawingPanel.setBounds(10, 90, 300, 150);
        drawingPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.add(drawingPanel);

        // Add focus listeners to the prompt field for placeholder text handling
        promptField.addFocusListener(new FocusListener() {
            // Clear placeholder text when the field gains focus
            @Override
            public void focusGained(FocusEvent e) {
                if (promptField.getText().equals(placeholderText)) {
                    promptField.setText("");
                    promptField.setForeground(Color.BLACK);
                }
            }

            // Restore placeholder text when the field loses focus and is empty
            @Override
            public void focusLost(FocusEvent e) {
                if (promptField.getText().isEmpty()) {
                    promptField.setForeground(Color.GRAY);
                    promptField.setText(placeholderText);
                }
            }
        });

        // Set up the submit button
        submitButton = new JButton("Submit");
        submitButton.setBounds(100, 50, 80, 25);
        frame.add(submitButton);

        // Add action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String promptText = promptField.getText();
                String targetShape = "";
                String targetColor = "";
                String targetClass = "";

                // Validate the prompt text
                if (promptText.equals(placeholderText)) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid prompt", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    Collection<String> promptWords = separatePromptWords(promptText);

                    // Find target shape from the prompt
                    targetShape = PatternRecognizer.computeTargetObject(
                        "shape", 
                        promptWords, 
                        shapeCluster, 
                        shapes, 
                        dictVec
                    );
                    System.out.println("Remaining prompt words: " + promptWords);

                    if (targetShape.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "No matching shape found.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                        targetClass = "org.aubg.shapes." + 
                        targetShape.substring(0, 1).toUpperCase() + 
                        targetShape.substring(1); 
                        // Find target color from the prompt
                        targetColor = PatternRecognizer.computeTargetObject(
                            "color", 
                            promptWords, 
                            colorCluster,
                            colors, 
                            dictVec
                        );
                        System.out.println("Remaining prompt words: " + promptWords);

                        if (!targetColor.isEmpty()) {
                            // Generate color code and set drawing color
                            ColorCodeGenerator colorCodeGenerator = new ColorCodeGenerator(targetColor);
                            int r = colorCodeGenerator.redValue;
                            int g = colorCodeGenerator.greenValue;
                            int b = colorCodeGenerator.blueValue;
                            drawingPanel.setDrawingColor(new Color(r, g, b));
                        }

                       // Set and draw the shape
                       drawingPanel.setDrawingShape(targetClass);
                    }
                }
            }
        });
    }

    // Method to separate prompt words and remove punctuation, special characters, and stop words
    static Collection<String> separatePromptWords(String prompt) {
        String input_text = charsPunctuationPattern.matcher(prompt.trim().toLowerCase()).replaceAll("");
        // Remove text between {}, [], and ()
        input_text = input_text.replaceAll("\\{.*?\\}", "");
        input_text = input_text.replaceAll("\\[.*?\\]", "");
        input_text = input_text.replaceAll("\\(.*?\\)", "");
        // Replace non-alphanumeric characters with spaces
        input_text = input_text.replaceAll("[^A-Za-z0-9(),!?@\'\\`\"\\_\n]", " ");
        // Replace slashes and semicolons with spaces
        input_text = input_text.replaceAll("[/]"," ");
        input_text = input_text.replaceAll(";"," ");
        
        // Collect tokens into a collection of words, excluding stop words
        Collection<String> words = Arrays.asList(input_text.split(" "))
            .parallelStream()
            .filter(label -> label.length() > 0)
            .collect(Collectors.toList());
        words = words.parallelStream()
            .filter(label -> !StopWords.getStopWords().contains(label.trim()))
            .collect(Collectors.toList());

        return words;
    } 

    // Method to make the frame visible
    public void show() {
        frame.setVisible(true);
    }
   
    // Main method to run the application
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

