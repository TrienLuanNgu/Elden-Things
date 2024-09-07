package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class defines the behavior of an Actor that makes it wander to a random location
 * on the map if possible.
 *
 * <p>Created by:
 * @author Riordan D. Alfredo
 * Modified by: Ngu Trien Luan</p>
 */
public class WanderBehaviour implements Behaviour {

    private final Random random = new Random();

    /**
     * Determines the next action for the actor to wander to a random location.
     *
     * <p>If there are valid moves available, it returns a MoveAction to a randomly
     * chosen location. If no valid move is possible, it returns null.</p>
     *
     * @param actor the Actor enacting the behaviour
     * @param map the map that the actor is currently on
     * @return an Action representing the move to a random valid location, or null if no move is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<>();

        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
            }
        }

        if (!actions.isEmpty()) {
            return actions.get(random.nextInt(actions.size()));
        } else {
            return null;
        }
    }
}