import java.util.Scanner;
import java.util.Arrays;

public class Main {
    private final static int[][] magicSquare = {
            {4, 9, 2},
            {3, 5, 7},
            {8, 1, 6}
    };

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String playAgain = "";
        while (!playAgain.equalsIgnoreCase("n")) {
            clearScreen();
            String[][] board = {
                    {"   A   B   C "},
                    {"1", " ", " ", " ", " ", "|", " ", " ", " ", "|", " ", " ", " "},
                    {"  -----------"},
                    {"2", " ", " ", " ", " ", "|", " ", " ", " ", "|", " ", " ", " "},
                    {"  -----------"},
                    {"3", " ", " ", " ", " ", "|", " ", " ", " ", "|", " ", " ", " "}
            };
            int[][] actualMagicSquare = new int[3][3];
            String previousTurn = "";
            String currentTurn = firstTurn();
            while (!winCondition(actualMagicSquare) && !tieCondition(actualMagicSquare)) {
                clearScreen();
                for (String[] line : board) {
                    for (String character : line) {
                        System.out.print(character);
                    }
                    System.out.println();
                }
                if (previousTurn.equalsIgnoreCase("x") && !previousTurn.equals("")) {
                    currentTurn = "O";
                } else if (!previousTurn.equals("")){
                    currentTurn = "X";
                }
                System.out.println("\n" + currentTurn + "'s turn ");
                System.out.print("Select a coordinate (i.e. B2): ");
                String coordinate = scan.nextLine().toLowerCase();
                while (!coordinate.contains("b") && !coordinate.contains("a") && !coordinate.contains("c") || !coordinate.contains("1") && !coordinate.contains("2") && !coordinate.contains("3")) {
                    System.out.print("Please select a valid coordinate: ");
                    coordinate = scan.nextLine().toLowerCase();
                }
                int row = Integer.parseInt(coordinate.substring(1, 2)) - 1;
                int column;
                if (coordinate.substring(0, 1).equals("a")) {
                    column = 0;
                } else if (coordinate.substring(0, 1).equals("b")){
                    column = 1;
                } else {
                    column = 2;
                }
                // checks to see if coordinate has already been selected
                while (!board[row * 2 + 1][column * 4 + 3].equals(" ")) {
                    System.out.print("That coordinate has already been selected \nPlease selected a new one: ");
                    coordinate = scan.nextLine().toLowerCase();
                    row = Integer.parseInt(coordinate.substring(1, 2)) - 1;
                    if (coordinate.substring(0, 1).equals("a")) {
                        column = 0;
                    } else if (coordinate.substring(0, 1).equals("b")){
                        column = 1;
                    } else {
                        column = 2;
                    }
                }
                board[row * 2 + 1][column * 4 + 3] = currentTurn;
                if (currentTurn.equalsIgnoreCase("x")) {
                    actualMagicSquare[row][column] = magicSquare[row][column];
                } else {
                    actualMagicSquare[row][column] = magicSquare[row][column] * 2;
                }
                previousTurn = currentTurn;
            }
            clearScreen();
            for (String[] line : board) {
                for (String character : line) {
                    System.out.print(character);
                }
                System.out.println();
            }
            if (winCondition(actualMagicSquare)) {
                System.out.println("Congratulations, " + winner(actualMagicSquare) + " wins!");
            } else if (tieCondition(actualMagicSquare)) {
                System.out.println("It's a tie!");
            }
            
            System.out.print("Would you like to play again? (y/n): ");
            playAgain = scan.nextLine();
        }
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static String firstTurn() {
        if (Math.random() >= .5) {
            return "X";
        } else {
            return "O";
        }
    }

    private static boolean winCondition(int[][] arr) {
        // checks each row for a win
        int sumLine = 0;
        for (int[] row : arr) {
            for (int element : row) {
                sumLine += element;
            }
            if (sumLine == 15 || sumLine == 30) {
                return true;
            }
            sumLine = 0;
        }
        // checks each column for a win
        for (int column = 0; column < arr[0].length; column ++) {
            for (int row = 0; row < arr.length; row ++) {
                sumLine += arr[row][column];
            }
            if (sumLine == 15 || sumLine == 30) {
                return true;
            }
            sumLine = 0;
        }
        // checks first diagonal for a win
        sumLine = (arr[0][0] + arr[1][1] + arr[2][2]);
        if (sumLine == 15 || sumLine == 30) {
            return true;
        }
        // checks second diagonal for a win
        sumLine = (arr[0][2] + arr[1][1] + arr[2][0]);
        return sumLine == 15 || sumLine == 30;
    }

    private static String winner(int[][] arr) {
        // checks each row for a win
        int sumLine = 0;
        for (int[] row : arr) {
            for (int element : row) {
                sumLine += element;
            }
            if (sumLine == 15) {
                return "X";
            } else if (sumLine == 30) {
                return "O";
            }
            sumLine = 0;
        }
        // checks each column for a win
        for (int column = 0; column < arr[0].length; column ++) {
            for (int row = 0; row < arr.length; row ++) {
                sumLine += arr[row][column];
            }
            if (sumLine == 15) {
                return "X";
            } else if (sumLine == 30) {
                return "O";
            }
            sumLine = 0;
        }
        // checks first diagonal for a win
        sumLine = (arr[0][0] + arr[1][1] + arr[2][2]);
        if (sumLine == 15) {
            return "X";
        } else if (sumLine == 30) {
            return "O";
        }
        // checks second diagonal for a win
        sumLine = (arr[0][2] + arr[1][1] + arr[2][0]);
        if (sumLine == 15) {
            return "X";
        } else if (sumLine == 30) {
            return "O";
        }
        return "yay";
    }

    private static boolean tieCondition(int[][] arr) {
        for (int[] row : arr) {
            for (int element : row) {
                if (element == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
