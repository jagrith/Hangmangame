package project;

import java.util.Scanner;

public class hangman {

	private static String[] words = {"java", "program", "computer"};
	private static String word = words[(int) (Math.random() * words.length)];
	private static String dash = new String(new char[word.length()]).replace("\0", ".");
	private static int count = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (dash.contains(".")) {
			System.out.println("Guess any letter in the word");
			System.out.println(dash);
			String guess = sc.next();
			hang(guess);
		}
		sc.close();
	}

	public static void hang(String guess) {
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
			count++;
		} else {
			dash = newdash;
		}
		
	}
}