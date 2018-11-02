package cn.dawnland.plugin.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class LoginListener implements Listener {

    @EventHandler
    public void login(PlayerCommandPreprocessEvent playerCommandPreprocessEvent){
        String command = playerCommandPreprocessEvent.getMessage().replace("/", "");
        playerCommandPreprocessEvent.getPlayer().sendMessage(command);
    }

}
