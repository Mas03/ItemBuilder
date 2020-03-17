package me.mason.impl;

import me.mason.api.builder.FireworkItemBuilder;
import me.mason.api.builder.ItemBuilder;
import me.mason.api.builder.SkullItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class Example {

    public Example() {
        exampleCode();
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

        final ItemStack skullStack = new SkullItemBuilder()
            .setOwningPlayer(Bukkit.getOnlinePlayers().parallelStream()
            .findFirst().get())
            .setLore(Arrays.asList("A masterpiece from heaven", "God's mightiest skull"))
            .get();

        Bukkit.getOnlinePlayers()
                .forEach(player -> player.getInventory().addItem(itemStack, fireworkStack, skullStack));

    }
}
