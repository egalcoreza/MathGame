import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.border.EmptyBorder;

public class MathGame extends JFrame {

    private JPanel difficultyPanel;
    private JPanel gamePanel;
    private JPanel gamePanel_1;
    private JPanel gamePanel_2;
    private JPanel gamePanel_3;
    
    private Timer t;
    private TimerTask tt;
    private JLabel gameTimer;
    private int num;
    private MouseTest mt;
    private int expectedAnswer;
    
    private JLabel ques;
    private JLabel questionLabel;
    private JTextField answerField;
    private JButton submitButton;
    private String operator;
    private int num1;
    private int num2;
    private int rnum1;
    private int rnum2;

    private int currentQuestion;
    private int correctAnswers;
    private int totalQuestions;

    private Operation currentOperation;

    private Random random = new Random();
    
    Font buttonFont = new Font("SansSerif", Font.BOLD, 30);
    JButton[] numButtons = new JButton[10];
    JButton[] funcButtons = new JButton[3];
    
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
    JButton negativeButton = new JButton("-");
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
        
        JButton randomButton = new JButton("Random");
        randomButton.setBounds(187,570,100,50);

        easyButton.addActionListener(new DifficultyButtonListener(Operation.ADDITION, 10));
        mediumButton.addActionListener(new DifficultyButtonListener(Operation.SUBTRACTION, 10));
        hardButton.addActionListener(new DifficultyButtonListener(Operation.MLTIPLICATION, 10));
        randomButton.addActionListener(new DifficultyButtonListener(Operation.RNDM, 10));


        difficultyPanel.add(tmath);
        difficultyPanel.add(subtthegame);
        difficultyPanel.add(choose);
        difficultyPanel.add(easyButton);
        difficultyPanel.add(mediumButton);
        difficultyPanel.add(hardButton);
        difficultyPanel.add(randomButton);
    }

    private void createGamePanel() {
        gamePanel = new JPanel();
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));
        gamePanel.setBorder(new EmptyBorder(10,20,10,20)); // ADDED BORDER/PADDING
        
        gamePanel_1 = new JPanel(); 
        gamePanel_1.setLayout(new BoxLayout(gamePanel_1, BoxLayout.Y_AXIS));
        gamePanel_2 = new JPanel(); 
        gamePanel_2.setLayout(new GridLayout(2,1));
        
        gamePanel_3 = new JPanel(); gamePanel_3.setBackground(Color.yellow);
        
        
        gameTimer = new JLabel("", SwingConstants.RIGHT);
        gameTimer.setFont(buttonFont);

        ques = new JLabel("");
        ques.setFont(new Font("Impact",Font.PLAIN,23));
        
        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Cambria Math",Font.PLAIN,48));
        
        answerField = new JTextField();
        answerField.setEditable(false);
        answerField.setFont(new Font("Arial",Font.PLAIN,32));
        answerField.setHorizontalAlignment(JTextField.CENTER);
        
        submitButton = new JButton("Submit");

        submitButton.addActionListener(new SubmitButtonListener());
        
        JPanel calPanel = new JPanel();
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
        funcButtons[2] = negativeButton;
        
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
                    if (e.getSource() == negativeButton){ 
                        answerField.setText(answerField.getText().concat("-"));
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
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        calPanel.add(negativeButton, gbc);
        
        // TOP TEXTFIELD
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10,0,10,0); // Add external padding
        gbc.fill = GridBagConstraints.BOTH;
        calPanel.add(answerField, gbc);
        
        // SUBMIT
        gbc.gridx = 3;
        gbc.gridy = 0;
//        gbc.insets = new Insets(10,0,10,0); // Add external padding
        gbc.fill = GridBagConstraints.BOTH;
        calPanel.add(submitButton, gbc);
             
        // GAME PANEL 1
        
        gamePanel_1.setLayout(new BorderLayout());
        gamePanel_1.add(gameTimer, BorderLayout.EAST);
        
        // GAME PANEL 2
        ques.setAlignmentX(Component.RIGHT_ALIGNMENT);
        gamePanel_2.add(ques);
        
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gamePanel_2.add(questionLabel);

        gamePanel.add(gamePanel_1);
        gamePanel.add(gamePanel_2);

        gamePanel.add(calPanel);
        
    }

    private void startGame(Operation operation, int numQuestions) {
        mt = new MouseTest();
        num=30;
        currentQuestion = 0;
        correctAnswers = 0;
        totalQuestions = numQuestions;
        currentOperation = operation;
        t = new Timer();
        tt = new TimerTask(){
            @Override
            public void run() {
                gameTimer.setText(Integer.toString(num));
                num--;
                if (num<0){
                    t.cancel();
                }
            }
            
        };
        t.schedule(tt, 0, 1000);

        setContentPane(gamePanel);
        updateQuestion();
        revalidate();
        repaint();
    }

    private void updateQuestion() {
        if (currentQuestion < totalQuestions && num!=-1) {
            num1 = random.nextInt(9)+1;
            num2 = random.nextInt(9)+1;

            expectedAnswer = 0;
            operator = null;

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
                    
                case RNDM:
                    mt.setVisible(true);
                    mt.addMouseMotionListener(new MouseAdapter(){
                        @Override
                        public void mouseClicked(MouseEvent me){
                            Operation randomOperation = generateRandomOperation();
                            rnum1 = mt.getEndX() - mt.getStartX();
                            rnum2 = mt.getEndY() - mt.getStartY();
                            switch (randomOperation) {
                            case ADDITION:
                                expectedAnswer = rnum1 + rnum2;
                                operator = "+";
                                break;
                            case SUBTRACTION:
                                expectedAnswer = rnum1 - rnum2;
                                operator = "-";
                                break;
                            case MLTIPLICATION:
                                num2 = (num2 == 0) ? 1 : rnum2;
                                expectedAnswer = rnum1 * rnum2;
                                operator = "*";
                                break;
                                default:
                                    throw new IllegalArgumentException("Invalid operation");
                            }
                            ques.setText("Question " + (currentQuestion + 1) + ":");
                            questionLabel.setText(rnum1 + " " + operator + " " + rnum2 + " = ?") ;
                            questionLabel.putClientProperty("answer", expectedAnswer);
                            System.out.println(rnum1); // NOT PRINTINGGG
                        }
                    });
           }
           switch (currentOperation){
               case ADDITION:
               case SUBTRACTION:
               case MLTIPLICATION:
                   ques.setText("Question " + (currentQuestion + 1) + ":");
                   questionLabel.setText(num1 + " " + operator + " " + num2 + " = ?") ;
                   questionLabel.putClientProperty("answer", expectedAnswer);
           }
        } else {
            showResults();
        }
    }

    private void showResults() {
        JOptionPane.showMessageDialog(this, "Game Over! \nYou got " + correctAnswers +
                " out of " + totalQuestions + " questions correct.", "Results", JOptionPane.INFORMATION_MESSAGE);

        setContentPane(difficultyPanel);
        revalidate();
        repaint();
    }

    private Operation generateRandomOperation() {
int randomIndex = random.nextInt(Operation.values().length);
    return Operation.values()[randomIndex];    }

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
        MLTIPLICATION,
        RNDM
    }
}
