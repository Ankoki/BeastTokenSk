package com.ankoki.beasttokensk.commands;

import com.ankoki.beasttokensk.utils.Utils;
import me.mraxetv.beasttokens.BeastTokensAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BTSKCMD implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!sender.hasPermission("btsk.cmd")) {
            sender.sendMessage(Utils.pCc("&bInsufficient perms to use this command"));
        }
        if (!(args.length == 1)) {
            sender.sendMessage(Utils.pCc("&bThank you for using BeastTokenSk!"));
            sender.sendMessage(Utils.pCc("&bIf you have any issues, report to Ankoki#0001"));
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.pCc("&bThis is only usable if your a player."));
            return true;
        }
        Player p = (Player) sender;
        if (args[0].equalsIgnoreCase("update")) {
            Utils.sendUpdate(p);
            return true;
        }
        else if (args[0].equalsIgnoreCase("docs") || args[0].equalsIgnoreCase("documentation") || args[0].equalsIgnoreCase("doc")) {
            p.sendMessage(Utils.pCc("Here is our documentation on skUnity!"));
            p.spigot().sendMessage(Utils.clickableUrl(Utils.cC("&8[&csk&fUnity&8]"), Utils.cC("&7Click me to visit our documentation!"), "https://docs.skunity.com/syntax/search/addon:BeastTokenSk", ChatColor.BLUE));
            return true;
        }
        else {
            p.sendMessage(Utils.pCc("&7Invalid argument!"));
        }
        return true;
    }
}
