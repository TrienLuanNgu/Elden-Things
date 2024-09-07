package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.flasks.Flask;

/**
 * An action that allows the player to use a flask to restore their mana and hit points.
 * This action can be performed by the player to gain benefits from the flask.
 *
 * @author Ngu Trien Luan
 */
public class FlaskAction extends Action {

    /**
     * The flask item that will be used by the player.
     */
    private Flask flask;

    /**
     * Constructor for the FlaskAction.
     *
     * @param flask the flask that will be consumed or used by the player.
     */
    public FlaskAction(Flask flask) {
        this.flask = flask;
    }

    /**
     * Executes the FlaskAction, applying the effects of the flask to the player's stats.
     *
     * @param actor the actor performing the action (must be a player).
     * @param map   the current map the actor is on.
     * @return a string describing the result of using the flask.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        flask.flasks((Player) actor);
        return "Player now has " + ((Player) actor).getMana() + " mana and " + ((Player) actor).getHitPoints() + " hit points!";
    }

    /**
     * Returns a string description of the action for the menu, showing that the actor is using the flask.
     *
     * @param actor the actor performing the action.
     * @return a string for the menu description.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " uses " + flask;
    }
}