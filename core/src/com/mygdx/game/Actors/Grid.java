package com.mygdx.game.Actors;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;

public class Grid extends Group{

    private float gridWidth = (float)(Gdx.graphics.getWidth() * 0.5);
    private float gridHeight = (float)(Gdx.graphics.getHeight() * 0.5);

    private int gridRow = 7;
    private int gridColumn = 7;

    public Grid(){

        for(int i=1; i<gridColumn; i++) {
			for(int j=1; j<gridRow; j++) {
				Cell cell = new Cell( ((int)gridWidth / gridColumn) * i, ((int)gridHeight / gridRow ) * j, i, j) ;
				addActor(cell);
			}
        }
        
    }

}