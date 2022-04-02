package net.minecartrapidtransit.minecartmold.command;

import net.minecartrapidtransit.minecartmold.MinecartMold;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    private final MinecartMold plugin;

    public ReloadCommand(MinecartMold plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        this.plugin.reloadConfig();
        sender.sendMessage(ChatColor.GREEN + "Config reloaded!");
        return true;
    }
}
