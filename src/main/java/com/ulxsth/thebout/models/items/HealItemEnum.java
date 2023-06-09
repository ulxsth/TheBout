package com.ulxsth.thebout.models.items;

import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum HealItemEnum {

    BANDAGE(
            Material.WHITE_CANDLE,
            "§a包帯",
            new ArrayList<String>(Arrays.asList(
                    "医療用の包帯。",
                    "",
                    "§8使用に要する時間：3s"
                    )),
            3.0,
            3.0
            ),
    FIRST_AID_KIT(
            Material.RED_SHULKER_BOX,
            "§a集中治療キット",
            new ArrayList<String>(Arrays.asList(
                    "大きめの鞄に、医療用道具が詰められたもの。",
                    "時間はかかるが、体力を全快できる。",
                    "",
                    "§8使用に要する時間：8s"
            )),
            8.0,
            1000.0
    )
    ;

    public final Material material;
    public final String name;
    public final List<String> lore;
    public final double useTime;
    public final double maxHealAmount;

    public final HealItem item;

    HealItemEnum(Material material, String name, List<String> lore, double useTime, double maxHealAmount) {
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
