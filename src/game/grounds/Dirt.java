package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import game.Status;

/**
 * A class that represents bare dirt.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Ngu Trien Luan
 *
 */
public class Dirt extends Ground {

    public Dirt() {
        super('.', "Dirt");
        this.addCapability(Status.BURNABLE);
    }

}