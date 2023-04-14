package es.danirod.sprint9.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class LevelScreen extends BaseScreen {

    private Stage stage;
    private Skin skin;
    private TextButton easyButton, mediumButton, hardButton;

    public LevelScreen(final MainGame game) {
        super(game);

        stage = new Stage(new FitViewport(640, 360));
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        easyButton = new TextButton("Easy", skin);
        mediumButton = new TextButton("Medium", skin);
        hardButton = new TextButton("Hard", skin);

        easyButton.setSize(200, 80);
        mediumButton.setSize(200, 80);
        hardButton.setSize(200, 80);

        easyButton.setPosition(40, 260);
        mediumButton.setPosition(40, 140);
        hardButton.setPosition(40, 40);

        easyButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
// Take me to the game screen!
                game.setScreen(new GameScreen(game, "easy"));
                Constants.GAME_MODE = 0;
            }
        });

        mediumButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game, "medium"));
                Constants.GAME_MODE = 1;
            }
        });

        hardButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game, "hard"));
                Constants.GAME_MODE = 2;

            }
        });

        stage.addActor(easyButton);
        stage.addActor(mediumButton);
        stage.addActor(hardButton);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.3f, 0.5f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
}