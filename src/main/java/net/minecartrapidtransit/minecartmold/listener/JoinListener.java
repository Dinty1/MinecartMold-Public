package net.minecartrapidtransit.minecartmold.listener;

import net.minecartrapidtransit.minecartmold.MinecartMold;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    final MinecartMold plugin;

    public JoinListener(MinecartMold plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent event) {
        Bukkit.getScheduler().runTaskLater(this.plugin, () -> {
            if (plugin.isActivated()) event.getPlayer().sendMessage(ChatColor.DARK_GREEN + "You have been infected with a mold spore!");
        }, 20L);
    }
}
