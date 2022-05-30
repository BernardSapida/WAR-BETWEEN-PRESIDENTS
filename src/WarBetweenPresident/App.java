package WarBetweenPresident;

import java.io.IOException;
import WarBetweenPresident.Objects.Board;
import WarBetweenPresident.Objects.Units;
// import java.util.ArrayList;
// import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {
        Board playerBoard = new Board();
        // Board computerBoard = new Board();

        // Calling the method `positionComputerUnits()` from the `Board` class.
        // Set up the computer units on the board.
        playerBoard.positionComputerUnits();
        
        // Printing the human board to the console.
        playerBoard.getBoard("human");

        // Testing: attack the board of Computer, it will mark "X" if the position is being hit
        // else "O" if missed
        playerBoard.queryHeavyTankAttackPosition();
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
    }
}