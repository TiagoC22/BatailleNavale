package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
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
    public static final float boatCoul = 0.65f;

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


    public void setHorizontal(boolean i) { horizontal = i; }
    public boolean horizontal() { return horizontal; }
    public boolean vertical() { return !horizontal; }
    public void setPosition(int x, int y) { posX = x; posY = y; }
    public boolean degat() { return degatPos.size == getSize(); }

    public Boat(Sprite shipDegat, Sprite shipSP) {
        boatDegaSP = shipDegat;
        boatSP = shipSP;
        reset();
    }

    public void reset() {
        posX = posY = -1;
        horizontal = true;
        degatPos = new Array<Integer>();
    }


    public boolean boatFire(int iXpos, int iYpos) {
        if(boatHit(iXpos, iYpos)) {
            if(horizontal())
                degatPos.add(iXpos - posX);
            else
                degatPos.add(iYpos - posY);
            return true;
        } return false;
    }

    public boolean boatHitNow(int iXpos, int iYpos) {
        if(boatHit(iXpos, iYpos)) {
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

    public boolean boatHit(int iXpos, int iYpos) {
        if(horizontal()) { if(iYpos == posY &&
                    iXpos >= posX &&
                    iXpos < posX + getSize())
                return true;
        } else { if(iXpos == posX &&
                    iYpos >= posY &&
                    iYpos < posY + getSize())
                return true;
        } return false;
    }


    public void draw(boolean bolB, Batch batch) {
        if(boatDegaSP == null || boatSP == null) return;
            if(posX < 0 || posY < 0) return;
        if(degat()) {
            boatDegaSP.setColor(1, 1, 1, boatCoul);
            boatSP.setColor(1, 1, 1, boatCoul);
        } if(bolB) {
            for(int i : degatPos) {
                float x = (posX + ((horizontal())?(i):(0))) * boatDegaSP.getWidth();
                float y = (posY + ((horizontal())?(0):(i))) * boatDegaSP.getHeight();
                boatSP.setPosition(x, y);
                boatSP.draw(batch);
                boatDegaSP.setPosition(x, y);
                boatDegaSP.draw(batch);
            }
        } else {
            for (int i = 0; i < getSize(); i++) {
                boatSP.setPosition((posX + ((horizontal())?(i):(0))) * boatSP.getWidth(), (posY + ((horizontal())?(0):(i))) * boatSP.getHeight());
                boatSP.draw(batch);
            } for(int i : degatPos) {
                boatDegaSP.setPosition((posX + ((horizontal())?(i):(0))) * boatDegaSP.getWidth(), (posY + ((horizontal())?(0):(i))) * boatDegaSP.getHeight());
                boatDegaSP.draw(batch);
            }
        } if(degat()) {
            boatSP.setColor(Color.WHITE);
            boatDegaSP.setColor(Color.WHITE);
        }
    }

    //Vérifie que les bateaux ne soient pas superposées

    public boolean boatPositionError(Boat otherboat) {
        boolean boleanBoatPos = false;
        if(horizontal() && otherboat.horizontal()) {
            if(posY == otherboat.posY) {
                if((posX <= otherboat.posX && posX + getSize() > otherboat.posX) ||
                        (otherboat.posX + otherboat.getSize() > posX && otherboat.posX <= posX))
                    boleanBoatPos = true;
            }
        } else if(!horizontal() && !otherboat.horizontal()) {
            if(posX == otherboat.posX) {
                if((posY <= otherboat.posY && posY + getSize() > otherboat.posY) ||
                        (otherboat.posY + otherboat.getSize() > posY && otherboat.posY <= posY))
                    boleanBoatPos = true;
            }
        } else if(horizontal()) {
            if(posX <= otherboat.posX &&
                    posX + getSize() > otherboat.posX &&
                    otherboat.posY <= posY &&
                    otherboat.posY + otherboat.getSize() > posY)
                boleanBoatPos = true;
        } else {
            if(otherboat.posX <= posX &&
                    otherboat.posX + otherboat.getSize() > posX &&
                    posY <= otherboat.posY &&
                    posY + getSize() > otherboat.posY)
                boleanBoatPos = true;
        }
        return boleanBoatPos;
    }
}

