package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actors.Player;

/**
 * A behaviour that enables an actor to follow a target actor (usually a player) within a certain range.
 * When the target is within the specified range, the following actor will attempt to move closer.
 *
 * @author Ngu Trien Luan
 */
public class FollowBehaviour implements Behaviour {

    /**
     * The target player to follow.
     */
    private final Player target;

    /**
     * The distance at which the actor will begin following the target.
     */
    private final int range;

    /**
     * Constructor to create a FollowBehaviour for the actor.
     *
     * @param subject the target player to follow
     * @param range the distance at which the actor should start following
     */
    public FollowBehaviour(Player subject, int range) {
        this.target = subject;
        this.range = range;
    }

    /**
     * Determines the action for the actor to follow the target.
     * If the target is within range, the actor will attempt to move closer.
     *
     * @param actor the actor performing the behaviour
     * @param map the map the actor is on
     * @return the action to move closer to the target, or null if no action is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if(!map.contains(target) || !map.contains(actor))
            return null;

        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);
        ActionList actions = new ActionList();

        int currentDistance = distance(here, there);
        if (currentDistance == range){
            target.addCapability(Status.BEING_FOLLOW);
            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();
                if (destination.canActorEnter(actor)) {
                    int newDistance = distance(destination, there);
                    if (newDistance < currentDistance) {
                        return new MoveActorAction(destination, exit.getName());
                    }
                }
            }
        }
        return null;
    }

    /**
     * Calculates the Manhattan distance between two locations.
     *
     * @param a the first location
     * @param b the second location
     * @return the Manhattan distance between the two locations
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}