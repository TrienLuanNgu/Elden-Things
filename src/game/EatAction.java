package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class EatAction extends Action {

    private Things things;

    public EatAction(Things things) {
        this.things = things;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        things.eat((Player) actor);

        return "Player now has " + ((Player) actor).getMana() + " mana and " + ((Player) actor).getHitPoints() + " hit points and " + ((Player) actor).getStrength() + " strength!";

    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " eats " + things;

    }
}
