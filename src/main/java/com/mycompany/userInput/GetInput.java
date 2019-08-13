package com.mycompany.userInput;

import java.util.Scanner;

public class GetInput {
    public static boolean firstResponse;
    public static boolean secondResponse;
    public static boolean thirdResponse;
    public static boolean fourthResponse;
    public static boolean fifthResponse;

    static Scanner scanner = new Scanner(System.in);

    public static void getSongInputs() {        
        firstSongInput();
        secondSongInput();
        thirdSongInput();
        fourthSongInput();
        fifthSongInput();

        scanner.close();
    }

    public static boolean getUserGoal() {
        System.out.println("Would you like to recalculate song preferences? (Type 'Y' or 'N')");

        String userGoalInput = scanner.nextLine();

        if (userGoalInput.equals("Y") || userGoalInput.equals("y") || userGoalInput.equals("YES") || userGoalInput.equals("yes") || userGoalInput.equals("Yes")) {
            System.out.println("Recalculating song preferences.");
            return true;
        } else {
            System.out.println("Not recalculating song preferences.");
            scanner.close();
            return false;
        }
    }

    public static void firstSongInput() {
        System.out.println("Do you like Spaceships by Nicki Minaj? (Y/N)");

        String firstSongRes = scanner.nextLine();
        if (firstSongRes.equals("Y")) {
            firstResponse = true;
        } else if (firstSongRes.equals("N")) {
            firstResponse = false;
        } else {
            System.out.println("Your response was " + firstSongRes + ". Please enter Y or N.");
            firstSongInput();
        }
    }

    public static void secondSongInput() {
        System.out.println("Do you like Enter Sandman by Metallica? (Y/N)");
        String secondSongRes = scanner.nextLine();
        if (secondSongRes.equals("Y")) {
            secondResponse = true;
        } else if (secondSongRes.equals("N")) {
            secondResponse = false;
        } else {
            System.out.println("Your response was " + secondSongRes + ". Please enter Y or N.");
            secondSongInput();
        }
    }

    public static void thirdSongInput() {
        System.out.println("Do you like Some Beach by Blake Shelton? (Y/N)");
        String thirdSongRes = scanner.nextLine();
        if (thirdSongRes.equals("Y")) {
            thirdResponse = true;
        } else if (thirdSongRes.equals("N")) {
            thirdResponse = false;
        } else {
            System.out.println("Your response was " + thirdSongRes + ". Please enter Y or N.");
            thirdSongInput();
        }
    }

    public static void fourthSongInput() {
        System.out.println("Do you like Symphony #9 by Beethoven? (Y/N)");
        String fourthSongRes = scanner.nextLine();
        if (fourthSongRes.equals("Y")) {
            fourthResponse = true;
        } else if (fourthSongRes.equals("N")) {
            fourthResponse = false;
        } else {
            System.out.println("Your response was " + fourthSongRes + ". Please enter Y or N.");
            fourthSongInput();
        }
    }

    public static void fifthSongInput() {
        System.out.println("Do you like In Da Club by 50 Cent? (Y/N)");
        String fifthSongRes = scanner.nextLine();
        if (fifthSongRes.equals("Y")) {
            fifthResponse = true;
        } else if (fifthSongRes.equals("N")) {
            fifthResponse = false;
        } else {
            System.out.println("Your response was " + fifthSongRes + ". Please enter Y or N.");
            fifthSongInput();
        }
    }
}