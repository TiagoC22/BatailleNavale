package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;


import java.awt.*;


public class BN extends ScreenAdapter implements InputProcessor {

    //Ressources
	private Texture fire = new Texture(Gdx.files.internal("battleshipressources/fire.png"));;
    private Texture echec = new Texture(Gdx.files.internal("battleshipressources/echec.png"));
	private Texture boat = new Texture(Gdx.files.internal("battleshipressources/boat.png"));
    private Texture grid = new Texture(Gdx.files.internal("grid.png"));;
    private Texture pointeur = new Texture(Gdx.files.internal("battleshipressources/pointeur.png"));
    private Texture pointeurXL = new Texture(Gdx.files.internal("battleshipressources/pointeurXL.png"));

    //Effets de sons
	private Sound toucher_eau = Gdx.audio.newSound(Gdx.files.internal("sound/toucher-eau.mp3"));
    private Sound toucher_bateau = Gdx.audio.newSound(Gdx.files.internal("sound/toucher-bateau.mp3"));;
    private Sound detruit = Gdx.audio.newSound(Gdx.files.internal("sound/detruit.mp3"));
    private Music gamesound = Gdx.audio.newMusic(Gdx.files.internal("sound/game-soundtrack.mp3"));


	private SpriteBatch batch;
    private ShapeRenderer renderpoint;
	private OrthographicCamera camera;
    private BitmapFont familyfont;
    private String text;


	private Grid_player gridplayer;
    private Grid gridIA;
    private IA ia; //Intelligence artificiel
    private Point mousepoint;


    private int actorGame;
    private final int posBoat = 0;
    private final int playerToPlay = 1;
    private final int iaToPlay = 2;
    private final int finish = 3;
    private final double degatTime = 1;

    private int ch;
    private final int playerWin=0;
    private final int aiWin=1;
    private final String aiMsgWin="Vous avez perdue !";
    private final String playerMsgWin="Vous avez gagner !";
    private final String rater="Râté !";
    private final String toucher="Touchée !";
    private final String couler="Coulé ! ";
    private final int strPoint=5;
    private final int subPoint=4;


    private final double aiMsgSize=3.0;
    private final double controls=15.0;
    private final int aiDecal=20;
    private long aiCompteur;
    private String messageText;

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


        familyfont = new BitmapFont(true);

        gridplayer = new Grid_player(grid, echec, fire, boat);
        gridIA = new Grid(grid, echec, fire, boat);
        ia = new IA();
        batch = new SpriteBatch();
        renderpoint = new ShapeRenderer();


        gridplayer.startPlacingShips();
        gridIA.placeShipsRandom();
        actorGame = posBoat;
        mousepoint = new Point(-1,-1);

        //Musique qui continue après avoir fini
        gamesound.setLooping(true);
        gamesound.play();


        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());



	}


    public void drawLgText(String sMsg)
    {
        if(sMsg.isEmpty())
            return;

        final int iTextOffset = 15;


        batch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        renderpoint.begin(ShapeRenderer.ShapeType.Filled);

        renderpoint.setColor(0, 0, 0, 0.65f);
        familyfont.getData().setScale(strPoint);
        renderpoint.rect(0, Gdx.graphics.getHeight() / 4 - iTextOffset, Gdx.graphics.getWidth(), familyfont.getLineHeight());    //Draw behind where the text will be

        renderpoint.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        batch.begin();


        familyfont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        familyfont.getData().setScale(strPoint);
        familyfont.draw(batch, sMsg, 0, Gdx.graphics.getHeight() / 4, Gdx.graphics.getWidth(), Align.center, false);
    }



    @Override
    public void render(float delta) {
        stage = new Stage(new FitViewport(WORLD_WIDTH,WORLD_HEIGHT));
        background = new Texture(Gdx.files.internal("wallpaper2.jpg"));
        backgroundImage = new Image(background);
        backgroundImage.setFillParent(true);
        stage.addActor(backgroundImage);

        camera.update();


        batch.setProjectionMatrix(camera.combined);
        renderpoint.setProjectionMatrix(camera.combined);
        batch.begin();

        if(actorGame == playerToPlay)
        {

            gridIA.draw(!Gdx.input.isKeyPressed(Input.Keys.S), batch);


                drawLgText(text);
        }
        else if(actorGame == iaToPlay)
        {

            gridplayer.draw(false, batch);

        }
        else if(actorGame == posBoat)
        {
            gridplayer.draw(false, batch);
        }
        else if(actorGame == finish)
        {
            if(ch == playerWin)
                gridIA.draw(false, batch);
            else
                gridplayer.draw(false, batch);



            if(ch == playerWin)
                familyfont.setColor(0.25f, 1.0f, 0.25f, 1.0f);
            else
                familyfont.setColor(1.0f, 0.1f, 0.1f, 1.0f);
                familyfont.getData().setScale(subPoint);
                familyfont.draw(batch, (ch == playerWin)?(playerMsgWin):(aiMsgWin), 0, Gdx.graphics.getHeight() / 2, Gdx.graphics.getWidth(), Align.center, false);
        }


        if(System.nanoTime() < aiCompteur)
        {
            familyfont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
            familyfont.getData().setScale(1);

        }


        batch.end();
	}


	@Override
	public boolean keyDown(int keycode)
	{
		if(keycode == Input.Keys.ESCAPE)
            Gdx.app.exit();
        else if(keycode == Input.Keys.F5)
        {

            actorGame = posBoat;
            gridplayer.reset();
            gridIA.reset();
            ia.reset();
            gridplayer.startPlacingShips();
            gridIA.placeShipsRandom();




        }
        else if(keycode == Input.Keys.D)
        {


            ia.setHardMode(!ia.isHardMode());

        }

		return false;
	}


	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{

        int iTileX, iTileY;
        iTileX = screenX / Grid.TILE_SIZE;
        iTileY = screenY / Grid.TILE_SIZE;

		if(button == Input.Buttons.LEFT)
		{
            if(actorGame == posBoat)
            {
                if(gridplayer.placeShip(iTileX, iTileY))
                {
                    actorGame = playerToPlay;
                }
            }

            else if(actorGame == finish)
            {

                actorGame = posBoat;
                gridplayer.reset();
                gridIA.reset();
                ia.reset();
                gridplayer.startPlacingShips();
                gridIA.placeShipsRandom();


                gamesound.stop();

            }
		}
		else if(button == Input.Buttons.RIGHT)
		{
			if(actorGame == posBoat)
                gridplayer.rotateShip();
		}
		return false;
	}


	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{

        int iTileX, iTileY;
        iTileX = screenX / Grid.TILE_SIZE;
        iTileY = screenY / Grid.TILE_SIZE;


        mousepoint.x = iTileX;
        mousepoint.y = iTileY;

        if(actorGame == posBoat)
            gridplayer.moveShip(iTileX, iTileY);

		return false;
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
        toucher_eau.dispose();
        toucher_bateau.dispose();
        detruit.dispose();
        gamesound.dispose();
        batch.dispose();
        renderpoint.dispose();
        familyfont.dispose();
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
