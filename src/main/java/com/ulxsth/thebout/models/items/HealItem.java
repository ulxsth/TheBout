package com.ulxsth.thebout.models.items;

import com.ulxsth.thebout.TheBoutPlugin;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class HealItem extends GameItem {
    private static final TheBoutPlugin plugin = TheBoutPlugin.getInstance();

    private final int useTime;
    private final double maxHealAmount;

    public HealItem(HealItemEnum healItem) {
        super(
                healItem.material,
                healItem.name,
                healItem.lore,
                false
        );

        this.useTime = healItem.useTime;
        this.maxHealAmount = healItem.maxHealAmount;
    }

    public void useItemCountdown(Player player) {
        int useTime = this.useTime;

        new BukkitRunnable() {
            int untilTime = useTime;

            @Override
            public void run() {
                if (!player.isOnline()) {
                    cancel();
                }

                if(!player.isSneaking()) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("使用をキャンセルしました"));
                    cancel();
                }

                if(untilTime <= 0) {
                    heal(player);
                    cancel();
                    return;
                }

                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("使用まで " + untilTime + " 秒..."));
                untilTime--;
            }
        }.runTaskTimer(plugin, 0, 20);
    }

    public void heal(Player player) {
        double heal_amount = this.maxHealAmount;
        double playerDamage = player.getHealthScale() - player.getHealth();
        if(playerDamage < this.maxHealAmount) {
            heal_amount = (int)playerDamage;
        }

        player.setHealth(player.getHealth() + heal_amount);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("体力を回復しました！"));
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 30);
    }
}
