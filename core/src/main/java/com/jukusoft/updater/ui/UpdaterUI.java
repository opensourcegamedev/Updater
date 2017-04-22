package com.jukusoft.updater.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jukusoft.updater.Updater;
import com.jukusoft.updater.config.AppConfig;
import com.jukusoft.updater.skin.SkinFactory;
import com.jukusoft.updater.utils.FileUtils;
import com.jukusoft.updater.utils.GameTime;
import com.jukusoft.updater.utils.PlatformUtils;
import javafx.application.Platform;

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

    //buttons
    protected TextButton updateButton = null;
    protected TextButton startButton = null;

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

        this.updateButton = new TextButton("Update", this.uiSkin);
        this.updateButton.setPosition(VIEWPORT_WIDTH - 240, 20);
        this.updateButton.setWidth(100);
        this.updateButton.setHeight(50);
        this.updateButton.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                //update game
                updateGame();
            }
        });
        this.uiStage.addActor(this.updateButton);

        this.startButton = new TextButton("Start Game", this.uiSkin);
        this.startButton.setPosition(VIEWPORT_WIDTH - 120, 20);
        this.startButton.setWidth(100);
        this.startButton.setHeight(50);
        this.startButton.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                //start game
                startGame();
            }
        });
        this.uiStage.addActor(this.startButton);

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

    protected void startGame () {
        //get operating system name
        String OS = System.getProperty("os.name").toLowerCase();

        //get start command
        String cmd = appConfig.getRunCMD(PlatformUtils.getNormalizedPlatformName());

        System.out.println("execute start command: " + cmd);

        //execute command
        try {
            Process process = Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();

            //write crash dump
            try {
                FileUtils.writeFile("./launcher-startup-crash.log", e.getLocalizedMessage(), StandardCharsets.UTF_8);
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }

            System.exit(1);
        }
    }

    protected void updateGame () {
        //hide update button
        updateButton.setVisible(false);

        //hide start button while updating game
        startButton.setVisible(false);
    }

}
