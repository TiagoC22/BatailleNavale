package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;

    //Récuperation largeur/hauteur écran
    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    int width = gd.getDisplayMode().getWidth();
    int height = gd.getDisplayMode().getHeight();

    @Override
    public void create () {
        batch = new SpriteBatch();
        img = new Texture("wallpaper.jpg");
    }

    @Override
    public void render () {

        batch.begin();
        batch.draw(img, 0, 0, width, height);
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
        img.dispose();
    }
}