package com.ulxsth.thebout.listeners;

import com.ulxsth.thebout.models.items.HealItem;
import com.ulxsth.thebout.models.items.HealItemEnum;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class HealItemListener implements Listener {
    @EventHandler
    public void onPlayerSneak(PlayerToggleSneakEvent event) {
        if(!event.isSneaking()) {
            return;
        }

        Player player = event.getPlayer();
        ItemMeta handItem = player.getInventory().getItemInMainHand().getItemMeta();
        if(handItem == null) {
            return;
        }

        HealItem healItem = HealItemEnum.findByItemMeta(handItem);
        if(healItem == null) {
            return;
        }

        healItem.useItemCountdown(player);
    }
}
