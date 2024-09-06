package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle());

        List<String> map = Arrays.asList(
                "..........~~~~~~~...~~~~~~~......~...........",
                "~..........~~~~~....~~~~~~...................",
                "~~.........~~~~.....~~~~~~...................",
                "~~~..#####..~~.....~~~~~~~...................",
                "~~~..#___#........~~~~~~~~~..................",
                "~~~..#___#.......~~~~~~.~~~..................",
                "~~~..##_##......~~~~~~.......................",
                "~~~~...........~~~~~~~...........~~..........",
                "~~~~~.........~~~~~~~~.......~~~~~~~.........",
                "~~~~~~.......~~~~~~~~~~.....~~~~~~~~.........");

        GameMap gameMap = new GameMap("Gravesite Plain", groundFactory, map);
        world.addGameMap(gameMap);

        // BEHOLD, ELDEN THING!
        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }



        Player player = new Player("Tarnished", '@', 150, 100, 5);

//        new Display().println("Health: " + player.getHitPoints() + "/" + 150);
//        new Display().println("Mana: " + player.getMana() + "/" + 100);
//        new Display().println("Strength: " + player.getStrength() + "/" + 5);
        ShortSword shortSword = new ShortSword();
        gameMap.at(8, 4).addItem(shortSword);
        GreatKnife greatKnife = new GreatKnife();
        gameMap.at(6, 4).addItem(greatKnife);
        FlaskOfHealing flaskOfHealing = new FlaskOfHealing();
        gameMap.at(6, 5).addItem(flaskOfHealing);
        FlaskOfRejuvenation flaskOfRejuvenation = new FlaskOfRejuvenation();
        gameMap.at(8, 5).addItem(flaskOfRejuvenation);

        ShadowtreeFragment shadowtreeFragment = new ShadowtreeFragment();
        gameMap.at(8, 8).addItem(shadowtreeFragment);

        world.addPlayer(player, gameMap.at(7, 4));


        FurnaceGolem furnaceGolem = new FurnaceGolem();
        furnaceGolem.behaviours.add(new StompBehaviour(player, 2));
        furnaceGolem.behaviours.add(new FollowBehaviour(player, 2));
        furnaceGolem.behaviours.add(new WanderBehaviour());

        gameMap.at(42, 4).addActor(furnaceGolem);



        world.run();

    }
}
