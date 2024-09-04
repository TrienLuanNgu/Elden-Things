package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class GreatKnife extends WeaponItem{

    public GreatKnife() {
        super("GreatKnife", '!', 75, "stab", 60);
    }

    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        return super.attack(attacker, target, map);
    }

}
