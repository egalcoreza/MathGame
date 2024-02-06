
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author TheFlash
 */
public class MathGame implements ActionListener {
    
    Font buttonFont = new Font("SansSerif", Font.BOLD, 30);
    JButton[] numButtons = new JButton[10];
    JButton[] funcButtons = new JButton[2];

    JTextField textfield = new JTextField(20);
    
    // DECLARE BUTTONS
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
    
//    // UNUSED
//    JButton addition = new JButton("Addition");
//    JButton subtraction = new JButton("Subtraction");
    
    public JPanel createGamePanel(){
        JPanel mainPanel = new JPanel();
        
//        // UNUSED
//        JPanel topPanel = new JPanel();
//        JLabel firstNum = new JLabel("1");
//        JLabel secondNum = new JLabel("2");
//        JLabel operator = new JLabel("+");

        mainPanel.setLayout(new BorderLayout());
        
        mainPanel.add(textfield, BorderLayout.NORTH);
        mainPanel.add(createCalculator(), BorderLayout.SOUTH);
        
        return mainPanel;        
    }
    
    public JPanel createCalculator(){
        JPanel calPanel = new JPanel();
        calPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // TEXTFIELD ATTRIBUTES
        textfield.setFont(buttonFont);
        textfield.setEditable(false);
        textfield.setBackground(Color.WHITE);
        
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
            myButton.addActionListener(this);
        }
        
        for (JButton func : funcButtons){
            func.setFont(buttonFont);
            func.setFocusable(false);
            func.addActionListener(this);
        }
        
        // MAKE THE GRIDS FOR THE NUMBERS
        // ROW 1
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.ipadx = 40;         // Add internal padding
        gbc.ipady = 40;         // Add internal padding
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
        calPanel.add(textfield, gbc);
        
        return calPanel;
    }
    
    private static void createAndShowGUI(){
        JFrame frame = new JFrame("Math Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        //Display the panel
        MathGame mathGame = new MathGame();
        frame.add(mathGame.createGamePanel(), BorderLayout.CENTER);
        
        //Display the window
        frame.setSize(500, 700);
        frame.setVisible(true);
        
    }
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0; i<numButtons.length; i++){
            if (e.getSource() == numButtons[i]){
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == clrButton){
                textfield.setText("");
        }
        if (e.getSource() == delButton){ 
            textfield.setText(textfield.getText().replaceAll(".$", "")); //Replace last character with "";
        }         
    }
}
