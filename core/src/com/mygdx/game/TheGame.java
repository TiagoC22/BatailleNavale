package com.mygdx.game;

import com.badlogic.gdx.Game;

//Projet bataille navale - UPMC 2018 @Tiago @Gautier


public class TheGame extends Game {


    public TheGame(){

    }
    @Override
    public void create () {
        setScreen(new MainMenu(this));
    }
}