package net.minecartrapidtransit.minecartmold.command;

import net.minecartrapidtransit.minecartmold.MinecartMold;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StopCommand implements CommandExecutor {

    private final MinecartMold plugin;

    public StopCommand(MinecartMold plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (this.plugin.isActivated()) {
            this.plugin.deactivate();
            Bukkit.broadcastMessage(ChatColor.DARK_GREEN + "The mold outbreak has been contained!");
        } else {
            sender.sendMessage(ChatColor.RED + "Mold is currently contained!");
        }
        return true;
    }
}
