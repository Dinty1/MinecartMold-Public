package net.minecartrapidtransit.minecartmold.command;

import net.minecartrapidtransit.minecartmold.MinecartMold;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StatusCommand implements CommandExecutor {

    private final MinecartMold plugin;

    public StatusCommand(MinecartMold plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (this.plugin.isActivated()) {
            sender.sendMessage(ChatColor.RED + "An outbreak is ongoing!");
            sender.sendMessage(ChatColor.RED + "Currently banned letters: " + this.plugin.getLetterBanManager().getBannedLettersList());
        } else {
            sender.sendMessage(ChatColor.RED + "There are no reported mold outbreaks.");
        }

        return true;
    }
}
