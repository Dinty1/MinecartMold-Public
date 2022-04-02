package net.minecartrapidtransit.minecartmold.command;

import net.minecartrapidtransit.minecartmold.MinecartMold;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartCommand implements CommandExecutor {

    private final MinecartMold plugin;

    public StartCommand(MinecartMold plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!this.plugin.isActivated()) {
            this.plugin.activate();
            Bukkit.broadcastMessage(ChatColor.DARK_GREEN + "You have been infected with a mold spore!");
        } else {
            sender.sendMessage(ChatColor.RED + "An outbreak is ongoing!");
        }
        return true;
    }
}
