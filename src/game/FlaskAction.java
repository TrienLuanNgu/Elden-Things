package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

public class FlaskAction extends Action {

    private Flask flask;


    public FlaskAction(Flask flask) {
        this.flask = flask;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        flask.flasks((Player) actor);
        return "Player now has " + ((Player) actor).getMana() + " mana and " + ((Player) actor).getHitPoints() + " hit points!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " uses " + flask;
    }
}
