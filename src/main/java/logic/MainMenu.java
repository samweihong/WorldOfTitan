package logic;

import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        System.out.println("Welcome to World Of Titan!");
        do{
            System.out.println("""
            
            1.  Eren's Allies
            2.  Soldiers Arrangement and Grouping
            3.  Titan Evaluation and Killing Priority
            4.  Scouting Mission Inside The Wall
            5.  Best Path to Kill Titan
            6.  Marley Word Converter
            7.  Protecting Wall of Maria
            8.  Extra: Titan Evaluation and Killing Priority
            9.  Extra: Best Path to Kill Titan 1
            10. Extra: Best Path to Kill Titan 2
            11. Real Battle
            12. Marley Word Converter with Cipher
            
            Please make your selection: """);

            input = scanner.nextInt();
            if(input < 1 || input > 12) System.out.println("Invalid input, please try again");
            System.out.println();

        }while(input < 1 || input > 12);



    }
}
