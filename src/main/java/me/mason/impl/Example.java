package me.mason.impl;

import me.mason.api.builder.FireworkItemBuilder;
import me.mason.api.builder.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class Example {

    private Example() {
        throw new UnsupportedOperationException("This is an example class and cannot be instantiated.");
    }

    public void exampleCode() {

        final ItemStack itemStack = new ItemBuilder<>(Material.SNOW)
                .setName("My itemstack")
                .setLore(Arrays.asList("my", "custom", "lore"))
                .addEnchantment(Enchantment.DIG_SPEED, 5, true)
                .setUnbreakble()
                .get();

        final FireworkEffect.Builder builder = FireworkEffect.builder();

        final ItemStack fireworkStack = new FireworkItemBuilder()
                .addEffects(builder.flicker(true)
                                .withColor(Color.FUCHSIA)
                                .build(),
                        builder.with(FireworkEffect.Type.BALL)
                                .trail(true).
                                build())
                .get();

        Bukkit.getOnlinePlayers()
                .forEach(player -> player.getInventory().addItem(itemStack, fireworkStack));

    }
}
