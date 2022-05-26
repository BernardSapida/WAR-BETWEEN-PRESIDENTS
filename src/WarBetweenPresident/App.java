package WarBetweenPresident;

import java.io.IOException;
import WarBetweenPresident.Objects.Board;
import WarBetweenPresident.Objects.Units;

public class App {
    public static void main(String[] args) throws Exception {
        Board board = new Board();
        board.positionUnits();
    }

    public static void clearTerminal() {
        System.out.print("\033[H\033[2J");

        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}

// a1 a2 b1 b2 c1 c2