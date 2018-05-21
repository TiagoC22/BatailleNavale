package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Cell extends Actor {

    private int posX;
    private int posY;

    private int gridX;
    private int gridY;

    
    private Texture bgIcon;
    private Texture currentIcon;

    public Cell(int x, int y, int gridX, int gridY, Texture bgIcon){
        this.posX = x;
        this.posY = y;
        this.gridX = gridX;
        this.gridY = gridY;
        this.bgIcon = bgIcon;
        setBounds(x, y, this.bgIcon.getWidth(), this.bgIcon.getHeight());
    }

    public void setCurrentIcon(Texture newIcon){
        this.currentIcon = newIcon;
    }

    public void setBGIcon(Texture newBGIcon){
        this.bgIcon = newBGIcon;
    }

    public void act(float delta){

    }

    public void draw(Batch batch, float a){
        batch.draw(this.bgIcon, posX, posY, posX / gridX, posY / gridY);

        if(this.currentIcon != null){
            batch.draw(this.currentIcon, posX, posY, posX / gridX, posY / gridY);
        }
    }
}