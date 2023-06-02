package com.ulxsth.thebout.models.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public abstract class GameItem {
    protected static Material material;
    protected static String name;
    protected static List<String> lore = new ArrayList<>();
    protected static boolean isUnbreakable = false;

    public static GameItem create(String name) {
        if(name.equals("bokutou")) {
            return new WoodenSword();
        }

        return null;
    }

    public ItemStack getItemStack(int amount) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        itemMeta.setUnbreakable(isUnbreakable);
        itemStack.setItemMeta(itemMeta);
        itemStack.setAmount(amount);
        return itemStack;
    }

    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }

    public boolean isIsUnbreakable() {
        return isUnbreakable;
    }
}
