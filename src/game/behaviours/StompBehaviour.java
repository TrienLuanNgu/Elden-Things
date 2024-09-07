package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.NumberRange;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actors.Behaviour;
import game.FancyMessage;
import game.Status;
import game.actors.Player;
import game.grounds.Fire;

import java.util.Random;

/**
 * Behaviour representing the stomp action of an actor.
 * This behaviour allows an actor to stomp and deal damage to a target player within a specified range.
 * Additionally, there is a chance for the ground to catch fire.
 *
 * @author Ngu Trien Luan
 */
public class StompBehaviour extends Action implements Behaviour {

    /**
     * The target player to stomp.
     */
    private final Player target;

    /**
     * The range within which the stomp can occur.
     */
    private final int stompRange;

    /**
     * Constructor to create a StompBehaviour.
     *
     * @param subject the target player to stomp
     * @param range the range within which the actor can stomp the target
     */
    public StompBehaviour(Player subject, int range) {
        this.target = subject;
        this.stompRange = range;
    }

    /**
     * Executes the stomp action.
     * The actor has a chance to either deal damage to the target or set nearby ground on fire.
     *
     * @param actor the actor performing the stomp
     * @param map the map the actor is on
     * @return a description of the action outcome
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Random rand = new Random();
        // 5% chance of executing the stomp
        if (rand.nextInt(100) < 5) {
            // 10% chance of setting ground on fire
            if (rand.nextInt(100) < 10) {
                for (Exit exit : map.locationOf(actor).getExits()) {
                    if (!exit.getDestination().containsAnActor()) {
                        exit.getDestination().setGround(new Fire());
                    }
                }
                target.deductHitpoints(50);
                if (target.getHitPoints() <= 0) {
                    System.out.println(actor + " stomps and explodes." + System.lineSeparator());
                    System.out.println(FancyMessage.YOU_DIED);
                    return target.unconscious(actor, map);
                }
            }
            target.deductHitpoints(100);
            if (target.getHitPoints() <= 0) {
                System.out.println(actor + " stomps " + target + System.lineSeparator());
                System.out.println(FancyMessage.YOU_DIED);
                return target.unconscious(actor, map);
            }
            return actor + " stomps " + target + "." + System.lineSeparator();
        }
        // 10% chance of an explosion
        else if (rand.nextInt(100) < 10) {
            for (Exit exit : map.locationOf(actor).getExits()) {
                if (!exit.getDestination().containsAnActor()) {
                    if (exit.getDestination().getGround().hasCapability(Status.BURNABLE)) {
                        exit.getDestination().setGround(new Fire());
                    }
                }
            }
            target.deductHitpoints(50);
            if (target.getHitPoints() <= 0) {
                System.out.println(actor + " explodes." + System.lineSeparator());
                System.out.println(FancyMessage.YOU_DIED);
                return target.unconscious(actor, map);
            }
            return actor + " explodes." + System.lineSeparator();
        }

        return actor + " misses " + target + ".";
    }

    /**
     * Determines if the actor should stomp based on the proximity of the target.
     *
     * @param actor the actor performing the behaviour
     * @param map the map the actor is on
     * @return the action to stomp if the target is within range, or null if no action is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        // Check if target is within the stomp range
        if (distance(here, there) <= stompRange) {
            NumberRange xs, ys;
            if (here.x() == there.x() || here.y() == there.y()) {
                xs = new NumberRange(Math.min(here.x(), there.x()), Math.abs(here.x() - there.x()) + 1);
                ys = new NumberRange(Math.min(here.y(), there.y()), Math.abs(here.y() - there.y()) + 1);

                for (int x : xs) {
                    for (int y : ys) {
                        if (map.at(x, y).getGround().blocksThrownObjects()) {
                            return null;  // Block if there's an obstacle
                        }
                    }
                }
                return this;  // Perform the stomp
            }
        }

        return null;  // Do nothing if target is not in range
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

    /**
     * Returns the description of the stomp action.
     *
     * @param actor the actor performing the stomp
     * @return a string representing the action in the menu (currently empty)
     */
    @Override
    public String menuDescription(Actor actor) {
        return "";
    }
}