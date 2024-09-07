package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.AttackAction;
import game.weapons.GreatKnife;
import game.weapons.ShortSword;
import game.weapons.WeaponItem;

import java.util.*;

/**
 * Class representing the Furnace Golem.
 * For now, it can only wander around the map.
 *
 * @author Adrian Kristanto
 * @modified by Ngu Trien Luan - Added special weapon actions based on the player's weapon capabilities.
 */
public class FurnaceGolem extends Actor {
    /**
     * A list of behaviours the Furnace Golem can exhibit.
     */
    public List<Behaviour> behaviours = new ArrayList<Behaviour>();

    /**
     * The weapon item the Furnace Golem can potentially wield.
     */
    private WeaponItem weaponItem;

    /**
     * A random number generator used for selecting random actions.
     */
    private final Random rand = new Random();

    /**
     * Constructor for the Furnace Golem. Initializes the Golem with high health and hostile behavior towards enemies.
     */
    public FurnaceGolem() {
        super("Furnace Golem", 'A', 1000);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
    }

    /**
     * Determines the action the Furnace Golem will take during its turn.
     * If any behavior provides an action, it will execute that. Otherwise, it picks a random action from the available list.
     *
     * @param actions the list of possible actions.
     * @param lastAction the action the Furnace Golem took last turn.
     * @param map the current game map.
     * @param display the display showing the game map.
     * @return the action the Furnace Golem will perform.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        return actions.get(rand.nextInt(actions.size()));
    }

    /**
     * Determines which actions are allowable when interacting with other actors, specifically based on their weaponry.
     *
     * @param otherActor the actor interacting with the Furnace Golem.
     * @param direction the direction from which the interaction occurs.
     * @param map the current game map.
     * @return a list of allowable actions the other actor can take.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        boolean hasGreatKnife = false;
        boolean hasShortSword = false;

        // Check the other actor's inventory for specific weapons
        for (Item item : otherActor.getItemInventory()) {
            if (item.hasCapability(Status.HAS_GREAT_KNIFE)) {
                hasGreatKnife = true;
            } else if (item.hasCapability(Status.HAS_SHORT_SWORD)) {  // Corrected to HAS_SHORT_SWORD capability check
                hasShortSword = true;
            }
        }

        // If the actor is hostile to enemies, they can attack the Furnace Golem
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }

        // Special weapon-based attack actions
        if (hasGreatKnife) {
            actions.add(new AttackAction(this, direction, new GreatKnife()));
        }
        if (hasShortSword) {
            actions.add(new AttackAction(this, direction, new ShortSword()));
        }

        return actions;
    }

}