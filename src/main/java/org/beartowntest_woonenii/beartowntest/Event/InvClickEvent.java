package org.beartowntest_woonenii.beartowntest.Event;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InvClickEvent implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e)
    {
        if(e.getClickedInventory() == null)
            return;

        if(e.getView().getTitle().equalsIgnoreCase("HOTTIME"))
        {
            e.setCancelled(true);
        }

    }

}
