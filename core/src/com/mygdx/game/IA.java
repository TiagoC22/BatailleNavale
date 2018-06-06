package com.mygdx.game;

import java.awt.*;
import java.util.LinkedList;


public class IA
{

    public enum tryFindBoat {
        rater, toucher, couler
    }


    private enum position {
        up, down, left, right, undefined
    }


    private class boatPos {
        public position direction;
        public Point point;
        public int type;

        public boatPos(position m, int x, int y, int type) {
            point = new Point(x,y);
            direction = m;
            type = type;
        }

        public boatPos(boatPos p) {
            point = new Point(p.point);
            direction = p.direction;
            type = p.type;
        }
    }

    private LinkedList<boatPos> boatPosition;



    public IA() {

    }


    public void reset() {

    }



    private position boatDirection(position dir) {
        switch(dir) {
            case up: return position.down;

            case down: return position.up;

            case left: return position.right;

            case right: return position.left;
        } return position.undefined;
    }


    private position boatDirectionBT(Point pointF, Point point) {
        if(pointF.x < point.x) return position.right;
        if(pointF.x > point.x) return position.left;
        if(pointF.y < point.y) return position.down;
        if(pointF.y > point.y) return position.up;
        return position.undefined;
    }

}
