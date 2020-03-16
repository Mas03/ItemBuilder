package me.mason.api.builder;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import jdk.nashorn.internal.objects.annotations.Setter;
import me.mason.impl.Example;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static java.util.UUID.randomUUID;
import static org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder.encodeString;

public final class SkullItemBuilder extends ItemBuilder<SkullMeta> {

    /**
     * FIELDS
     **/
    @NotNull
    private final String TEXTURES_URL = "http://textures.minecraft.net/texture/";

    /**
     * The Constructor {@link Example} for an example
     *
     * @param amount the amount of the ItemStack
     */
    public SkullItemBuilder(int amount) {
        super(Material.SKELETON_SKULL, amount);
    }

    /**
     * The Constructor {@link Example} for an example
     */
    public SkullItemBuilder() {
        super(Material.SKELETON_SKULL);
    }

    /**
     * Apply an owner to the skull.
     *
     * @param owner Name of the Owner to be applied.
     * @return {@link String}
     * @deprecated [1.12.1+]
     */

    @Setter
    @Deprecated
    public SkullItemBuilder setOwner(@Nullable String owner) {
        if (owner != null) applyMeta(skullMeta -> skullMeta.setOwner(owner));
        return this;
    }

    /**
     * Apply an owner to the skull.
     *
     * @param offlinePlayer OfflinePlayer object to be set.
     * @return {@link OfflinePlayer}
     * @since 1.12.1
     */
    @Setter
    public SkullItemBuilder setOwningPlayer(@Nullable OfflinePlayer offlinePlayer) {
        if (offlinePlayer != null) applyMeta(skullMeta -> skullMeta.setOwningPlayer(offlinePlayer));
        return this;
    }

    /**
     * Apply a texture to the skull.
     *
     * @param texture Texture to be applied.
     * @return {@link String}
     */
    @Setter
    public SkullItemBuilder applyTexture(@Nullable String texture) {
        if (texture != null) applyMeta(skullMeta -> {
            final GameProfile gameProfile = new GameProfile(randomUUID(), null);
            final String encodedTexture = encodeString(String.format(
                    "{textures:{SKIN:{url:\"%s%s\"}}}",
                    TEXTURES_URL,
                    texture
            ));

            final Property property = new Property("textures", encodedTexture);
            gameProfile.getProperties().put("textures", property);

            try {
                final Field field = skullMeta.getClass().getDeclaredField("profile");
                field.setAccessible(true);
                field.set(skullMeta, gameProfile);
            } catch (Exception ex) {
                System.out.println("Failed to set Game Profile.");
            }
        });
        return this;
    }
}
