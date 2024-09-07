package game.flasks;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actors.Player;

/**
 * Represents a base class for flasks in the game.
 * <p>
 * This abstract class is extended by specific types of flasks that have
 * various effects when used.
 * </p>
 *
 * <p>Created by:
 * @author Ngu Trien Luan
 * </p>
 */
public abstract class Flask extends Item {
    /** Number of charges the flask has. */
    public final int CHARGES;

    /**
     * Constructor for the Flask class.
     *
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     * @param charges the number of charges the flask has
     */
    public Flask(String name, char displayChar, boolean portable, int charges) {
        super(name, displayChar, true);
        this.CHARGES = charges;
    }

    /**
     * Abstract method to define the action when the flask is used by a player.
     *
     * @param actor the Player using the flask
     */
    public abstract void flasks(Player actor);
}