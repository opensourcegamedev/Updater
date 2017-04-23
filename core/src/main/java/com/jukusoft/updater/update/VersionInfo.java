package com.jukusoft.updater.update;

import org.ini4j.Ini;
import org.ini4j.Profile;

import java.io.File;
import java.io.IOException;

/**
 * get current version of game
 *
 * Created by Justin on 23.04.2017.
 */
public class VersionInfo {

    //own build of game
    protected int ownBuild = 0;
    protected String ownVersion = "";

    public VersionInfo () {
        //get own build
        loadOwnVersion();
    }

    public void loadOwnVersion () {
        try {
            Ini ini = new Ini(new File("./data/version.cfg"));
            Profile.Section section = ini.get("Version");

            this.ownBuild = Integer.parseInt(section.getOrDefault("build", "0"));
            this.ownVersion = section.getOrDefault("version", "Unknown Version");
        } catch (IOException e) {
            e.printStackTrace();
            this.ownBuild = 0;
            this.ownVersion = "Unknown Version";
        }
    }

    public int getOwnBuild () {
        return this.ownBuild;
    }

    public String getOwnVersion () {
        return this.ownVersion;
    }

}
