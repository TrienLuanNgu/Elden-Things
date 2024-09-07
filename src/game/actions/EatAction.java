package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.things.Things;

/**
 * An action for eating a specific item that can restore or boost the player's stats.
 * This action is executed by an actor, typically a player, and applies effects such as restoring mana, hit points, or increasing strength.
 * @author Ngu Trien Luan
 */
public class EatAction extends Action {

    /**
     * The item (thing) to be eaten by the player.
     */
    private Things things;

    /**
     * Constructor for the EatAction.
     *
     * @param things the item that will be consumed by the player.
     */
    public EatAction(Things things) {
        this.things = things;
    }

    /**
     * Executes the EatAction, which allows the player to consume the item and modifies their stats.
     *
     * @param actor the actor performing the action (must be a player).
     * @param map   the current map the actor is on.
     * @return a string describing the result of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        things.eat((Player) actor);

        return "Player now has " + ((Player) actor).getMana() + " mana and " + ((Player) actor).getHitPoints() + " hit points and " + ((Player) actor).getStrength() + " strength!";
    }

    /**
     * Returns a string description of the action for the menu, showing that the actor is eating the item.
     *
     * @param actor the actor performing the action.
     * @return a string for the menu description.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " eats " + things;
    }
}