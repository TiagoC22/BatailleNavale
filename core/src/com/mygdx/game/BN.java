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

//Projet bataille navale - UPMC 2018 @Tiago @Gautier

public class BN extends ScreenAdapter implements InputProcessor {


    private float pointeurMinSize=0.45f;
    private float pointeurMaxSize=0.7f;

    //Ressources
	private Texture fire = new Texture(Gdx.files.internal("battleshipressources/fire.png"));;
    private Texture echec = new Texture(Gdx.files.internal("battleshipressources/echec.png"));
	private Texture boat = new Texture(Gdx.files.internal("battleshipressources/Boat.png"));
    private Texture grid = new Texture(Gdx.files.internal("grid.png"));;
    private Texture background = new Texture(Gdx.files.internal("black.jpg"));;
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

    //Int
    private int actorGame;
    private int posBoat=0;
    private int playerToPlay=1;
    private int iaToPlay=2;
    private int finish=3;
    private int strPoint=5;
    private int subPoint=4;
    private int ch;
    private int playerWin=0;
    private int aiWin=1;
    private int aiDecal=20;

    //Long
    private long timefinish;
    private long aiTime;
    private long aiCompteur;

    //Double
    private double changeTime=0.6;
    private double degatTime=1;
    private double pointeurF=1.65;
    private double secP=1000000000.0;
    private double pointeurImageSize=20.0;
    private double aiMsgSize=3.0;
    private double controls=15.0;

    //String
    private String aiMsgWin="Vous avez perdue !";
    private String playerMsgWin="Vous avez gagne !";
    private String rater="Rate !";
    private String toucher="Touchee !";
    private String couler="Coule ! ";
    private String messageText;

    private TheGame game;
    private Stage stage;

    private int WORLD_WIDTH = Gdx.graphics.getWidth();
    private int WORLD_HEIGHT = Gdx.graphics.getHeight();


    public BN(TheGame game) {
        this.game = game;
        create();
    }


	public void create() {

        Gdx.input.setInputProcessor(this);
        familyfont = new BitmapFont(true);
        gridplayer = new Grid_player(background, grid, echec, fire, boat);
        gridIA = new Grid(background, grid, echec, fire, boat);
        ia = new IA();
        batch = new SpriteBatch();
        renderpoint = new ShapeRenderer();
        gridplayer.startPlacingShips();
        gridIA.boatShuffle();
        actorGame = posBoat;
        timefinish = 0;
        aiTime = 0;
        mousepoint = new Point(-1,-1);

        //Musique qui continue après avoir fini
        gamesound.setLooping(true);
        gamesound.play();

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        aiCompteur = System.nanoTime() + (long)(controls * secP);
	}


    public void drawText(String msg) {
        if(msg.isEmpty())
            return;
        final int textOff = 15;

        batch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        renderpoint.begin(ShapeRenderer.ShapeType.Filled);
        renderpoint.setColor(0, 0, 0, 0.65f);
        familyfont.getData().setScale(strPoint);
        renderpoint.rect(0, Gdx.graphics.getHeight() / 4 - textOff, Gdx.graphics.getWidth(), familyfont.getLineHeight());
        renderpoint.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        batch.begin();

        familyfont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        familyfont.getData().setScale(strPoint);
        familyfont.draw(batch, msg, 0, Gdx.graphics.getHeight() / 4, Gdx.graphics.getWidth(), Align.center, false);
    }



