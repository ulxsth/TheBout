package com.ulxsth.thebout.models.items;

import com.ulxsth.thebout.TheBoutPlugin;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public abstract class GameItem {
    private static final TheBoutPlugin plugin = TheBoutPlugin.getInstance();

    protected Material material;
    protected String name;
    protected List<String> lore;
    protected boolean isUnbreakable;

    public GameItem(Material material, String name, List<String> lore, boolean isUnbreakable) {
        this.material = material;
        this.name = name;
        this.lore = lore;
        this.isUnbreakable = isUnbreakable;
    }

    public static GameItem create(String name) {
        if(name.equals("bokutou")) {
            return new WeaponItem(WeaponItemEnum.WOODEN_SWORD);
        }
        if(name.equals("houtai")) {
            return new HealItem(HealItemEnum.BANDAGE);
        }

        return null;
    }

    public ItemStack getItemStack() {
        return getItemStack(1);
    }

    public ItemStack getItemStack(int amount) {
        ItemStack itemStack = new ItemStack(this.material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.name);
        itemMeta.setLore(this.lore);
        itemMeta.setUnbreakable(this.isUnbreakable);
        itemStack.setItemMeta(itemMeta);
        itemStack.setAmount(amount);
        return itemStack;
    }

    public ItemMeta getItemMeta() {
        ItemStack itemStack = getItemStack();
        return itemStack.getItemMeta();
    }

    public String getName() {
        return this.name;
    }

    public List<String> getLore() {
        return this.lore;
    }

    public boolean isIsUnbreakable() {
        return this.isUnbreakable;
    }
}
