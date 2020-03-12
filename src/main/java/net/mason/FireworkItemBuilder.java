package net.mason;

import org.bukkit.FireworkEffect;
import org.bukkit.inventory.meta.FireworkMeta;

public interface FireworkItemBuilder extends ItemBuilder {

    default ItemBuilder addEffect(FireworkEffect effect) {
        return applyItemMeta(FireworkMeta.class, fireworkMeta -> fireworkMeta.addEffect(effect));
    }

    default ItemBuilder addEffects(Iterable<FireworkEffect> effects) {
        return applyItemMeta(FireworkMeta.class, fireworkMeta -> fireworkMeta.addEffects(effects));
    }

    default ItemBuilder addEffects(FireworkEffect... effects) {
        return applyItemMeta(FireworkMeta.class, fireworkMeta -> fireworkMeta.addEffects(effects));
    }

    default ItemBuilder removeEffect(Integer integer) {
        return applyItemMeta(FireworkMeta.class, fireworkMeta -> fireworkMeta.removeEffect(integer));
    }

    default ItemBuilder setPower(Integer integer) {
        return applyItemMeta(FireworkMeta.class, fireworkMeta -> fireworkMeta.setPower(integer));
    }

    default ItemBuilder clearEffects() {
        return applyItemMeta(FireworkMeta.class, FireworkMeta::clearEffects);
    }
}
