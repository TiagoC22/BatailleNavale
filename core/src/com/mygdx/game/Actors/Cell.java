package com.mygdx.game.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Cell extends Actor {

    private final Texture cellBG = new Texture(Gdx.files.internal("cell.jpg"));

    private int posX;
    private int posY;

    private int gridX;
    private int gridY;

    public Cell(int x, int y, int gridX, int gridY){
        this.posX = x;
        this.posY = y;
        this.gridX = gridX;
        this.gridY = gridY;
        setBounds(x, y, cellBG.getWidth(), cellBG.getHeight());
    }

    public void act(float delta){

    }

    public void draw(Batch batch, float a){
        batch.draw(cellBG, posX, posY, posX / gridX, posY / gridY);
    }
}