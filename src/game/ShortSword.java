package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class ShortSword extends WeaponItem{

    public ShortSword() {
        super("Short Sword", '+', 100, "swing", 75);
    }

    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        return super.attack(attacker, target, map);
    }



}
