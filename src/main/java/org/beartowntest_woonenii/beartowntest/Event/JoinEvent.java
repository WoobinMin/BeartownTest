package org.beartowntest_woonenii.beartowntest.Event;

import org.beartowntest_woonenii.beartowntest.BeartownTest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    FileConfiguration playerConfig = BeartownTest.getConfigManager().getConfig("player");

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        String playerName = player.getName();
        //만약 월드 데이터에 플레이어 정보가 없다면
        if(!player.hasPlayedBefore()){
            playerConfig.set("players."+player.getName()+".giveaway.use", false);
            playerConfig.set("players."+player.getName()+".giveaway.admin", false);
        }

        BeartownTest.getConfigManager().saveConfig("player");

    }

}


