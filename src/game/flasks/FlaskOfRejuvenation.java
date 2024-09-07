package game.flasks;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.FlaskAction;
import game.actors.Player;

/**
 * A specific type of Flask that restores mana to a player.
 * <p>
 * This flask can restore a certain amount of mana to the player if it has charges left.
 * </p>
 *
 * <p>Created by:
 * @author Ngu Trien Luan
 * </p>
 */
public class FlaskOfRejuvenation extends Flask {

    public static int count = 0;

    /**
     * Constructor for FlaskOfRejuvenation.
     * Initializes the flask with a name, display character, portability, and number of charges.
     */
    public FlaskOfRejuvenation() {
        super("Flask of Rejuvenation", 'o', true, 3);
    }

    /**
     * Restores mana to the player if the flask has charges left.
     *
     * @param actor the Player using the flask
     */
    @Override
    public void flasks(Player actor) {
        if (CHARGES != count) {
            actor.addMana(100);
            count++;
        } else {
            actor.addMana(0);
            System.out.println("Flask Of Rejuvenation is out of charges");
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