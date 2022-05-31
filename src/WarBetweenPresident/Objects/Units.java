package WarBetweenPresident.Objects;

/**
 * It's a class that holds all the units for a player
 */
public class Units {
    protected String player;
    protected int president = 0;
    protected int soldiers = 0;
    protected int lightTanks = 0;
    protected int mediumTanks = 0;
    protected int heavyTanks = 0;

    protected int nuke = 1;
    protected int mortars = 1;

    protected int tankGunCooldown;
    protected int cannonCooldown;
    protected int missilesCooldown;

    public boolean isPresidentDead = false;
}