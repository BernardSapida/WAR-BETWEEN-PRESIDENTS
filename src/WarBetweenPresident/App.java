package WarBetweenPresident;

import java.io.IOException;
import java.util.*;

import WarBetweenPresident.Objects.Board;
import WarBetweenPresident.Objects.Helper;
// import WarBetweenPresident.Objects.Units;

public class App {
    public static App obj;
    private static App app = new App();
    private static Board humanBoard = new Board("human");
    private static Board computerBoard = new Board("computer");
    private Scanner in = new Scanner(System.in);
    private String kindDeployment;
    
    /**
     * The function is a main function that runs the game
     */
    public static void main(String[] args) throws Exception {
        obj = new App();
        computerBoard.randomUnitsPosition();
        app.unitsDeploymentType();

        computerBoard.humanBoard = humanBoard.humanBoard;
        computerBoard.recordHumanBoard = humanBoard.recordHumanBoard;
        humanBoard.computerBoard = computerBoard.computerBoard;
        humanBoard.recordComputerBoard = computerBoard.recordComputerBoard;

        // The main loop of the game, where the human and the computer will attack each other until their
        // President get killed.
        while(true) {
            Timer timer = new Timer();
            TimerTask computerAttackDone = new Helper();
            Random random = new Random();

            humanBoard.queryAttack();
            if(humanBoard.isPresidentDead) break;

            computerBoard.humanBoard = humanBoard.humanBoard;
            computerBoard.recordHumanBoard = humanBoard.recordHumanBoard;

            computerBoard.computerAttack();
            if(computerBoard.isPresidentDead) break;
            humanBoard.computerBoard = computerBoard.computerBoard;
            humanBoard.recordComputerBoard = computerBoard.recordComputerBoard;
            
            System.out.println("\n================================================\n");
            System.out.println("Computer is thinking what to attack...");
            System.out.println("\n================================================\n");
            timer.scheduleAtFixedRate(computerAttackDone, (random.nextInt(5) + 1) * 1000, (random.nextInt(5) + 1) * 1000);
            synchronized(obj) { 
                obj.wait(); 
            }

            timer.cancel();
            timer.purge();
        }
    }

    /**
     * This function is responsible for asking the user what kind of units deployment he wants to use,
     * and then, depending on the user's choice, it will either call the function that will randomly
     * position the units on the board or the function that will ask the user to position the units on
     * the board
     */
    private void unitsDeploymentType() {
        boolean isValid = false;

        System.out.println("\n\u001B[36m■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n■■                                                                                                                      ■■\n■■                                      ■        ■       ■        ■         ■ ■ ■ ■                                     ■■\n■■                                       ■      ■ ■     ■        ■ ■        ■     ■                                     ■■\n■■                                        ■    ■   ■   ■        ■   ■       ■ ■ ■ ■                                     ■■\n■■                                         ■  ■     ■ ■        ■ ■ ■ ■      ■   ■                                       ■■\n■■                                          ■        ■        ■       ■     ■     ■                                     ■■\n■■                                                                                                                      ■■\n■■                     ■ ■ ■     ■ ■ ■    ■ ■ ■ ■    ■       ■       ■    ■ ■ ■    ■ ■ ■    ■■    ■                     ■■\n■■                     ■    ■    ■           ■        ■     ■ ■     ■     ■        ■        ■ ■   ■                     ■■\n■■                     ■ ■ ■     ■ ■ ■       ■         ■   ■   ■   ■      ■ ■ ■    ■ ■ ■    ■  ■  ■                     ■■\n■■                     ■    ■    ■           ■          ■ ■     ■ ■       ■        ■        ■   ■ ■                     ■■\n■■                     ■ ■ ■     ■ ■ ■       ■           ■       ■        ■ ■ ■    ■ ■ ■    ■    ■■                     ■■\n■■                                                                                                                      ■■\n■■          ■ ■ ■     ■ ■ ■     ■ ■ ■    ■ ■ ■    ■ ■ ■ ■    ■ ■ ■      ■ ■ ■    ■■    ■    ■ ■ ■ ■    ■ ■ ■ ■          ■■\n■■          ■    ■    ■    ■    ■        ■           ■       ■    ■     ■        ■ ■   ■       ■       ■                ■■\n■■          ■ ■ ■     ■ ■ ■     ■ ■ ■    ■ ■ ■       ■       ■     ■    ■ ■ ■    ■  ■  ■       ■       ■ ■ ■ ■          ■■\n■■          ■         ■  ■      ■            ■       ■       ■    ■     ■        ■   ■ ■       ■             ■          ■■\n■■          ■         ■   ■     ■ ■ ■    ■ ■ ■    ■ ■ ■ ■    ■ ■ ■      ■ ■ ■    ■    ■■       ■       ■ ■ ■ ■          ■■\n■■                                                                                                                      ■■\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n\u001B[37m");

        humanBoard.getBoard();

        while(!isValid) {
            System.out.println("\n================================================\n");
            System.out.println("Kind of units deployment:");
            System.out.println("[1] -> Manual Units Deployment (You will position units whenever you want)");
            System.out.println("[2] -> Generated Units Deployment (We will randomly position your units on the board)");
            System.out.print("Choose what type of units deployment: ");
            kindDeployment = in.nextLine();
            System.out.println("\n================================================\n");
            switch(kindDeployment) {
                case "1" -> { 
                    humanBoard.positionPlayerUnits();
                    humanBoard.getBoard();
                    isValid = true;
                }
                case "2" -> { 
                    humanBoard.randomUnitsPosition();
                    humanBoard.getBoard();
                    isValid = true;
                }

                default -> { 
                    isValid = false;
                }
            }

            clearTerminal();
            humanBoard.getBoard();
        }
    }

    /**
     * It clears the terminal screen
     */
    public static void clearTerminal() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
    }
}