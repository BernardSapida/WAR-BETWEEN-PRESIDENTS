package WarBetweenPresident.Objects;

import WarBetweenPresident.App;
import java.util.*;

public class UnitsAttack extends Units {

    public UnitsAttack(String player) {
        super(player);
    }

    public void attackBoard(int position, String player) {
        if(player.equals("human")) {
            if(!computerBoard[position].equals(" ")) {
                if(computerBoard[position].equals("P")) isPresidentDead = true;
                computerBoard[position] = "X";
                recordHumanBoard[position] = "X";
            } else {
                recordHumanBoard[position] = "O";
            }
        } else {
            if(!humanBoard[position].equals(" ")) {
                if(humanBoard[position].equals("P")) isPresidentDead = true;
                humanBoard[position] = "X";
                recordComputerBoard[position] = "X";
            } else {
                recordComputerBoard[position] = "O";
            }
        }
    }

    public void queryPresidentAttack() {
        boolean isValidPosition = false;
        Scanner in_presidentAttack = new Scanner(System.in);

        while(!isValidPosition) {            
            System.out.println("President Area of Effect (Nuke): 3x3");
            System.out.println("■■■■■■■■■■■■■\n■ X ■ X ■ X ■\n■■■■■■■■■■■■■\n■ X ■ X ■ X ■\n■■■■■■■■■■■■■\n■ X ■ X ■ X ■\n■■■■■■■■■■■■■\n ");
            System.out.print("Enter position to be attack (Ex: a1 a2 a3 b1 b2 b3 c1 c2 c3): ");
            String setPresidentAttackPosition = in_presidentAttack.nextLine();
            String[] presidentAttackPosition = setPresidentAttackPosition.split(" ");
            System.out.println();
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
    
                if(isValidPosition) {
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
                            for (int index : position) attackBoard(index, player);
                            getBoard(); // Print board
                            if(isPresidentDead) announceWinner(player);
                        }
                    } else {
                        isValidPosition = false;
                        System.out.println("\u001B[31mInvalid attack position! Try again.\u001B[37m");
                        App.printLine();
                    }
                } else {
                    System.out.println("\u001B[31mInvalid attack position! Try again.\u001B[37m");
                    App.printLine();
                }
            } else {
                System.out.println("\u001B[31mInvalid attack position! Try again.\u001B[37m");
                App.printLine();
            }
        }
    }

    public void querySoldiersAttack() {
        boolean isValidPosition = false;
        Scanner in_soldiersAttack = new Scanner(System.in);

        while(!isValidPosition) {            
            System.out.println("Soldiers Area of Effect (Mortars): 1x1");
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

            if(isValidPosition) {
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
    
                    attackBoard(x + y, player);
                    getBoard(); // Print board
                    if(isPresidentDead) announceWinner(player);
                } else {
                    System.out.println("\u001B[31mInvalid attack position! Try again.\u001B[37m");
                    App.printLine();
                }
            } else {
                System.out.println("\u001B[31mInvalid attack position! Try again.\u001B[37m");
                App.printLine();
            }
        }
    }

    public void queryLightTankAttack() {
        boolean isValidPosition = false;
        Scanner in_lightTanksAttack = new Scanner(System.in);

        while(!isValidPosition) {
            System.out.println("Light Tank Area of Effect (Tank Gun): 2x1");
            System.out.println("■■■■■■■■■\n■ X ■ X ■\n■■■■■■■■■\n    OR    \n■■■■■\n■ X ■\n■■■■■\n■ X ■\n■■■■■\n");
            System.out.print("Enter position to be attack (Ex: a1 b1): ");
            String setLightTankAttackPosition = in_lightTanksAttack.nextLine();
            String[] lightTankAttackPosition = setLightTankAttackPosition.split(" ");
            System.out.println();

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
    
                if(isValidPosition) {
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
                            for (int index : position) attackBoard(index, player);
                            getBoard(); // Print board
                            if(isPresidentDead) announceWinner(player);
                        }
                    } else {
                        isValidPosition = false;
                        System.out.println("\u001B[31mInvalid attack position! Try again.\u001B[37m");
                        App.printLine();
                    }
                } else {
                    System.out.println("\u001B[31mInvalid attack position! Try again.\u001B[37m");
                    App.printLine();
                }
            } else {
                System.out.println("\u001B[31mInvalid attack position! Try again.\u001B[37m");
                App.printLine();
            }
        }
    }

    public void queryMediumTankAttack() {
        boolean isValidPosition = false;
        Scanner in_mediumTanksAttack = new Scanner(System.in);

        while(!isValidPosition) {
            System.out.println("Medium Tank Area of Effect (Cannon): 2x2");
            System.out.println("■■■■■■■■■\n■ M ■ M ■\n■■■■■■■■■\n■ M ■ M ■\n■■■■■■■■■\n");
            System.out.print("Enter position to be attack (Ex: a1 a2 b1 b2): ");
            String setMediumTanksAttackPosition = in_mediumTanksAttack.nextLine();
            String[] mediumTanksAttackPosition = setMediumTanksAttackPosition.split(" ");
            System.out.println();

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
    
                if(isValidPosition) {
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
                            for (int index : position) attackBoard(index, player);
                            getBoard(); // Print board
                            if(isPresidentDead) announceWinner(player);
                        }
                    } else {
                        isValidPosition = false;
                        System.out.println("\u001B[31mInvalid attack position! Try again.\u001B[37m");
                        App.printLine();
                    }
                } else {
                    System.out.println("\u001B[31mInvalid attack position! Try again.\u001B[37m");
                    App.printLine();
                }
            } else {
                System.out.println("\u001B[31mInvalid attack position! Try again.\u001B[37m");
                App.printLine();
            }
        }
    }

    public void queryHeavyTankAttack() {
        boolean isValidPosition = false;
        Scanner in_heavyTanksAttack = new Scanner(System.in);

        while(!isValidPosition) {
            System.out.println("Heavy Tank Area of Effect (Missiles): 3x2");
            System.out.println("■■■■■■■■■\n■ X ■ X ■\n■■■■■■■■■\n■ X ■ X ■\n■■■■■■■■■\n■ X ■ X ■\n■■■■■■■■■\n");
            System.out.print("Enter position to be attack (Ex: a1 a2 b1 b2 c1 c2): ");
            String setHeavyTankAttackPosition = in_heavyTanksAttack.nextLine();
            String[] heavyTankAttackPosition = setHeavyTankAttackPosition.split(" ");
            System.out.println();

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
    
                if(isValidPosition) {
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
                            for (int index : position) attackBoard(index, player);
                            getBoard(); // Print board
                            if(isPresidentDead) announceWinner(player);
                        }
                    } else {
                        isValidPosition = false;
                        System.out.println("\u001B[31mInvalid attack position! Try again.\u001B[37m");
                        App.printLine();
                    }
                } else {
                    System.out.println("\u001B[31mInvalid attack position! Try again.\u001B[37m");
                    App.printLine();
                }
            } else {
                System.out.println("\u001B[31mInvalid attack position! Try again.\u001B[37m");
                App.printLine();
            }
        }
    }

    public int queryAttack() {
        Scanner in_attack = new Scanner(System.in);
        boolean isValid = false;

        if(nuke == 0 && soldiers == 0 && ((lightTanks == 0 && mediumTanks == 0 && heavyTanks == 0) || (tankGunCooldown != 0 &&  cannonCooldown != 0 && missilesCooldown != 0))) {
            getBoard();
            System.out.println("You don't have attacks yet. So, it's computer turn!");
            return 0;
        }
        
        if(player.equals("human")) {
            String lightTanksPositionString = "";
            String mediumTanksPositionString = "";
            String heavyTanksPositionString = "";

            if(soldiers > 0) {
                for(int index = 0; index < soldiersPositionArray.size(); index++) {
                    if(soldiersPositionArray.get(index) != -1) {
                        if(humanBoard[soldiersPositionArray.get(index)].equals("X")) {
                            soldiersPositionArray.set(index, -1);
                            soldiersPositionArray.remove(index);
                            soldiers -= 1;
                        }
                    }
                }
            }

            if(lightTanks > 0) {
                for(int arr = 0; arr < lightTanksPositionArray.size(); arr++) {
                    for(int index = 0; index < lightTanksPositionArray.get(arr).length; index++) {
                        if(lightTanksPositionArray.get(arr)[index] != -1) {
                            if(humanBoard[lightTanksPositionArray.get(arr)[index]].equals("X")) {
                                int[] currentArr = lightTanksPositionArray.get(arr);
                                currentArr[index] = -1;
                                lightTanksPositionArray.set(arr, currentArr);
                            }
                        }
                    }
    
                    for(int index: lightTanksPositionArray.get(arr)) lightTanksPositionString += (index + " ");
    
                    if(lightTanksPositionString.equals("-1 -1 ")) {
                        if(lightTanks != 0) lightTanks -= 1;
                        lightTanksPositionString = "";
                    }
                }

                for(int each = 0; each < lightTanksPositionArray.size(); each++) {
                    lightTanksPositionString = "";
                    for(int index: lightTanksPositionArray.get(each)) lightTanksPositionString += (index + " ");
                    if(lightTanksPositionString.equals("-1 -1 ")) {
                        lightTanksPositionArray.remove(each);
                    }
                }
            }

            if(mediumTanks > 0) {
                for(int arr = 0; arr < mediumTanksPositionArray.size(); arr++) {
                    for(int index = 0; index < mediumTanksPositionArray.get(arr).length; index++) {
                        if(mediumTanksPositionArray.get(arr)[index] != -1) {
                            if(humanBoard[mediumTanksPositionArray.get(arr)[index]].equals("X")) {
                                int[] currentArr = mediumTanksPositionArray.get(arr);
                                currentArr[index] = -1;
                                mediumTanksPositionArray.set(arr, currentArr);
                            }
                        }
                    }

                    for(int index: mediumTanksPositionArray.get(arr)) mediumTanksPositionString += (index + " ");

                    if(mediumTanksPositionString.equals("-1 -1 -1 -1 ")) {
                        mediumTanks -= 1;
                        mediumTanksPositionString = "";
                    }
                }

                for(int each = 0; each < mediumTanksPositionArray.size(); each++) {
                    mediumTanksPositionString = "";
                    for(int index: mediumTanksPositionArray.get(each)) mediumTanksPositionString += (index + " ");
                    if(mediumTanksPositionString.equals("-1 -1 ")) {
                        mediumTanksPositionArray.remove(each);
                    }
                }
            }

            if(heavyTanks > 0) {
                for(int arr = 0; arr < heavyTanksPositionArray.size(); arr++) {
                    for(int index = 0; index < heavyTanksPositionArray.get(arr).length; index++) {
                        if(heavyTanksPositionArray.get(arr)[index] != -1) {
                            if(humanBoard[heavyTanksPositionArray.get(arr)[index]].equals("X")) {
                                int[] currentArr = heavyTanksPositionArray.get(arr);
                                currentArr[index] = -1;
                                heavyTanksPositionArray.set(arr, currentArr);
                            }
                        }
                    }

                    for(int index: heavyTanksPositionArray.get(arr)) heavyTanksPositionString += (index + " ");

                    if(heavyTanksPositionString.equals("-1 -1 -1 -1 -1 -1 ")) {
                        heavyTanks -= 1;
                        heavyTanksPositionString = "";
                    }
                }

                for(int each = 0; each < heavyTanksPositionArray.size(); each++) {
                    heavyTanksPositionString = "";
                    for(int index: heavyTanksPositionArray.get(each)) heavyTanksPositionString += (index + " ");
                    if(heavyTanksPositionString.equals("-1 -1 ")) {
                        heavyTanksPositionArray.remove(each);
                    }
                }
            }

            getBoard();
        }

        while(!isValid) {
            System.out.println("\n[1] President - Nuke");
            System.out.println("    Remaining President: " + president);
            System.out.println("    Area of Effect: 3x3");
            System.out.println("    Attack Status: " + (nuke == 0 ? "Unavailable" : "Ready") + "\n");

            System.out.println("[2] Soldiers - Mortars (Cooldown: No Cooldown)");
            System.out.println("    Remaining Soldiers: " + soldiers);
            System.out.println("    Area of Effect: 1x1");
            System.out.println("    Attack Status: " + (soldiers == 0 ? "Unavailable" : "Ready") + "\n");

            System.out.println("[3] Light Tank - Tank Gun (Cooldown: 4 turns)");
            System.out.println("    Remaining Light Tank: " + lightTanks);
            System.out.println("    Area of Effect: 2x1");
            System.out.println("    Attack Status: " + (lightTanks != 0 ? ((tankGunCooldown != 0) ? tankGunCooldown + " turns (Cooldown)" : "Ready") : "Unavailable") + "\n");

            System.out.println("[4] Medium Tank - Cannon (Cooldown: 6 turns)");
            System.out.println("    Remaining Medium Tanks: " + mediumTanks);
            System.out.println("    Area of Effect: 2x2");
            System.out.println("    Attack Status: " + (mediumTanks != 0 ? ((cannonCooldown != 0) ? cannonCooldown + " turns (Cooldown)" : "Ready") : "Unavailable") + "\n");

            System.out.println("[5] Heavy Tank - Missiles (Cooldown: 8 turns)");
            System.out.println("    Remaining Heavy Tanks: " + heavyTanks);
            System.out.println("    Area of Effect: 3x2");
            System.out.println("    Attack Status: " + (heavyTanks != 0 ? ((missilesCooldown != 0) ? missilesCooldown + " turns (Cooldown)" : "Ready") : "Unavailable") + "\n");
            
            System.out.print("Choose an attack: ");
            String attack = in_attack.nextLine();

            if((nuke == 0) && (mortars == 0) && (tankGunCooldown != 0) && (cannonCooldown != 0) && (missilesCooldown != 0)) {
                if(player.equals("human")) System.out.println("All of your attacks are on cooldown. It's computer turn again!");
                else System.out.println("\u001B[31mAll of computer attacks are on cooldown. It's your turn again!\u001B[37m");
                App.printLine();
                break;
            }

            switch(attack) {
                case "1" -> { 
                    if(nuke > 0) {
                        App.printLine();
                        queryPresidentAttack();

                        nuke -= 1;

                        if(tankGunCooldown > 0 && lightTanks > 0) tankGunCooldown -= 1;
                        if(cannonCooldown > 0 && mediumTanks > 0) cannonCooldown -= 1;
                        if(missilesCooldown > 0 && heavyTanks > 0) missilesCooldown -= 1;

                        isValid = true;
                    } else {
                        System.out.println("\u001B[31mSorry, you can use only 1 Nuke. Please try another attack!\u001B[37m");
                        App.printLine();
                    }
                }

                case "2" -> { 
                    if(soldiers > 0) {
                        App.printLine();
                        querySoldiersAttack();

                        if(tankGunCooldown > 0 && lightTanks > 0) tankGunCooldown -= 1;
                        if(cannonCooldown > 0 && mediumTanks > 0) cannonCooldown -= 1;
                        if(missilesCooldown > 0 && heavyTanks > 0) missilesCooldown -= 1;

                        isValid = true;
                    } else {
                        System.out.println("\u001B[31mSorry, all of your soldiers has been killed. Please try another attack!\u001B[37m");
                        App.printLine();
                    }
                }

                case "3" -> { 
                    if(lightTanks > 0 && tankGunCooldown == 0) {
                        App.printLine();
                        queryLightTankAttack();
                        
                        tankGunCooldown = 4;

                        if(cannonCooldown > 0 && mediumTanks > 0) cannonCooldown -= 1;
                        if(missilesCooldown > 0 && heavyTanks > 0) missilesCooldown -= 1;

                        isValid = true;
                    } else {
                        if(tankGunCooldown != 0) System.out.println("\u001B[31mSorry, tank gun is on cooldown. Please try another attack!\u001B[37m");
                        if(lightTanks == 0) System.out.println("\u001B[31mSorry, all of your light tanks has been destroyed. Please try another attack!\u001B[37m");
                        App.printLine();
                    }
                }

                case "4" -> { 
                    if(mediumTanks > 0 && cannonCooldown == 0) {
                        App.printLine();
                        queryMediumTankAttack();

                        cannonCooldown = 6;

                        if(tankGunCooldown > 0 && lightTanks > 0) tankGunCooldown -= 1;
                        if(missilesCooldown > 0 && heavyTanks > 0) missilesCooldown -= 1;

                        isValid = true;
                    } else {
                        if(cannonCooldown != 0) System.out.println("\u001B[31mSorry, cannon is on cooldown. Please try another attack!\u001B[37m");
                        if(mediumTanks == 0) System.out.println("\u001B[31mSorry, all of your medium tanks has been destroyed. Please try another attack!\u001B[37m");
                        App.printLine();
                    }
                }

                case "5" -> { 
                    if(heavyTanks > 0 && missilesCooldown == 0) {
                        App.printLine();
                        queryHeavyTankAttack();

                        missilesCooldown = 8;

                        if(tankGunCooldown > 0 && lightTanks > 0) tankGunCooldown -= 1;
                        if(cannonCooldown > 0 && mediumTanks > 0) cannonCooldown -= 1;

                        isValid = true;
                    } else {
                        if(missilesCooldown != 0) System.out.println("\u001B[31mSorry, missiles is on cooldown. Please try another attack!\u001B[37m");
                        if(heavyTanks == 0) System.out.println("\u001B[31mSorry, all of your heavy tanks has been destroyed. Please try another attack!\u001B[37m");
                        App.printLine();
                    }
                }

                default -> {
                    System.out.println("\u001B[31mYour input is invalid! Please try again.\u001B[37m");
                    App.printLine();
                }
            }
        }

        return 1;
    }

    public void computerAttack() {
        Random random = new Random();
        computerAvailableAttacks = new ArrayList<String>();

        if(soldiers == 0) computerAttacks.remove("Mortars");
        if(lightTanks == 0) computerAttacks.remove("Tank Gun");
        if(mediumTanks == 0) computerAttacks.remove("Cannon");
        if(heavyTanks == 0) computerAttacks.remove("Missiles");

        if(tankGunCooldown == 0) computerAttacks.put("Tank Gun", "Ready");
        if(cannonCooldown == 0) computerAttacks.put("Cannon", "Ready");
        if(missilesCooldown == 0) computerAttacks.put("Missiles", "Ready");

        for(String value : computerAttacks.keySet()) {
            if(computerAttacks.get(value).equals("Ready")) computerAvailableAttacks.add(value);
        }

        if(computerAvailableAttacks.size() != 0) {
            switch(computerAvailableAttacks.get(random.nextInt(computerAvailableAttacks.size()))) {
                case "Nuke" -> { 
                    ArrayList<int[]> attackOptions = new ArrayList<int[]>();
                
                    for(int i = 0; i < 100; i++) {
                        int[] attack = new int[9];
        
                        if((i >= 0 && i < 8) || (i >= 10 && i < 18) || (i >= 20 && i < 29) || (i >= 30 && i < 38) || (i >= 40 && i < 48) || (i >= 50 && i < 58) || (i >= 60 && i < 68) || (i >= 70 && i < 78)) {
                            attack[0] = i;
                            attack[1] = i+1;
                            attack[2] = i+2;
                            attack[3] = i+10;
                            attack[4] = i+11;
                            attack[5] = i+12;
                            attack[6] = i+20;
                            attack[7] = i+21;
                            attack[8] = i+22;
                            attackOptions.add(attack);
                        }
                    }
        
                    for (int attackPosition : attackOptions.get(random.nextInt(attackOptions.size()))) {
                        attackBoard(attackPosition, player);
                        if(isPresidentDead) announceWinner(player);
                    }
                    
                    if(tankGunCooldown > 0 && lightTanks > 0) tankGunCooldown -= 1;
                    if(cannonCooldown > 0 && mediumTanks > 0) cannonCooldown -= 1;
                    if(missilesCooldown > 0 && heavyTanks > 0) missilesCooldown -= 1;
    
                    computerAttacks.put("Nuke", "Not Available");
                }
    
                case "Mortars" -> { 
                    int attackPosition = random.nextInt(100);
    
                    attackBoard(attackPosition, player);
                    if(isPresidentDead) announceWinner(player);
    
                    if(tankGunCooldown > 0 && lightTanks > 0) tankGunCooldown -= 1;
                    if(cannonCooldown > 0 && mediumTanks > 0) cannonCooldown -= 1;
                    if(missilesCooldown > 0 && heavyTanks > 0) missilesCooldown -= 1;
                }
    
                case "Tank Gun" -> { 
                    ArrayList<int[]> attackOptions = new ArrayList<int[]>();
                
                    for(int i = 0; i < 100; i++) {
                        int[] attack = new int[2];
    
                        if((i >= 0 && i < 9) || (i >= 10 && i < 19) || (i >= 20 && i < 29) || (i >= 30 && i < 39) || (i >= 40 && i < 49) || (i >= 50 && i < 59) || (i >= 60 && i < 69) || (i >= 70 && i < 79) || (i >= 80 && i < 89) || (i >= 90 && i < 99)) {
                            if(humanBoard[i].equals(" ") && humanBoard[i+1].equals(" ")) {
                                attack[0] = i;
                                attack[1] = i+1;
                                attackOptions.add(attack);
                            }
                        }
    
                        if((i >= 0 && i <= 9) || (i >= 10 && i <= 19) || (i >= 20 && i <= 29) || (i >= 30 && i <= 39) || (i >= 40 && i <= 49) || (i >= 50 && i <= 59) || (i >= 60 && i <= 69) || (i >= 70 && i <= 79) || (i >= 80 && i <= 89)) {
                            if(humanBoard[i].equals(" ") && humanBoard[i+10].equals(" ")) {
                                attack[0] = i;
                                attack[1] = i+10;
                                attackOptions.add(attack);
                            }
                        }
                    }
    
                    for (int attackPosition : attackOptions.get(random.nextInt(attackOptions.size()))) {
                        attackBoard(attackPosition, player);
                        if(isPresidentDead) announceWinner(player);
                    }
    
                    tankGunCooldown = 4;

                    if(cannonCooldown > 0 && mediumTanks > 0) cannonCooldown -= 1;
                    if(missilesCooldown > 0 && heavyTanks > 0) missilesCooldown -= 1;
    
                    computerAttacks.put("Tank Gun", "Cooldown");
                }
    
                case "Cannon" -> { 
                    ArrayList<int[]> attackOptions = new ArrayList<int[]>();
                
                    for(int i = 0; i < 100; i++) {
                        int[] attack = new int[4];
    
                        if((i >= 0 && i < 9) || (i >= 10 && i < 19) || (i >= 20 && i < 29) || (i >= 30 && i < 39) || (i >= 40 && i < 49) || (i >= 50 && i < 59) || (i >= 60 && i < 69) || (i >= 70 && i < 79) || (i >= 80 && i < 89)) {
                            if(humanBoard[i].equals(" ") && humanBoard[i+1].equals(" ") && humanBoard[i+10].equals(" ") && humanBoard[i+11].equals(" ")) {
                                attack[0] = i;
                                attack[1] = i+1;
                                attack[2] = i+10;
                                attack[3] = i+11;
                                attackOptions.add(attack);
                            }
                        }
                    }
    
                    for (int attackPosition : attackOptions.get(random.nextInt(attackOptions.size()))) {
                        attackBoard(attackPosition, player);
                        if(isPresidentDead) announceWinner(player);
                    }
    
                    cannonCooldown = 6;
    
                    if(tankGunCooldown > 0 && lightTanks > 0) tankGunCooldown -= 1;
                    if(missilesCooldown > 0 && heavyTanks > 0) missilesCooldown -= 1;
    
                    computerAttacks.put("Cannon", "Cooldown");
                }
    
                case "Missiles" -> { 
                    ArrayList<int[]> attackOptions = new ArrayList<int[]>();
                
                    for(int i = 0; i < 100; i++) {
                        int[] attack = new int[6];
        
                        if((i >= 0 && i < 9) || (i >= 10 && i < 19) || (i >= 20 && i < 29) || (i >= 30 && i < 39) || (i >= 40 && i < 49) || (i >= 50 && i < 59) || (i >= 60 && i < 69) || (i >= 70 && i < 79)) {
                            if(
                            humanBoard[i].equals(" ") && 
                            humanBoard[i+1].equals(" ") && 
                            humanBoard[i+10].equals(" ") && 
                            humanBoard[i+11].equals(" ") &&
                            humanBoard[i+20].equals(" ") && 
                            humanBoard[i+21].equals(" ")) {
                                attack[0] = i;
                                attack[1] = i+1;
                                attack[2] = i+10;
                                attack[3] = i+11;
                                attack[4] = i+20;
                                attack[5] = i+21;
                                attackOptions.add(attack);
                            }
                        }
        
                        if((i >= 0 && i < 6) || (i >= 10 && i < 16) || (i >= 20 && i < 26) || (i >= 30 && i < 36) || (i >= 40 && i < 46) || (i >= 50 && i < 56) || (i >= 60 && i < 66) || (i >= 70 && i < 76) || (i >= 80 && i < 86)) {
                            if(
                            humanBoard[i].equals(" ") && 
                            humanBoard[i+1].equals(" ") && 
                            humanBoard[i+2].equals(" ") && 
                            humanBoard[i+10].equals(" ") &&
                            humanBoard[i+11].equals(" ") && 
                            humanBoard[i+12].equals(" ")) {
                                attack[0] = i;
                                attack[1] = i+1;
                                attack[2] = i+2;
                                attack[3] = i+10;
                                attack[4] = i+11;
                                attack[5] = i+12;
                                attackOptions.add(attack);
                            }
                        }
                    }
        
                    for (int attackPosition : attackOptions.get(random.nextInt(attackOptions.size()))) {
                        attackBoard(attackPosition, player);
                        if(isPresidentDead) announceWinner(player);
                    }
    
                    missilesCooldown = 8;
    
                    if(tankGunCooldown > 0 && lightTanks > 0) tankGunCooldown -= 1;
                    if(cannonCooldown > 0 && mediumTanks > 0) cannonCooldown -= 1;
    
                    computerAttacks.put("Missiles", "Cooldown");
                }
            }
        } else {
            System.out.println("It's your turn! All of computer attacks are on cooldown.");
            if(tankGunCooldown > 0 && lightTanks > 0) tankGunCooldown -= 1;
            if(cannonCooldown > 0 && mediumTanks > 0) cannonCooldown -= 1;
            if(missilesCooldown > 0 && heavyTanks > 0) missilesCooldown -= 1;
        }
    }

    public void announceWinner(String playerWinner) {
        App.printLine();
        if(playerWinner.equals("human")) System.out.println("\n\u001B[32m■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n■■                                                                                            ■■\n■■              ■   ■    ■■■■■■    ■    ■       ■     ■     ■    ■■■■■    ■■   ■              ■■\n■■              ■   ■    ■■  ■■    ■    ■       ■     ■     ■      ■      ■■   ■              ■■\n■■               ■ ■     ■■  ■■    ■    ■        ■   ■ ■   ■       ■      ■ ■  ■              ■■\n■■                ■      ■■  ■■    ■    ■         ■ ■   ■ ■        ■      ■  ■ ■              ■■\n■■                ■      ■■■■■■    ■■■■■■          ■     ■       ■■■■■    ■   ■■              ■■\n■■                                                                                            ■■\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n\u001B[37m");
        else System.out.println("\n\u001B[31m■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n■■                                                                                            ■■\n■■             ■   ■    ■■■■■■    ■    ■        ■         ■■■■■■    ■■■■■    ■■■■■            ■■\n■■             ■   ■    ■■  ■■    ■    ■        ■         ■    ■    ■        ■                ■■\n■■              ■ ■     ■■  ■■    ■    ■        ■         ■    ■    ■■■■■    ■■■■■            ■■\n■■               ■      ■■  ■■    ■    ■        ■         ■    ■        ■    ■                ■■\n■■               ■      ■■■■■■    ■■■■■■        ■■■■■■    ■■■■■■    ■■■■■    ■■■■■            ■■\n■■                                                                                            ■■\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n\u001B[37m");
        displayBoard(humanBoard, computerBoard);
        System.out.println("\u001B[34m■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■     \u001B[31m■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n\u001B[34m■ **************** YOUR BOARD ************** ■     \u001B[31m■ ************* COMPUTER BOARD ************* ■\n\u001B[34m■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■     \u001B[31m■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n\u001B[37m");
    }

    public void displayBoard(String[] playerBoard, String[] computerBoard) {
        int start = 90;
        int end = 100;

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

                // Player Board
                for(int cols = start; cols < end; cols++) {
                    if(cols != end-1) System.out.print("\u001B[31m" + playerBoard[cols] + "\u001B[0m" + " ■ \u001B[0m");
                    else System.out.print("\u001B[31m" + playerBoard[cols] + "\u001B[0m " + " ■ \u001B[0m");
                }

                // Computer Board
                System.out.print("    ■ \u001B[0m\u001B[33m" + yCoordinates[9-rows] + "\u001B[0m ■ \u001B[0m");
                for(int cols = start; cols < end; cols++) {
                    if(cols != end-1) System.out.print("\u001B[31m" + computerBoard[cols] + "\u001B[0m" + " ■ \u001B[0m");
                    else System.out.print("\u001B[31m" + computerBoard[cols] + "\u001B[0m " + " ■ \u001B[0m");
                }

                start -= 10;
                end -= 10;
                System.out.println("\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■     ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\u001B[0m");
            }
        }
    }
}
