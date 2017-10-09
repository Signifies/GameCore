package commands;

import Utilities.GameCoreUtils;
import me.ES.com.GameCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Evan on 10/9/2017.
 */
public class GameCoreCommand extends GameCoreUtils implements CommandExecutor
{

    //TODO Implement tier system into the permission API used in the build system??

    GameCore instance;
    public GameCoreCommand(GameCore val)
    {
        instance = val;
    }


    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {
        if(args.length == 0)
        {
            //List Authors
            //Functionality
            //Brief library...

            desc(sender, instance);

        }else if (args.length > 0)
        {
            switch (args[0].toLowerCase())
            {
                case "help":
                case "?":
                    commandList(); //This will be implemented using Builds sendText Methods...
                    break;

                case "permissions":
                case "perms":
                    break;

                case "ver":
                case "version":
                    getPluginVersion(instance,sender);
                    break;

                case "about":
                    break;

                case "reload":
                case "rl":
                    break;

                default:
                    sender.sendMessage(color("&7You've used incorrect arguments with this command, use &c/gamecore."));
            }
        }
        return true;
    }
}
