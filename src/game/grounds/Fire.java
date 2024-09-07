package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actors.Player;

/**
 * Represents a fire on the game map.
 * <p>
 * The fire causes damage to actors with the BURN status and eventually extinguishes itself after a certain period.
 * </p>
 *
 * <p>Created by:
 * @author Ngu Trien Luan
 * </p>
 */
public class Fire extends Ground {
    private int age = 0;

    /**
     * Constructor for the Fire class.
     * Initializes the fire with a display character and a name.
     */
    public Fire() {
        super('w', "Fire");
        addCapability(Status.BURN);
    }

    /**
     * Updates the state of the fire each game tick.
     * <p>
     * If an actor with the BURN status is present, it deducts hitpoints from the actor.
     * The fire extinguishes itself after 5 ticks.
     * </p>
     *
     * @param location the Location where the fire is situated
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        Location origin = location;
        age++;

        Actor actor = location.getActor();
        if (actor != null) {
            if (actor.hasCapability(Status.BURN)) {
                ((Player) actor).deductHitpoints(5);
            }
        }

        if (age == 5) {
            setDisplayChar(origin.getDisplayChar());
            removeCapability(Status.BURN);
        }
    }
}