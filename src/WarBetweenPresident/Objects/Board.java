package WarBetweenPresident.Objects;

import WarBetweenPresident.App;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Board {
    private int president = 0;
    private int soldiers = 0;
    private int lightTanks = 0;
    private int mediumTanks = 0;
    private int heavyTanks = 0;
    private boolean isPresidentDead = false;

    private String[] humanBoard = new String[100];
    private String[] recordHumanBoard = new String[100];

    private String[] computerBoard = new String[100];
    private String[] recordComputerBoard = new String[100];

    private String[] xCoordinates = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
    private String[] yCoordinates = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };

    
    // Initializing the board.
    public Board() {
        for(int i = 0; i < humanBoard.length; i++) {
            humanBoard[i] = " ";
            recordHumanBoard[i] = " ";
            computerBoard[i] = " ";
            recordComputerBoard[i] = " ";
        }

        // getBoard("human");
    }

    /**
     * It prints out a 10x10 grid of the player's board and the player's record board
     * 
     * @param player "human" or "computer"
     */
    public void getBoard(String player) {
        String[] playerBoard = (player.equals("human") ? humanBoard : computerBoard);
        String[] playerRecordBoard = (player.equals("human") ? recordHumanBoard : recordComputerBoard);
        int start = 90;
        int end = 100;

        for (int rows = 0; rows <= 10; rows++) {
            if(rows == 0) {
                System.out.println("\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■     ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\u001B[0m");
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
                    if(cols != end-1) System.out.print("\u001B[31m" + playerRecordBoard[cols] + "\u001B[0m" + " ■ \u001B[0m");
                    else System.out.print("\u001B[31m" + playerRecordBoard[cols] + "\u001B[0m " + " ■ \u001B[0m");
                }

                start -= 10;
                end -= 10;
                System.out.println("\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■     ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\u001B[0m");
            }
        }
    }

    
    /**
     * While the number of units is not equal to the number of units that should be on the board, query
     * the user for the position of the unit.
     */
    public void positionPlayerUnits() {
        while(president != 1) queryPresidentPosition();
        while(soldiers != 5) querySoldiersPosition();
        while(lightTanks != 3) queryLightTankPosition();
        while(mediumTanks != 2) queryMediumTankPosition();
        while(heavyTanks != 1) queryHeavyTankPosition();
    }

    /**
     * The function queries the user for the position of the President, validates the input, checks if
     * the position is available, and if it is, it places the President on the board.
     */
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
                    humanBoard[x + y] = "P";
                }

                if(isValidPosition) {
                    president++; // Increment President
                    App.clearTerminal(); // Clear terminal window
                    getBoard("human"); // Print board
                }
            }
        }
    }
    
    /**
     * The function asks the user to input the position of the soldiers, then it checks if the position
     * is available, if it is, it marks the position with "S" and increments the soldiers variable by
     * 1, if not, it asks the user to input another position.
     */
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
                    humanBoard[x + y] = "S";
                }

                if(isValidPosition) {
                    soldiers++; // Increment soldiers
                    App.clearTerminal(); // Clear terminal window
                    getBoard("human"); // Print board
                }
            }
        }
    }


    /**
     * It takes a string of 4 positions (Ex: a1 a2) and checks if the positions are valid and
     * available. If it is, it marks the positions with "L" and increments the lightTanks variable
     */
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
                            for (int index : position) humanBoard[index] = "L";
                            lightTanks++; // Increment light tank
                            App.clearTerminal(); // Clear terminal window
                            getBoard("human"); // Print board
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

    /**
     * It takes a string of 4 positions (Ex: a1 a2 b1 b2) and checks if the positions are valid and
     * available. If it is, it marks the positions with "M" and increments the mediumTanks variable
     */
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
                            for (int index : position) humanBoard[index] = "M";
                            mediumTanks++; // Increment medium tank
                            App.clearTerminal(); // Clear terminal window
                            getBoard("human"); // Print board
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

    /**
     * It takes a string of 6 positions (Ex: a1 a2 b1 b2 c1 c2) and checks if the positions are valid and
     * available. If it is, it marks the positions with "H" and increments the heavyTanks variable
     */
    private void queryHeavyTankPosition() {
        boolean isValidPosition = false;
        Scanner in_heavyTanks = new Scanner(System.in);

        while(!isValidPosition) {
            System.out.println("\nHeavy tank size: 3x2");
            System.out.println("■■■■■■■■■\n■ H ■ H ■\n■■■■■■■■■\n■ H ■ H ■\n■■■■■■■■■\n■ H ■ H ■\n■■■■■■■■■\n");
            System.out.println("Remaining Heavy Tanks: " + (1 - heavyTanks));
            System.out.print("Enter heavy tank position (Ex: a1 a2 b1 b2 c1 c2): ");
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
                            for (int index : position) humanBoard[index] = "H";
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
        getBoard("human"); // Print board
    }

    /**
     * @param position the position on the board that the player wants to place their marker
     * @return The method is returning a boolean value.
     */
    private boolean checkAvailablePosition(int position) {
        if(humanBoard[position].equals(" ")) return true;
        return false;
    }

    /**
     * If the player is human, then check if the computerBoard at the position is not empty. If it's
     * not empty, then set the computerBoard at the position to "X" (Hit) and the recordHumanBoard at the
     * position to "X". If it is empty, then set the recordHumanBoard at the position to "O" (Missed). If the
     * player is not human, then check if the humanBoard at the position is not empty. If it's not
     * empty, then set the humanBoard at the position to "X" (Hit) and the recordComputerBoard at the
     * position to "X". If it is empty, then set the recordComputerBoard at the position to "O" (Missed)
     * 
     * @param position The position on the board that the player is attacking
     * @param player String
     */
    public void attackBoard(int position, String player) {
        if(player.equals("human")) {
            if(!computerBoard[position].equals(" ")) {
                computerBoard[position] = "X";
                recordHumanBoard[position] = "X";
            } else {
                recordHumanBoard[position] = "O";
            }
        } else {
            if(!humanBoard[position].equals(" ")) {
                humanBoard[position] = "X";
                recordComputerBoard[position] = "X";
            } else {
                recordComputerBoard[position] = "O";
            }
        }

        // App.clearTerminal(); // Clear terminal window
        // getBoard("human"); // Print board
    }

    /**
     * It places the computer's units on the board
     */
    public void positionComputerUnits() {
        Random random = new Random();

        while(president < 1) {
            int presidentPosition = random.nextInt(100);

            if(computerBoard[presidentPosition].equals(" ")) {
                computerBoard[presidentPosition] = "P";
                president++;
            }
        }

        while(soldiers < 5) {
            int soldiersPosition = random.nextInt(100);

            if(computerBoard[soldiersPosition].equals(" ")) {
                computerBoard[soldiersPosition] = "S";
                soldiers++;
            }
        }

        while(lightTanks < 3) {
            ArrayList<int[]> lightTanksPosition = new ArrayList<int[]>();
            
            for(int i = 0; i < 100; i++) {
                int[] availablePosition = new int[2];

                if((i >= 0 && i < 9) || (i >= 10 && i < 19) || (i >= 20 && i < 29) || (i >= 30 && i < 39) || (i >= 40 && i < 49) || (i >= 50 && i < 59) || (i >= 60 && i < 69) || (i >= 70 && i < 79) || (i >= 80 && i < 89) || (i >= 90 && i < 99)) {
                    if(computerBoard[i].equals(" ") && computerBoard[i+1].equals(" ")) {
                        availablePosition[0] = i;
                        availablePosition[1] = i+1;
                        lightTanksPosition.add(availablePosition);
                    }
                }

                if((i >= 0 && i <= 9) || (i >= 10 && i <= 19) || (i >= 20 && i <= 29) || (i >= 30 && i <= 39) || (i >= 40 && i <= 49) || (i >= 50 && i <= 59) || (i >= 60 && i <= 69) || (i >= 70 && i <= 79) || (i >= 80 && i <= 89)) {
                    if(computerBoard[i].equals(" ") && computerBoard[i+10].equals(" ")) {
                        availablePosition[0] = i;
                        availablePosition[1] = i+10;
                        lightTanksPosition.add(availablePosition);
                    }
                }
            }

            for (int position : lightTanksPosition.get(random.nextInt(lightTanksPosition.size()))) {
                computerBoard[position] = "L";
            }

            lightTanks++;
        }

        while(mediumTanks < 2) {
            ArrayList<int[]> mediumTanksPosition = new ArrayList<int[]>();
            
            for(int i = 0; i < 100; i++) {
                int[] availablePosition = new int[4];

                if((i >= 0 && i < 9) || (i >= 10 && i < 19) || (i >= 20 && i < 29) || (i >= 30 && i < 39) || (i >= 40 && i < 49) || (i >= 50 && i < 59) || (i >= 60 && i < 69) || (i >= 70 && i < 79) || (i >= 80 && i < 89)) {
                    if(computerBoard[i].equals(" ") && computerBoard[i+1].equals(" ") && computerBoard[i+10].equals(" ") && computerBoard[i+11].equals(" ")) {
                        availablePosition[0] = i;
                        availablePosition[1] = i+1;
                        availablePosition[2] = i+10;
                        availablePosition[3] = i+11;
                        mediumTanksPosition.add(availablePosition);
                    }
                }
            }

            for (int position : mediumTanksPosition.get(random.nextInt(mediumTanksPosition.size()))) {
                computerBoard[position] = "M";
            }

            mediumTanks++;
        }

        while(heavyTanks < 1) {
            ArrayList<int[]> heavyTanksPosition = new ArrayList<int[]>();
            
            for(int i = 0; i < 100; i++) {
                int[] availablePosition = new int[6];

                if((i >= 0 && i < 9) || (i >= 10 && i < 19) || (i >= 20 && i < 29) || (i >= 30 && i < 39) || (i >= 40 && i < 49) || (i >= 50 && i < 59) || (i >= 60 && i < 69) || (i >= 70 && i < 79)) {
                    if(
                    computerBoard[i].equals(" ") && 
                    computerBoard[i+1].equals(" ") && 
                    computerBoard[i+10].equals(" ") && 
                    computerBoard[i+11].equals(" ") &&
                    computerBoard[i+20].equals(" ") && 
                    computerBoard[i+21].equals(" ")) {
                        availablePosition[0] = i;
                        availablePosition[1] = i+1;
                        availablePosition[2] = i+10;
                        availablePosition[3] = i+11;
                        availablePosition[4] = i+20;
                        availablePosition[5] = i+21;
                        heavyTanksPosition.add(availablePosition);
                    }
                }

                if((i >= 0 && i < 6) || (i >= 10 && i < 16) || (i >= 20 && i < 26) || (i >= 30 && i < 36) || (i >= 40 && i < 46) || (i >= 50 && i < 56) || (i >= 60 && i < 66) || (i >= 70 && i < 76) || (i >= 80 && i < 86)) {
                    if(
                    computerBoard[i].equals(" ") && 
                    computerBoard[i+1].equals(" ") && 
                    computerBoard[i+2].equals(" ") && 
                    computerBoard[i+10].equals(" ") &&
                    computerBoard[i+11].equals(" ") && 
                    computerBoard[i+12].equals(" ")) {
                        availablePosition[0] = i;
                        availablePosition[1] = i+1;
                        availablePosition[2] = i+2;
                        availablePosition[3] = i+10;
                        availablePosition[4] = i+11;
                        availablePosition[5] = i+12;
                        heavyTanksPosition.add(availablePosition);
                    }
                }
            }

            for (int position : heavyTanksPosition.get(random.nextInt(heavyTanksPosition.size()))) {
                computerBoard[position] = "H";
            }

            heavyTanks++;
        }

        // getBoard("computer");
    }

    /**
     * The function checks if the inputted attack position of the president (Nuke: 3x3) is valid or not.
     * If it is, it marks the attack positions with "X" (Hit) if the position is not empty and if empty then
     * it marks the attack position with "O" (Missed)
     */
    public void queryPresidentAttack() {
        boolean isValidPosition = false;
        Scanner in_presidentAttack = new Scanner(System.in);

        while(!isValidPosition) {            
            System.out.println("\nPresident Area of Effect (Nuke): 3x3");
            System.out.println("■■■■■■■■■■■■■\n■ X ■ X ■ X ■\n■■■■■■■■■■■■■\n■ X ■ X ■ X ■\n■■■■■■■■■■■■■\n■ X ■ X ■ X ■\n■■■■■■■■■■■■■\n ");
            System.out.print("Enter position to be attack (Ex: a1 a2 a3 b1 b2 b3 c1 c2 c3): ");
            String setPresidentAttackPosition = in_presidentAttack.nextLine();
            String[] presidentAttackPosition = setPresidentAttackPosition.split(" ");

            if(presidentAttackPosition.length == 9) {
                position: for(int i = 0; i < presidentAttackPosition.length; i++) {
                    // Validate input for y (vertical) attack position of the President (Nuke)
                    switch(presidentAttackPosition[i].split("")[0].toLowerCase()) {
                        case "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" -> { isValidPosition = true; }
                        default -> {
                            isValidPosition = false;
                            break position;
                        }
                    }
    
                    // Validate input for x (horizontal) attack position of the President (Nuke)
                    switch(presidentAttackPosition[i].substring(1)) {
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
                    int[] position = new int[9];
    
                    // Find the index attack position of the President (Nuke)
                    for(int i = 0; i < presidentAttackPosition.length; i++) {
                        // Find the y (vertical) attack position of the President (Nuke)
                        switch(presidentAttackPosition[i].split("")[0].toLowerCase()) {
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

                        x = Integer.parseInt(presidentAttackPosition[i].substring(1))-1;

                        // If position is available
                        position[i] = x + y;
                    }

                    Arrays.sort(position); // Sort positions

                    // Check if attack position of the President (Nuke) 9x9 is valid
                    if(
                        (double) (position[0] + position[1] + position[2])/3 == position[1] &&
                        (double) (position[3] + position[4] + position[5])/3 == position[4] &&
                        (double) (position[6] + position[7] + position[8])/3 == position[7]
                    ) {
                        if(isValidPosition) {
                            // Mark 4 positions with "M"
                            for (int index : position) attackBoard(index, "human");
                            // mediumTanks++; // Increment medium tank
                            // App.clearTerminal(); // Clear terminal window
                            getBoard("human"); // Print board
                        }
                    } else {
                        isValidPosition = false;
                        System.out.println("Invalid attack position! Try again.");
                    }
                }
            } else {
                System.out.println("Invalid attack position! Try again.");
            }
        }
    }

    /**
     * The function checks if the inputted attack position of the soldiers (Mortars: 1x1) is valid or not.
     * If it is, it marks the attack positions with "X" (Hit) if the position is not empty and if empty then
     * it marks the attack position with "O" (Missed)
     */
    public void querySoldiersAttack() {
        boolean isValidPosition = false;
        Scanner in_soldiersAttack = new Scanner(System.in);

        while(!isValidPosition) {            
            System.out.println("\nSoldiers Area of Effect (Mortars): 1x1");
            System.out.println("■■■■■\n■ X ■\n■■■■■\n");
            System.out.print("Enter position to be attack (Ex: a1): ");
            String setSoldiersAttackPosition = in_soldiersAttack.nextLine();

            switch(setSoldiersAttackPosition.toLowerCase().substring(0, 1)) {
                // Validate input for y (vertical) attack position of the soldiers (Mortars)
                case "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" -> { isValidPosition = true; }
                default -> {
                    isValidPosition = false;
                }
            }

            switch(setSoldiersAttackPosition.substring(1)) {
                // Validate input for x (horizontal) attack position of the soldiers (Mortars)
                case "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" -> { isValidPosition = true; }
                default -> {
                    isValidPosition = false;
                }
            }

            if(isValidPosition) {
                int y = 0;
                int x = 0;

                // Find the y (vertical) attack position of the soldiers (Mortars)
                switch(setSoldiersAttackPosition.toLowerCase().substring(0, 1)) {
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

                // Find the x (horizontal) attack position of the soldiers (Mortars)
                x = Integer.parseInt(setSoldiersAttackPosition.substring(1))-1;

                attackBoard(x + y, "human");

                // president++; // Increment President
                App.clearTerminal(); // Clear terminal window
                getBoard("human"); // Print board
            }
        }
    }

    /**
     * The function checks if the inputted attack position of the light tanks (Tank Gun: 2x1) is valid or not.
     * If it is, it marks the attack positions with "X" (Hit) if the position is not empty and if empty then
     * it marks the attack position with "O" (Missed)
     */
    public void queryLightTankAttackPosition() {
        boolean isValidPosition = false;
        Scanner in_lightTanksAttack = new Scanner(System.in);

        while(!isValidPosition) {
            System.out.println("\nLight Tank Area of Effect (Tank Gun): 2x1");
            System.out.println("■■■■■■■■■\n■ X ■ X ■\n■■■■■■■■■\n    OR    \n■■■■■\n■ X ■\n■■■■■\n■ X ■\n■■■■■\n");
            System.out.print("Enter position to be attack (Ex: a1 b1): ");
            String setLightTankAttackPosition = in_lightTanksAttack.nextLine();
            String[] lightTankAttackPosition = setLightTankAttackPosition.split(" ");

            if(lightTankAttackPosition.length == 2) {
                position: for(int i = 0; i < lightTankAttackPosition.length; i++) {
                    // Validate input for y (vertical) attack position of the light tanks (Tank Gun)
                    switch(lightTankAttackPosition[i].split("")[0].toLowerCase()) {
                        case "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" -> { isValidPosition = true; }
                        default -> {
                            isValidPosition = false;
                            break position;
                        }
                    }
    
                    // Validate input for x (horizontal) attack position of the light tanks (Tank Gun)
                    switch(lightTankAttackPosition[i].substring(1)) {
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
    
                    // Find the index attack position of the light tank (Tank Gun)
                    for(int i = 0; i < lightTankAttackPosition.length; i++) {
                        // Find the y (vertical) attack position of the light tank (Tank Gun)
                        switch(lightTankAttackPosition[i].split("")[0].toLowerCase()) {
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
    
                        // Find the x (horizontal) attack position of the light tank (Tank Gun)
                        x = Integer.parseInt(lightTankAttackPosition[i].substring(1))-1;
                        position[i] = x + y;
                    }

                    // Check if light tank position 2x1 is valid
                    if(Math.abs(position[0] - position[1]) == 1 || Math.abs(position[0] - position[1]) == 10) {
                        if(isValidPosition) {
                            // Mark 2 positions with "X"
                            for (int index : position) attackBoard(index, "human");
                            App.clearTerminal(); // Clear terminal window
                            getBoard("human"); // Print board
                        }
                    } else {
                        isValidPosition = false;
                        System.out.println("Invalid attack position! Try again.");
                    }
                }
            } else {
                System.out.println("Invalid attack position! Try again.");
            }
        }
    }

    /**
     * The function checks if the inputted attack position of the medium tanks (Cannon: 2x2) is valid or not.
     * If it is, it marks the attack positions with "X" (Hit) if the position is not empty and if empty then
     * it marks the attack position with "O" (Missed)
     */
    public void queryMediumTankAttackPosition() {
        boolean isValidPosition = false;
        Scanner in_mediumTanksAttack = new Scanner(System.in);

        while(!isValidPosition) {
            System.out.println("\nMedium Tank Area of Effect (Cannon): 2x2");
            System.out.println("■■■■■■■■■\n■ M ■ M ■\n■■■■■■■■■\n■ M ■ M ■\n■■■■■■■■■\n");
            System.out.print("Enter position to be attack (Ex: a1 a2 b1 b2): ");
            String setMediumTanksAttackPosition = in_mediumTanksAttack.nextLine();
            String[] mediumTanksAttackPosition = setMediumTanksAttackPosition.split(" ");

            if(mediumTanksAttackPosition.length == 4) {
                position: for(int i = 0; i < mediumTanksAttackPosition.length; i++) {
                    // Validate input for y (vertical) attack position of the medium (Cannon)
                    switch(mediumTanksAttackPosition[i].split("")[0].toLowerCase()) {
                        case "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" -> { isValidPosition = true; }
                        default -> {
                            isValidPosition = false;
                            break position;
                        }
                    }
    
                    // Validate input for x (horizontal)  attack position of the medium (Cannon)
                    switch(mediumTanksAttackPosition[i].substring(1)) {
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
                    for(int i = 0; i < mediumTanksAttackPosition.length; i++) {
                        // Find the y (vertical) position of medium tank
                        switch(mediumTanksAttackPosition[i].split("")[0].toLowerCase()) {
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
                        x = Integer.parseInt(mediumTanksAttackPosition[i].substring(1))-1;
                        position[i] = x + y;
                    }

                    Arrays.sort(position); // Sort positions

                    // Check if medium tank position 2x2 is valid
                    if(
                        (Math.abs(position[0] - position[1]) + Math.abs(position[2] - position[3])) == 2 &&
                        (Math.abs(position[0] - position[2]) + Math.abs(position[1] - position[3])) == 20
                    ) {
                        if(isValidPosition) {
                            // Mark 4 positions with "X"
                            for (int index : position) attackBoard(index, "human");
                            App.clearTerminal(); // Clear terminal window
                            getBoard("human"); // Print board
                        }
                    } else {
                        isValidPosition = false;
                        System.out.println("Invalid attack position! Try again.");
                    }
                }
            } else {
                System.out.println("Invalid attack position! Try again.");
            }
        }
    }

    
    /**
     * The function checks if the inputted attack position of the heavy tanks (Missiles: 3x2) is valid or not.
     * If it is, it marks the attack positions with "X" (Hit) if the position is not empty and if empty then
     * it marks the attack position with "O" (Missed)
     */
    public void queryHeavyTankAttackPosition() {
        boolean isValidPosition = false;
        Scanner in_heavyTanksAttack = new Scanner(System.in);

        while(!isValidPosition) {
            System.out.println("\nHeavy Tank Area of Effect (Missiles): 3x2");
            System.out.println("■■■■■■■■■\n■ X ■ X ■\n■■■■■■■■■\n■ X ■ X ■\n■■■■■■■■■\n■ X ■ X ■\n■■■■■■■■■\n");
            System.out.print("Enter position to be attack (Ex: a1 a2 b1 b2 c1 c2): ");
            String setHeavyTankAttackPosition = in_heavyTanksAttack.nextLine();
            String[] heavyTankAttackPosition = setHeavyTankAttackPosition.split(" ");

            if(heavyTankAttackPosition.length == 6) {
                position: for(int i = 0; i < heavyTankAttackPosition.length; i++) {
                    // Validate input for y (vertical) attack position of the heavy tanks (Missiles)
                    switch(heavyTankAttackPosition[i].split("")[0].toLowerCase()) {
                        case "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" -> { isValidPosition = true; }
                        default -> {
                            isValidPosition = false;
                            break position;
                        }
                    }
    
                    // Validate input for x (horizontal) attack position of the heavy tanks (Missiles)
                    switch(heavyTankAttackPosition[i].substring(1)) {
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
                    for(int i = 0; i < heavyTankAttackPosition.length; i++) {
                        // Find the y (vertical) position of heavy tank
                        switch(heavyTankAttackPosition[i].split("")[0].toLowerCase()) {
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
                        x = Integer.parseInt(heavyTankAttackPosition[i].substring(1))-1;
                        position[i] = x + y;
                    }

                    Arrays.sort(position); // Sort positions
                    // Check if heavy tank position 3x2 is valid
                    if(
                        ((double) (position[0] + position[2] + position[4])/3 == position[2] &&
                        (double) (position[1] + position[3] + position[5])/3 == position[3]) ||
                        ((double) (position[0] + position[1] + position[2])/3 == position[1] &&
                        (double) (position[3] + position[4] + position[5])/3 == position[4])
                    ) {
                        if(isValidPosition) {
                            // Mark 6 positions with "X"
                            for (int index : position) attackBoard(index, "human");
                            App.clearTerminal(); // Clear terminal window
                            getBoard("human"); // Print board
                        }
                    } else {
                        isValidPosition = false;
                        System.out.println("Invalid attack position! Try again.");
                    }
                }
            } else {
                System.out.println("Invalid attack position! Try again.");
            }
        }

        App.clearTerminal(); // Clear terminal window
        getBoard("human"); // Print board
    }
}