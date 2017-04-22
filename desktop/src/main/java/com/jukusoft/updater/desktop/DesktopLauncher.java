package com.jukusoft.updater.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jukusoft.updater.Updater;
import com.jukusoft.updater.utils.FileUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by Justin on 27.03.2017.
 */
public class DesktopLauncher {

    public static void main (String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Space Chaos";
        config.height = 720;
        config.width = 1280;
        //config.addIcon("./data/icons/icon.png", Files.FileType.Absolute);

        try {
            //start game
            new LwjglApplication(new Updater(), config);
        } catch (Exception e) {
            e.printStackTrace();

            try {
                //write crash dump
                FileUtils.writeFile("./updater-crash.log", e.getLocalizedMessage(), StandardCharsets.UTF_8);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            System.exit(-1);
        }
    }

}
