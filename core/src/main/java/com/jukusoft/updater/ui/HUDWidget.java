package com.jukusoft.updater.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jukusoft.updater.utils.GameTime;

/**
 * Created by Justin on 08.02.2017.
 */
public interface HUDWidget {

    public void update(GameTime time);

    public void drawLayer0(GameTime time, SpriteBatch batch);

    public void drawLayer1(GameTime time, ShapeRenderer shapeRenderer);

    public void drawLayer2(GameTime time, SpriteBatch batch);

    public float getX();

    public float getY();

    public void setPosition(float x, float y);

    public void onMoveGroup(float groupX, float groupY);

    public void dispose();

}
