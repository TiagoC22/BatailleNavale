package com.mygdx.game.desktop;


import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import com.badlogic.gdx.Files.FileType;

public class DesktopLauncher {

    public static void main (String[] arg) {

        //Récuperer size écran
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.addIcon("com/mygdx/game/desktop/icon.png", FileType.Internal);
        config.title = "BatailleNavale";
        config.height = height;
        config.width = width;

        new LwjglApplication(new MyGdxGame(), config);
    }
}