package test;

import static org.junit.Assert.*;
import org.junit.Test;

import entities.Player;
import main.Game;

import static utils.Constants.PlayerConstants.INITPLAYERX;

/**
 * This is going to act as a test for {@link main.Game}
 *
 */


public class GameTest {
    @Test
    public void testGameInitialization() {
        Game game = new Game();
        game.getAudioPlayer().stopSoundTrack();

        assertNotNull("The player is not initialized", game.getPlaying().getPlayer()); // Verify that the player is not null

        assertNotNull("The levels are not initialized", game.getPlaying().getLevelManager().getLevels()); // Verify that the levels have been loaded correctly
        assertTrue("The levels are not loaded correctly", game.getPlaying().getLevelManager().getLevels().size() > 0); // Verify that at least one level has been loaded
    }

    @Test
    public void testPlayerMovement() {
        Game game = new Game();
        game.getAudioPlayer().stopSoundTrack();
        Player player = game.getPlaying().getPlayer();

        player.setRight(true);
        player.update(0);
        player.setRight(false);

        assertEquals("Player movement is not correct", (INITPLAYERX + player.getPlayerSpeed()), player.getPlayerX(), 0); // Checks whether the player's x coordinate has changed as expected

        player.setLeft(true);
        player.update(0);
        player.setLeft(false);

        assertEquals("Player movement is not correct", (INITPLAYERX), player.getPlayerX(), 0); // Checks whether the player's x coordinate has changed as expected
    }

    @Test
    public void testPlayerCollision() {
        Game game = new Game();
        game.getAudioPlayer().stopSoundTrack();

        game.getPlaying().getPlayer().setPosition(100, 100);
        game.getPlaying().getEntityManager().currentEntities.get(1).setPosition(100, 100);
        game.getPlaying().update();

        assertTrue("Collision detection is not working", game.getPlaying().isDead); // Checks if the collision check system is working
    }
}
