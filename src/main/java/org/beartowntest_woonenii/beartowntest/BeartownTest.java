package org.beartowntest_woonenii.beartowntest;

import org.beartowntest_woonenii.beartowntest.Command.GiveAwayAdmin;
import org.beartowntest_woonenii.beartowntest.Command.OpenInv;
import org.beartowntest_woonenii.beartowntest.Command.RemovePermision;
import org.beartowntest_woonenii.beartowntest.Command.TakePermision;
import org.beartowntest_woonenii.beartowntest.Config.ConfigManager;
import org.beartowntest_woonenii.beartowntest.Event.InvClickEvent;
import org.beartowntest_woonenii.beartowntest.Event.JoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class BeartownTest extends JavaPlugin {

    private static BeartownTest plugin;
    private static ConfigManager configManager;
    @Override
    public void onEnable() {
        plugin = this;
        getConfigManager();
        // Plugin startup logic
        getLogger().info("beartown test plugin on");

        getServer().getPluginCommand("giveaway").setExecutor(new OpenInv());
        getServer().getPluginManager().registerEvents(new InvClickEvent(), this);
        getServer().getPluginCommand("giveawayadmin").setExecutor(new GiveAwayAdmin());
        getServer().getPluginCommand("takepermision").setExecutor(new TakePermision());
        getServer().getPluginCommand("removepermision").setExecutor(new RemovePermision());
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("beartown test plugin off");

    }

    public static BeartownTest getPlugin()
    {
        return plugin;
    }

    public static ConfigManager getConfigManager(){
        if(configManager == null)
            configManager = new ConfigManager();
        return configManager;
    }

}
