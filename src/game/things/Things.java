package game.things;

import edu.monash.fit2099.engine.items.Item;
import game.actors.Player;

/**
 * Represents a base class for items that can be interacted with in various ways.
 * <p>
 * This abstract class defines common properties for items and provides an abstract
 * method for actions like eating, which should be implemented by subclasses.
 * </p>
 *
 * <p>Created by:
 * @author Ngu Trien Luan
 * </p>
 */
public abstract class Things extends Item {

    /**
     * Constructor for the Things class.
     *
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Things(String name, char displayChar, boolean portable) {
        super(name, displayChar, true);
    }

    /**
     * Abstract method to define the action when the item is consumed.
     *
     * @param actor the Player who consumes the item
     */
    public abstract void eat(Player actor);
}