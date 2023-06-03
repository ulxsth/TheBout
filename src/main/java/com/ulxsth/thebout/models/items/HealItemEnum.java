package com.ulxsth.thebout.models.items;

import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum HealItemEnum {

    BANDAGE(
            Material.WHITE_CANDLE,
            "包帯",
            new ArrayList<String>(Arrays.asList(
                    "医療用の包帯。",
                    "",
                    "§8使用に要する時間：5s"
                    )),
            3,
            3.0
            );

    public final Material material;
    public final String name;
    public final List<String> lore;
    public final int useTime;
    public final double maxHealAmount;

    public final HealItem item;

    HealItemEnum(Material material, String name, List<String> lore, int useTime, double maxHealAmount) {
        this.material = material;
        this.name = name;
        this.lore = lore;
        this.useTime = useTime;
        this.maxHealAmount = maxHealAmount;

        item = new HealItem(this);
    }

    public static HealItem findByItemMeta(ItemMeta itemMeta1) {
        for (HealItemEnum value: HealItemEnum.values()) {
            ItemMeta itemMeta2 = value.item.getItemMeta();
            if(itemMeta1.equals(itemMeta2)) {
                return value.item;
            }
        }

        return null;
    }
}
