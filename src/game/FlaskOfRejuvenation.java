package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

public class FlaskOfRejuvenation extends Flask {

    public static int count = 0;

    public FlaskOfRejuvenation() {
        super("Flask of Rejuvenation", 'o', true,3);
    }

//    public String toString() {
//        return 100 + " mana";
//    }

    @Override
    public void flasks(Player actor){
        if(CHARGES != count) {
            actor.addMana(100);
            count++;
        }
        else {
            actor.addMana(0);
            System.out.println("Flask Of Rejuvenation is out of charges");
        }

    }


    public ActionList allowableActions(Actor owner) {

        ActionList actions = new ActionList();
        actions.add(new FlaskAction(this));
        return actions;
    }
}
