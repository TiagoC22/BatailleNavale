package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;

import java.awt.*;
import java.util.LinkedList;

//Projet bataille navale - UPMC 2018 @Tiago @Gautier

public class IA {

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

        public boatPos(position Direction, int x, int y, int Type) {
            point = new Point(x,y);
            direction = Direction;
            type = Type;
        }

        public boatPos(boatPos p) {
            point = new Point(p.point);
            direction = p.direction;
            type = p.type;
        }
    }

    private LinkedList<boatPos> boatPosition;
    private boolean gameplay;

    private int inexistingPosition = -1;
    private int touherUndefined = -1;
    private int toucherManquer = 0;

    private int[][] gridState;
    private Point nextTry;


    public boolean StartGamePlay() { return gameplay; }
    public void setGamePlay(boolean gp) { gameplay = gp; }



    public IA() {
        gridState = new int[Grid.gridSize][Grid.gridSize];
        nextTry = new Point();
        boatPosition = new LinkedList<boatPos>();
        gameplay = false;
        reset();
    }


    public void reset() {
        for(int i = 0; i < Grid.gridSize; i++) {
            for(int j = 0; j < Grid.gridSize; j++) {
                gridState[i][j] = touherUndefined;
            }
        } nextTry.setLocation(inexistingPosition, inexistingPosition);
    }


    private Point translate(Point point, position direction) {
        Point FindPoint = new Point(point);
        switch(direction) {
            case up: FindPoint.y--; break;
            case down: FindPoint.y++; break;
            case left: FindPoint.x--; break;
            case right: FindPoint.x++; break;
        } return FindPoint;
    }


    private position flipDir(position direction) {
        switch(direction) {
            case up: return position.down;
            case down: return position.up;
            case left: return position.right;
            case right: return position.left;
        } return position.undefined;
    }


    private position DB(Point pointInit, Point pointFinal) {
        if(pointInit.x < pointFinal.x) return position.right;
        if(pointInit.x > pointFinal.x) return position.left;
        if(pointInit.y < pointFinal.y) return position.down;
        if(pointInit.y > pointFinal.y) return position.up;
        return position.undefined;
    }


    private boolean tryGuessFind(Point point, position p) {
        if(point.x != inexistingPosition && point.y != inexistingPosition) {
            Point xy = translate(point, p);
            if(xy.x < 0 || xy.x >= Grid.gridSize || xy.y < 0 || xy.y >= Grid.gridSize)
                return false;
            if(gridState[xy.x][xy.y] == touherUndefined)
                return true;
        } return false;
    }


    public Point nextTryPosition() {
        if(boatPosition.isEmpty()) {
            while(nextTry.x == inexistingPosition || nextTry.y == inexistingPosition) {
                nextTry.y = MathUtils.random(0, Grid.gridSize - 1);
                if(gameplay) {
                    nextTry.x = MathUtils.random(0, (Grid.gridSize/2) - 1);
                    if((nextTry.y % 2) == 0)
                        nextTry.x = nextTry.x * 2 + 1;
                    else nextTry.x *= 2;
                } else nextTry.x = MathUtils.random(0, Grid.gridSize - 1);

                if(gridState[nextTry.x][nextTry.y] != touherUndefined)
                    nextTry.setLocation(inexistingPosition, inexistingPosition);
            }
        } else if(nextTry.x == inexistingPosition || nextTry.y == inexistingPosition) {
            boatPos sp = new boatPos(boatPosition.getFirst());
            if(sp.direction == position.undefined) {

                if(tryGuessFind(sp.point, position.right))
                    sp.direction = position.right;
                else if(tryGuessFind(sp.point, position.down))
                    sp.direction = position.down;
                else if(tryGuessFind(sp.point, position.left))
                    sp.direction = position.left;
                else if(tryGuessFind(sp.point, position.up))
                    sp.direction = position.up;
                else {
                    boatPosition.pollFirst();
                    return nextTryPosition();
                }
                nextTry = translate(sp.point, sp.direction);
            } else {
                if(tryGuessFind(sp.point, sp.direction))
                    nextTry = translate(sp.point,sp.direction);
                else {
                    boatPosition.pollFirst();
                    return nextTryPosition();
                }
            }
        } return new Point(nextTry);
    }


    public tryFindBoat guess(Grid boatGrid) {
        tryFindBoat x = tryFindBoat.rater;
        Point pointPos = nextTryPosition();
        Boat boatHit = boatGrid.firePos(pointPos.x, pointPos.y);
        if(boatHit != null) {
            gridState[pointPos.x][pointPos.y] = boatHit.getType();
            if(boatHit.degat()) {
                x = tryFindBoat.couler;
                while(!boatPosition.isEmpty()) {
                    boatPos searchT = boatPosition.getFirst();
                    if(searchT == null)
                        break;
                    if(searchT.type == boatHit.getType())
                        boatPosition.pollFirst();
                    else break;
                }
            } else {
                x = tryFindBoat.toucher;
                if(boatPosition.isEmpty())
                    boatPosition.add(new boatPos(position.undefined, pointPos.x, pointPos.y, boatHit.getType()));
                else {
                    boatPos search = boatPosition.getFirst();
                    if(search.type == boatHit.getType()) {
                        if(search.direction == position.undefined) {
                            search.direction = DB(pointPos, search.point);

                            boatPosition.addFirst(new boatPos(DB(search.point, pointPos), pointPos.x, pointPos.y, boatHit.getType()));
                        } else
                            search.point = pointPos;
                    } else
                        boatPosition.addLast(new boatPos(position.undefined, pointPos.x, pointPos.y, boatHit.getType()));
                }
            }
        } else
            gridState[pointPos.x][pointPos.y] = toucherManquer;
            nextTry.setLocation(inexistingPosition, inexistingPosition);
            return x;
    }
}
