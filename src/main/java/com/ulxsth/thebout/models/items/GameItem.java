package com.ulxsth.thebout.models.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class GameItem {
    protected static Material material;
    protected static String name;
    protected static List<String> lore = new ArrayList<>();
    protected static boolean isUnbreakable = false;

    public abstract ItemStack getItemStack();

    public static String getName() {
        return name;
    }

    public static List<String> getLore() {
        return lore;
    }

    public static boolean isIsUnbreakable() {
        return isUnbreakable;
    }
}
