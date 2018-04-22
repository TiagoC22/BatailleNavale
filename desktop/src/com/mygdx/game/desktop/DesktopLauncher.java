package com.mygdx.game.desktop;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

public class DesktopLauncher {

    public static void main (String[] arg) {

        Settings settings = new Settings();
        settings.maxWidth = 1024;
        settings.maxHeight = 1024;
        settings.duplicatePadding = false;
        //settings.debug = drawDebugOutline;
        settings.filterMag = Texture.TextureFilter.Linear;
        settings.filterMin = Texture.TextureFilter.Linear;
        //TexturePacker.process(settings, "C:/Users/lepor/Desktop/BN/desktop/assets-raw/images-buttons","C:/Users/lepor/Desktop/BN/core/assets/skins","button.pack");

        //Récuperer size écran
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "BatailleNavale";
        config.height = height;
        config.width = width;

        new LwjglApplication(new MyGdxGame(), config);
    }
}