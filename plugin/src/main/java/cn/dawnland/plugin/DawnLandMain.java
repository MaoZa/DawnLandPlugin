package cn.dawnland.plugin;

import cn.dawnland.plugin.Listener.LoginListener;
import cn.dawnland.plugin.Listener.MainListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class DawnLandMain extends JavaPlugin {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("dawnland") && (sender instanceof Player)){
            sender.sendMessage("欢迎使用DawnLandPlugin");
        }
        return true;
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new MainListener(), this);
        getServer().getPluginManager().registerEvents(new LoginListener(), this);
        getLogger().info("DawnLandPlugin 加载成功");
        super.onEnable();
    }

    @Override
    public void onDisable() {
        getLogger().info("DawnLandPlugin 卸载成功");
        super.onDisable();
    }
}
