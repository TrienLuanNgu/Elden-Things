package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.*;

/**
 * Class representing the Furnace Golem
 * For now, it can only wander around the map.
 * @author Adrian Kristanto
 */
public class FurnaceGolem extends Actor{
    public List<Behaviour> behaviours = new ArrayList<Behaviour>();
    //Map<Integer, Behaviour> behaviours = new HashMap<>();
    private WeaponItem weaponItem;
    private final Random rand = new Random();




    public FurnaceGolem() {
        super("Furnace Golem", 'A', 1000);
        //this.behaviours.add(new WanderBehaviour());
        this.addCapability(Status.HOSTILE_TO_ENEMY);



    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }

        //return new DoNothingAction();
        return actions.get(rand.nextInt(actions.size()));
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
//            actions.add(new AttackAction(this, direction, weaponItem));
        }
        if (otherActor.getItemInventory().contains(new GreatKnife())) {
            actions.add(new AttackAction(this, direction, new GreatKnife()));
        }
        if (otherActor.getItemInventory().contains(new ShortSword())) {
            actions.add(new AttackAction(this, direction, new ShortSword()));
        }
        return actions;
    }

}
