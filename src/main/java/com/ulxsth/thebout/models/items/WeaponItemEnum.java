package com.ulxsth.thebout.models.items;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum WeaponItemEnum {
    WOODEN_SWORD(
            Material.WOODEN_SWORD,
            "木刀",
            new ArrayList<String>(Arrays.asList(
                    "普通の木刀。しなやかで振りやすい。"
            )),
            true
            );

    public final Material material;
    public final String name;
    public final List<String> lore;
    public final boolean isUnbreakable;

    public final GameItem item;

    WeaponItemEnum(Material material, String name, List<String> lore, boolean isUnbreakable) {
        this.material = material;
        this.name = name;
        this.lore = lore;
        this.isUnbreakable = isUnbreakable;

        item = new WeaponItem(this);
    }
}
