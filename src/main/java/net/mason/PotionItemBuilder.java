package net.mason;

import com.sun.istack.internal.NotNull;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;

public class PotionItemBuilder extends ItemBuilder<PotionMeta> {

    public PotionItemBuilder(int amount) {
        super(Material.POTION, amount);
    }

    /**
     * @param potionData
     * @return
     */
    @NotNull
    public ItemBuilder<PotionMeta> setBasePotionData(PotionData potionData) {
        return applyMeta(potionMeta -> potionMeta.setBasePotionData(potionData));
    }

    /**
     * @param color
     * @return
     */
    @NotNull
    public ItemBuilder<PotionMeta> setColor( Color color) {
        return applyMeta(potionMeta -> potionMeta.setColor(color));
    }

}
