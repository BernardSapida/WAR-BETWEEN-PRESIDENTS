package WarBetweenPresident;

import java.util.*;
import WarBetweenPresident.Objects.UnitsAttack;
import WarBetweenPresident.Objects.Helper;

public class App {
    public static App obj;
    private static App app = new App();
    private static UnitsAttack humanBoard = new UnitsAttack("human");
    private static UnitsAttack computerBoard = new UnitsAttack("computer");
    private Scanner in = new Scanner(System.in);
    private String kindDeployment;

    public static void main(String[] args) throws Exception {
        app.startGame();
    }

    /**
     * The function above is a function that starts the game, it also contains the main game loop
     */
    private void startGame() throws InterruptedException {
        obj = new App();
        computerBoard.randomUnitsPosition();
        app.unitsDeploymentType();
        boolean isValid = false;

        computerBoard.humanBoard = humanBoard.humanBoard;
        computerBoard.recordHumanBoard = humanBoard.recordHumanBoard;
        humanBoard.computerBoard = computerBoard.computerBoard;
        humanBoard.recordComputerBoard = computerBoard.recordComputerBoard;

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
            
            printLine();
            System.out.println("Computer is thinking what to attack...");
            printLine();
            timer.scheduleAtFixedRate(computerAttackDone, (random.nextInt(1) + 1) * 1000, (random.nextInt(5) + 1) * 1000);
            synchronized(obj) { 
                obj.wait(); 
            }

            timer.cancel();
            timer.purge();
        }

        while(!isValid) {
            System.out.print("Do you want to play again (Yes/No): ");
            String isPlayAgain = in.nextLine().toLowerCase();

            switch(isPlayAgain) {
                case "yes" -> {
                    isValid = true;
                    humanBoard = new UnitsAttack("human");
                    computerBoard = new UnitsAttack("computer");
                    startGame();
                }
                case "no" -> {
                    isValid = true;
                    System.out.println("Thank you for playing 'War Between President', we hope that you enjoy our game! Be safe and godbless!");
                }
                default -> { 
                    System.out.println("\u001B[31mYour input is incorrect! Please try again.\u001B[37m");
                    printLine();
                }
            }
        }
    }

    /**
     * The function is used to choose the type of units deployment
     */
    private void unitsDeploymentType() {
        boolean isValid = false;
        System.out.println("\n\u001B[36m■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n■■                                                                                                                      ■■\n■■                                      ■        ■       ■        ■         ■ ■ ■ ■                                     ■■\n■■                                       ■      ■ ■     ■        ■ ■        ■     ■                                     ■■\n■■                                        ■    ■   ■   ■        ■   ■       ■ ■ ■ ■                                     ■■\n■■                                         ■  ■     ■ ■        ■ ■ ■ ■      ■   ■                                       ■■\n■■                                          ■        ■        ■       ■     ■     ■                                     ■■\n■■                                                                                                                      ■■\n■■                     ■ ■ ■     ■ ■ ■    ■ ■ ■ ■    ■       ■       ■    ■ ■ ■    ■ ■ ■    ■■    ■                     ■■\n■■                     ■    ■    ■           ■        ■     ■ ■     ■     ■        ■        ■ ■   ■                     ■■\n■■                     ■ ■ ■     ■ ■ ■       ■         ■   ■   ■   ■      ■ ■ ■    ■ ■ ■    ■  ■  ■                     ■■\n■■                     ■    ■    ■           ■          ■ ■     ■ ■       ■        ■        ■   ■ ■                     ■■\n■■                     ■ ■ ■     ■ ■ ■       ■           ■       ■        ■ ■ ■    ■ ■ ■    ■    ■■                     ■■\n■■                                                                                                                      ■■\n■■          ■ ■ ■     ■ ■ ■     ■ ■ ■    ■ ■ ■    ■ ■ ■ ■    ■ ■ ■      ■ ■ ■    ■■    ■    ■ ■ ■ ■    ■ ■ ■ ■          ■■\n■■          ■    ■    ■    ■    ■        ■           ■       ■    ■     ■        ■ ■   ■       ■       ■                ■■\n■■          ■ ■ ■     ■ ■ ■     ■ ■ ■    ■ ■ ■       ■       ■     ■    ■ ■ ■    ■  ■  ■       ■       ■ ■ ■ ■          ■■\n■■          ■         ■  ■      ■            ■       ■       ■    ■     ■        ■   ■ ■       ■             ■          ■■\n■■          ■         ■   ■     ■ ■ ■    ■ ■ ■    ■ ■ ■ ■    ■ ■ ■      ■ ■ ■    ■    ■■       ■       ■ ■ ■ ■          ■■\n■■                                                                                                                      ■■\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n\u001B[37m");
        humanBoard.getBoard();

        while(!isValid) {
            System.out.println("\nKind of units deployment:");
            System.out.println("[1] -> Manual Units Deployment (You will position units whenever you want)");
            System.out.println("[2] -> Generated Units Deployment (We will randomly position your units on the board)");
            System.out.print("Choose what type of units deployment: ");
            kindDeployment = in.nextLine();
            switch(kindDeployment) {
                case "1" -> { 
                    humanBoard.positionPlayerUnits();
                    isValid = true;
                }
                
                case "2" -> { 
                    humanBoard.randomUnitsPosition();
                    isValid = true;
                }

                default -> { 
                    System.out.println("\u001B[31mYour input is incorrect! Please try again.\u001B[37m");
                    isValid = false;
                }
            }

            printLine();
        }
    }

    public static void printLine() {
        System.out.println("\u001B[35m\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n\u001B[37m");
    }
}