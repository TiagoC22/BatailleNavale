package com.mygdx.game;

import boats.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import java.awt.*;

//Projet bataille navale - UPMC 2018 @Tiago @Gautier

public class Grid {
    public static int gridSize = 10;
    public static int cellSize = 64;

    private Texture gridbackground;
    private Texture missSprite;
    protected Array<Boat> shipArray;
    private Array<Point> tryFindPos;


    public Grid(Texture texturebackground, Texture texturemiss, Texture texturecenter, Texture textureSE) {
        gridbackground = texturebackground;
        missSprite = texturemiss;
        shipArray = new Array<Boat>();
        tryFindPos = new Array<Point>();
        Sprite spriteCent = new Sprite(texturecenter);
        Sprite spriteSE = new Sprite(textureSE);
        shipArray.add(new porte_avion(spriteCent, spriteSE));
        shipArray.add(new cuirasser(spriteCent, spriteSE));
        shipArray.add(new croiseur(spriteCent, spriteSE));
        shipArray.add(new sous_marin(spriteCent, spriteSE));
        shipArray.add(new destructeur(spriteCent, spriteSE));
    }


    public void reset() {
        for(Boat s : shipArray)
            s.reset();
        tryFindPos.clear();
    }


    public void draw(boolean hide, Batch batch) {
        batch.draw(gridbackground, 0, 0);
        for(Point p : tryFindPos)
            batch.draw(missSprite, p.x * cellSize, p.y * cellSize);
        for(Boat s : shipArray)
            s.draw(hide, batch);
    }


    public boolean boardClean() {
        for(Boat s : shipArray) {
            if(!s.degat())
                return false;
        } return true;
    }


    public int boatLT() {
        int numLeft = 0;
        for(Boat s : shipArray) {
            if(!s.degat()) numLeft++;
        }
        return numLeft;
    }


    public void boatShuffle() {
        for(Boat s : shipArray)
            s.setPosition(-1, -1);
        for(int i = 0; i < shipArray.size; i++) {
            int xPos = -1, yPos = -1;
            while(xPos < 0 || yPos < 0) {
                shipArray.get(i).setHorizontal(MathUtils.randomBoolean());
                if(shipArray.get(i).horizontal()) {
                    xPos = MathUtils.random(0, gridSize - shipArray.get(i).getSize());
                    yPos = MathUtils.random(0, gridSize - 1);
                } else {
                    xPos = MathUtils.random(0, gridSize - 1);
                    yPos = MathUtils.random(0, gridSize - shipArray.get(i).getSize());
                } shipArray.get(i).setPosition(xPos, yPos);
                for(int j = 0; j < i; j++) {
                    if(shipArray.get(i).boatPositionError(shipArray.get(j))) {
                        xPos = yPos = -1;
                        break;
                    }
                }
            }
        }
    }

    public boolean fireInBoatNow(int xPos, int yPos) {
        for(Point p : tryFindPos) {
            if (p.x == xPos && p.y == yPos)
                return true;
        } for(Boat s : shipArray) {
            if(s.boatHitNow(xPos, yPos))
                return true;
        } return false;
    }


    public Boat firePos(int xPos, int yPos) {
        for(Boat s : shipArray) {
            if(s.boatFire(xPos, yPos))
                return s;
        } tryFindPos.add(new Point(xPos, yPos));
          return null;
    }
}













