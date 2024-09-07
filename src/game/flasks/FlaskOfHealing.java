package game.flasks;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import game.actions.FlaskAction;
import game.actors.Player;

/**
 * A specific type of Flask that provides healing to a player.
 * <p>
 * This flask can heal the player for a certain amount of hitpoints if it has charges left.
 * </p>
 *
 * <p>Created by:
 * @author Ngu Trien Luan
 * </p>
 */
public class FlaskOfHealing extends Flask {
    public static int count = 0;

    /**
     * Constructor for FlaskOfHealing.
     * Initializes the flask with a name, display character, portability, and number of charges.
     */
    public FlaskOfHealing() {
        super("Flask of Healing", 'u', true, 5);
    }

    /**
     * Heals the player if the flask has charges left.
     *
     * @param actor the Player using the flask
     */
    @Override
    public void flasks(Player actor){
        if (CHARGES != count) {
            actor.addHitpoints(150);
            count++;
        }
        else{
            actor.addHitpoints(0);
            System.out.println("Flask Of Healing is out of charges");
        }
    }

    /**
     * Provides the list of actions allowed for this flask.
     *
     * @param owner the Actor who owns this flask
     * @return an ActionList containing the allowable actions for this flask
     */
    public ActionList allowableActions(Actor owner) {
        ActionList actions = new ActionList();
        actions.add(new FlaskAction(this));
        return actions;
    }
}