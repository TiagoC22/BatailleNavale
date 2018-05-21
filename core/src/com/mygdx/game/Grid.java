
package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.Cell;

public class Grid extends Group{

    private float gridWidth = (float)(Gdx.graphics.getWidth() * 0.6);
    private float gridHeight = (float)(Gdx.graphics.getHeight() * 0.7);

    private int gridRow = 11;
    private int gridColumn = 11;

    private final Texture cellBG = new Texture(Gdx.files.internal("cell.jpg"));
    Skin Skin = new Skin(Gdx.files.internal("uiskin.json"));
    private final Texture headerBG = new Texture(Gdx.files.internal("headercell.png"));




    public Grid(){
        for(int i=1; i<gridColumn; i++) {
            for(int j=1; j<gridRow; j++) {
                if(i == 1){
                    Table table = new Table();
                    Label labeltest = new Label("A1", Skin);
                    Cell headerRow = new Cell (((int)gridWidth / gridColumn) * i, ((int)gridHeight / gridRow ) * j, i, j, headerBG) ;
                    table.add(headerRow);
                    table.add(labeltest);
                    headerRow.setUserObject(labeltest);
                    labeltest.setZIndex(1);
                    table.getCell(headerRow).size(0, 0);
                    addActor(table);


                }
                if(j == 10){
                    Cell headerColumn = new Cell(((int)gridWidth / gridColumn) * i, ((int)gridHeight / gridRow ) * j, i, j, headerBG) ;
                    addActor(headerColumn);
                }
                if(i != 1 && j != 10){
                    Cell cell = new Cell( ((int)gridWidth / gridColumn) * i, ((int)gridHeight / gridRow ) * j, i, j, cellBG) ;
                    addActor(cell);
                }
            }
        }
    }

}