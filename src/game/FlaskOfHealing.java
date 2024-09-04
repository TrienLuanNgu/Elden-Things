package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;


public class FlaskOfHealing extends Flask {
    public static int count = 0;

    public FlaskOfHealing() {
        super("Flask of Healing", 'u', true, 5);
    }


//    public String toString() {
//        int energy = 150;
//        return energy + " hit point";
//    }

    @Override
    public void flasks(Player actor){
        if (CHARGES != count) {
            actor.addHitpoints(150);
            count++;
        }
        else{
            actor.addHitpoints(0);
            System.out.println("Flask Of Healing is out of charges");
        }
    }

    public ActionList allowableActions(Actor owner) {

        ActionList actions = new ActionList();
        actions.add(new FlaskAction(this));
        return actions;
    }

}
