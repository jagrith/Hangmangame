package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class HangMan_DualPlayer {
	static String[] ar = {""};
	JTextField textfield;

	public void EnterWord(){    
		JFrame f=new JFrame("Enter Word"); 
					//submit button
		JButton b=new JButton("Play");    
		b.setBounds(100,100,140, 40);    
					//enter name label
		JLabel label = new JLabel();		
		label.setText("Enter word :");
		label.setBounds(10, 10, 100, 100);
					//empty label which will show event after button clicked
		JLabel label1 = new JLabel();
		label1.setBounds(10, 110, 200, 100);
					//textfield to enter name
		textfield= new JTextField();
		textfield.setBounds(110, 50, 130, 30);
					//add to frame
		f.add(label1);
		f.add(textfield);
		f.add(label);
		f.add(b);    
		f.setSize(300,300);    
		f.setLayout(null);    
		f.setVisible(true);    
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
		
							//action listener
		b.addActionListener(new ActionListener() {
	        
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ar[0] = textfield.getText();				
				new Guessing_Word_DualPlayer();
								
			}          
	      });
		}         	

}
