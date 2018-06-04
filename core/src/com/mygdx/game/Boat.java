package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;


public abstract class Boat {
    private Sprite boatDegaSP;
    private Sprite boatSP;
    private boolean horizontal;
    private int posX, posY;
    private Array<Integer> degatPos;
    abstract public String getName();
    abstract public int getSize();
    abstract public int getType();

    //Porte avion
    public static final int porteavionType = 6;
    public static final int porteavionSize = 5;
    public static final String porteavionName = "Porte-avion";

    //Cuirasser
    public static final int cuirasserType = 5;
    public static final int cuirasserSize = 4;
    public static final String cuirasserName = "Cuirasser";

    //Croiseur
    public static final int croiseurType = 4;
    public static final int croiseurSize = 3;
    public static final String croiseurName = "Croiseur";

    //Sous marin
    public static final int sousmarinType = 3;
    public static final int sousmarinSize = 3;
    public static final String sousmarinName = "Sous-marin";

    //Destructeur
    public static final int destructeurType = 2;
    public static final int destructeurSize = 2;
    public static final String destructeurName = "Destructeur";

    public static final float boatCoul = 0.65f;


    public void setHorizontal(boolean i) { horizontal = i; }
    public boolean horizontal() { return horizontal; }
    public boolean vertical() { return !horizontal; }
    public void setPosition(int x, int y) { posX = x; posY = y; }
    public boolean degat() { return degatPos.size == getSize(); }

    public Boat(Sprite shipDegat, Sprite shipSP) {
        boatDegaSP = shipDegat;
        boatSP = shipSP;
    }


    public boolean boatDegatPoint(int iXpos, int iYpos) {
        if(boatDegatExist(iXpos, iYpos)) {
            if(horizontal())
                degatPos.add(iXpos - posX);
            else
                degatPos.add(iYpos - posY);
            return true;
        }
        return false;
    }

    public boolean alreadyHit(int iXpos, int iYpos) {
        if(boatDegatExist(iXpos, iYpos)) {
            for(int i : degatPos) {
                if(horizontal()) {
                    if(i == iXpos - posX)
                        return true;
                } else {
                    if(i == iYpos - posY)
                        return true;
                }
            }
        }
        return false;
    }

    public boolean boatDegatExist(int posX, int posY) {
        if(horizontal()) { if(posY == posY &&
                posX >= posX &&
                posX < posX + getSize())
                return true;
        } else { if(posX == posX &&
                posY >= posY &&
                posY < posY + getSize())
                return true;
        } return false;
    }
}

