package net.mason;

import com.sun.istack.internal.NotNull;
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

public class ItemBuilder<T extends ItemMeta> implements Supplier<ItemStack> {

    private final Material material;
    private final int amount;
    private Consumer<T> meta;


    /**
     * @param material
     * @param amount
     */
    public ItemBuilder(@NotNull Material material, @NotNull int amount) {
        this.material = material;
        this.amount = amount;
    }

    /**
     * @param name
     * @return the ItemStack display name
     */
    @NotNull
    public ItemBuilder<T> setName(String name) {
        return applyMeta(meta -> meta.setDisplayName(name));
    }

    /**
     * @param lore
     * @return
     */
    @NotNull
    public ItemBuilder<T> setLore(List<String> lore) {
        return applyMeta(meta -> meta.setLore(lore));
    }

    /**
     * @param line
     * @return
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
     * @param index
     * @return
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
     * @param enchantment
     * @param amount
     * @param var
     * @return
     */
    @NotNull
    public ItemBuilder<T> addEnchantment(Enchantment enchantment, int amount, boolean var) {
        return applyMeta(meta -> meta.addEnchant(enchantment, amount, var));
    }

    /**
     * @param enchantment
     * @return
     */
    @NotNull
    public ItemBuilder<T> removeEnchantment(Enchantment enchantment) {
        return applyMeta(meta -> meta.removeEnchant(enchantment));
    }

    /**
     * @param itemFlag
     * @return
     */
    @NotNull
    public ItemBuilder<T> addItemFlags(ItemFlag itemFlag) {
        return applyMeta(meta -> meta.addItemFlags(itemFlag));
    }

    /**
     * @param itemFlag
     * @return
     */
    @NotNull
    public ItemBuilder<T> removeItemFlags(ItemFlag itemFlag) {
        return applyMeta(meta -> meta.removeItemFlags(itemFlag));
    }

    /**
     * @param attribute
     * @param attributeModifier
     * @return
     */
    @NotNull
    public ItemBuilder<T> addAttributes(Attribute attribute, AttributeModifier attributeModifier) {
        return applyMeta(meta -> meta.addAttributeModifier(attribute, attributeModifier));
    }

    /**
     * @param attribute
     * @return
     */
    @NotNull
    public ItemBuilder<T> removeAttributes(Attribute attribute) {
        return applyMeta(meta -> meta.removeAttributeModifier(attribute));
    }

    /**
     * @param equipmentSlot
     * @return
     */
    @NotNull
    public ItemBuilder<T> removeAttributes(EquipmentSlot equipmentSlot) {
        return applyMeta(meta -> meta.removeAttributeModifier(equipmentSlot));
    }

    /**
     * @param attribute
     * @param attributeModifier
     * @return
     */
    @NotNull
    public ItemBuilder<T> removeAttributes(Attribute attribute, AttributeModifier attributeModifier) {
        return applyMeta(meta -> meta.removeAttributeModifier(attribute, attributeModifier));
    }

    /**
     * @return
     */
    @NotNull
    public ItemBuilder<T> setUnbreakble() {
        return applyMeta(meta -> meta.setUnbreakable(true));
    }

    /**
     * @param meta
     * @return
     */
    @NotNull
    protected ItemBuilder<T> applyMeta(Consumer<T> meta) {
        this.meta = meta;
        return this;
    }

    /**
     * @return
     */
    @Override
    public ItemStack get() {
        final ItemMeta meta = this.get().getItemMeta();
        this.meta.accept((T) meta);
        get().setItemMeta(meta);
        return new ItemStack(material, amount);
    }
}
