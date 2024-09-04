package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

public class ShadowtreeFragment extends Things {

    public ShadowtreeFragment() {
        super("Shadowtree Fragment", 'e', true);
    }

    @Override
    void eat(Player actor) {
        actor.addMana(25);
        actor.addHitpoints(50);
        actor.addStrength(5);
        actor.removeItemFromInventory(this);
    }

    public ActionList allowableActions(Actor owner) {

        ActionList actions = new ActionList();
        actions.add(new EatAction(this));
        return actions;
    }
}
