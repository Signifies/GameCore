package Utilities;

import me.ES.com.GameCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.logging.Level;

public class GameConfig
{
    private FileConfiguration GameConfig = null;
    private File GameConfigFile = null;
    private static final String fileName = "config.yml";

    private GameCore main;

    public GameConfig(GameCore instance)
    {
        main = instance;
    }


    public void reloadGameConfig() {
        if (GameConfigFile == null) {
            GameConfigFile = new File(main.getDataFolder(),fileName);
        }
        GameConfig = YamlConfiguration.loadConfiguration(GameConfigFile);

        // Look for defaults in the jar
        Reader defConfigStream = null;
        try {
            defConfigStream = new InputStreamReader(main.getResource(fileName), "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            GameConfig.setDefaults(defConfig);
        }
    }

    public FileConfiguration getGameConfig() {
        if (GameConfig == null) {
            reloadGameConfig();
        }
        return GameConfig;
    }

    public void saveGameConfig() {
        if (GameConfig == null || GameConfigFile == null) {
            return;
        }
        try {
            getGameConfig().save(GameConfigFile);
        } catch (IOException ex) {
            main.getLogger().log(Level.SEVERE, "Could not save config to " + GameConfigFile, ex);
        }
    }

    public void saveDefaultGameConfig() {
        if (GameConfigFile == null) {
            GameConfigFile = new File(main.getDataFolder(), fileName);
        }
        if (!GameConfigFile.exists()) {
            main.saveResource(fileName, false);
        }
    }
}