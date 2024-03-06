package org.beartowntest_woonenii.beartowntest;

import net.kyori.adventure.text.Component;
import org.beartowntest_woonenii.beartowntest.DB.DBConnector;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemManager {
    public static ItemStack BuildItem(Material type, int amount, String displayName, String... lore)
    {
        ItemStack stack = new ItemStack(type, amount);
        ItemMeta meta = stack.getItemMeta();
        meta.displayName(Component.text(displayName));
        meta.setLore(Arrays.asList(lore));
        stack.setItemMeta(meta);
        return stack;
    }


    public static final ItemStack hotTimeExplain = BuildItem(Material.DIAMOND, 1, "핫타임이란?"
            , "접속해있는 유저에게 보상을 주는 것");

    public static final ItemStack hotTimeReward = BuildItem(Material.EMERALD, 1, "핫타임 보상"
            , "현재 설정된 핫타임 보상 금액 : " + BeartownTest.getPlugin().getConfig().getString("reward.money"));

}
