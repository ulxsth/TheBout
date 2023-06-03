package com.ulxsth.thebout;

import com.ulxsth.thebout.commands.TbCommandExecutor;
import com.ulxsth.thebout.listeners.HealItemListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class TheBoutPlugin extends JavaPlugin {
    private static TheBoutPlugin plugin = null;

    public static TheBoutPlugin getInstance() {
        if(plugin == null) {
            throw new IllegalStateException("プラグインが初期化されていません");
        }
        return plugin;
    }

    @Override
    public void onEnable() {
        if(plugin == null) {
            plugin = this;
        }

        // リスナの登録
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new HealItemListener(), this);

        getCommand("tb").setExecutor(new TbCommandExecutor());
    }
}
