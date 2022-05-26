package WarBetweenPresident.Objects;

import WarBetweenPresident.App;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Board {
    private int president = 0;
    private int soldiers = 0;
    private int lightTanks = 0;
    private int mediumTanks = 0;
    private int heavyTanks = 0;

    private String[] playerBoard = new String[100];
    private String[] recordPlayerBoard = new String[100];

    private String[] computerBoard = new String[100];
    private String[] recordComputerBoard = new String[100];

    private String[] xCoordinates = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
    private String[] yCoordinates = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };

    // Set the board
    public Board() {
        for(int i = 0; i < playerBoard.length; i++) {
            playerBoard[i] = " ";
            recordPlayerBoard[i] = " ";
            computerBoard[i] = " ";
            recordComputerBoard[i] = " ";
        }

        getBoard();
    }

    // Print the board
    public void getBoard() {
        int start = 90;
        int end = 100;

        for (int rows = 0; rows <= 10; rows++) {
            if(rows == 0) {
                System.out.println("\n\n\n\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■     ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\u001B[0m");
                System.out.print("■\u001B[0m # ■ \u001B[0m");
                for(int x = 0; x < xCoordinates.length; x++) System.out.print("\u001B[33m" + xCoordinates[x] + "\u001B[0m ■ \u001B[0m");
                System.out.print("    ■\u001B[0m # ■ \u001B[0m");
                for(int x = 0; x < xCoordinates.length; x++) System.out.print("\u001B[33m" + xCoordinates[x] + "\u001B[0m ■ \u001B[0m");
                System.out.println("\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■     ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\u001B[0m");
            }

            if(rows != 10) {
                System.out.print("■ \u001B[0m\u001B[33m" + yCoordinates[9-rows] + "\u001B[0m ■ \u001B[0m");

                // Board for troops deployment.
                for(int cols = start; cols < end; cols++) {
                    if(cols != end-1) System.out.print("\u001B[31m" + playerBoard[cols] + "\u001B[0m" + " ■ \u001B[0m");
                    else System.out.print("\u001B[31m" + playerBoard[cols] + "\u001B[0m " + " ■ \u001B[0m");
                }

                // Board for battle marks (Missed / Hit).
                System.out.print("    ■ \u001B[0m\u001B[33m" + yCoordinates[9-rows] + "\u001B[0m ■ \u001B[0m");
                for(int cols = start; cols < end; cols++) {
                    if(cols != end-1) System.out.print("\u001B[31m" + recordPlayerBoard[cols] + "\u001B[0m" + " ■ \u001B[0m");
                    else System.out.print("\u001B[31m" + recordPlayerBoard[cols] + "\u001B[0m " + " ■ \u001B[0m");
                }

                start -= 10;
                end -= 10;
                System.out.println("\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■     ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\u001B[0m");
            }
        }
    }

    // Set up the units on the board
    public void positionUnits() {
        while(president != 1) queryPresidentPosition();
        while(soldiers != 5) querySoldiersPosition();
        while(lightTanks != 3) queryLightTankPosition();
        while(mediumTanks != 2) queryMediumTankPosition();
        while(heavyTanks != 1) queryHeavyTankPosition();
    }

    // Make a query for president's position on the board
    private void queryPresidentPosition() {
        boolean isValidPosition = false;
        Scanner in_president = new Scanner(System.in);

        while(!isValidPosition) {
            System.out.println("\nPresident size: 1x1");
            System.out.println("■■■■■\n■ P ■\n■■■■■\n");
            System.out.println("Remaining President: " + (1 - president));
            System.out.print("Enter president position (Ex: a1): ");
            String setPresidentPosition = in_president.nextLine();

            switch(setPresidentPosition.toLowerCase().substring(0, 1)) {
                // Validate input for y (vertical) position of the President
                case "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" -> { isValidPosition = true; }
                default -> {
                    isValidPosition = false;
                }
            }

            switch(setPresidentPosition.substring(1)) {
                // Validate input for x (horizontal) position of the President
                case "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" -> { isValidPosition = true; }
                default -> {
                    isValidPosition = false;
                }
            }

            if(isValidPosition == true) {
                int y = 0;
                int x = 0;

                // Find the y (vertical) position of President
                switch(setPresidentPosition.toLowerCase().substring(0, 1)) {
                    case "a" -> { y = 0; }
                    case "b" -> { y = 10; }
                    case "c" -> { y = 20; }
                    case "d" -> { y = 30; }
                    case "e" -> { y = 40; }
                    case "f" -> { y = 50; }
                    case "g" -> { y = 60; }
                    case "h" -> { y = 70; }
                    case "i" -> { y = 80; }
                    case "j" -> { y = 90; }
                }

                // Find the x (horizontal) position of President
                x = Integer.parseInt(setPresidentPosition.substring(1))-1;

                // Check if the position is available / not available
                isValidPosition = checkAvailablePosition(x + y);

                if(!isValidPosition){
                    // If position is not available
                    System.out.println("Try again! Occupied slot!");
                } else {
                    // If position is available
                    playerBoard[x + y] = "P";
                }

                if(isValidPosition) {
                    president++; // Increment President
                    App.clearTerminal(); // Clear terminal window
                    getBoard(); // Print board
                }
            }
        }
    }
    
    // Make a query for soldier's position on the board
    private void querySoldiersPosition() {
        boolean isValidPosition = false;
        Scanner in_soldiers = new Scanner(System.in);

        while(!isValidPosition) {
            System.out.println("\nSoldiers size: 1x1");
            System.out.println("■■■■■\n■ S ■\n■■■■■\n");
            System.out.println("Remaining Soldiers: " + (5 - soldiers));
            System.out.print("Enter soldiers position (Ex: a1): ");
            String setSoldiersPosition = in_soldiers.nextLine();

            // Validate input for y (vertical) position of Soldiers
            switch(setSoldiersPosition.toLowerCase().substring(0, 1)) {
                case "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" -> { isValidPosition = true; }
                default -> {
                    isValidPosition = false;
                }
            }

            // Validate input for x (horizontal) position of Soldiers
            switch(setSoldiersPosition.toLowerCase().substring(1)) {
                case "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" -> { isValidPosition = true; }
                default -> {
                    isValidPosition = false;
                }
            }

            if(isValidPosition == true) {
                int y = 0;
                int x = 0;

                // Find the y (vertical) position of Soldiers
                switch(setSoldiersPosition.toLowerCase().substring(0, 1)) {
                    case "a" -> { y = 0; }
                    case "b" -> { y = 10; }
                    case "c" -> { y = 20; }
                    case "d" -> { y = 30; }
                    case "e" -> { y = 40; }
                    case "f" -> { y = 50; }
                    case "g" -> { y = 60; }
                    case "h" -> { y = 70; }
                    case "i" -> { y = 80; }
                    case "j" -> { y = 90; }
                }

                // Find the x (hiruzibtak) position of Soldiers
                x = Integer.parseInt(setSoldiersPosition.substring(1))-1;

                isValidPosition = checkAvailablePosition(x + y);

                if(!isValidPosition){
                    // If position is not available
                    System.out.println("Try again! Occupied slot!");
                } else {
                    // Mark 1 position with "S"
                    playerBoard[x + y] = "S";
                }

                if(isValidPosition) {
                    soldiers++; // Increment soldiers
                    App.clearTerminal(); // Clear terminal window
                    getBoard(); // Print board
                }
            }
        }
    }

    // Make a query for light tank's position on the board
    private void queryLightTankPosition() {
        boolean isValidPosition = false;
        Scanner in_lightTanks = new Scanner(System.in);

        while(!isValidPosition) {
            System.out.println("\nLight tank size: 2x1");
            System.out.println("■■■■■■■■■\n■ L ■ L ■\n■■■■■■■■■\n    OR    \n■■■■■\n■ L ■\n■■■■■\n■ L ■\n■■■■■\n");
            System.out.println("Remaining Light Tanks: " + (3 - lightTanks));
            System.out.print("Enter light tank position (Ex: a1 b1): ");
            String setLightTankPosition = in_lightTanks.nextLine();
            String[] lightTankPosition = setLightTankPosition.split(" ");

            if(lightTankPosition.length == 2) {
                position: for(int i = 0; i < lightTankPosition.length; i++) {
                    // Validate input for y (vertical) position of light tanks
                    switch(lightTankPosition[i].split("")[0].toLowerCase()) {
                        case "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" -> { isValidPosition = true; }
                        default -> {
                            isValidPosition = false;
                            break position;
                        }
                    }
    
                    // Validate input for x (horizontal) position of light tanks
                    switch(lightTankPosition[i].substring(1)) {
                        case "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" -> { isValidPosition = true; }
                        default -> {
                            isValidPosition = false;
                            break position;
                        }
                    }
                }
    
                if(isValidPosition == true) {
                    int y = 0;
                    int x = 0;
                    int[] position = new int[2];
    
                    // Find the index position of light tank
                    validPosition: for(int i = 0; i < lightTankPosition.length; i++) {
                        // Find the y (vertical) position of light tank
                        switch(lightTankPosition[i].split("")[0].toLowerCase()) {
                            case "a" -> { y = 0; }
                            case "b" -> { y = 10; }
                            case "c" -> { y = 20; }
                            case "d" -> { y = 30; }
                            case "e" -> { y = 40; }
                            case "f" -> { y = 50; }
                            case "g" -> { y = 60; }
                            case "h" -> { y = 70; }
                            case "i" -> { y = 80; }
                            case "j" -> { y = 90; }
                        }
    
                        // Find the x (horizontal) position of light tank
                        x = Integer.parseInt(lightTankPosition[i].substring(1))-1;
    
                        // Check if the position is available / not available
                        isValidPosition = checkAvailablePosition(x + y);
    
                        if(!isValidPosition){
                            // If position is not available
                            System.out.println("Try again! Occupied slot!");
                            break validPosition;
                        } else {
                            // If position is available
                            position[i] = x + y;
                        }
                    }

                    // Check if light tank position 2x2 is valid
                    if(Math.abs(position[0] - position[1]) == 1 || Math.abs(position[0] - position[1]) == 10) {
                        if(isValidPosition) {
                            // Mark 2 positions with "L"
                            for (int index : position) playerBoard[index] = "L";
                            lightTanks++; // Increment light tank
                            App.clearTerminal(); // Clear terminal window
                            getBoard(); // Print board
                        }
                    } else {
                        isValidPosition = false;
                        System.out.println("Invalid position! Try again.");
                    }
                }
            } else {
                System.out.println("Invalid position! Try again.");
            }
        }
    }

    // Make a query for medium tank's position on the board
    private void queryMediumTankPosition() {
        boolean isValidPosition = false;
        Scanner in_mediumTanks = new Scanner(System.in);

        while(!isValidPosition) {
            System.out.println("\nMedium tank size: 2x2");
            System.out.println("■■■■■■■■■\n■ M ■ M ■\n■■■■■■■■■\n■ M ■ M ■\n■■■■■■■■■\n");
            System.out.println("Remaining Medium Tanks: " + (2 - mediumTanks));
            System.out.print("Enter medium tank position (Ex: a1 a2 b1 b2): ");
            String setMediumTankPosition = in_mediumTanks.nextLine();
            String[] mediumTankPosition = setMediumTankPosition.split(" ");

            if(mediumTankPosition.length == 4) {
                position: for(int i = 0; i < mediumTankPosition.length; i++) {
                    // Validate input for y (vertical) position of medium tanks
                    switch(mediumTankPosition[i].split("")[0].toLowerCase()) {
                        case "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" -> { isValidPosition = true; }
                        default -> {
                            isValidPosition = false;
                            break position;
                        }
                    }
    
                    // Validate input for x (horizontal) position of medium tanks
                    switch(mediumTankPosition[i].substring(1)) {
                        case "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" -> { isValidPosition = true; }
                        default -> {
                            isValidPosition = false;
                            break position;
                        }
                    }
                }
    
                if(isValidPosition == true) {
                    int y = 0;
                    int x = 0;
                    int[] position = new int[4];
    
                    // Find the index position of medium tank
                    validPosition: for(int i = 0; i < mediumTankPosition.length; i++) {
                        // Find the y (vertical) position of medium tank
                        switch(mediumTankPosition[i].split("")[0].toLowerCase()) {
                            case "a" -> { y = 0; }
                            case "b" -> { y = 10; }
                            case "c" -> { y = 20; }
                            case "d" -> { y = 30; }
                            case "e" -> { y = 40; }
                            case "f" -> { y = 50; }
                            case "g" -> { y = 60; }
                            case "h" -> { y = 70; }
                            case "i" -> { y = 80; }
                            case "j" -> { y = 90; }
                        }
                        
                        // Find the x (horizontal) position of medium tank
                        x = Integer.parseInt(mediumTankPosition[i].substring(1))-1;
    
                        // Check if the position is available / not available
                        isValidPosition = checkAvailablePosition(x + y);

                        if(!isValidPosition){
                            // If position is not available
                            System.out.println("Try again! Occupied slot!");
                            break validPosition;
                        } else {
                            // If position is available
                            position[i] = x + y;
                        }
                    }

                    Arrays.sort(position); // Sort positions

                    // Check if medium tank position 2x2 is valid
                    if(
                        (Math.abs(position[0] - position[1]) + Math.abs(position[2] - position[3])) == 2 &&
                        (Math.abs(position[0] - position[2]) + Math.abs(position[1] - position[3])) == 20
                    ) {
                        if(isValidPosition) {
                            // Mark 4 positions with "M"
                            for (int index : position) playerBoard[index] = "M";
                            mediumTanks++; // Increment medium tank
                            App.clearTerminal(); // Clear terminal window
                            getBoard(); // Print board
                        }
                    } else {
                        isValidPosition = false;
                        System.out.println("Invalid position! Try again.");
                    }
                }
            } else {
                System.out.println("Invalid position! Try again.");
            }
        }
    }

    // Make a query for heavy tank's position on the board
    private void queryHeavyTankPosition() {
        boolean isValidPosition = false;
        Scanner in_heavyTanks = new Scanner(System.in);

        while(!isValidPosition) {
            System.out.println("\nHeavy tank size: 3x2");
            System.out.println("■■■■■■■■■\n■ H ■ H ■\n■■■■■■■■■\n■ H ■ H ■\n■■■■■■■■■\n■ H ■ H ■\n■■■■■■■■■\n");
            System.out.println("Remaining Heavy Tanks: " + (1 - heavyTanks));
            System.out.print("Enter heavy tank position (Ex: a1 a2 b1 b2): ");
            String setHeavyTankPosition = in_heavyTanks.nextLine();
            String[] heavyTankPosition = setHeavyTankPosition.split(" ");

            if(heavyTankPosition.length == 6) {
                position: for(int i = 0; i < heavyTankPosition.length; i++) {
                    // Validate input for y (vertical) position of heavy tanks
                    switch(heavyTankPosition[i].split("")[0].toLowerCase()) {
                        case "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" -> { isValidPosition = true; }
                        default -> {
                            isValidPosition = false;
                            break position;
                        }
                    }
    
                    // Validate input for x (horizontal) position of heavy tanks
                    switch(heavyTankPosition[i].substring(1)) {
                        case "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" -> { isValidPosition = true; }
                        default -> {
                            isValidPosition = false;
                            break position;
                        }
                    }
                }
    
                if(isValidPosition == true) {
                    int y = 0;
                    int x = 0;
                    int[] position = new int[6];
    
                    // Find the index position of heavy tank
                    validPosition: for(int i = 0; i < heavyTankPosition.length; i++) {
                        // Find the y (vertical) position of heavy tank
                        switch(heavyTankPosition[i].split("")[0].toLowerCase()) {
                            case "a" -> { y = 0; }
                            case "b" -> { y = 10; }
                            case "c" -> { y = 20; }
                            case "d" -> { y = 30; }
                            case "e" -> { y = 40; }
                            case "f" -> { y = 50; }
                            case "g" -> { y = 60; }
                            case "h" -> { y = 70; }
                            case "i" -> { y = 80; }
                            case "j" -> { y = 90; }
                        }
                        
                        // Find the x (horizontal) position of heavy tank
                        x = Integer.parseInt(heavyTankPosition[i].substring(1))-1;
    
                        // Check if the position is available / not available
                        isValidPosition = checkAvailablePosition(x + y);

                        if(!isValidPosition){
                            // If position is not available
                            System.out.println("Try again! Occupied slot!");
                            break validPosition;
                        } else {
                            // If position is available
                            position[i] = x + y;
                        }
                    }

                    Arrays.sort(position); // Sort positions
                    // Check if heavy tank position 3x2 is valid
                    if(
                        (position[0] + position[2] + position[4])/3 == position[2] &&
                        ((double) position[1] + (double) position[3] + (double) position[5])/3 == (double) position[3]
                    ) {
                        if(isValidPosition) {
                            // Mark 4 positions with "H"
                            for (int index : position) playerBoard[index] = "H";
                            heavyTanks++; // Increment heavy tank
                        }
                    } else {
                        isValidPosition = false;
                        System.out.println("Invalid position! Try again.");
                    }
                }
            } else {
                System.out.println("Invalid position! Try again.");
            }
        }

        App.clearTerminal(); // Clear terminal window
        System.out.print("\033[H\033[2J");
        getBoard(); // Print board
    }

    // Check Available Spots
    private boolean checkAvailablePosition(int position) {
        if(playerBoard[position].equals(" ")) return true;
        return false;
    }
}