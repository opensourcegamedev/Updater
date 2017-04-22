package com.jukusoft.updater.utils;

/**
 * Created by Justin on 22.04.2017.
 */
public class PlatformUtils {

    public static String getOSName () {
        //get operating system name
        String OS = System.getProperty("os.name").toLowerCase();

        return OS;
    }

    public static boolean isWin () {
        return getOSName().contains("win");
    }

    public static boolean isMac () {
        return getOSName().contains("mac");
    }

    public static boolean isUnix () {
        return getOSName().contains("nux");
    }

    public static String getNormalizedPlatformName () {
        if (isWin()) {
            return "Windows";
        } else if (isMac()) {
            return "Mac";
        } else if (isUnix()) {
            return "Linux";
        } else {
            throw new IllegalStateException("Unsupported Operating System: " + getOSName());
        }
    }

}
