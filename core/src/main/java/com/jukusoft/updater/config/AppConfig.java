package com.jukusoft.updater.config;

import org.ini4j.Ini;
import org.ini4j.Profile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justin on 22.04.2017.
 */
public class AppConfig {

    protected Ini ini = null;
    protected Profile.Section runSection = null;

    protected List<String> supportedPlatforms = new ArrayList<>();

    public AppConfig () {
        //
    }

    public void load (File configFile) throws IOException {
        this.ini = new Ini(configFile);
        this.runSection = this.ini.get("Run");
    }

    public String getRunCMD (String platformName) {
        if (!supportedPlatforms.contains(platformName)) {
            throw new IllegalStateException("platform isnt supported.");
        }

        if (!this.ini.containsKey("Run" + platformName)) {
            throw new IllegalStateException("RUN Section not found: " + "'Run" + platformName + "'.");
        }

        Profile.Section section = this.ini.get("Run" + platformName);
        return section.get("runCMD");
    }

}
