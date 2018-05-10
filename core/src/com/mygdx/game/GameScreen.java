package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Actors.Grid;

public class GameScreen extends ScreenAdapter {

    private int WORLD_WIDTH = Gdx.graphics.getWidth();
    private int WORLD_HEIGHT = Gdx.graphics.getHeight();
    private Stage stage;


    private Texture background;
    private Image backgroundImage2;
    private final MyGdxGame game;

    public GameScreen(MyGdxGame game)
    {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(WORLD_WIDTH,WORLD_HEIGHT),this.game.getBatch());

        background = new Texture(Gdx.files.internal("wallpaper2.jpg"));
        backgroundImage2 = new Image(background);
        backgroundImage2.setFillParent(true);
        stage.addActor(backgroundImage2);

        Grid grid = new Grid();
        grid.setX(WORLD_WIDTH / 2-400);
        grid.setY(WORLD_HEIGHT / 2-200);
        stage.addActor(grid);


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