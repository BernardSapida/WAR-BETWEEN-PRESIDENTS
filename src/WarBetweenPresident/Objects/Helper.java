package WarBetweenPresident.Objects;

import java.util.*;
import WarBetweenPresident.App;

public class Helper extends TimerTask {
    public void run() {
        System.out.println("Computer attacked your board and it is your turn!");
        System.out.println("\n================================================\n");
        
        synchronized(App.obj){
            App.obj.notify();
        }
    }
}
