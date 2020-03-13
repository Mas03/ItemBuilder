package net.mason;

import com.sun.istack.internal.NotNull;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullItemBuilder extends ItemBuilder<SkullMeta> {

    public SkullItemBuilder(int amount) {
        super(Material.SKELETON_SKULL, amount);
    }

    /**
     * @param offlinePlayer 
     * @return
     */
    @NotNull
    public ItemBuilder<SkullMeta> setOwningPlayer(OfflinePlayer offlinePlayer){
        return applyMeta(skullMeta -> skullMeta.setOwningPlayer(offlinePlayer));
    }


}