    @Override
    public void render(float delta) {
        
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        renderpoint.setProjectionMatrix(camera.combined);

        if(actorGame == playerToPlay && timefinish > 0) {
            if(System.nanoTime() >= timefinish) {
                timefinish = 0;
                if(gridIA.boardClean()) {
                    actorGame = finish;
                    ch = playerWin;
                } else {
                    actorGame = iaToPlay;
                    aiTime = (long) (System.nanoTime() + changeTime * secP);
                }
            }
        }
        else if(actorGame == iaToPlay) {
            if(aiTime > 0) {
                if(System.nanoTime() >= aiTime) {
                    IA.tryFindBoat gHit = ia.guess(gridplayer);
                        if(gHit == IA.tryFindBoat.toucher)
                            toucher_bateau.play();
                        else if(gHit == IA.tryFindBoat.rater)
                            toucher_eau.play();
                        else if(!gridplayer.boardClean())
                            detruit.play();
                    timefinish = (long)(System.nanoTime() + changeTime * secP);
                    aiTime = 0;
                }
            }
            else if(timefinish > 0) {
                if(System.nanoTime() >= timefinish) {
                    timefinish = 0;
                        if(gridplayer.boardClean()) {
                            actorGame = finish;
                            ch = aiWin;
                        } else {
                            actorGame = playerToPlay;
                        }
                }
            }
        } batch.begin();


        if(actorGame == playerToPlay) {
            gridIA.draw(!Gdx.input.isKeyPressed(Input.Keys.S), batch);
                if(timefinish == 0) {
                    Color cCursorCol = new Color(1, 1, 1, pointeurMaxSize);
                    double fSecondsElapsed = (double) System.nanoTime() / secP;
                    cCursorCol.lerp(1, 1, 1, pointeurMinSize, (float) Math.sin(fSecondsElapsed * Math.PI * pointeurF));   //Linearly interpolate this color to final value
                    batch.setColor(cCursorCol);
                    batch.draw(pointeur, mousepoint.x * Grid.cellSize, mousepoint.y * Grid.cellSize);
                    batch.setColor(Color.WHITE);
                } else drawText(text);
        }

        else if(actorGame == iaToPlay) {
            gridplayer.draw(false, batch);
            if(aiTime > 0) {
                Point AiTryFindPos = ia.nextTryPosition();
                double xCrossPos = AiTryFindPos.x * Grid.cellSize + (double)Grid.cellSize / 2.0;
                double yCrossPos = AiTryFindPos.y * Grid.cellSize + (double)Grid.cellSize / 2.0;
                double fCrossSc = ((double)(aiTime - System.nanoTime()) / secP) * changeTime * pointeurImageSize + ((double)Grid.cellSize / (double)pointeurXL.getHeight());
                double drawCross = fCrossSc * pointeurXL.getHeight();
                batch.draw(pointeurXL, (float)(xCrossPos - drawCross / 2.0), (float)(yCrossPos - drawCross / 2.0), (float)drawCross, (float)drawCross);
            }
        } else if(actorGame == posBoat) {
            gridplayer.draw(false, batch);
        } else if(actorGame == finish) {
            if (ch == playerWin)
                gridIA.draw(false, batch);
            else
                gridplayer.draw(false, batch);

            if (ch == playerWin)
                familyfont.setColor(0.25f, 1.0f, 0.25f, 1.0f);
            else
                familyfont.setColor(1.0f, 0.1f, 0.1f, 1.0f);
            familyfont.getData().setScale(subPoint);
            familyfont.draw(batch, (ch == playerWin) ? (playerMsgWin) : (aiMsgWin), 0, Gdx.graphics.getHeight() / 2, Gdx.graphics.getWidth(), Align.center, false);
        } if(System.nanoTime() < aiCompteur) {
            familyfont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
            familyfont.getData().setScale(1);
        }
        batch.end();
	}


	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.ESCAPE)
            Gdx.app.exit();
        else if(keycode == Input.Keys.F5) {
            actorGame = posBoat;
            timefinish = 0;
            aiTime = 0;
            gridplayer.reset();
            gridIA.reset();
            ia.reset();
            gridplayer.startPlacingShips();
            gridIA.boatShuffle();
        } else if(keycode == Input.Keys.D) {
            aiCompteur = System.nanoTime() + (long)(aiMsgSize * secP);
            ia.setGamePlay(!ia.StartGamePlay());
        } return false;
	}


	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        int tX, tY;
        tX = screenX / Grid.cellSize; tY = screenY / Grid.cellSize;
		if(button == Input.Buttons.LEFT) {
            if(actorGame == posBoat) {
                if(gridplayer.placeShip(tX, tY)) {
                    actorGame = playerToPlay;
                }
            } else if(actorGame == playerToPlay && timefinish == 0) {
                if (!gridIA.fireInBoatNow(tX, tY)) {
                    Boat boatHits = gridIA.firePos(tX, tY);
                    if(boatHits != null) {
                        if(boatHits.degat()) {
                            if(!gridIA.boardClean())
                                detruit.play();
                            text = couler + boatHits.getName();
                        } else {
                            toucher_bateau.play();
                            text = toucher + boatHits.getName();
                        }
                    } else {
                        toucher_eau.play();
                        text = rater;
                    }
                    timefinish = (long)(System.nanoTime() + degatTime * secP);
                }
            } else if(actorGame == finish) {
                actorGame = posBoat;
                gridplayer.reset();
                gridIA.reset();
                ia.reset();
                gridplayer.startPlacingShips();
                gridIA.boatShuffle();
                gamesound.stop();
            }
		} else if(button == Input.Buttons.RIGHT) {
			if(actorGame == posBoat)
                gridplayer.rotateShip();
		}
		return false;
	}


	@Override
	public boolean mouseMoved(int screenX, int screenY) {
        int iTileX, iTileY;
        iTileX = screenX / Grid.cellSize;
        iTileY = screenY / Grid.cellSize;
        mousepoint.x = iTileX;
        mousepoint.y = iTileY;

        if(actorGame == posBoat)
            gridplayer.movingShip(iTileX, iTileY);

		return false;
	}

    @Override
    public void show() { }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

	@Override
	public void dispose() {
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
