package com.jukusoft.updater.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jukusoft.updater.Updater;
import com.jukusoft.updater.config.AppConfig;
import com.jukusoft.updater.skin.SkinFactory;
import com.jukusoft.updater.utils.FileUtils;
import com.jukusoft.updater.utils.GameTime;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by Justin on 22.04.2017.
 */
public class UpdaterUI extends Updater {

    protected static final String BG_IMAGE_PATH = "./data/launcher/wallpaper/galaxy1/galaxy1.jpg";

    protected Texture bgImage = null;

    //UI
    protected Skin uiSkin = null;
    protected Stage uiStage = null;

    protected boolean updateable = false;

    protected AppConfig appConfig = new AppConfig();

    @Override
    protected void onCreate(AssetManager assetManager) {
        //TODO: read background image from configuration

        //load assets
        assetManager.load(BG_IMAGE_PATH, Texture.class);

        //load all assets
        assetManager.finishLoading();

        //get assets
        this.bgImage = assetManager.get(BG_IMAGE_PATH, Texture.class);

        //create and load ui skin from json file
        this.uiSkin = SkinFactory.createSkin("./data/launcher/ui/skin/libgdx/uiskin.json");

        //create stage for user interface (UI)
        this.uiStage = new Stage();

        //this.uiStage.s
        Gdx.input.setInputProcessor(this.uiStage);

        //load configuration
        try {
            this.appConfig.load(new File("./config/launcher.cfg"));
        } catch (IOException e) {
            e.printStackTrace();

            //write crash dump
            try {
                FileUtils.writeFile("./updater-crash.log", e.getLocalizedMessage(), StandardCharsets.UTF_8);
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }

            System.exit(1);
        }
    }

    @Override
    public void draw(GameTime time, SpriteBatch batch) {
        batch.draw(this.bgImage, 0, 0, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        batch.flush();

        //draw UI stage
        this.uiStage.draw();
    }

}
