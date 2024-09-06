package game;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;

abstract class Pickupable extends PickUpAction {
    public Pickupable(Item item) {
        super(item);
    }

}
