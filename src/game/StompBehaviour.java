//package game;
//
//import edu.monash.fit2099.engine.actions.Action;
//import edu.monash.fit2099.engine.positions.NumberRange;
//import edu.monash.fit2099.engine.actors.Actor;
//import edu.monash.fit2099.engine.positions.GameMap;
//import edu.monash.fit2099.engine.positions.Location;
//import edu.monash.fit2099.engine.actors.Behaviour;
//
//public class StompBehaviour extends Action implements Behaviour {
//
//    private final Actor target;
//
//    public StompBehaviour(Actor subject) {
//        this.target = subject;
//    }
//
//    @Override
//    public String execute(Actor actor, GameMap map) {
//        return actor + " stomps " + target + "." + System.lineSeparator();
//    }
//
//    @Override
//    public Action getAction(Actor actor, GameMap map) {
//        Location here = map.locationOf(actor);
//        Location there = map.locationOf(target);
//
//        NumberRange xs, ys;
//        if (here.x() == there.x() || here.y() == there.y()) {
//            xs = new NumberRange(Math.min(here.x(), there.x()), Math.abs(here.x() - there.x()) + 1);
//            ys = new NumberRange(Math.min(here.y(), there.y()), Math.abs(here.y() - there.y()) + 1);
//
//            for (int x : xs) {
//                for (int y : ys) {
//                    if(map.at(x, y).getGround().blocksThrownObjects())
//                        return null;
//                }
//            }
//            return this;
//        }
//        return null;
//    }
//
//    @Override
//    public String menuDescription(Actor actor) {
//        return "";
//    }
//}


package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.positions.NumberRange;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actors.Behaviour;

import java.util.Random;

public class StompBehaviour extends Action implements Behaviour {

    private final Player target;
    private final int stompRange;  // Range within which stomp can happen

    public StompBehaviour(Player subject, int range) {
        this.target = subject;
        this.stompRange = range;  // Set stomp range
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        //return actor + " stomps " + target + "." + System.lineSeparator();
        Random rand = new Random();
        if (!(rand.nextInt(100) < 5 )) {
            return actor + " misses " + target + ".";
        }
        target.deductHitpoints(100);
        if (target.getHitPoints() <= 0) {
            //map.removeActor(target);
            //return actor + " killed " + target + ".";
            return target.unconscious(actor,map);
        }
        return actor + " stomps " + target + "." + System.lineSeparator();




//        return actor + " stomps " + target + "." + System.lineSeparator();
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);  // Golem's location
        Location there = map.locationOf(target);  // Target's location

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

    // Calculate the Manhattan distance between two locations
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

    @Override
    public String menuDescription(Actor actor) {
        return "";
    }
}