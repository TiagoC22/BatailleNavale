package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;





public class BN extends ScreenAdapter implements InputProcessor {

    //Ressources
	private Texture fire = new Texture(Gdx.files.internal("fire.png"));;
    private Texture echec = new Texture(Gdx.files.internal("echec.png"));
	private Texture boat = new Texture(Gdx.files.internal("boat.png"));
    private Texture grid = new Texture(Gdx.files.internal("grid.png"));;
    private Texture pointeur = new Texture(Gdx.files.internal("pointeur.png"));
    private Texture pointeurXL = new Texture(Gdx.files.internal("pointeurXL.png"));


    private Texture background;
    private Image backgroundImage;
    private TheGame game;
    private Stage stage;
    private int WORLD_WIDTH = Gdx.graphics.getWidth();
    private int WORLD_HEIGHT = Gdx.graphics.getHeight();


    public BN(TheGame game)
    {
        this.game = game;
        create();
    }


	public void create()
	{

        Gdx.input.setInputProcessor(this);



	}


    public void drawLgText(String sMsg)
    {
        if(sMsg.isEmpty())
            return;

        final int iTextOffset = 15;



    }



    @Override
    public void render(float delta) {
        stage = new Stage(new FitViewport(WORLD_WIDTH,WORLD_HEIGHT));
        background = new Texture(Gdx.files.internal("wallpaper2.jpg"));
        backgroundImage = new Image(background);
        backgroundImage.setFillParent(true);
        stage.addActor(backgroundImage);

	}


	@Override
	public boolean keyDown(int keycode)
	{
		if(keycode == Input.Keys.ESCAPE)
            Gdx.app.exit();
        else if(keycode == Input.Keys.F5)
        {



        }
        else if(keycode == Input.Keys.D)
        {



		return false;
	}


	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
        {

            int iTileX, iTileY;
            iTileX = screenX / Grid.TILE_SIZE;
            iTileY = screenY / Grid.TILE_SIZE;

        }

    @Override
    public void show() {

    }



    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }


	@Override
	public void dispose()
	{

		fire.dispose();
		boat.dispose();
        echec.dispose();
        grid.dispose();
        pointeur.dispose();
        pointeurXL.dispose();
	}


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }

    @Override
    public boolean scrolled(int amount)	{ return false; }

    @Override
    public boolean keyUp(int keycode) { return false; }

    @Override
    public boolean keyTyped(char character) { return false; }
}
