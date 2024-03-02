package org.beartowntest_woonenii.beartowntest.Inventory;

import org.beartowntest_woonenii.beartowntest.DB.DBConnector;
import org.beartowntest_woonenii.beartowntest.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

public class HotTimeGUI implements Listener {
    private final Inventory inv;

    private void initItemSetting(Player player)
    {
        inv.setItem(0, ItemManager.hotTimeExplain);
        inv.setItem(1,ItemManager.hotTimeReward);
        var item = ItemManager.BuildItem(Material.CLOCK, 1, "핫타임 횟수",
                "핫타임 받은 횟수는 : " + DBConnector.GetUserHotTimeCount(player.getName()));
        inv.setItem(2, item);
    }

    public HotTimeGUI(Player player)
    {
        this.inv = Bukkit.createInventory(null,9,"HOTTIME");
        initItemSetting(player);
    }

    public void open(Player player)
    {
        player.openInventory(inv);
    }
}
