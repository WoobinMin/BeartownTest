package org.beartowntest_woonenii.beartowntest.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.beartowntest_woonenii.beartowntest.Inventory.HotTimeGUI;

public class OpenInv implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player)
        {
            Player player = (Player)commandSender;
            HotTimeGUI inv = new HotTimeGUI(player);
            inv.open(player);
        }
        return false;

    }
}
