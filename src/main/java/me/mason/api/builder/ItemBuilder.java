package me.mason.api.builder;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

public class ItemBuilder<T extends ItemMeta> implements Supplier<ItemStack> {

    /**
     * FIELDS
     */
    private final Material material;
    private int amount;
    @NotNull
    private Consumer<T> meta = ($) -> {};

    /**
     * @param material The material that will be used {@link Material}
     * @param amount The amount of the material {@link Integer}
     */
    public ItemBuilder(@NotNull Material material, @NotNull int amount) {
        this.material = material;
        this.amount = amount;
    }

    /**
     * @param material The material that will be used {@link Material}
     */
    public ItemBuilder(@NotNull Material material){
        this.material = material;
        this.amount = 1;
    }

    /**
     * @param name The name of the ItemStack
     * @return {@link String}
     */
    @NotNull
    public ItemBuilder<T> setName(String name) {
        return applyMeta(meta -> meta.setDisplayName(name));
    }

    /**
     * @param lore The List of lores attached to the ItemStack
     * @return {@link List<String>}
     */
    @NotNull
    public ItemBuilder<T> setLore(List<String> lore) {
        return applyMeta(meta -> meta.setLore(lore));
    }

    /**
     * @param line The line you want to remove
     * @return {@link String}
     */
    public ItemBuilder<T> removeLoreLine(String line) {
        if (!get().hasItemMeta()) {
            return this;
        }

        final ItemMeta itemMeta = get().getItemMeta();
        final List<String> lore = itemMeta.hasLore() ? itemMeta.getLore() : new ArrayList<>();

        if (lore.isEmpty() || !lore.contains(line)) {
            return this;
        }

        lore.remove(line);
        return applyMeta(meta -> meta.setLore(lore));
    }

    /**
     * @param index The index of the lore line you want to remove
     * @return {@link String}
     */
    public ItemBuilder<T> removeLoreLine(int index) {
        if (!get().hasItemMeta()) {
            return this;
        }

        final ItemMeta itemMeta = get().getItemMeta();
        final List<String> lore = itemMeta.hasLore() ? itemMeta.getLore() : new ArrayList<>();

        if (index < 0 || index > lore.size() - 1) {
            return this;
        }
        lore.remove(index);
        return applyMeta(meta -> meta.setLore(lore));
    }

    /**
     * @param enchantment The enchantment you want to add
     * @param level The enchantment Level you want to add
     * @param ignoreLevelRestriction whether you want to make the enchantment level higher than natural level.
     * @return {@link Enchantment} {@link Integer} {@link Boolean}
     */
    @NotNull
    public ItemBuilder<T> addEnchantment(Enchantment enchantment, int level, boolean ignoreLevelRestriction) {
        return applyMeta(meta -> meta.addEnchant(enchantment, level, ignoreLevelRestriction));
    }

    /**
     * @param enchantment Removes the specified enchantment from this item meta.
     * @return {@link Enchantment}
     */
    @NotNull
    public ItemBuilder<T> removeEnchantment(Enchantment enchantment) {
        return applyMeta(meta -> meta.removeEnchant(enchantment));
    }

    /**
     * @param itemFlag Set item flags which should be ignored when rendering a ItemStack in the Client.
     * @return {@link ItemFlag}
     */
    @NotNull
    public ItemBuilder<T> addItemFlags(ItemFlag itemFlag) {
        return applyMeta(meta -> meta.addItemFlags(itemFlag));
    }

    /**
     * @param itemFlag Remove specific set of itemFlags.
     * @return {@link ItemFlag}
     */
    @NotNull
    public ItemBuilder<T> removeItemFlags(ItemFlag itemFlag) {
        return applyMeta(meta -> meta.removeItemFlags(itemFlag));
    }

    /**
     * @param attribute Adds an attribute
     * @param attributeModifier Adds its corresponding modifier
     * @return {@link Attribute} {@link AttributeModifier}
     */
    @NotNull
    public ItemBuilder<T> addAttributeModifier(Attribute attribute, AttributeModifier attributeModifier) {
        return applyMeta(meta -> meta.addAttributeModifier(attribute, attributeModifier));
    }

    /**
     * @param attribute Remove all AttributeModifiers associated with the given Attribute.
     * @return {@link Attribute}
     */
    @NotNull
    public ItemBuilder<T> removeAttributes(Attribute attribute) {
        return applyMeta(meta -> meta.removeAttributeModifier(attribute));
    }

    /**
     * @param equipmentSlot Remove all Attributes and AttributeModifiers for a given EquipmentSlot.
     * If the given EquipmentSlot is null, this will remove all AttributeModifiers that do not have an EquipmentSlot set.
     *
     * @return {@link EquipmentSlot}
     */
    @NotNull
    public ItemBuilder<T> removeAttributes(EquipmentSlot equipmentSlot) {
        return applyMeta(meta -> meta.removeAttributeModifier(equipmentSlot));
    }

    /**
     * @param attribute Removes the specific attribute
     * @param attributeModifier removes the specific modifier
     * @return {@link Attribute}, {@link AttributeModifier}
     */
    @NotNull
    public ItemBuilder<T> removeAttributes(Attribute attribute, AttributeModifier attributeModifier) {
        return applyMeta(meta -> meta.removeAttributeModifier(attribute, attributeModifier));
    }

    /**
     * @return {@link Boolean}
     */
    @NotNull
    public ItemBuilder<T> setUnbreakble() {
        return applyMeta(meta -> meta.setUnbreakable(true));
    }

    /**
     * @param meta The meta you want to apply
     * @return {@link ItemMeta}
     */
    @NotNull
    protected ItemBuilder<T> applyMeta(Consumer<T> meta) {
        this.meta = this.meta.andThen(meta);
        return this;
    }

    /**
     * @return {@link ItemStack}
     */
    @Override
    public ItemStack get() {
        final ItemStack itemStack = new ItemStack(material, amount);
        final ItemMeta meta = itemStack.getItemMeta();
        this.meta.accept((T) meta);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
