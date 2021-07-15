package io.github.wfjsw.DanmakuBukkit;

import io.papermc.lib.PaperLib;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;
import io.github.wfjsw.DanmakuBukkit.Config;

public class DanmakuBukkit extends JavaPlugin implements Listener {
    @Override
    public void onEnable(){
        //Fired when the server enables the plugin
        PaperLib.suggestPaper(this);
        Config.attach(this);
        Config.initDefaults();
        super.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        //Fired when the server stops and disables all plugins
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        return false;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

    }

    @EventHandler
    public void onLoad(ServerLoadEvent event) {

    }

}
