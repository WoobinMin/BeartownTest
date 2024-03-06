package org.beartowntest_woonenii.beartowntest.GiveAway;

import net.kyori.adventure.text.Component;
import org.beartowntest_woonenii.beartowntest.BeartownTest;
import org.beartowntest_woonenii.beartowntest.DB.DBConnector;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public  class GiveAwayBukkitRunnable extends BukkitRunnable
{
    private GiveAway giveAway;
    public GiveAwayBukkitRunnable(GiveAway giveAway)
    {
        this.giveAway = giveAway;
    }

    public void run() {
        BeartownTest.getPlugin().getLogger().info("GiveAway Excute Run");
        var plugin = BeartownTest.getPlugin();
        var players = plugin.getServer().getOnlinePlayers();
        plugin.getServer().broadcast(Component.text(plugin.getConfig()
                .getString("broadcast_message")
                .replace("{player_count}", String.valueOf(players.size()))));

        List<String> playerIPAddresses = new ArrayList<>();
        for(Player player : players)
        {
            var playerIP = player.getAddress().getAddress().getHostAddress();
            if(playerIPAddresses.contains(playerIP)) continue;
            playerIPAddresses.add(playerIP);
            player.sendMessage(Component.text(plugin.getConfig()
                    .getString("individual_message")
                    .replace("{player}", player.getName())
                    .replace("{money}", plugin.getConfig().getString("reward.money"))));

            DBConnector.incrementHotTimeValue(player.getName());

        }
        giveAway.RemoveGABRInQueue(this);
    }
}