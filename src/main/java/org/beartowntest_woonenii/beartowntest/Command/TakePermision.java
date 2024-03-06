package org.beartowntest_woonenii.beartowntest.Command;

import net.kyori.adventure.text.Component;
import org.beartowntest_woonenii.beartowntest.BeartownTest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TakePermision implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player)
        {
            Player player = (Player)commandSender;
            FileConfiguration playerConfig = BeartownTest.getConfigManager().getConfig("player");

            if(strings.length == 0) return  false;

            if(strings[0].equalsIgnoreCase("use"))
            {
                playerConfig.set("players."+player.getName()+".giveaway.use", true);
                player.sendMessage(Component.text("/giveaway 권한을 얻었습니다"));
            }
            else if(strings[0].equalsIgnoreCase("admin"))
            {
                playerConfig.set("players."+player.getName()+".giveaway.admin", true);
                player.sendMessage(Component.text("/giveawayadmin 권한을 얻었습니다"));
            }

            BeartownTest.getConfigManager().saveConfig("player");
        }

        return false;
    }
}
