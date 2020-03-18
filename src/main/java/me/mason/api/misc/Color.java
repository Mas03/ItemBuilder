package me.mason.api.misc;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

public class Color {

    private Color() {
        throw new UnsupportedOperationException(
            "This is a utility class and cannot be instantiated.");
    }

    public static String translateColor(@NotNull String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
}
