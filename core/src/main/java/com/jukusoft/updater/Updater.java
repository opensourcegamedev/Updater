package com.jukusoft.updater;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jukusoft.updater.utils.GameTime;

/**
 * Created by Justin on 22.04.2017.
 */
public abstract class Updater extends ApplicationAdapter {

    protected AssetManager assetManager = new AssetManager();

    /**
     * instance of game time
     */
    protected GameTime time = GameTime.getInstance();

    protected SpriteBatch batch = null;

    protected static int VIEWPORT_WIDTH = 600;
    protected static int VIEWPORT_HEIGHT = 500;

    protected OrthographicCamera uiCamera = null;

    /**
     * backround color
     */
    protected Color bgColor = Color.BLACK;

    @Override
    public void create () {
        this.VIEWPORT_WIDTH = Gdx.graphics.getWidth();
        this.VIEWPORT_HEIGHT = Gdx.graphics.getHeight();

        //create sprite batcher
        this.batch = new SpriteBatch();

        //initialize UI camera
        this.uiCamera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        this.uiCamera.translate(VIEWPORT_WIDTH / 2, VIEWPORT_HEIGHT / 2, 0);
        this.uiCamera.update();

        this.onCreate(this.assetManager);
    }

    @Override
    public void render () {
        //update time
        this.time.update();

        //update UI camera
        this.uiCamera.update();

        //clear all color buffer bits and clear screen
        Gdx.gl.glClearColor(bgColor.r, bgColor.g, bgColor.b, bgColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.batch.begin();
        this.batch.setProjectionMatrix(this.uiCamera.combined);

        draw(this.time, this.batch);

        this.batch.end();
    }

    protected abstract void onCreate (AssetManager assetManager);

    public abstract void draw (GameTime time, SpriteBatch batch);

}
