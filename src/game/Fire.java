package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

public class Fire extends Ground{
    private int age = 0;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     * @param name
     */
    public Fire() {
        super('w', "Fire");
        addCapability(Status.BURN);
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        Location origin = location;
        age++;

        Actor actor = location.getActor();
        if (actor != null) {
            // If the actor is a player and the fire is burning, apply burn damage
            if (actor.hasCapability(Status.BURN)) {
                ((Player) actor).deductHitpoints(5);  // Deduct 5 hit points as burn damage
                //System.out.println(actor + " is burned and loses 5 hit points!");
            }
        }

        if (age == 5) {
            setDisplayChar(origin.getDisplayChar());
            removeCapability(Status.BURN);
        }
    }

//    @Override
//    public boolean canActorEnter(Actor actor) {
//        if (actor.hasCapability(Status.BURN)) {
//            ((Player) actor).deductHitpoints(5);
//            return true;
//        }
//        else return actor instanceof FurnaceGolem;
//    }
}
