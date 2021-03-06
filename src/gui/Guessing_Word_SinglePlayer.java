package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Guessing_Word_SinglePlayer {
    int failCounter = HangMan_SinglePlayer.failCounter;
    String whichHangmanPath = "/ImageAssets/hangman" + failCounter + ".png";
    JTextField tf;
	JLabel jlLetsUsed;
	String letter;
	int[] wordLength = new int[64];
	int level = (int) (Math.random() * 64);// random word
	JFrame frame = new JFrame();	
	String[] wordList = HangMan_SinglePlayer.wordList;
	JLabel picLabel = new JLabel();
	JFrame gameFrame = new JFrame();
    
    private void restart()
    {
    	int dialogResult = JOptionPane.showConfirmDialog(null, 
                        "\nWould You Like to Start a New Game?",
                        "Play Again?",
                        JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    new choosemode();
                gameFrame.setVisible(false);
                }                
                else
                    System.exit(0);
            }
    

	
Guessing_Word_SinglePlayer() {

	JPanel bottomRight = new JPanel();
        JPanel bottomLeft = new JPanel();
        JPanel top = new JPanel();
        JPanel bottom = new JPanel();
        JPanel imgPane = new JPanel();
        JPanel panel1 = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
        imgPane.setLayout(new BorderLayout());
        panel1.setLayout(new BorderLayout());
        panel1.setOpaque(false);// !!
        top.setBorder(BorderFactory.createTitledBorder(""));
        bottom.setBorder(BorderFactory.createTitledBorder(""));
        tf = new JTextField(1);
        JLabel jl = new JLabel("Enter a letter", JLabel.CENTER);
        jlLetsUsed = new JLabel("Letters used: ", JLabel.CENTER);
        final JLabel jlLines = new JLabel("__ ", JLabel.CENTER);
        jl.setFont(new Font("Rockwell", Font.PLAIN, 20));
        tf.setFont(new Font("Rockwell", Font.PLAIN, 20));
        jlLetsUsed.setFont(new Font("Rockwell", Font.PLAIN, 20));
        jlLines.setFont(new Font("Rockewell", Font.PLAIN, 20));
        top.add(jl);// top center
        top.add(tf);// top center
        bottomLeft.add(jlLetsUsed);// bottom left position
        bottomRight.add(jlLines);// bottom right position
        bottom.add(bottomLeft);// bottom
        bottom.add(bottomRight);// bottom
        panel1.add(imgPane, BorderLayout.CENTER);// background image (center)
        panel1.add(top, BorderLayout.NORTH);
        panel1.add(bottom, BorderLayout.SOUTH);// blank spaces and letters used
        gameFrame.setTitle("Hangman");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
        gameFrame.add(panel1);
        gameFrame.setSize(800, 800);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
        int j = 0;
        String line = "";
        for (j = 0; j < 64; j++) {
            wordLength[j] = wordList[j].length();
        }
        int m = 0;
        while (m < wordLength[level]) {
            line += "__ ";
            m++;
        }

        jlLines.setText(line);

        tf.addActionListener(new ActionListener() {
            int right = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
            	imgPane.remove(picLabel);
                JTextField tf = (JTextField) e.getSource();
                letter = tf.getText();
                tf.setText("");
                jlLetsUsed.setText(jlLetsUsed.getText() + letter + " ");
                char[] jlabelText = jlLines.getText().toCharArray();
                char userEnteredChar = letter.charAt(0);
                if (!wordList[level].contains(letter)) {
                	failCounter++;
                	if(failCounter > 6) {
	                	restart();
	                }
                	else{
                		String whichHangmanPath = "/home/jagrith/eclipse-workspace/project/src/ImageAssets/hangman" + failCounter + ".png";		
                	try {
                		BufferedImage myPicture = ImageIO.read(new File(whichHangmanPath));
                		picLabel = new JLabel(new ImageIcon(myPicture));
                		imgPane.add(picLabel);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                    return;                    
                	}                    
                }
                int i = 0;

                for (i = 0; i < wordList[level].length(); i++) {

                    if (wordList[level].charAt(i) == userEnteredChar) {

                        jlabelText[3 * i] = ' ';

                        jlabelText[3 * i + 1] = userEnteredChar;

                        right++;
                        

                    }// end if

                }// end for

                jlLines.setText(String.valueOf(jlabelText));

                if (jlabelText.length / 3 == right) {


                }
                if(right == wordList[level].length()) {
                	int dialogResult = JOptionPane.showConfirmDialog(null, 
	                        "\nYou Won!!! \nWould You Like to Start a New Game?",
	                        "Play Again?",
	                        JOptionPane.YES_NO_OPTION);
	                if (dialogResult == JOptionPane.YES_OPTION) {
	                    new choosemode();
	                gameFrame.setVisible(false);
	                }                
	                else
	                    System.exit(0);
                	
                }


            }// end actionPerformed method

        });
	}

}
