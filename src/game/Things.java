package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

abstract class Things extends Item {
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Things(String name, char displayChar, boolean portable) {
        super(name, displayChar, true);
    }

    abstract void eat(Player actor);
}
