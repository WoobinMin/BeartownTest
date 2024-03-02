package org.beartowntest_woonenii.beartowntest.GiveAway;

import net.kyori.adventure.text.Component;
import org.beartowntest_woonenii.beartowntest.BeartownTest;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

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
        for(Player player : players)
        {
            player.sendMessage(Component.text(plugin.getConfig()
                    .getString("individual_message")
                    .replace("{player}", player.getName())
                    .replace("{money}", plugin.getConfig().getString("reward.money"))));

        }
        giveAway.RemoveGABRInQueue(this);
    }
}