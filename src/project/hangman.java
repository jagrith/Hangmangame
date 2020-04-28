package project;

import java.util.Scanner;

public class hangman {
	
	public static void main(String[] args) {
		guess han = new guess();
		Scanner sc = new Scanner(System.in);
		System.out.println("****************WELCOME TO HANGMAN******************\n");
		System.out.println("                                              GUESS THE WORD                                                        \n");

		while (han.no_of_tries < 8 && han.dash.contains(".")) {
			
			System.out.println("Guess any letter in the word");
			System.out.println(han.dash);
			String guess = sc.next();
			han.hang(guess);
		}
		while (han.no_of_tries == 8 ) {
			System.out.println("---------------------------------------Better luck next time------------------------------------------------------");
			break;
		}
		
		sc.close();
	}
}