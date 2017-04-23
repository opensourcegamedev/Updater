package com.jukusoft.updater.update;

import com.jukusoft.updater.utils.WebsiteUtils;
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

    //newest build of game
    protected int newestBuild = 0;
    protected String newestVersion = "Unknown";

    protected boolean updateAvailable = false;

    protected String updateInfoPath = "";

    public VersionInfo (String updateInfoPath) {
        this.updateInfoPath = updateInfoPath;

        //get own build
        loadOwnVersion();

        //get newest version
        this.loadNewestVersion();
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

    public void loadNewestVersion () {
        //get website content of info file
        try {
            String content = WebsiteUtils.getWebsiteContent(this.updateInfoPath);

            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
            this.updateAvailable = false;
        }
    }

    public int getOwnBuild () {
        return this.ownBuild;
    }

    public String getOwnVersion () {
        return this.ownVersion;
    }

    public int getNewestBuild () {
        return this.newestBuild;
    }

    public String getNewestVersion () {
        return this.newestVersion;
    }

    public boolean isUpdateAvailable () {
        return this.updateAvailable;
    }

}
