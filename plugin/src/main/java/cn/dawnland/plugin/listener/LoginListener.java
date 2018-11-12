package cn.dawnland.plugin.listener;

import cn.dawnland.plugin.models.ReturnData;
import cn.dawnland.plugin.utils.UrlUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.HashMap;
import java.util.Map;

public class LoginListener implements Listener {

    private final String HOST = "http://localhost:8081/";

    @EventHandler
    public Boolean allPlayerCommand(PlayerCommandPreprocessEvent playerCommandPreprocessEvent){
        String command = playerCommandPreprocessEvent.getMessage().replace("/", "");
        playerCommandPreprocessEvent.getPlayer().getServer().getLogger().info(playerCommandPreprocessEvent.getPlayer().getDisplayName() + "："+ command);
        String[] args = command.split(" ");
        if (args[0].equals("findUUID")){
            if(args.length > 1){
                playerCommandPreprocessEvent.getPlayer().sendMessage(findUUIDByName(args[1]));
                return true;
            }else {
                playerCommandPreprocessEvent.getPlayer().sendMessage("请输入name");
                return true;
            }
        }
        if (args[0].equals("findName")){
            if(args.length > 1){
                playerCommandPreprocessEvent.getPlayer().sendMessage(findNameByUUID(args[1]));
                return true;
            }else {
                playerCommandPreprocessEvent.getPlayer().sendMessage("请输入uuid");
                return true;
            }
        }
        if (args[0].equals("score")){
            String playuerName = playerCommandPreprocessEvent.getPlayer().getDisplayName();
            playerCommandPreprocessEvent.getPlayer().sendMessage(this.findScore(playuerName));
            return true;
        }
        return true;
    }


    private String findUUIDByName(String name){
        String result = UrlUtil.sendPost(HOST + "player/findUUIDByName", this.oneParamsToMap("name", name));
        Gson gson = new Gson();
        ReturnData returnData = gson.fromJson(result, ReturnData.class);
        return returnData.getData() == null ? "name不存在" : returnData.getData().toString();
    }

    private String findNameByUUID(String uuid){
        String result = UrlUtil.sendPost(HOST + "player/findNameByUUID", this.oneParamsToMap("uuid", uuid));
        Gson gson = new Gson();
        ReturnData returnData = gson.fromJson(result, ReturnData.class);
        return returnData.getData() == null ? "uuid不存在" : returnData.getData().toString();
    }

    private String findScore(String name){
        String result = UrlUtil.sendPost(HOST + "user/findScore", this.oneParamsToMap("name", name));
        Gson gson = new Gson();
        ReturnData returnData = gson.fromJson(result, ReturnData.class);
        return returnData.getData() == null ? "" : returnData.getData().toString();
    }

    private Map<String, String> oneParamsToMap(String key, String value){
        Map data = new HashMap();
        data.put(key, value);
        return data;
    }

}
