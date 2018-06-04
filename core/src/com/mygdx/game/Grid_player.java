package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

import java.awt.*;


public class Grid_player extends Grid {
    private Point pointeurMousePos;
    private int place;


    public Grid_player(Texture texturebackground, Texture texturemiss, Texture texturecenter, Texture textureSE) {
        super(texturebackground, texturemiss, texturecenter, textureSE); //Constructeur
        pointeurMousePos = new Point(-1,-1);  //Réinitialisation de la position
        place = -1;
    }

    public void startPlacingShips()
    {
        place = 0;
    }


    public boolean placeShip(int xPos, int yPos) {
        if(place >= 0 && place < shipArray.size) {
            moveShip(xPos, yPos);
            boolean boatPlacerSucess = true;
            for(int i = 0; i < shipArray.size; i++) {
                if(i == place)
                    continue;
                if(shipArray.get(i).boatPositionError(shipArray.get(place))) {
                    boatPlacerSucess = false;
                    break; }
            } if(boatPlacerSucess)
                place++;
        } if(place >= shipArray.size)
            return true;
        return false;
    }


    public void moveShip(int xPos, int yPos) {
        pointeurMousePos.x = xPos;
        pointeurMousePos.y = yPos;
        if(place >= 0 && place < shipArray.size) {
            Boat sPlace = shipArray.get(place);
            if(sPlace.horizontal()) {
                if(sPlace.getSize() + xPos > gridSize)
                    xPos = gridSize - sPlace.getSize();
            } else {
                if(sPlace.getSize() + yPos > gridSize)
                    yPos = gridSize - sPlace.getSize();
            } shipArray.get(place).setPosition(xPos, yPos);
        }
    }


    public void rotateShip() {
        if(place >= 0 && place < shipArray.size) {
            Boat sPlace = shipArray.get(place);
            sPlace.setHorizontal(sPlace.vertical());
            moveShip(pointeurMousePos.x, pointeurMousePos.y);
        }
    }
}
