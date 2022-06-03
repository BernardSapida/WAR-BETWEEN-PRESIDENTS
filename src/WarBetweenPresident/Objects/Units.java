package WarBetweenPresident.Objects;

import WarBetweenPresident.App;
import java.util.*;

public class Units extends Board {
    protected String player;
    protected int president = 0;
    protected int soldiers = 0;
    protected int lightTanks = 0;
    protected int mediumTanks = 0;
    protected int heavyTanks = 0;
    protected int nuke = 1;
    protected int mortars = 1;
    protected int tankGunCooldown;
    protected int cannonCooldown;
    protected int missilesCooldown;
    public boolean isPresidentDead = false;

    public Units(String player) {
        super(player);

        this.player = player;
    }

    public void randomUnitsPosition() {
        Random random = new Random();

        while(president < 1) {
            int presidentPosition = random.nextInt(100);

            if(player.equals("human")) {
                if(humanBoard[presidentPosition].equals(" ")) {
                    humanBoard[presidentPosition] = "P";
                    president++;
                }
            } else {
                if(computerBoard[presidentPosition].equals(" ")) {
                    computerBoard[presidentPosition] = "P";
                    president++;
                }
            }
        }

        while(soldiers < 5) {
            int soldiersPosition = random.nextInt(100);

            if(player.equals("human")) {
                if(humanBoard[soldiersPosition].equals(" ")) {
                    humanBoard[soldiersPosition] = "S";
                    soldiers++;
                }
            } else {
                if(computerBoard[soldiersPosition].equals(" ")) {
                    computerBoard[soldiersPosition] = "S";
                    soldiers++;
                }
            }            
        }

        while(lightTanks < 3) {
            ArrayList<int[]> lightTanksPosition = new ArrayList<int[]>();
            
            for(int i = 0; i < 100; i++) {
                int[] availablePosition = new int[2];

                if((i >= 0 && i < 9) || (i >= 10 && i < 19) || (i >= 20 && i < 29) || (i >= 30 && i < 39) || (i >= 40 && i < 49) || (i >= 50 && i < 59) || (i >= 60 && i < 69) || (i >= 70 && i < 79) || (i >= 80 && i < 89) || (i >= 90 && i < 99)) {
                    if(player.equals("human")) {
                        if(humanBoard[i].equals(" ") && humanBoard[i+1].equals(" ")) {
                            availablePosition[0] = i;
                            availablePosition[1] = i+1;
                            lightTanksPosition.add(availablePosition);
                        }
                    } else {
                        if(computerBoard[i].equals(" ") && computerBoard[i+1].equals(" ")) {
                            availablePosition[0] = i;
                            availablePosition[1] = i+1;
                            lightTanksPosition.add(availablePosition);
                        }
                    }
                }

                if((i >= 0 && i <= 9) || (i >= 10 && i <= 19) || (i >= 20 && i <= 29) || (i >= 30 && i <= 39) || (i >= 40 && i <= 49) || (i >= 50 && i <= 59) || (i >= 60 && i <= 69) || (i >= 70 && i <= 79) || (i >= 80 && i <= 89)) {
                    if(player.equals("human")) {
                        if(humanBoard[i].equals(" ") && humanBoard[i+10].equals(" ")) {
                            availablePosition[0] = i;
                            availablePosition[1] = i+10;
                            lightTanksPosition.add(availablePosition);
                        }
                    } else {
                        if(computerBoard[i].equals(" ") && computerBoard[i+10].equals(" ")) {
                            availablePosition[0] = i;
                            availablePosition[1] = i+10;
                            lightTanksPosition.add(availablePosition);
                        }
                    }
                }
            }

            for (int position : lightTanksPosition.get(random.nextInt(lightTanksPosition.size()))) {
                if(player.equals("human")) humanBoard[position] = "L";
                else computerBoard[position] = "L";
            }

            lightTanks++;
        }

        while(mediumTanks < 2) {
            ArrayList<int[]> mediumTanksPosition = new ArrayList<int[]>();
            
            for(int i = 0; i < 100; i++) {
                int[] availablePosition = new int[4];

                if((i >= 0 && i < 9) || (i >= 10 && i < 19) || (i >= 20 && i < 29) || (i >= 30 && i < 39) || (i >= 40 && i < 49) || (i >= 50 && i < 59) || (i >= 60 && i < 69) || (i >= 70 && i < 79) || (i >= 80 && i < 89)) {
                    if(player.equals("human")) {
                        if(humanBoard[i].equals(" ") && humanBoard[i+1].equals(" ") && humanBoard[i+10].equals(" ") && humanBoard[i+11].equals(" ")) {
                            availablePosition[0] = i;
                            availablePosition[1] = i+1;
                            availablePosition[2] = i+10;
                            availablePosition[3] = i+11;
                            mediumTanksPosition.add(availablePosition);
                        }
                    } else {
                        if(computerBoard[i].equals(" ") && computerBoard[i+1].equals(" ") && computerBoard[i+10].equals(" ") && computerBoard[i+11].equals(" ")) {
                            availablePosition[0] = i;
                            availablePosition[1] = i+1;
                            availablePosition[2] = i+10;
                            availablePosition[3] = i+11;
                            mediumTanksPosition.add(availablePosition);
                        }
                    }
                }
            }

            for (int position : mediumTanksPosition.get(random.nextInt(mediumTanksPosition.size()))) {
                if(player.equals("human")) humanBoard[position] = "M";
                else computerBoard[position] = "M";
            }

            mediumTanks++;
        }

        while(heavyTanks < 1) {
            ArrayList<int[]> heavyTanksPosition = new ArrayList<int[]>();
            
            for(int i = 0; i < 100; i++) {
                int[] availablePosition = new int[6];

                if((i >= 0 && i < 9) || (i >= 10 && i < 19) || (i >= 20 && i < 29) || (i >= 30 && i < 39) || (i >= 40 && i < 49) || (i >= 50 && i < 59) || (i >= 60 && i < 69) || (i >= 70 && i < 79)) {
                    if(player.equals("human")) {
                        if(
                        humanBoard[i].equals(" ") && 
                        humanBoard[i+1].equals(" ") && 
                        humanBoard[i+10].equals(" ") && 
                        humanBoard[i+11].equals(" ") &&
                        humanBoard[i+20].equals(" ") && 
                        humanBoard[i+21].equals(" ")) {
                            availablePosition[0] = i;
                            availablePosition[1] = i+1;
                            availablePosition[2] = i+10;
                            availablePosition[3] = i+11;
                            availablePosition[4] = i+20;
                            availablePosition[5] = i+21;
                            heavyTanksPosition.add(availablePosition);
                        }
                    } else {
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
                }

                if((i >= 0 && i < 6) || (i >= 10 && i < 16) || (i >= 20 && i < 26) || (i >= 30 && i < 36) || (i >= 40 && i < 46) || (i >= 50 && i < 56) || (i >= 60 && i < 66) || (i >= 70 && i < 76) || (i >= 80 && i < 86)) {
                    if(player.equals("human")) {
                        if(
                        humanBoard[i].equals(" ") && 
                        humanBoard[i+1].equals(" ") && 
                        humanBoard[i+2].equals(" ") && 
                        humanBoard[i+10].equals(" ") &&
                        humanBoard[i+11].equals(" ") && 
                        humanBoard[i+12].equals(" ")) {
                            availablePosition[0] = i;
                            availablePosition[1] = i+1;
                            availablePosition[2] = i+2;
                            availablePosition[3] = i+10;
                            availablePosition[4] = i+11;
                            availablePosition[5] = i+12;
                            heavyTanksPosition.add(availablePosition);
                        }
                    } else {
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
            }

            for (int position : heavyTanksPosition.get(random.nextInt(heavyTanksPosition.size()))) {
                if(player.equals("human")) humanBoard[position] = "H";
                else computerBoard[position] = "H";
            }

            heavyTanks++;
        }

        // getBoard("computer");
    }

    public void positionPlayerUnits() {
        while(president != 1) queryPresidentPosition();
        while(soldiers != 5) querySoldiersPosition();
        while(lightTanks != 3) queryLightTankPosition();
        while(mediumTanks != 2) queryMediumTankPosition();
        while(heavyTanks != 1) queryHeavyTankPosition();
    }

    private void queryPresidentPosition() {
        boolean isValidPosition = false;
        Scanner in_president = new Scanner(System.in);

        while(!isValidPosition) {            
            System.out.println("President size: 1x1");
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

            if(isValidPosition) {
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
                    System.out.println("The position is occupied! Please try another position for your units.");
                    App.printLine();
                } else {
                    // If position is available
                    humanBoard[x + y] = "P";
                }

                if(isValidPosition) {
                    president++; // Increment President
                    App.printLine(); // Clear terminal window
                    getBoard(); // Print board
                }
            } else {
                System.out.println("The position is invalid! Please try again.");
                App.printLine();
            }
        }
    }

    private void querySoldiersPosition() {
        boolean isValidPosition = false;
        Scanner in_soldiers = new Scanner(System.in);

        while(!isValidPosition) {
            System.out.println("Soldiers size: 1x1");
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

            if(isValidPosition) {
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
                    System.out.println("The position is occupied! Please try another position for your units.");
                    App.printLine();
                } else {
                    // Mark 1 position with "S"
                    humanBoard[x + y] = "S";
                }

                if(isValidPosition) {
                    soldiers++; // Increment soldiers
                    App.printLine(); // Clear terminal window
                    getBoard(); // Print board
                }
            } else {
                System.out.println("The position is invalid! Please try again.");
                App.printLine();
            }
        }
    }

    private void queryLightTankPosition() {
        boolean isValidPosition = false;
        Scanner in_lightTanks = new Scanner(System.in);

        while(!isValidPosition) {
            System.out.println("Light tank size: 2x1");
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
    
                if(isValidPosition) {
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
                            System.out.println("The position is occupied! Please try another position for your units.");
                            App.printLine();
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
                            App.printLine(); // Clear terminal window
                            getBoard(); // Print board
                        }
                    } else {
                        isValidPosition = false;
                        System.out.println("Invalid position! Try again.");
                        App.printLine();
                    }
                } else {
                    System.out.println("The position is invalid! Please try again.");
                    App.printLine();
                }
            } else {
                System.out.println("Invalid position! Try again.");
                App.printLine();
            }
        }
    }

    private void queryMediumTankPosition() {
        boolean isValidPosition = false;
        Scanner in_mediumTanks = new Scanner(System.in);

        while(!isValidPosition) {
            System.out.println("Medium tank size: 2x2");
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
    
                if(isValidPosition) {
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
                            System.out.println("The position is occupied! Please try another position for your units.");
                            App.printLine();
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
                            App.printLine(); // Clear terminal window
                            getBoard(); // Print board
                        }
                    } else {
                        isValidPosition = false;
                        System.out.println("Invalid position! Try again.");
                        App.printLine();
                    }
                }
            } else {
                System.out.println("The position is invalid! Please try again.");
                App.printLine();
            }
        }
    }

    private void queryHeavyTankPosition() {
        boolean isValidPosition = false;
        Scanner in_heavyTanks = new Scanner(System.in);

        while(!isValidPosition) {
            System.out.println("Heavy tank size: 3x2");
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
    
                if(isValidPosition) {
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
                            System.out.println("The position is occupied! Please try another position for your units.");
                            App.printLine();
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
                        App.printLine();
                    }
                }
            } else {
                System.out.println("The position is invalid! Please try again.");
                App.printLine();
            }
        }

        App.printLine(); // Clear terminal window
        getBoard(); // Print board
    }

    private boolean checkAvailablePosition(int position) {
        if(humanBoard[position].equals(" ")) return true;
        return false;
    }
}