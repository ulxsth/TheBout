package com.ulxsth.thebout.commands;

import com.ulxsth.thebout.models.items.GameItem;
import com.ulxsth.thebout.models.items.WoodenSword;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class TbCommandExecutor implements CommandExecutor {
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
        }
        return true;
    }
}