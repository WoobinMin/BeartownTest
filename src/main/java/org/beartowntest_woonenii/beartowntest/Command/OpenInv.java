package org.beartowntest_woonenii.beartowntest.Command;

import org.beartowntest_woonenii.beartowntest.BeartownTest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.beartowntest_woonenii.beartowntest.Inventory.HotTimeGUI;

public class OpenInv implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player)
        {
            Player player = (Player)commandSender;
            FileConfiguration playerConfig = BeartownTest.getConfigManager().getConfig("player");

            if(!playerConfig.getBoolean("players."+player.getName()+".giveaway.use"))
            {
                player.sendMessage(net.kyori.adventure.text.Component.text("권한이 없습니다."));
                return false;
            }

            HotTimeGUI inv = new HotTimeGUI(player);
            inv.open(player);
        }
        return false;

    }
}
