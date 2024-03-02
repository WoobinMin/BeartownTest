package org.beartowntest_woonenii.beartowntest.GiveAway;


import org.beartowntest_woonenii.beartowntest.BeartownTest;

import java.util.LinkedList;
import java.util.Queue;

public class GiveAway {
    private static GiveAway instance = new GiveAway();
    private Queue<GiveAwayBukkitRunnable> giveAwayBukkitRunnables = new LinkedList<>();
    private GiveAway() {}

    public static GiveAway getInstance()
    {
        return instance;
    }

    public void GiveAwayRun()
    {
        GiveAwayBukkitRunnable giveAwayBukkitRunnable = new GiveAwayBukkitRunnable(this);
        giveAwayBukkitRunnable.run();
    }

    public void GiveAwayRunAfter(long seconds)
    {
        GiveAwayBukkitRunnable giveAwayBukkitRunnable = new GiveAwayBukkitRunnable(this);
        giveAwayBukkitRunnable.runTaskLater(BeartownTest.getPlugin(), 20L * seconds);
        giveAwayBukkitRunnables.add((giveAwayBukkitRunnable));
    }

    public void GiveAwayCancle()
    {
        if(giveAwayBukkitRunnables.size() == 0) return;
        GiveAwayBukkitRunnable topGABR = giveAwayBukkitRunnables.poll();
        topGABR.cancel();
    }

    public void RemoveGABRInQueue(GiveAwayBukkitRunnable gabr)
    {
        if(this.giveAwayBukkitRunnables.contains((gabr)))
            giveAwayBukkitRunnables.remove(gabr);
    }




}
