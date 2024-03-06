package org.beartowntest_woonenii.beartowntest.Command;

import org.beartowntest_woonenii.beartowntest.BeartownTest;
import org.beartowntest_woonenii.beartowntest.GiveAway.GiveAway;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class GiveAwayAdmin implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player)
        {
            Player player = (Player)commandSender;
            FileConfiguration playerConfig = BeartownTest.getConfigManager().getConfig("player");

            if(!playerConfig.getBoolean("players."+player.getName()+".giveaway.admin"))
            {
                player.sendMessage(net.kyori.adventure.text.Component.text("권한이 없습니다."));
                return false;
            }

            if(strings.length == 0) return  false;

            if(strings[0].equalsIgnoreCase("run"))
            {
                GiveAway.getInstance().GiveAwayRun();
            }
            else if(strings[0].equalsIgnoreCase("runafter"))
            {
                if(strings.length < 2) return false;

                try
                {
                    GiveAway.getInstance().GiveAwayRunAfter(Integer.parseInt(strings[1]));
                }
                catch (Exception e)
                {

                }

            }
            else if(strings[0].equalsIgnoreCase("cancle"))
            {
                GiveAway.getInstance().GiveAwayCancle();
            }
        }

        return false;
    }
}
