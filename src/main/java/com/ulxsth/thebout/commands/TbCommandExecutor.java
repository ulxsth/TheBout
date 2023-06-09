package com.ulxsth.thebout.commands;

import com.ulxsth.thebout.TheBoutPlugin;
import com.ulxsth.thebout.models.GamePlayer;
import com.ulxsth.thebout.models.items.GameItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class TbCommandExecutor implements CommandExecutor {
    private static final TheBoutPlugin plugin = TheBoutPlugin.getInstance();
    private static final List<String> USAGE = new ArrayList<>(Arrays.asList(
            ""
    ));

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0) {
            // 使い方を表示
            for(String s: USAGE) {
                sender.sendMessage(s);
            }
        }

        String order = args[0];
        if(order.equals("give")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage("§c/tb giveはコンソールからは実行できないよ");
                return true;
            }

            if(args.length == 1) {
                sender.sendMessage("§cつかいかた: /tb give <アイテム名> [個数]");
                return true;
            }

            String name = args[1];
            GameItem item = GameItem.create(name);
            if(item == null) {
                sender.sendMessage("§cそんなアイテムはないよ");
                return true;
            }

            int amount = 1;
            if(args.length >= 3) {
                try {
                    amount = Integer.parseInt(args[2]);
                } catch(NumberFormatException err) {
                    sender.sendMessage("§c[数値]には数値を入力してね");
                    return true;
                }
            }
            ItemStack itemStack = item.getItemStack(amount);
            ((Player)sender).getInventory().addItem(itemStack);
            sender.sendMessage(item.getName() + " を " + amount + " 個渡したよ");
            return true;
        }

        if (order.equals("list")) {
            sender.sendMessage("[参加者]");
            for (Player player: GamePlayer.getParticipants()) {
                String displayName = player.getDisplayName();
                sender.sendMessage("> " + displayName);
            }
            return true;
        }

        if (order.equals("join")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage("§c/tb joinはコンソールからは実行できないよ");
                return true;
            }

            GamePlayer.addPlayer((Player) sender);
            sender.sendMessage("ゲームに参加したよ");
            return true;
        }

        if (order.equals("joinall")) {
            for (Player player: plugin.getServer().getOnlinePlayers()) {
                GamePlayer.addPlayer(player);
                player.sendMessage("ゲームに参加したよ");
            }
            sender.sendMessage("全員をゲームに参加させたよ");
            return true;
        }

        // TODO: テスト
        if(order.equals("remove")) {
            if(args.length == 1) {
                sender.sendMessage("つかいかた: /tb remove <プレイヤーID>");
                return true;
            }

            String userId = args[1];
            Player player = plugin.getServer().getPlayer(userId);
            if(player == null) {
                sender.sendMessage("§cプレイヤーが見つからないよ");
                return true;
            }

            GamePlayer.removePlayer(player);
            sender.sendMessage("プレイヤーを外したよ");
            player.sendMessage("ゲームから抜けたよ");
            return true;
        }

        // TODO: テスト
        if(order.equals("removeall")) {
            GamePlayer.clear();
            plugin.getServer().broadcastMessage("ゲームを解散したよ");
            return true;
        }
        return true;
    }
}
