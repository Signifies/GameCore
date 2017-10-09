package MapManager;

import Utilities.GameCoreUtils;
import me.ES.com.GameCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.logging.Level;

public class WorldConfig extends GameCoreUtils {

    private FileConfiguration WorldConfig = null;
    private File WorldConfigFile = null;
    private static final String fileName = "Worlds.yml";

    private GameCore main;

    public WorldConfig(GameCore instance)
    {
        main = instance;
    }


    public void reloadWorldConfig() {
        if (WorldConfigFile == null) {
            WorldConfigFile = new File(main.getDataFolder(),fileName);
        }
        WorldConfig = YamlConfiguration.loadConfiguration(WorldConfigFile);

        // Look for defaults in the jar
        Reader defConfigStream = null;
        try {
            defConfigStream = new InputStreamReader(main.getResource(fileName), "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            WorldConfig.setDefaults(defConfig);
        }
    }

    public FileConfiguration getWorldConfig() {
        if (WorldConfig == null) {
            reloadWorldConfig();
        }
        return WorldConfig;
    }

    public void saveWorldConfig() {
        if (WorldConfig == null || WorldConfigFile == null) {
            return;
        }
        try {
            getWorldConfig().save(WorldConfigFile);
        } catch (IOException ex) {
            main.getLogger().log(Level.SEVERE, "Could not save config to " + WorldConfigFile, ex);
        }
    }

    public void saveDefaultWorldConfig() {
        if (WorldConfigFile == null) {
            WorldConfigFile = new File(main.getDataFolder(), fileName);
        }
        if (!WorldConfigFile.exists()) {
            main.saveResource(fileName, false);
        }
    }

}