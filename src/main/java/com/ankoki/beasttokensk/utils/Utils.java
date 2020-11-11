package com.ankoki.beasttokensk.utils;

import com.ankoki.beasttokensk.BeastTokenSk;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

public class Utils {

    public static void log(String s) {
        BeastTokenSk.plugin().getServer().getLogger().info(cC("&7[&6BTSK&7] " + s));
    }

    public static void logError(String message) {
        BeastTokenSk.plugin().getServer().getLogger().severe(Utils.cC(message));
    }

    public static String cC(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static void dependencyNotFound(String dependency, BeastTokenSk plugin) {
        Utils.logError("&c## Fatal Error with BeastTokenSk");
        Utils.logError("&c##");
        Utils.logError("&c## " + dependency + " was not found");
        Utils.logError("&c## Are you sure you have it enabled?");
        Utils.logError("&c## Check previous logs for " + dependency + " errors");
        Utils.logError("&c##");
        Utils.logError("&c## Disabling this plugin...");
        plugin.getServer().getPluginManager().disablePlugin(plugin);
    }

    public static String pCc(String s) {
        return ChatColor.translateAlternateColorCodes('&', "&7[&6BTSK&7] " + s);
    }

    public static void sendUpdate(Player p) {
        p.sendMessage(Utils.pCc("&bClick one of these links to find out if there is an update!"));
        p.sendMessage(" ");
        TextComponent spigot = new TextComponent(Utils.cC("            &7[&6SPIGOT&7]"));
        spigot.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me to visit Spigot!").color(net.md_5.bungee.api.ChatColor.GRAY).italic(true).create()));
        spigot.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.ankoki.com"));
        TextComponent github = new TextComponent(Utils.cC("                  &7[&6GITHUB&7]"));
        github.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me to visit Github!").color(net.md_5.bungee.api.ChatColor.GRAY).italic(true).create()));
        github.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.github.com/ankoki-dev/BeastTokenSk/releases"));
        github.addExtra(spigot);
        p.spigot().sendMessage(github);
        PluginDescriptionFile desc = BeastTokenSk.plugin().getDescription();
        p.sendMessage(Utils.cC("\n&7[&6BTSK&7] &bYou are currently running BeastTokenSk v" + desc.getVersion()));
    }

}
