package WarBetweenPresident.Objects;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Board {
    private int start = 90;
    private int end = 100;

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

    public Board() {
        for(int i = 0; i < playerBoard.length; i++) {
            playerBoard[i] = " ";
            recordPlayerBoard[i] = " ";
            computerBoard[i] = " ";
            recordComputerBoard[i] = " ";
        }
    }

    public void getBoard() {
        for (int rows = 0; rows <= 10; rows++) {
            if(rows == 0) {
                System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■     ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\u001B[0m");
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

        // reset values
        start = 90;
        end = 100;

        System.out.println("ENDDDDDDDDDDDDDDDDDD!!!");
    }

    public void positionUnits() {
        while(president != 1) queryPresidentPosition();
        while(soldiers != 5) querySoldiersPosition();
        while(lightTanks != 3) queryLightTankPosition();
        while(mediumTanks != 2) queryMediumTankPosition();
    }

    private void queryPresidentPosition() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            getBoard();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

        boolean isValidPosition = false;
        Scanner in_president = new Scanner(System.in);

        while(!isValidPosition) {
            System.out.println("\nPresident size: 1x1");
            System.out.println("■■■■■\n■ P ■\n■■■■■\n");
            System.out.println("Remaining President: " + (1 - president));
            System.out.print("Enter president position (Ex: a1): ");
            String setPresidentPosition = in_president.nextLine();

            switch(setPresidentPosition.toLowerCase().substring(0, 1)) {
                case "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" -> { isValidPosition = true; }
                default -> {
                    isValidPosition = false;
                }
            }

            switch(setPresidentPosition.substring(1)) {
                case "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" -> { isValidPosition = true; }
                default -> {
                    isValidPosition = false;
                }
            }

            if(isValidPosition == true) {
                int y = 0;
                int x = 0;

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

                x = Integer.parseInt(setPresidentPosition.substring(1))-1;

                isValidPosition = checkAvailablePosition(x + y);

                if(!isValidPosition){
                    System.out.println("Try again! Occupied slot!");
                } else {
                    playerBoard[x + y] = "P";
                }

                if(isValidPosition) {
                    try {
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }

                    president++;
                    getBoard();
                }
            }
        }
    }

    private void querySoldiersPosition() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            getBoard();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }


        boolean isValidPosition = false;
        Scanner in_soldiers = new Scanner(System.in);

        while(!isValidPosition) {
            System.out.println("\nSoldiers size: 1x1");
            System.out.println("■■■■■\n■ S ■\n■■■■■\n");
            System.out.println("Remaining Soldiers: " + (5 - soldiers));
            System.out.print("Enter soldiers position (Ex: a1): ");
            String setSoldiersPosition = in_soldiers.nextLine();

            switch(setSoldiersPosition.toLowerCase().substring(0, 1)) {
                case "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" -> { isValidPosition = true; }
                default -> {
                    isValidPosition = false;
                }
            }

            switch(setSoldiersPosition.toLowerCase().substring(1)) {
                case "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" -> { isValidPosition = true; }
                default -> {
                    isValidPosition = false;
                }
            }

            if(isValidPosition == true) {
                int y = 0;
                int x = 0;

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

                x = Integer.parseInt(setSoldiersPosition.substring(1))-1;

                isValidPosition = checkAvailablePosition(x + y);

                if(!isValidPosition){
                    System.out.println("Try again! Occupied slot!");
                } else {
                    playerBoard[x + y] = "S";
                }

                if(isValidPosition) {
                    try {
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }

                    soldiers++;
                    getBoard();
                }
            }
        }
    }

    private void queryLightTankPosition() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            getBoard();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }


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
                    switch(lightTankPosition[i].split("")[0].toLowerCase()) {
                        case "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" -> { isValidPosition = true; }
                        default -> {
                            isValidPosition = false;
                            break position;
                        }
                    }
    
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
    
                    validPosition: for(int i = 0; i < lightTankPosition.length; i++) {
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
    
                        x = Integer.parseInt(lightTankPosition[i].substring(1))-1;
    
                        isValidPosition = checkAvailablePosition(x + y);
    
                        if(!isValidPosition){
                            System.out.println("Try again! Occupied slot!");
                            break validPosition;
                        } else {
                            position[i] = x + y;
                        }
                    }

                    if(Math.abs(position[0] - position[1]) == 1 || Math.abs(position[0] - position[1]) == 10) {
                        if(isValidPosition) {
                            try {
                                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                            } catch (InterruptedException | IOException e) {
                                e.printStackTrace();
                            }
                            
                            for (int index : position) {
                                playerBoard[index] = "L";
                            }
        
                            lightTanks++;
                            getBoard();
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

    private void queryMediumTankPosition() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

        getBoard();

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
                    switch(mediumTankPosition[i].split("")[0].toLowerCase()) {
                        case "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" -> { isValidPosition = true; }
                        default -> {
                            isValidPosition = false;
                            break position;
                        }
                    }
    
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
    
                    validPosition: for(int i = 0; i < mediumTankPosition.length; i++) {
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
    
                        x = Integer.parseInt(mediumTankPosition[i].substring(1))-1;
    
                        isValidPosition = checkAvailablePosition(x + y);
    
                        if(!isValidPosition){
                            System.out.println("Try again! Occupied slot!");
                            break validPosition;
                        } else {
                            position[i] = x + y;
                        }
                    }

                    if((Math.abs(position[0] - position[1]) + Math.abs(position[2] - position[3])) == 2) {
                        if(isValidPosition) {
                            try {
                                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                            } catch (InterruptedException | IOException e) {
                                e.printStackTrace();
                            }
                            
                            for (int index : position) {
                                playerBoard[index] = "M";
                            }
                            
                            mediumTanks++;
                            getBoard();
                        }
                    } else {
                        isValidPosition = false;
                        System.out.println(Arrays.toString(position));
                        System.out.println("Invalid position! Try again.");
                    }
                }
            } else {
                System.out.println("Invalid position! Try again.");
            }
        }
    }

    private boolean checkAvailablePosition(int position) {
        if(playerBoard[position].equals(" ")) return true;
        return false;
    }
}