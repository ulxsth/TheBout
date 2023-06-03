package com.ulxsth.thebout.models.items;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeaponItem extends GameItem {
    public WeaponItem(WeaponItemEnum item) {
        super(
                item.material,
                item.name,
                item.lore,
                item.isUnbreakable
        );
    }
}
