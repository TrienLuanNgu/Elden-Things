package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

abstract class Flask extends Item {
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public final int CHARGES;
    public Flask(String name, char displayChar, boolean portable, int charges) {
        super(name, displayChar, true);
        this.CHARGES = charges;
    }

    abstract void flasks(Player actor);
}
