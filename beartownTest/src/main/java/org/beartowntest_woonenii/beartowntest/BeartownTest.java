package org.beartowntest_woonenii.beartowntest;

import org.beartowntest_woonenii.beartowntest.Command.GiveAwayAdmin;
import org.beartowntest_woonenii.beartowntest.Command.OpenInv;
import org.beartowntest_woonenii.beartowntest.Event.InvClickEvent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class BeartownTest extends JavaPlugin {

    private static BeartownTest plugin;
    FileConfiguration config = this.getConfig();
    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic
        getLogger().info("beartown test plugin on");

        getServer().getPluginCommand("giveaway").setExecutor(new OpenInv());
        getServer().getPluginManager().registerEvents(new InvClickEvent(), this);
        getServer().getPluginCommand("giveawayadmin").setExecutor(new GiveAwayAdmin());
    }

    @Override
    public void onDisable() {
        getLogger().info("beartown test plugin off");

    }

    public static BeartownTest getPlugin()
    {
        return plugin;
    }

}
