package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class that represents the floor inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class Floor extends Ground{
    public Floor() {
        super('_', "Floor");
        this.addCapability(Status.BURNABLE);
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        if (!(actor.hasCapability(Status.BURN))) {
            return false;
        }
        return super.canActorEnter(actor);
    }


}






