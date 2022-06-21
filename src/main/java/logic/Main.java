package logic;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final String MENU = """
                |===========================================|========================================|
                |============= BASIC FEATURES ==============|============= EXTRA FEATURES ===========|
                |===========================================|========================================|
                | 1. Eren’s Allies                          |                                        |
                | 2. Soldiers Arrangement and Grouping      |  8. Fight or Flight                    |
                | 3. Titan Evaluation and Killing Priority  |  9. Moving Titan?!                     |
                | 4. Scouting Mission inside the Wall       | 10. Obstacles Everywhere               |
                | 5. Best Path to Kill Titan                | 11. Enter REAL Battle                  |
                | 6. Marley Word Converter                  | 12. Marley Word Converter with Cipher  |
                | 7. Protecting Wall of Maria               |                                        |
                |===========================================|========================================|
                """;
    private static final String PROMPT = "Select your choice, or select 0 to quit : ";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the World of Titan!");

        for (int choice = -1; choice != 0; ) {
            System.out.println(MENU);

            while (true) {
                System.out.print(PROMPT);
                try {
                    choice = input.nextInt();
                    if (choice == 0) break;
                    if (choice < 0 || choice > 12) throw new IllegalArgumentException();

                    System.out.println();

                    switch (choice) {
                        case 4 -> Graph.startHamiltonianCycle();
                        case 5 -> Graph.startBestPathToKillTitan();
                        case 6 -> WordConverter.startConvertWords();
                        case 7 -> WallOfMaria.start();
                        case 11 -> RealBattle.start();
                    }

                    System.out.println("(Press Enter to continue...)");
                    input.nextLine();
                    input.nextLine();

                    break;

                } catch (InputMismatchException | IllegalArgumentException e) {
                    System.out.println("Invalid choice! Please enter again.\n");
                    input = new Scanner(System.in);
                }
            }
        }
        input.close();
        System.out.println("Thanks for playing! See you next time :)");
    }
}
