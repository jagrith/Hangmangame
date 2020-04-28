package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class guess {
	Scanner infile1;
	 String token1 = "";
	 String[] words;
	 String word;
	 String dash; 
	 int no_of_tries = 0;
	 guess(){		 
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
		     words = temps.toArray(new String[0]);
		     word = words[(int) (Math.random() * words.length)];
		     dash = new String(new char[word.length()]).replace("\0", ".");
	 }
	 public void hang(String guess) {
			String newdash = "";
			for (int i = 0; i < word.length(); i++) {
				if (word.charAt(i) == guess.charAt(0)) {
					newdash += guess.charAt(0);
				} else if (dash.charAt(i) != '.') {
					newdash += word.charAt(i);
				} else {
					newdash += ".";
				}
			}
			if (dash.equals(newdash)) {
				no_of_tries++;
			} else {
				dash = newdash;
			}
			if (dash.equals(word)) {
				System.out.println("------------------------------------------------Congratulations!------------------------------------------------\n\n");
				
				System.out.println("You guessed the correct word and the word was " + word);
			} 			
		}

}
