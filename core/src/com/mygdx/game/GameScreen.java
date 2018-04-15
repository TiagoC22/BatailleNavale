package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen extends ScreenAdapter {

    private int WORLD_WIDTH = Gdx.graphics.getWidth();
    private int WORLD_HEIGHT = Gdx.graphics.getHeight();
    private Stage stage;


    private Texture background;
    private Image backgroundImage;
    private final Game game;

    public GameScreen(Game game)
    {
        this.game = game;
    }

    @Override
    public void show() {

        stage = new Stage(new FitViewport(WORLD_WIDTH,WORLD_HEIGHT));
        background = new Texture(Gdx.files.internal("wallpaper_2.jpg"));
        backgroundImage = new Image(background);
        stage.addActor(backgroundImage);

    }

}