package net.mason.api.builder;

import com.sun.istack.internal.NotNull;
import net.mason.impl.Example;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;

public final class PotionItemBuilder extends ItemBuilder<PotionMeta> {

    /**
     * The Constructor {@link Example} for an example
     *
     * @param amount the amount of an ItemStack
     */
    public PotionItemBuilder(int amount) {
        super(Material.POTION, amount);
    }

    /**
     * @param potionData the data you input
     * @return {@link PotionData}
     */
    @NotNull
    public ItemBuilder<PotionMeta> setBasePotionData(PotionData potionData) {
        return applyMeta(potionMeta -> potionMeta.setBasePotionData(potionData));
    }

    /**
     * @param color the Color of a potion
     * @return {@link Color}
     */
    @NotNull
    public ItemBuilder<PotionMeta> setColor(Color color) {
        return applyMeta(potionMeta -> potionMeta.setColor(color));
    }

}