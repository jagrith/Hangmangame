/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author shak
 */
public class GameSetting extends JFrame {
    private final int WIDTH;
    
    private final int HEIGHT;
    
    private final int MAX_INCORRECT;

    private final int MAX_WORD_LENGTH;
    
    private final String HANGMAN_IMAGE_DIRECTORY;
    
    private final String HANGMAN_ImageProp;

    private final String HANGMAN_IMAGE_BASE_NAME;
 
    private final String LETTER_IMAGE_DIRECTORY;
    
    private final String LETTER_ImageProp;
 
    private LetterSetting gameRack;

    private Hangman gameHangman;
    
    private int numIncorrect;

    private JLabel correct;

    private JLabel incorrect;

    private String word;

    private StringBuilder hiddenW;
    
    public GameSetting() 
    {
        WIDTH = 500;
        HEIGHT = 500;
        MAX_INCORRECT = 5;
        MAX_WORD_LENGTH = 15;
        
        HANGMAN_IMAGE_DIRECTORY = LETTER_IMAGE_DIRECTORY = "images/";
        HANGMAN_ImageProp = LETTER_ImageProp = ".png";
        HANGMAN_IMAGE_BASE_NAME = "hang";
        
        setTitle("Hangman");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        addCloseWindowListener();
        
        initialize();
    
}

private void initialize()
    {        
        numIncorrect = 0;
        
        correct = new JLabel("Word: ");
        incorrect = new JLabel("Incorrect Guesses: " + numIncorrect);
        word = new String();
        hiddenW = new StringBuilder();
        
        getword();
        addTextPanel();
        addLetterSetting();
        addHangman();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2,
                dim.height / 2 - getSize().height / 2 - 200);
        setVisible(true);
    }
    

    private void addCloseWindowListener()
    {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent we)
            {
                int prompt = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to quit?",
                        "Quit?", 
                        JOptionPane.YES_NO_OPTION);
                
                if (prompt == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });
    }
    

    private void addTextPanel()
    {
    	
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(1,2));
        textPanel.add(correct);
        textPanel.add(incorrect);
  
        add(textPanel, BorderLayout.NORTH);
    }
  
    private void addLetterSetting()
    {
        gameRack = new LetterSetting(word, 
                LETTER_IMAGE_DIRECTORY, 
                LETTER_ImageProp);
        gameRack.attachListeners(new TileListener());
        add(gameRack, BorderLayout.SOUTH);
    }
    

    private void addHangman()
    {
        JPanel hangmanPanel = new JPanel();
        gameHangman = new Hangman(HANGMAN_IMAGE_BASE_NAME,
                HANGMAN_IMAGE_DIRECTORY,
                HANGMAN_ImageProp);
        hangmanPanel.add(gameHangman);
        add(hangmanPanel, BorderLayout.CENTER);
    }
    

    private void getword()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] options = {"Play", "Quit"};
        JPanel wordPanel = new JPanel();
        JLabel wordLabel = new JLabel("Enter a Word: ");
        JTextField wordText = new JTextField(MAX_WORD_LENGTH);
        wordPanel.add(wordLabel);
        wordPanel.add(wordText);
        int confirm = -1;
        
        while (word.isEmpty())
        {
            confirm = JOptionPane.showOptionDialog(null, 
                    wordPanel, 
                    "Enter Word", 
                    JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, 
                    null, 
                    options, 
                    options[0]);

            if (confirm == 0)
            {
                word = wordText.getText();
                
                if (!word.matches("[a-zA-Z]+") || 
                    word.length() > MAX_WORD_LENGTH)
                {
                    JOptionPane.showMessageDialog(null, 
                            "word must be less than 15 characters and " +
                            "only contain letters A-Z.", 
                            "Invalid word", 
                            JOptionPane.ERROR_MESSAGE);
                    word = ""; 
                }
            }
                    
            else if (confirm == 1)
                System.exit(0);
        }
        

        hiddenW.append(word.replaceAll(".", "*"));
        correct.setText(correct.getText() + hiddenW.toString());
    }
    

    private void newGameDialog()
    {
        int dialogResult = JOptionPane.showConfirmDialog(null, 
                "The word was: " + word +
                "\nWould You Like to Start a New Game?",
                "Play Again?",
                JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION)
            initialize(); // re-initialize the GameBoard
        else
            System.exit(0);
    }
    

    private class TileListener implements MouseListener 
    {
        @Override
        public void mousePressed(MouseEvent e) 
        {
            Object source = e.getSource();
            if(source instanceof LetterImgs)
            {
                char c = ' ';
                int index = 0;
                boolean updated = false;
                
            
                LetterImgs tilePressed = (LetterImgs) source;
                c = tilePressed.guess();
                
          
                while ((index = word.toLowerCase().indexOf(c, index)) != -1)
                {
                    hiddenW.setCharAt(index, word.charAt(index));
                    index++;
                    updated = true;
                }
                
         
                if (updated)
                {
                    correct.setText("Word: " + hiddenW.toString());
                    
                    if (hiddenW.toString().equals(word))
                    {
                        gameRack.removeListeners();
                        gameHangman.winImage();
                        newGameDialog();
                    }
                }
                
   
                else
                {
                    incorrect.setText("Incorrect: " + ++numIncorrect);
                    
                    if (numIncorrect >= MAX_INCORRECT)
                    {
                        gameHangman.loseImage();
                        gameRack.removeListeners();
                        newGameDialog();
                    }
                    
                    else
                        gameHangman.nextImage(numIncorrect);
                }
            }
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {}  

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}
        
        @Override
        public void mouseExited(MouseEvent e) {}
    }
}
