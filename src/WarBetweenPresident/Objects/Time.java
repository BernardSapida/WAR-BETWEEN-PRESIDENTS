package WarBetweenPresident.Objects;

import java.util.*;
import WarBetweenPresident.App;

/**
 * It's a class that extends the TimerTask class and overrides the run() method
 */
public class Time extends TimerTask {
    public void run() {
        System.out.println("Computer attacked your board and it is your turn!");
        App.printLine();
        
        synchronized(App.obj){
            App.obj.notify();
        }
    }
}
