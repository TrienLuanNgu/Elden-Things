package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Ground;
import game.Status;
import game.weapons.BareFist;
import game.weapons.WeaponItem;

/**
 * Class representing the Player character.
 * The Player class is responsible for managing the player's stats (mana, hit points, strength),
 * and determining what actions the player can take during their turn.
 *
 * Created by:
 * @author Adrian Kristanto
 * @modified by Ngu Trien Luan - Added mana, strength attributes and related functionality.
 */
public class Player extends Actor {
    /**
     * Mana points of the player.
     */
    public int mana;

    /**
     * Strength attribute of the player.
     */
    public int strength;

    /**
     * Current hit points of the player.
     */
    public int hitPoints;

    /**
     * The player's equipped weapon, if any.
     */
    private WeaponItem weaponItem;

    /**
     * The amount of hit points healed.
     */
    public int healedHitPoints = 0;

    /**
     * The amount of mana restored.
     */
    public int healedMana = 0;

    /**
     * Ground the player is currently on.
     */
    private Ground ground;

    /**
     * Constructor to initialize the Player.
     *
     * @param name        Name to call the player in the UI.
     * @param displayChar Character to represent the player in the UI.
     * @param hitPoints   Player's starting number of hit points.
     * @param mana        Player's starting mana points.
     * @param strength    Player's initial strength.
     */
    public Player(String name, char displayChar, int hitPoints, int mana, int strength) {
        super(name, displayChar, hitPoints);
        this.mana = mana;
        this.hitPoints = hitPoints;
        this.strength = strength;
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Status.BURN);
        this.setIntrinsicWeapon(new BareFist());  // Player starts with bare fists as a weapon.
    }

    /**
     * Handles the player's actions during their turn.
     * If the player is in the middle of a multi-turn action, it will continue that action.
     * Otherwise, it displays the available actions in a menu.
     *
     * @param actions     the list of possible actions for the player.
     * @param lastAction  the action the player performed last turn.
     * @param map         the current game map.
     * @param display     the display used to show the game status.
     * @return the action that the player chooses to perform.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // Return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }

    /**
     * Returns the current mana of the player.
     *
     * @return the player's mana points.
     */
    public int getMana() {
        return mana;
    }

    /**
     * Returns the current hit points of the player.
     *
     * @return the player's hit points.
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Returns the current strength of the player.
     *
     * @return the player's strength.
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Sets the player's mana.
     *
     * @param mana the new mana value.
     */
    public void setMana(int mana) {
        this.mana = mana;
    }

    /**
     * Sets the player's hit points.
     *
     * @param hitPoints the new hit points value.
     */
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    /**
     * Sets the player's strength.
     *
     * @param strength the new strength value.
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * Increases the player's mana by a specified amount.
     *
     * @param amount the amount to add to the player's mana.
     */
    public void addMana(int amount) {
        this.mana += amount;
    }

    /**
     * Increases the player's strength by a specified amount.
     *
     * @param amount the amount to add to the player's strength.
     */
    public void addStrength(int amount) {
        this.strength += amount;
    }

    /**
     * Increases the player's hit points by a specified amount.
     *
     * @param amount the amount to add to the player's hit points.
     */
    public void addHitpoints(int amount) {
        this.hitPoints += amount;
    }

    /**
     * Deducts the player's hit points by a specified amount.
     *
     * @param amount the amount to subtract from the player's hit points.
     */
    public void deductHitpoints(int amount) {
        this.hitPoints -= amount;
    }

    /**
     * Returns a string representation of the player, including their current stats (mana, strength, and hit points).
     *
     * @return a string containing the player's name and current stats.
     */
    @Override
    public String toString() {
        return name + " (Mana: " + mana + ", Strength: " + strength + ", Hit points: " + hitPoints + ")";
    }
}