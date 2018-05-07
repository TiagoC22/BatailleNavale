package com.mygdx.game.Actors;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;

public class Grid extends Group{

    private float gridWidth = (float)(Gdx.graphics.getWidth() * 0.5);
    private float gridHeight = (float)(Gdx.graphics.getHeight() * 0.5);

    private int gridRow = 8;
    private int gridColumn = 8;

    private final Texture cellBG = new Texture(Gdx.files.internal("cell.jpg"));

    private final Texture headerBG = new Texture(Gdx.files.internal("headercell.png"));
    

    public Grid(){
        for(int i=1; i<gridColumn; i++) {
			for(int j=1; j<gridRow; j++) {
                if(i == 1){
                    Cell headerRow = new Cell (((int)gridWidth / gridColumn) * i, ((int)gridHeight / gridRow ) * j, i, j, headerBG) ;
                    addActor(headerRow);
                }
                if(j == 7){
                    Cell headerColumn = new Cell(((int)gridWidth / gridColumn) * i, ((int)gridHeight / gridRow ) * j, i, j, headerBG) ;
                    addActor(headerColumn);
                }
                if(i != 1 && j != 7){
                    Cell cell = new Cell( ((int)gridWidth / gridColumn) * i, ((int)gridHeight / gridRow ) * j, i, j, cellBG) ;
				    addActor(cell);
                }
			}
        }
    }

}