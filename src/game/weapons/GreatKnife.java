package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actors.Player;

/**
 * Represents a powerful weapon, the Great Knife, in the game.
 * <p>
 * The Great Knife can be used for attacking and has specific requirements for pickup based on the player's strength.
 * </p>
 *
 * <p>Created by:
 * @author Ngu Trien Luan
 * </p>
 */
public class GreatKnife extends WeaponItem {

    /**
     * Constructor for the GreatKnife class.
     * Initializes the weapon with its name, display character, damage, action description, and hit points.
     */
    public GreatKnife() {
        super("GreatKnife", '!', 75, "stab", 60);
        addCapability(Status.HAS_GREAT_KNIFE);
    }

    /**
     * Performs an attack with the Great Knife.
     * <p>
     * Inherits attack behavior from the superclass and can be customized further if needed.
     * </p>
     *
     * @param attacker the Actor attacking with the knife
     * @param target the Actor being attacked
     * @param map the GameMap where the attack takes place
     * @return a description of the attack result
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        return super.attack(attacker, target, map);
    }

    /**
     * Determines the action to pick up the Great Knife based on the player's strength.
     *
     * @param actor the Actor attempting to pick up the knife
     * @return a PickUpAction if the player's strength is sufficient, or null otherwise
     */
    public PickUpAction getPickUpAction(Actor actor) {
        Player player = (Player) actor;
        if (player.getStrength() >= 5) {
            return new PickUpAction(this);
        }
        return null;
    }
}