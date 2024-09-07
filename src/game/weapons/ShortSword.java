package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actors.Player;

/**
 * Represents a powerful weapon, the Short Sword, in the game.
 * <p>
 * The Short Sword can be used for attacking and has specific requirements for pickup based on the player's strength.
 * </p>
 *
 * <p>Created by:
 * @author Ngu Trien Luan
 * </p>
 */
public class ShortSword extends WeaponItem {

    /**
     * Constructor for the ShortSword class.
     * Initializes the weapon with its name, display character, damage, action description, and hit points.
     */
    public ShortSword() {
        super("Short Sword", '+', 100, "swing", 75);
        addCapability(Status.HAS_SHORT_SWORD);
    }

    /**
     * Performs an attack with the Short Sword.
     * <p>
     * Inherits attack behavior from the superclass and can be customized further if needed.
     * </p>
     *
     * @param attacker the Actor attacking with the sword
     * @param target the Actor being attacked
     * @param map the GameMap where the attack takes place
     * @return a description of the attack result
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        return super.attack(attacker, target, map);
    }

    /**
     * Determines the action to pick up the Short Sword based on the player's strength.
     *
     * @param actor the Actor attempting to pick up the sword
     * @return a PickUpAction if the player's strength is sufficient, or null otherwise
     */
    public PickUpAction getPickUpAction(Actor actor) {
        Player player = (Player) actor;
        if (player.getStrength() >= 10) {
            return new PickUpAction(this);
        }
        return null;
    }
}