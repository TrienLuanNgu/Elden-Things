package game.things;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.EatAction;
import game.actors.Player;

/**
 * Represents a Shadowtree Fragment item that can be consumed by a player.
 * <p>
 * Consuming this fragment restores mana, hitpoints, and strength to the player.
 * </p>
 *
 * <p>Created by:
 * @author Ngu Trien Luan
 * </p>
 */
public class ShadowtreeFragment extends Things {

    /**
     * Constructor for the ShadowtreeFragment class.
     * Initializes the fragment with a name, display character, and portability.
     */
    public ShadowtreeFragment() {
        super("Shadowtree Fragment", 'e', true);
    }

    /**
     * Defines the effect of consuming the Shadowtree Fragment.
     * <p>
     * When consumed by a player, it restores mana, hitpoints, and strength, and then removes itself from the player's inventory.
     * </p>
     *
     * @param actor the Player who consumes the fragment
     */
    @Override
    public void eat(Player actor) {
        actor.addMana(25);
        actor.addHitpoints(50);
        actor.addStrength(5);
        actor.removeItemFromInventory(this);
    }

    /**
     * Provides the list of actions allowed for this fragment.
     *
     * @param owner the Actor who owns this fragment
     * @return an ActionList containing the allowable actions for this fragment
     */
    public ActionList allowableActions(Actor owner) {
        ActionList actions = new ActionList();
        actions.add(new EatAction(this));
        return actions;
    }
}