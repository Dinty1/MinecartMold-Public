package net.minecartrapidtransit.minecartmold;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class LetterBanManager extends Thread {

    private final List<String> notBannedLetters = new ArrayList<>(Arrays.asList(
            "a", "b", "c", "d", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
    ));
    private final List<String> bannedLetters = new ArrayList<>();
    private final MinecartMold plugin;

    public LetterBanManager(MinecartMold plugin) {
        this.setName("Letter Ban Manager");
        this.plugin = plugin;
    }

    public void run() {
        Action nextAction = Action.SKIP;
        bannedLetters.add("e");
        while (true) {
            try {
                switch (nextAction) {
                    case BAN_LETTER:
                        int randomIndex = new Random().nextInt(notBannedLetters.size());
                        String letter = notBannedLetters.get(randomIndex);
                        bannedLetters.add(letter);
                        notBannedLetters.remove(randomIndex);

                        this.broadcastMutation();
                        this.plugin.getLogger().info("Banned letter " + letter.toString().toUpperCase());

                        if (bannedLetters.size() >= this.plugin.getConfig().getInt("max-banned-letters"))
                            nextAction = Action.UNBAN_LETTER;
                        else nextAction = Action.SKIP;
                        break;
                    case UNBAN_LETTER:
                        randomIndex = new Random().nextInt(bannedLetters.size());
                        letter = bannedLetters.get(randomIndex);

                        bannedLetters.remove(randomIndex);
                        notBannedLetters.add(letter);

                        this.broadcastMutation();
                        this.plugin.getLogger().info("Unbanned letter " + letter.toString().toUpperCase());

                        nextAction = Action.BAN_LETTER;
                        break;
                    case SKIP:
                        nextAction = Action.BAN_LETTER;
                }

                Thread.sleep(TimeUnit.MINUTES.toMillis(plugin.getConfig().getInt("letter-ban-interval") / 2));
            } catch (InterruptedException e) {
                plugin.getLogger().info("Broke from Letter Ban Manager thread: Sleep interrupted");
            }
        }
    }

    public List<String> getBannedLetters() {
        return this.bannedLetters;
    }

    public String getBannedLettersList() {
        return String.join(", ", this.bannedLetters);
    }

    private void broadcastMutation() {
        Bukkit.broadcastMessage(ChatColor.DARK_GREEN + "The mold has mutated!");

        for (Player player : this.plugin.getServer().getOnlinePlayers()) {
            if (player.hasPermission("science.status")) {
                player.sendMessage(ChatColor.DARK_PURPLE + "Currently banned letters: " + this.getBannedLettersList());
            }
        }
    }

    private enum Action {
        BAN_LETTER,
        UNBAN_LETTER,
        SKIP
    }
}
