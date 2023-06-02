package com.ulxsth.thebout.models.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

    @Override
    public ItemStack getItemStack() {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        itemMeta.setUnbreakable(isUnbreakable);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
