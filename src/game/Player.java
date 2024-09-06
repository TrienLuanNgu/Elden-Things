package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;

/**
 * Class representing the Player.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Player extends Actor {
    public int mana;
    public int strength;
    public int hitPoints;
    private WeaponItem weaponItem;
    public int healedHitPoints = 0;
    public int healedMana = 0;


    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints, int mana, int strength) {
        super(name, displayChar, hitPoints);
        this.mana = mana;
        this.hitPoints = hitPoints;
        this.strength = strength;
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.setIntrinsicWeapon(new BareFist());

    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }

//    @Override
//    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
//        ActionList actions = new ActionList();
//        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
//            actions.add(new AttackAction(this, direction));
//
//        }
//        return actions;
//
//    }

    public int getMana() {
        return mana;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getStrength() {
        return strength;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void addMana(int amount) {
        this.mana += amount;
    }

    public void addStrength(int amount) {
        this.strength += amount;
    }

    public void addHitpoints(int amount) {
        this.hitPoints += amount;
    }

    public void deductHitpoints(int amount) {
        this.hitPoints -= amount;
    }



    @Override
    public String toString(){
        return name + " (Mana: " + mana + ", Strength: " + strength + ", Hit points: " + hitPoints + ")";
    }

}
