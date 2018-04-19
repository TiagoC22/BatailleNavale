package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;


import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class MainMenu extends ScreenAdapter {

    private int WORLD_WIDTH = Gdx.graphics.getWidth();
    private int WORLD_HEIGHT = Gdx.graphics.getHeight();
    private Game game;
    private Stage stage;


    private Texture background;
    private Image backgroundImage;

    private Texture buttonTexturePlay;
    private Texture buttonTexturePlayDown;
    private Texture buttonTextureExit;
    private Image buttonPlay;
    private Image buttonPlayDown;
    private Image buttonExit;
    


    public MainMenu(final Game game)
    {
        this.game = game;
    }

    @Override
    public void show()
    {
    	
    
        
        stage = new Stage(new FitViewport(WORLD_WIDTH,WORLD_HEIGHT));
    // Add a background
        background = new Texture(Gdx.files.external("wallpaper.jpg"));
        backgroundImage = new Image(background);
        backgroundImage.setFillParent(true);
        stage.addActor(backgroundImage);
    //Create ButtonPlayTexture
        buttonTexturePlay = new Texture(Gdx.files.external("button_play.up.png"));
        buttonTexturePlayDown = new Texture(Gdx.files.external("button_play.down.png"));
    //Create ButtonPlayTexture
        buttonTextureExit = new Texture(Gdx.files.external("button_exit.up.png"));
        
    //Create ExitButton
        buttonExit = new Image(buttonTextureExit);
        buttonExit.setPosition(WORLD_WIDTH / 2+100 ,WORLD_HEIGHT / 2-200);
        buttonExit.setWidth(166);
        buttonExit.setHeight(68);
        stage.addActor(buttonExit);

    //Create PlayButton
        buttonPlay = new Image(buttonTexturePlay);
        buttonPlay.setPosition(WORLD_WIDTH / 2-250 ,WORLD_HEIGHT / 2-200);
        buttonPlay.setWidth(166);
        buttonPlay.setHeight(68);
        stage.addActor(buttonPlay);

        Gdx.input.setInputProcessor(stage);

        backgroundImage.addAction(sequence(Actions.alpha(0),Actions.fadeIn(3,Interpolation.pow2In)));
        buttonPlay.addAction(sequence(Actions.alpha(0),Actions.delay(1),Actions.fadeIn(1,Interpolation.pow2In)));
        buttonExit.addAction(sequence(Actions.alpha(0),Actions.delay(1),Actions.fadeIn(1,Interpolation.pow2In)));
        
    //ActionListener PlayButton
        buttonPlay.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event,float x,float y) {
                game.setScreen( new GameScreen(game) );
            };
        });
        
        buttonPlay.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	buttonPlay = new Image(buttonTexturePlayDown);
            	buttonPlay.setPosition(WORLD_WIDTH / 2-250 ,WORLD_HEIGHT / 2-200);
            	stage.addActor(buttonPlay);
				return false;
            };
        });
        
        buttonPlay.addListener(new InputListener() {
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	buttonPlay = new Image(buttonTexturePlay);
            	buttonPlay.setPosition(WORLD_WIDTH / 2-250 ,WORLD_HEIGHT / 2-200);
            	stage.addActor(buttonPlay);
            };
        });
            

    //ActionListener ExitButton
        buttonExit.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event,float x,float y) {
                Gdx.app.exit();                
            };
        });


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
}
