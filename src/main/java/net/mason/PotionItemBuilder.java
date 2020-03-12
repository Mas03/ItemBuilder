package net.mason;

import org.bukkit.Color;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;

public interface PotionItemBuilder extends ItemBuilder {

    default ItemBuilder setBasePotionData(PotionData potionData) {
        return applyItemMeta(PotionMeta.class, potionMeta -> potionMeta.setBasePotionData(potionData));
    }

    default ItemBuilder setColor(Color color) {
        return applyItemMeta(PotionMeta.class, potionMeta -> potionMeta.setColor(color));
    }

}
