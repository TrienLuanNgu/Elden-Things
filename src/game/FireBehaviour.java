package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;

public class FireBehaviour extends Action implements Behaviour {
    private int counter;
    private final Actor target;
    private final int range;


    public FireBehaviour(Actor target, int range) {
        counter = 0;
        this.target = target;
        this.range = range;

    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
////        Random rand = new Random();
////        counter = rand.nextInt(100);
////        return counter <= 10 ? this : null;
//        Location there = map.locationOf(target);
//
//        // Check if the player is standing on a tile that contains fire
//        if (there.getGround() instanceof Fire) {
//            // Apply fire damage to the player
//            target.hurt(5);  // Example: Deduct 10 hit points
//
//            // You can return the action itself, meaning fire damage is applied
//            return this;
//        }
//        return null;
        if(!map.contains(target) || !map.contains(actor))
            return null;

        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);
        ActionList actions = new ActionList();

        int currentDistance = distance(here, there);
        Random rand = new Random();

        if(rand.nextInt(100) < 10) {
            if (currentDistance == range) {
                for (Exit exit : here.getExits()) {
                    Location destination = exit.getDestination();
                    if (destination.canActorEnter(actor)) {
                        int newDistance = distance(destination, there);
                        if (newDistance < currentDistance) {

                            target.hurt(50);
                        }
                    }
                }

            }
        }
        return null;
    }

    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

    @Override
    public String execute(Actor actor, GameMap map) {

            for (Exit exit : map.locationOf(actor).getExits()) {
                if (!exit.getDestination().containsAnActor()) {
                    exit.getDestination().setGround(new Fire());
                    return actor + " exploded";
                }


        }
        return "";
    }
//    @Override
//    public String execute(Actor actor, GameMap map) {
//        for (Exit exit : map.locationOf(actor).getExits()) {
//            if (!exit.getDestination().containsAnActor()) {
//                exit.getDestination().setGround(new Fire());
//            }
//        }
//        return actor + " exploded!";
//    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " exploded.";
    }
}
