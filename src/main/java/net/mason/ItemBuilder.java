package net.mason;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.banner.Pattern;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.util.Consumer;

import java.util.ArrayList;
import java.util.List;

public interface ItemBuilder extends Cloneable {

    static ItemBuilder of(ItemStack itemStack) {
        return () -> itemStack;
    }

    static ItemBuilder of(Material material) {
        return of(material, 1);
    }

    static ItemBuilder of(Material material, Integer amount) {
        return of(new ItemStack(material, amount));
    }

    ItemStack item();

    default ItemBuilder setName(String name) {
        return applyItemMeta(ItemMeta.class, itemMeta -> itemMeta.setDisplayName(name));
    }

    default ItemBuilder setLore(List<String> lore) {
        return applyItemMeta(ItemMeta.class, itemMeta -> itemMeta.setLore(lore));
    }

    default ItemBuilder removeLoreLine(String line) {
        if (!item().hasItemMeta()) {
            return this;
        }

        final ItemMeta meta = item().getItemMeta();
        final List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();

        if (lore.isEmpty() || !lore.contains(line)) {
            return this;
        }

        lore.remove(line);
        return applyItemMeta(ItemMeta.class, itemMeta -> itemMeta.setLore(lore));
    }

    default ItemBuilder removeLoreLine(int index) {
        if (!item().hasItemMeta()) {
            return this;
        }

        final ItemMeta meta = item().getItemMeta();
        final List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();

        if (index < 0 || index > lore.size() - 1) {
            return this;
        }

        lore.remove(index);
        return applyItemMeta(ItemMeta.class, itemMeta -> itemMeta.setLore(lore));
    }

    default ItemBuilder addEnchantment(Enchantment enchantment, Integer amount, boolean var) {
        return applyItemMeta(ItemMeta.class, itemMeta -> itemMeta.addEnchant(enchantment, amount, var));
    }

    default ItemBuilder removeEnchantment(Enchantment enchantment) {
        return applyItemMeta(ItemMeta.class, itemMeta -> itemMeta.removeEnchant(enchantment));
    }

    default ItemBuilder setInfiniteDurability() {
        return applyItemMeta(ItemMeta.class, itemMeta -> itemMeta.setUnbreakable(true));
    }

    default ItemBuilder setSkullOwner(OfflinePlayer player) {
        return applyItemMeta(SkullMeta.class, itemMeta -> itemMeta.setOwningPlayer(player));
    }

    default ItemBuilder setLeatherArmorColor(Color color) {
        return applyItemMeta(LeatherArmorMeta.class, meta -> meta.setColor(color));
    }

    default ItemBuilder setBannerPattern(Pattern pattern) {
        return applyItemMeta(BannerMeta.class, bannerMeta -> bannerMeta.addPattern(pattern));
    }

    default ItemBuilder addItemFlags(ItemFlag itemFlag) {
        return applyItemMeta(ItemMeta.class, itemMeta -> itemMeta.addItemFlags(itemFlag));
    }

    default ItemBuilder removeItemFlags(ItemFlag itemFlag) {
        return applyItemMeta(ItemMeta.class, itemMeta -> itemMeta.removeItemFlags(itemFlag));
    }
    
    default ItemBuilder addAttributes(Attribute attribute, AttributeModifier attributeModifier) {
        return applyItemMeta(ItemMeta.class, itemMeta -> itemMeta.addAttributeModifier(attribute, attributeModifier));
    }

    default ItemBuilder removeAttributes(Attribute attribute) {
        return applyItemMeta(ItemMeta.class, itemMeta -> itemMeta.removeAttributeModifier(attribute));
    }

    default ItemBuilder removeAttributes(EquipmentSlot equipmentSlot) {
        return applyItemMeta(ItemMeta.class, itemMeta -> itemMeta.removeAttributeModifier(equipmentSlot));
    }

    default ItemBuilder removeAttributes(Attribute attribute, AttributeModifier attributeModifier) {
        return applyItemMeta(ItemMeta.class, itemMeta -> itemMeta.removeAttributeModifier(attribute, attributeModifier));
    }

    default <T> ItemBuilder applyItemMeta(Class<T> clazz, Consumer<T> metaAction) {
        if (!item().hasItemMeta()) {
            return this;
        }

        try {
            final ItemMeta itemMeta = item().getItemMeta();
            final T value = clazz.cast(itemMeta);
            metaAction.accept(value);

        } catch (RuntimeException exception) {
            throw new RuntimeException(exception);
        }
        return this;

    }
}
