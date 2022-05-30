package WarBetweenPresident;

import java.io.IOException;
import WarBetweenPresident.Objects.Board;
import WarBetweenPresident.Objects.Units;
// import java.util.ArrayList;
// import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {
        Board humanBoard = new Board("human");
        Board computerBoard = new Board("computer");

        humanBoard.randomUnitsPosition();
        computerBoard.randomUnitsPosition();
        
        humanBoard.getBoard();

        while(true) {
            humanBoard.queryAttack();
            
            computerBoard.computerAttack();
            // computerBoard.getBoard();

            humanBoard.computerBoard = computerBoard.computerBoard;
            humanBoard.recordComputerBoard = computerBoard.recordComputerBoard;

            computerBoard.humanBoard = humanBoard.humanBoard;
            computerBoard.recordHumanBoard = humanBoard.recordHumanBoard;
        }
    }

    public static void clearTerminal() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
    }
}