package com.mygdx.game;

import com.badlogic.gdx.Game;

public class TheGame extends Game {


    public TheGame(){

    }
    @Override
    public void create () {

        setScreen(new MainMenu(this));


    }
}