 package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;



public class HangMan_SinglePlayer {
	
	JTextField tf;
	JLabel jlLetsUsed;
	String letter;
	int[] wordLength = new int[64];
	int level = (int) (Math.random() * 64);// random word
	JFrame frame = new JFrame();
    static int failCounter = 1;

	String whichHangmanPath = "/ImageAssets/hangman" + failCounter + ".png";

	String token1 = "";
	Scanner infile1;
	static String[] wordList;
	public static void ImageDemo(final String filename) throws Exception
	  {
	    SwingUtilities.invokeLater(new Runnable()
	    {
	      public void run()
	      {
	        JFrame editorFrame = new JFrame("Image Demo");
	        
	        BufferedImage image = null;
	        try
	        {
	        	System.out.println(filename);
	          image = ImageIO.read(new File(filename));
	        }
	        catch (Exception e)
	        {
	          e.printStackTrace();
	          System.exit(1);
	        }
	        ImageIcon imageIcon = new ImageIcon(image);
	        JLabel jLabel = new JLabel();
	        jLabel.setIcon(imageIcon);
	        editorFrame.getContentPane().add(jLabel, BorderLayout.CENTER);
	        editorFrame.pack();
	        editorFrame.setLocationRelativeTo(null);
	        editorFrame.setVisible(true);
	      }
	    });
	  }
	
	HangMan_SinglePlayer(){	
		try {
			String arbitrary_path = "/home/jagrith/Desktop/word.txt";
			File f = new File(arbitrary_path);
			infile1 = new Scanner(f);
		} catch (FileNotFoundException fe){
		    fe.printStackTrace();
		}
		List<String> temps = new ArrayList<String>();
	    // while loop
	    while (infile1.hasNext()) {
	      // find next line
	      token1 = infile1.next();
	      temps.add(token1);
	    }
	    infile1.close();
	    wordList = temps.toArray(new String[0]);
	} 
}
