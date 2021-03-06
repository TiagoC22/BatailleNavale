package com.mygdx.game;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

//Projet bataille navale - UPMC 2018 @Tiago @Gautier

public class MainMenu extends ScreenAdapter  {
    private int WORLD_WIDTH = Gdx.graphics.getWidth();
    private int WORLD_HEIGHT = Gdx.graphics.getHeight();
    private TheGame game;
    private Stage stage;


    private Texture background;
    private Image backgroundImage;

    private Button buttonPlay;
    private Button buttonExit;
    private Texture textureregles;
    private TextureRegion reglesregion;
    private TextureRegionDrawable reglesregiondrawable;
    private ImageButton buttonregles;

    public MainMenu(TheGame game) {
        this.game = game;
        create();
    }

    @Override
    public void show() {
        //Musique
        final Sound sound = Gdx.audio.newSound(Gdx.files.internal("sound/soundtrack.mp3"));
        final long soundtrack = sound.play(1.0f);

        background = new Texture(Gdx.files.internal("wallpaper.jpg"));
        backgroundImage = new Image(background);
        backgroundImage.setFillParent(true);
        stage.addActor(backgroundImage);
        stage.addActor(buttonPlay);
        stage.addActor(buttonExit);
        stage.addActor(buttonregles); //Add the button to the stage to perform rendering and take input.

        //Bouton qui envoie vers la phase "Gameplay"
        buttonPlay.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event,float x,float y) {
                game.setScreen(new BN(game));
                sound.stop(soundtrack);
            };
        });

        //Bouton qui quitte le jeu
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

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

    public void render(float delta) {
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
        buttonPlay.setY(WORLD_HEIGHT / 2-100);
        buttonPlay.setWidth(166);
        buttonPlay.setHeight(68);

        buttonExit = new ImageButton(buttonsStyle, "buttonE") ;
        buttonExit.setX(WORLD_WIDTH / 2+100);
        buttonExit.setY(WORLD_HEIGHT / 2-100);
        buttonExit.setWidth(166);
        buttonExit.setHeight(68);

        textureregles = new Texture(Gdx.files.internal("ui/regles.png"));
        reglesregion = new TextureRegion(textureregles);
        reglesregiondrawable = new TextureRegionDrawable(reglesregion);
        buttonregles = new ImageButton(reglesregiondrawable); //Set the button up

    }
}
