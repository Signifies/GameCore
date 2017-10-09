package events;

import Utilities.GameCoreUtils;
import me.ES.com.GameCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by Evan on 10/9/2017.
 */

//TODO reference TODO.txt for information..
public class GameCoreEvents extends GameCoreUtils implements Listener
{

    GameCore instance;
    public GameCoreEvents(GameCore val)
    {
        instance = val;
    }

    //TODO work with this later.

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event)
    {
        Player p = event.getPlayer();


    }
}
