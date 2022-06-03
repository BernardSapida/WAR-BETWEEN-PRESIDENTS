package WarBetweenPresident.Objects;

import java.util.*;

public abstract class Board {
    public String[] humanBoard = new String[100];
    public String[] recordHumanBoard = new String[100];
    public String[] computerBoard = new String[100];
    public String[] recordComputerBoard = new String[100];
    protected String[] xCoordinates = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
    protected String[] yCoordinates = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
    protected ArrayList<String> computerAvailableAttacks = new ArrayList<String>();
    protected HashMap<String, String> computerAttacks = new HashMap<String, String>();
	protected String player;

    public Board(String player) {
        for(int i = 0; i < humanBoard.length; i++) {
            humanBoard[i] = " ";
            recordHumanBoard[i] = " ";
            computerBoard[i] = " ";
            recordComputerBoard[i] = " ";
        }

        this.player = player;

        computerAttacks.put("Nuke", "Ready");
        computerAttacks.put("Mortars", "Ready");
        computerAttacks.put("Tank Gun", "Ready");
        computerAttacks.put("Cannon", "Ready");
        computerAttacks.put("Missiles", "Ready");
    }

    public void getBoard() {
        String[] playerBoard = (player.equals("human") ? humanBoard : computerBoard);
        String[] playerRecordBoard = (player.equals("human") ? recordHumanBoard : recordComputerBoard);
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

        System.out.println("\u001B[34m■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■     ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n■ **************** UNITS BOARD ************* ■     ■ ************** BATTLE BOARD ************** ■\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■     ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n\u001B[37m");
    }
}
