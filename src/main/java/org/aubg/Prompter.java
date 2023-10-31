package org.aubg;

// import java.util.Arrays;
// import java.util.Collection;
// import java.util.regex.Pattern;
// import java.util.stream.Collectors;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
// import org.deeplearning4j.text.stopwords.StopWords;

public class Prompter {

    private JFrame frame;
    private JTextField promptField;
    private JLabel promptLabel;
    private JButton submitButton;
    
    public Prompter() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        promptLabel = new JLabel("Prompt");
        promptLabel.setBounds(10, 10, 80, 25);
        frame.add(promptLabel);

        promptField = new JTextField("Please enter a prompt");
        promptField.setForeground(Color.GRAY);
        promptField.setBounds(100, 10, 200, 25);
        promptField.setFocusable(false);
        frame.add(promptField);

        promptField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (promptField.getText().equals("Please enter a prompt")) {
                    promptField.setText("");
                    promptField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (promptField.getText().isEmpty()) {
                    promptField.setForeground(Color.GRAY);
                    promptField.setText("Please enter a prompt");
                }
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                promptField.setFocusable(true);
            }
        });

        submitButton = new JButton("Submit");
        submitButton.setBounds(100, 50, 80, 25);
        frame.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String promptText = promptField.getText();
                if (!promptText.equals("Please enter a prompt")) {
                    System.out.println("Entered Prompt: " + promptText);
                }
            }
        });
    }

    public void show() {
        frame.setVisible(true);
    }

   /*  private static final Pattern charsPunctuationPattern = Pattern.compile("[\\d:,\"\'\\`\\_\\|?!\n\r@;]+");

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
    */
    public static void main(String[] args) {
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

