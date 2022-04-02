package net.minecartrapidtransit.minecartmold.listener;

import net.minecartrapidtransit.minecartmold.MinecartMold;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Arrays;
import java.util.StringJoiner;

public class ChatListener implements Listener {

    private final MinecartMold plugin;

    public ChatListener(MinecartMold plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent event) {
        // For some reason it blocks all chat in every chat channel. It's a feature not a bug.
        final String[] splitMessage = event.getMessage().split(" ");
        if (!this.plugin.getConfig().getStringList("commands-to-check").contains(splitMessage[0])) return;

        final String message = String.join(" ", Arrays.copyOfRange(splitMessage, 1, splitMessage.length));
        event.setMessage(splitMessage[0] + " " + removeBannedLetters(message));
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event) {
        event.setMessage(removeBannedLetters(event.getMessage()));
    }

    private String removeBannedLetters(String message) {
        if (!plugin.isActivated()) return message;
        final String[] splitMessage = message.replaceAll("[^\\x00-\\x7F]+", "").split("");
        final StringJoiner newMessageBuilder = new StringJoiner("");
        for (final String letter : splitMessage) {
            if (!plugin.getLetterBanManager().getBannedLetters().contains(StringUtils.stripAccents(letter).toLowerCase())) {
                newMessageBuilder.add(letter);
            }
        }
        return newMessageBuilder.toString();
    }
}
