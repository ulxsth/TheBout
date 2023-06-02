package com.ulxsth.thebout.models.items;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;

public class WoodenSword extends GameItem {
    static {
        material = Material.WOODEN_SWORD;
        name = "木刀";
        lore = new ArrayList<>(Arrays.asList(
                "普通の木刀。しなやかで振りやすい。"
        ));
        isUnbreakable = true;
    }
}
