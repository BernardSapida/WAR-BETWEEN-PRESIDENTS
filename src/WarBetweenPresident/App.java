package WarBetweenPresident;

import java.io.IOException;
import WarBetweenPresident.Objects.Board;
import WarBetweenPresident.Objects.Units;
import java.util.ArrayList;
import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {
        Board playerBoard = new Board();
        // Board computerBoard = new Board();

        playerBoard.positionComputerUnits();
        // playerBoard.attackBoard(0, "human");
        // playerBoard.attackBoard(1, "human");
        // playerBoard.attackBoard(10, "human");
        // playerBoard.attackBoard(11, "human");
        // playerBoard.attackBoard(91, "human");
        playerBoard.getBoard("human");

        playerBoard.queryHeavyTankAttackPosition();
    }

    public static void clearTerminal() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}

// a1 a2 a3 b1 b2 b3