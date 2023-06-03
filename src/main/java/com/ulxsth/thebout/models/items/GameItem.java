package com.ulxsth.thebout.models.items;

import com.ulxsth.thebout.TheBoutPlugin;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public abstract class GameItem {
    private static final TheBoutPlugin plugin = TheBoutPlugin.getInstance();

    protected static Material material;
    protected static String name;
    protected static List<String> lore = new ArrayList<>();
    protected static boolean isUnbreakable = false;

    public static GameItem create(String name) {
        if(name.equals("bokutou")) {
            return new WoodenSword();
        }
        if(name.equals("houtai")) {
            return new Bandage();
        }

        return null;
    }

    public ItemStack getItemStack() {
        return getItemStack(1);
    }

    public static ItemStack getItemStack(int amount) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        itemMeta.setUnbreakable(isUnbreakable);
        itemStack.setItemMeta(itemMeta);
        itemStack.setAmount(amount);
        return itemStack;
    }

    public ItemMeta getItemMeta() {
        ItemStack itemStack = getItemStack();
        return itemStack.getItemMeta();
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
