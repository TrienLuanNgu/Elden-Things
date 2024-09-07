package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;

public class ShortSword extends WeaponItem{

    public ShortSword() {
        super("Short Sword", '+', 100, "swing", 75);
        addCapability(Status.HAS_SHORT_SWORD);

    }

    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        return super.attack(attacker, target, map);
    }
//    @Override
//    public ActionList allowableActions(Actor owner){
//        Player player = (Player) owner;
//        ActionList actions = new ActionList();
//        if (player.getStrength() < 10){
//            System.out.println("You're not strong enough");
//        }
//        return actions;
//
//    }
    public PickUpAction getPickUpAction(Actor actor) {
        Player player = (Player) actor;
        if ((player).getStrength() >= 10){
            return new PickUpAction(this);
        }
        return null;
    }





}
