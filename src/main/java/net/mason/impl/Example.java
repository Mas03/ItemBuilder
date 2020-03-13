package net.mason.impl;

import net.mason.api.builder.FireworkItemBuilder;
import net.mason.api.builder.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class Example {

    public void exampleCode() {

        final ItemStack itemStack = new ItemBuilder<>(Material.SNOW, 1)
                .setName("My itemstack")
                .setLore(Arrays.asList("my", "custom", "lore"))
                .addEnchantment(Enchantment.DIG_SPEED, 5, true)
                .setUnbreakble()
                .get();

        final FireworkEffect.Builder builder = FireworkEffect.builder();

        final ItemStack fireworkStack = new FireworkItemBuilder(1)
                .addEffects(builder.flicker(true)
                                .withColor(Color.FUCHSIA)
                                .build(),
                        builder.with(FireworkEffect.Type.BALL)
                                .trail(true).
                                build())
                .get();
    }
}
