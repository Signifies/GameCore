package me.ES.com;

import MapManager.WorldConfig;
import Utilities.Debug;
import Utilities.GameConfig;
import Utilities.GameCoreUtils;
import commands.GameCoreCommand;
import events.GameCoreEvents;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by ES on 10/4/2017.
 */
public class GameCore extends JavaPlugin
{

    public PluginDescriptionFile pdfFile = getDescription();
    private GameCoreUtils util = new GameCoreUtils();
    public static boolean DEBUG = true; //TODO implement level system on debug status.
    private GameConfig gc = new GameConfig(this);
    private WorldConfig wc = new WorldConfig(this);
    PluginManager pm = Bukkit.getServer().getPluginManager();

    void lockout()
    {
        if(getServer().getPluginManager().getPlugin("Build") == null)
        {
            util.log("&4Error: No Build dependency found [!] Please install and configure Build core.");
            getServer().getPluginManager().disablePlugin(this);
        }
    }


    public void onEnable()
    {
        lockout();

        Events();
        configuration();
        Commands();
        Maps();
        Worlds();
    }

    private void Events()
    {
        pm.registerEvents(new GameCoreEvents(this), this);

    }

    private void Commands()
    {
        Debug.log(Debug.LOG + "&4Loading commands...");
        registerCmd("gamecore",new GameCoreCommand(this));
    }

    private void configuration()
    {
        Debug.log(Debug.LOG + "&6Configuration loading...");
        gc.saveDefaultGameConfig();
        gc.saveGameConfig();
    }

    private void Maps()
    {
        //TODO... once map manager has been created.
    }

    private void Worlds()
    {
        Debug.log(Debug.LOG + "&2World configuration loading...");
        wc.saveDefaultWorldConfig();
        wc.saveWorldConfig();
    }

    private void registerCmd(String command, CommandExecutor commandExecutor) {
        Bukkit.getServer().getPluginCommand(command).setExecutor(commandExecutor);
    }

    //TODO WORLD SAVING, Player Data etc..
    public void onDisable()
    {

    }
}
