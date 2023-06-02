package com.ulxsth.thebout;

import com.ulxsth.thebout.commands.TbCommandExecutor;
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

        getCommand("tb").setExecutor(new TbCommandExecutor());
    }
}
