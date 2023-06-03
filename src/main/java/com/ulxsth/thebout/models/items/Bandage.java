package com.ulxsth.thebout.models.items;

import com.ulxsth.thebout.TheBoutPlugin;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;

public class Bandage extends GameItem {
    private static final TheBoutPlugin plugin = TheBoutPlugin.getInstance();

    static {
        material = Material.WHITE_CANDLE;
        name = "包帯";
        lore = new ArrayList<>(Arrays.asList(
                "医療用の包帯。",
                "",
                "§8使用に要する時間：5s"
        ));
    }

    public static final double MAX_HEAL_AMOUNT = 3.0;
    public static final int USE_TIME = 3;

    public void useItemCountdown(Player player) {
        new BukkitRunnable() {
            private int untilTime = USE_TIME;

            @Override
            public void run() {
                if(untilTime <= 0 || !player.isOnline()) {
                    use(player);
                    cancel();
                    return;
                }

                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("使用まで " + untilTime + " 秒..."));
                untilTime--;
            }
        }.runTaskTimer(plugin, 0, 20);
    }

    public static void use(Player player) {
        double heal_amount = MAX_HEAL_AMOUNT;
        double playerDamage = player.getHealthScale() - player.getHealth();
        if(playerDamage < MAX_HEAL_AMOUNT) {
            heal_amount = (int)playerDamage;
        }

        player.setHealth(player.getHealth() + heal_amount);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("体力を回復しました！"));
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 30);
    }
}
