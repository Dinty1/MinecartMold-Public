package net.minecartrapidtransit.minecartmold;

import net.minecartrapidtransit.minecartmold.command.ReloadCommand;
import net.minecartrapidtransit.minecartmold.command.StartCommand;
import net.minecartrapidtransit.minecartmold.command.StatusCommand;
import net.minecartrapidtransit.minecartmold.command.StopCommand;

import net.minecartrapidtransit.minecartmold.listener.ChatListener;
import net.minecartrapidtransit.minecartmold.listener.JoinListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinecartMold extends JavaPlugin {

    private LetterBanManager letterBanManager;
    private boolean activated = false;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.letterBanManager = new LetterBanManager(this);

        this.getCommand("startmoldoutbreak").setExecutor(new StartCommand(this));
        this.getCommand("stopmoldoutbreak").setExecutor(new StopCommand(this));
        this.getCommand("reloadmoldconfig").setExecutor(new ReloadCommand(this));
        this.getCommand("moldstatus").setExecutor(new StatusCommand(this));

        this.getServer().getPluginManager().registerEvents(new ChatListener(this), this);
        this.getServer().getPluginManager().registerEvents(new JoinListener(this), this);
    }

    public void onDisable() {
        // Kill LetterBanManager
        this.letterBanManager.interrupt();
    }

    public LetterBanManager getLetterBanManager() {
        return this.letterBanManager;
    }

    public boolean isActivated() {
        return this.activated;
    }

    public void activate() {
        this.getLetterBanManager().start();
        this.activated = true;
    }

    public void deactivate() {
        this.letterBanManager.interrupt();
        this.letterBanManager.getBannedLetters().clear();
        this.activated = false;
    }
}
