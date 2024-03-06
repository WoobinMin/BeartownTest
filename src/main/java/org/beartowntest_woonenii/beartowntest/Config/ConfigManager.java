package org.beartowntest_woonenii.beartowntest.Config;

import org.beartowntest_woonenii.beartowntest.BeartownTest;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.*;

public class ConfigManager {
    private final BeartownTest plugin = BeartownTest.getPlugin();
    private final String path = plugin.getDataFolder().getAbsolutePath();

    private HashMap<String, ConfigMaker> configSet = new HashMap<>();

    public ConfigManager() {
        configSet.put("player",new ConfigMaker(path,"player.yml"));
        configSet.put("config",new ConfigMaker(path,"config.yml"));
        loadSettings();
        saveConfigs();
    }

    public void reloadConfigs() {
        for (String key : configSet.keySet()){
            plugin.getLogger().info(key);
            configSet.get(key).reloadConfig();
        }
    }

    public void reloadConfig(String fileName){
        configSet.get(fileName).reloadConfig();
    }

    public void saveConfigs(){
        for (String key : configSet.keySet())
            configSet.get(key).saveConfig();
    }

    public void saveConfig(String fileName){
        configSet.get(fileName).saveConfig();
    }

    public FileConfiguration getConfig(String fileName) {
        return configSet.get(fileName).getConfig();
    }

    public String getConfigColorString(String fileName, String path){ return ChatColor.translateAlternateColorCodes('&',
            getConfig(fileName).getString(path));
    }

    public void loadSettings(){
        FileConfiguration defaultconfig = getConfig("config");
        getConfig("player").options().copyDefaults(true);
        defaultconfig.options().copyDefaults(true);

        defaultconfig.addDefault("reward.money", 1000);
        defaultconfig.addDefault("broadcast_message", "핫타임이 시작되어 {player_count}명의 플레이어께서 보상을 획득했습니다!");
        defaultconfig.addDefault("individual_message", "{player} 님! 핫타임으로 {money}원을 받았습니다!");
    }


}