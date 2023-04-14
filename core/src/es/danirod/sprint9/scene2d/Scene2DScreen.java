package es.danirod.sprint9.scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import es.danirod.sprint9.game.BaseScreen;
import es.danirod.sprint9.game.GameOverScreen;
import es.danirod.sprint9.game.MainGame;

/**
 * This screen uses Scene2D to show how it works.
 */
public class Scene2DScreen extends BaseScreen {

    /** The Scene2D stage where all the actors are added. */
    private Stage stage;

    /** The actor that represents the player. */
    private PlayerActor player;

    /** The actor that represent spikes. */
    private es.danirod.sprint9.scene2d.SpikeActor spikes;

    /** Textures used in this screen. */
    private Texture playerTexture, spikeTexture;

    /** Regions used in this screen. */
    private TextureRegion spikeRegion;
    public int num_muertes = 0;

    public Scene2DScreen(MainGame game) {
        super(game);

        // Load assets using new Texture instead of asset manager.
        playerTexture = new Texture("player.png");
        spikeTexture = new Texture("spike.png");
        spikeRegion = new TextureRegion(spikeTexture, 0, 64, 128, 64);
    }

    @Override
    public void show() {
        // Create a new stage.
        stage = new Stage(new FitViewport(640, 400));

        // Load the actors.
        player = new PlayerActor(playerTexture);
        spikes = new es.danirod.sprint9.scene2d.SpikeActor(spikeRegion, 2100, 100, 500);
        player.setPosition(20, 100);

        // Add the actors to the screen. They won't be visible if you don't add them.
        stage.addActor(player);
        stage.addActor(spikes);
    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        checkCollisions();
        stage.draw();
    }

    /**
     * This method checks collisions between the player and the spikes using the bounding box
     * method. There is a collision if the bounding boxes that represent both actors overlap.
     */
    private void checkCollisions() {
        if (player.isAlive() &&  (player.getX() + player.getWidth()) > spikes.getX()) {
            System.out.println("A collision has happened.");

            num_muertes++;
            // show the number of deaths in the screen
            game.setScreen(new GameOverScreen(game));
            player.setAlive(false);
        }
    }

    @Override
    public void dispose() {
        playerTexture.dispose();
        spikeTexture.dispose();
        stage.dispose();
    }
}
