package org.psigames.ssclient.tools;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.psigames.ssclient.Client;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by jadengiordano on 8/26/15.
 */
public class Constants {

    private static class FileIOContainer {
        int width;
        int height;
        int windowMode;

        protected FileIOContainer(int w, int h, int winMode) {
            width = w;
            height = h;
            windowMode = winMode;
        }
    }

    public static final String OS = System.getProperty("os.name").toUpperCase();
    public static String OSType;

    public static String dataPath, logPath, savePath, assetsPath;

    public static String title = "SS13 Client - Starting", version = "v0.0.01";
    public static int width = 800, height = 450, WINDOWED = 1, BORDERLESS = 2, FULLSCREEN = 3, fsMode = WINDOWED;
    public static float glVersion = -1;

    public static final Date startDate = new Date();

    public static Gson gson;

    public static double deltaTime;

    public static void init() {
        gson = new Gson();
        getOSType();
        getDataPath();
        loadFromFile();
    }

    private static void loadFromFile() {
        try {
            File file = new File(Constants.savePath + "config.json");
            if (file.length() == 0) {
                FileIOContainer configWrite = new FileIOContainer(width, height, fsMode);

                String fWriteTo = gson.toJson(configWrite);
                FileUtils.write(file, fWriteTo);
                return;
            }
            String lines = FileUtils.readFileToString(file);
            FileIOContainer cfgRead = gson.fromJson(lines, FileIOContainer.class);

            width = cfgRead.width;
            height = cfgRead.height;
            fsMode = cfgRead.windowMode;

        } catch (IOException e) {
            Client.singleton.errorHandler.logError(e);
            e.printStackTrace();
        }
    }

    private static void getOSType() {
        if (OS.toUpperCase().contains("windows".toUpperCase())) {
            OSType = "windows";
        } else if (OS.toUpperCase().contains("mac".toUpperCase())) {
            OSType = "mac";
        } else if (OS.toUpperCase().contains("nix".toUpperCase()) || OS.toUpperCase().contains("nux".toUpperCase()) || OS.toUpperCase().contains("aix".toUpperCase())) {
            OSType = "unix";
        } else if (OS.toUpperCase().contains("sunos".toUpperCase())) {
            OSType = "solaris";
        } else {
            OSType = "unknown";
        }
    }

    private static void getDataPath() {
        if (OSType == "windows") {
            dataPath = System.getProperty("user.home") + "\\AppData" + File.separator + "Roaming" + File.separator + "SS13" + File.separator;
        } else if (OSType == "mac" || OSType == "unix" || OSType == "solaris") {
            dataPath = System.getProperty("user.home") + File.separator + "SS13" + File.separator;
        } else {
            dataPath = Constants.class.getProtectionDomain().getCodeSource().getLocation().toString() + File.separator;
        }
        logPath = dataPath + "Logs" + File.separator;
        savePath = dataPath + "Saves" + File.separator;
        assetsPath = dataPath + "assets" +File.separator;
    }

}
