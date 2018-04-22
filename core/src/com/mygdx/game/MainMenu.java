package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class MainMenu extends ScreenAdapter {
    private int WORLD_WIDTH = Gdx.graphics.getWidth();
    private int WORLD_HEIGHT = Gdx.graphics.getHeight();
    private Game game;
    private Stage stage;


    private Texture background;
    private Image backgroundImage;

    private Button buttonPlay;
    private Button buttonExit;

    public MainMenu(final Game game)
    {
        this.game = game;
        create();
    }

    @Override
    public void show()
    {
        //Musique
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("sound/soundtrack.mp3"));
        long soundtrack = sound.play(1.0f);

        background = new Texture(Gdx.files.internal("wallpaper.jpg"));
        backgroundImage = new Image(background);
        backgroundImage.setFillParent(true);
        stage.addActor(backgroundImage);
        stage.addActor(buttonPlay);
        stage.addActor(buttonExit);

        //Bouton qui envoie le screen bleu de la bataille navale
        buttonPlay.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event,float x,float y) {
                game.setScreen( new GameScreen(game) );
                sound.stop(soundtrack);
            };
        });

        buttonExit.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event,float x,float y) {
                Gdx.app.exit();
            };
        });

        //Effet d'apparition background + boutons
        backgroundImage.addAction(sequence(Actions.alpha(0),Actions.fadeIn(3,Interpolation.pow2In)));
        buttonPlay.addAction(sequence(Actions.alpha(0),Actions.delay(1),Actions.fadeIn(1,Interpolation.pow2In)));
        buttonExit.addAction(sequence(Actions.alpha(0),Actions.delay(1),Actions.fadeIn(1,Interpolation.pow2In)));
    }


    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void render(float delta)
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0,0);
        stage.act();
        stage.draw();
    }

    public void create () {
        stage = new Stage(new FitViewport(WORLD_WIDTH,WORLD_HEIGHT));
        Gdx.input.setInputProcessor (stage);
        Skin buttonsStyle = new Skin(Gdx.files.internal("skins/button.json"),new TextureAtlas("skins/button.pack.atlas"));
        buttonPlay = new ImageButton(buttonsStyle, "buttonP") ;
        buttonPlay.setX(WORLD_WIDTH / 2-250);
        buttonPlay.setY(WORLD_HEIGHT / 2-200);
        buttonPlay.setWidth(166);
        buttonPlay.setHeight(68);

        buttonExit = new ImageButton(buttonsStyle, "buttonE") ;
        buttonExit.setX(WORLD_WIDTH / 2+100);
        buttonExit.setY(WORLD_HEIGHT / 2-200);
        buttonExit.setWidth(166);
        buttonExit.setHeight(68);

    }

}
