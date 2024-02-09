import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MathGame extends JFrame {

    private JPanel difficultyPanel;
    private JPanel gamePanel;

    private JLabel ques;
    private JLabel questionLabel;
    private JTextField answerField;
    private JButton submitButton;

    private int currentQuestion;
    private int correctAnswers;
    private int totalQuestions;

    private Operation currentOperation;

    private Random random = new Random();
    
    Font buttonFont = new Font("SansSerif", Font.BOLD, 30);
    JButton[] numButtons = new JButton[10];
    JButton[] funcButtons = new JButton[2];
    
    JButton oneButton = new JButton("1");
    JButton twoButton = new JButton("2");
    JButton threeButton = new JButton("3");
    JButton fourButton = new JButton("4");
    JButton fiveButton = new JButton("5");
    JButton sixButton = new JButton("6");
    JButton sevenButton = new JButton("7");
    JButton eightButton = new JButton("8");
    JButton nineButton = new JButton("9");
    JButton zeroButton = new JButton("0");
    JButton delButton = new JButton("DEL"); 
    JButton clrButton = new JButton("CLR");

    public MathGame() {
        setTitle("Math Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 750);
        setLocationRelativeTo(null);

        createDifficultyPanel();
        createGamePanel();

        setContentPane(difficultyPanel);

        setVisible(true);
    }

    private void createDifficultyPanel() {
        difficultyPanel = new JPanel();
        difficultyPanel.setLayout(null);
        
        JLabel tmath = new JLabel("MATH");
        tmath.setBounds(116,80,300,85);
        tmath.setFont(new Font("Impact", Font.BOLD, 103));
        
        JLabel subtthegame = new JLabel("THE GAME");
        subtthegame.setBounds(180,170,300,30);
        subtthegame.setFont(new Font("Impact", Font.PLAIN, 28));
        
        JLabel choose = new JLabel("Difficulty:");
        choose.setBounds(180,265,300,30);
        choose.setFont(new Font("Cambria", Font.BOLD, 23));

        JButton easyButton = new JButton("Easy");
        easyButton.setBounds(187,330,100,50);
        
        JButton mediumButton = new JButton("Medium");
        mediumButton.setBounds(187,410,100,50);
        
        JButton hardButton = new JButton("Hard");
        hardButton.setBounds(187,490,100,50);

        easyButton.addActionListener(new DifficultyButtonListener(Operation.ADDITION, 10));
        mediumButton.addActionListener(new DifficultyButtonListener(Operation.SUBTRACTION, 10));
        hardButton.addActionListener(new DifficultyButtonListener(Operation.MLTIPLICATION, 10));

        difficultyPanel.add(tmath);
        difficultyPanel.add(subtthegame);
        difficultyPanel.add(choose);
        difficultyPanel.add(easyButton);
        difficultyPanel.add(mediumButton);
        difficultyPanel.add(hardButton);
    }

    private void createGamePanel() {
        gamePanel = new JPanel();
        gamePanel.setLayout(null);

        ques = new JLabel("");
        ques.setBounds(40,40,300,30);
        ques.setFont(new Font("Impact",Font.PLAIN,23));
        
        questionLabel = new JLabel("");
        questionLabel.setBounds(140,100,300,40);
        questionLabel.setFont(new Font("Cambria Math",Font.PLAIN,48));
        
        answerField = new JTextField();
        answerField.setBounds(45,175,300,65);
        answerField.setEditable(false);
        answerField.setFont(new Font("Arial",Font.PLAIN,32));
        answerField.setHorizontalAlignment(JTextField.CENTER);
        
        submitButton = new JButton("Submit");
        submitButton.setBounds(355,175,100,65);

        submitButton.addActionListener(new SubmitButtonListener());
        
        JPanel calPanel = new JPanel();
        calPanel.setBounds(0,200,500,500);
        calPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // ADD NUMBER BUTTONS TO ARRAY
        numButtons[0] = zeroButton;
        numButtons[1] = oneButton;
        numButtons[2] = twoButton;
        numButtons[3] = threeButton;
        numButtons[4] = fourButton;
        numButtons[5] = fiveButton;
        numButtons[6] = sixButton;
        numButtons[7] = sevenButton;
        numButtons[8] = eightButton;
        numButtons[9] = nineButton;
        
        // ADD FUNCTION BUTTONS TO ARRAY
        funcButtons[0] = delButton;
        funcButtons[1] = clrButton;
        
        // SET FONT FOR BUTTONS
        for (JButton myButton : numButtons) {
            myButton.setFont(buttonFont);
            myButton.setFocusable(false);
            myButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int i=0; i<numButtons.length; i++){
                        if (e.getSource() == numButtons[i]){
                            answerField.setText(answerField.getText().concat(String.valueOf(i)));
                        }
                    }
                }
            });
        }
        
        for (JButton func : funcButtons){
            func.setFont(buttonFont);
            func.setFocusable(false);
            func.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == clrButton){
                            answerField.setText("");
                    }
                    if (e.getSource() == delButton){ 
                        answerField.setText(answerField.getText().replaceAll(".$", "")); //Replace last character with "";
                    } 
                }
            });
        }
        
        // MAKE THE GRIDS FOR THE NUMBERS
        // ROW 1
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.ipadx = 40;         // Add internal padding
        gbc.ipady = 40;         // Add internal padding
        gbc.insets = new Insets(3, 3, 3, 3); // Add external padding
        calPanel.add(sevenButton, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        calPanel.add(eightButton, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        calPanel.add(nineButton, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        calPanel.add(delButton, gbc);
        
        // ROW 2
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        calPanel.add(fourButton, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        calPanel.add(fiveButton, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        calPanel.add(sixButton, gbc);
        
        //ROW 3
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        calPanel.add(oneButton, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        calPanel.add(twoButton, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        calPanel.add(threeButton, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        calPanel.add(clrButton, gbc);
        
        // ROW 4
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        calPanel.add(zeroButton, gbc);
        
        // TOP TEXTFIELD
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(10,0,10,0); // Add external padding
        gbc.fill = GridBagConstraints.BOTH;
        calPanel.add(answerField, gbc);
        
        gamePanel.add(ques);
        gamePanel.add(questionLabel);
        gamePanel.add(answerField);
        gamePanel.add(submitButton);
        gamePanel.add(calPanel);
        
    }

    private void startGame(Operation operation, int numQuestions) {
        currentQuestion = 0;
        correctAnswers = 0;
        totalQuestions = numQuestions;
        currentOperation = operation;

        setContentPane(gamePanel);
        updateQuestion();
        revalidate();
        repaint();
    }

    private void updateQuestion() {
        if (currentQuestion < totalQuestions) {
            int num1 = random.nextInt(9)+1;
            int num2 = random.nextInt(9)+1;

            int expectedAnswer;
            String operator;

            switch (currentOperation) {
                case ADDITION:
                    expectedAnswer = num1 + num2;
                    operator = "+";
                    break;
                case SUBTRACTION:
                    expectedAnswer = num1 - num2;
                    operator = "-";
                    break;
                case MLTIPLICATION:
                    num2 = (num2 == 0) ? 1 : num2;
                    expectedAnswer = num1 * num2;
                    operator = "*";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operation");
            }

            ques.setText("Question " + currentQuestion+1 + ":");
            
            questionLabel.setText(num1 + " " + operator + " " + num2 + " = ?");
            questionLabel.putClientProperty("answer", expectedAnswer);
            
        } else {
            showResults();
        }
    }

    private void showResults() {
        JOptionPane.showMessageDialog(this, "All questions anqesered! \nYou got " + correctAnswers +
                " out of " + totalQuestions + " questions correct.", "Results", JOptionPane.INFORMATION_MESSAGE);

        setContentPane(difficultyPanel);
        revalidate();
        repaint();
    }

    private class DifficultyButtonListener implements ActionListener {
        private Operation operation;
        private int numQuestions;

        public DifficultyButtonListener(Operation operation, int numQuestions) {
            this.operation = operation;
            this.numQuestions = numQuestions;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            startGame(operation, numQuestions);
        }
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentQuestion < totalQuestions) {
                int userAnswer;
                try {
                    userAnswer = Integer.parseInt(answerField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MathGame.this, 
                            "Invalid input. Please enter a number.", "Error", 
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int expectedAnswer = (int) questionLabel.getClientProperty("answer");

                if (userAnswer == expectedAnswer) {
                    correctAnswers++;
                }

                currentQuestion++;
                answerField.setText("");
                updateQuestion();
            } else {
                showResults();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MathGame::new);
    }

    enum Operation {
        ADDITION,
        SUBTRACTION,
        MLTIPLICATION
    }
}

