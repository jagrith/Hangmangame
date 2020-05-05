package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
public class choosemode {
	choosemode(){    
		JFrame f=new JFrame("choose mode"); 
		JButton b=new JButton("Single Player");    
		b.setBounds(150,250,300, 40);    
		JButton b1= new JButton("Dual player");
		b1.setBounds(150, 300, 300, 40);
					//add to frame
		f.add(b1);
		f.add(b);    
		f.setSize(600,600);    
		f.setLayout(null);    
		f.setVisible(true);    
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   	
							//action listener
		b.addActionListener(new ActionListener() {      
			@Override
			public void actionPerformed(ActionEvent arg0) {
				f.setVisible(false);	
				new Main();			
					
			}          
	      });
		b1.addActionListener(new ActionListener() {
	        
			@Override
			public void actionPerformed(ActionEvent arg0) {
				f.setVisible(false);
				new GameSetting();
				//HangMan_DualPlayer hangd = new HangMan_DualPlayer();
				//hangd.EnterWord(); 
			}          
	      });
		}         
	
	
		public static void main(String[] args) {    
		    new choosemode();    
		}    
 }