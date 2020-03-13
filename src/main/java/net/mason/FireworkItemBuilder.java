package net.mason;

import com.sun.istack.internal.NotNull;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.inventory.meta.FireworkMeta;

public class FireworkItemBuilder extends ItemBuilder<FireworkMeta> {

    public FireworkItemBuilder(int amount) {
        super(Material.FIREWORK_ROCKET, amount);
    }

    /**
     * @param effect
     * @return
     */
    @NotNull
    public ItemBuilder<FireworkMeta> addEffect(FireworkEffect effect) {
        return applyMeta(fireworkMeta -> fireworkMeta.addEffect(effect));
    }

    /**
     * @param effects
     * @return
     */
    @NotNull
    public ItemBuilder<FireworkMeta> addEffects(Iterable<FireworkEffect> effects) {
        return applyMeta(fireworkMeta -> fireworkMeta.addEffects(effects));
    }

    /**
     * @param effects
     * @return
     */
    @NotNull
    public ItemBuilder<FireworkMeta> addEffects(FireworkEffect... effects) {
        return applyMeta(fireworkMeta -> fireworkMeta.addEffects(effects));
    }

    /**
     * @param integer
     * @return
     */
    @NotNull
    public ItemBuilder<FireworkMeta> removeEffect(Integer integer) {
        return applyMeta(fireworkMeta -> fireworkMeta.removeEffect(integer));
    }

    /**
     * @param integer
     * @return
     */
    @NotNull
    public ItemBuilder<FireworkMeta> setPower(Integer integer) {
        return applyMeta(fireworkMeta -> fireworkMeta.setPower(integer));
    }

    /**
     * @return
     */
    @NotNull
    public ItemBuilder<FireworkMeta> clearEffects() {
        return applyMeta(FireworkMeta::clearEffects);
    }
}
