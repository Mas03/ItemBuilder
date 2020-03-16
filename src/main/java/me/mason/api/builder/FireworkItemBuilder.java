package me.mason.api.builder;

import me.mason.impl.Example;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.inventory.meta.FireworkMeta;
import org.jetbrains.annotations.NotNull;

public final class FireworkItemBuilder extends ItemBuilder<FireworkMeta> {

    /**
     * The Constructor {@link Example} for an example
     *
     * @param amount the amount of the ItemStack
     */
    public FireworkItemBuilder(int amount) {
        super(Material.FIREWORK_ROCKET, amount);
    }

    /**
     * The Constructor {@link Example} for an example
     */
    public FireworkItemBuilder(){
        super(Material.FIREWORK_ROCKET);
    }

    /**
     * @param effect Add another effect to this firework.
     * @return {@link FireworkEffect}
     */
    @NotNull
    public ItemBuilder<FireworkMeta> addEffect(FireworkEffect effect) {
        return applyMeta(fireworkMeta -> fireworkMeta.addEffect(effect));
    }

    /**
     * @param effects Add several firework effects to this firework.
     * @return {@link Iterable<FireworkEffect>}
     */
    @NotNull
    public ItemBuilder<FireworkMeta> addEffects(Iterable<FireworkEffect> effects) {
        return applyMeta(fireworkMeta -> fireworkMeta.addEffects(effects));
    }

    /**
     * @param effects Add several effects to this firework.
     * @return {@link FireworkEffect}
     */
    @NotNull
    public ItemBuilder<FireworkMeta> addEffects(FireworkEffect... effects) {
        return applyMeta(fireworkMeta -> fireworkMeta.addEffects(effects));
    }

    /**
     * @param index Remove an effect from this firework.
     * @return {@link Integer}
     */
    @NotNull
    public ItemBuilder<FireworkMeta> removeEffect(Integer index) {
        return applyMeta(fireworkMeta -> fireworkMeta.removeEffect(index));
    }

    /**
     * @param integer Sets the approximate power of the firework.
     * @return {@link Integer}
     */
    @NotNull
    public ItemBuilder<FireworkMeta> setPower(Integer integer) {
        return applyMeta(fireworkMeta -> fireworkMeta.setPower(integer));
    }

    /**
     * @return {@link FireworkMeta}
     */
    @NotNull
    public ItemBuilder<FireworkMeta> clearEffects() {
        return applyMeta(FireworkMeta::clearEffects);
    }
}
