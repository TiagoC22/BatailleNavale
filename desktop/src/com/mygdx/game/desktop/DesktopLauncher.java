package com.mygdx.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class DesktopLauncher {
    public static Texture backgroundTexture;

    public static void main (String[] arg) {

        //Récuperer size écran
            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            int width = gd.getDisplayMode().getWidth();
            int height = gd.getDisplayMode().getHeight();

            LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
            new LwjglApplication(new MyGdxGame(), config);
                backgroundTexture = new Texture(Gdx.files.internal("assets/background.jpg"));

                config.title = "BatailleNavale";
                config.height = height;
                config.width = width;

    }
}
