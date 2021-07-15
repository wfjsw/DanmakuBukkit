package io.github.wfjsw.DanmakuBukkit;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

// name => platform, roomID, loadOn(onInit, onPlayer)

public class Config {
    static JavaPlugin plugin;
    static FileConfiguration config;

    public static void attach(JavaPlugin plugin) {
        Config.plugin = plugin;
        Config.config = plugin.getConfig();
    }

    public static void initDefaults() {

        plugin.saveDefaultConfig();
    }

}
