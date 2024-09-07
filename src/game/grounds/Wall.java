package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.Status;

/**
 * A class representing a wall that cannot be entered by any actor
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:  Ngu Trien Luan
 *
 */
public class Wall extends Ground {

    public Wall() {
        super('#', "Wall");
        this.addCapability(Status.BURNABLE);
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
