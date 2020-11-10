package com.ankoki.beasttokensk.commands;

import com.ankoki.beasttokensk.utils.Utils;
import me.mraxetv.beasttokens.BeastTokensAPI;
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
            sender.sendMessage(Utils.pCc("&bIf you have any issues, report to jayy#2003"));
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.pCc("&bThis is only usable if your a player."));
            return true;
        }
        Player p = (Player) sender;
        if (args[0].equalsIgnoreCase("debug")) {
            p.sendMessage(Utils.cC("&7[&6BEFORE ADD&7] &bPlayer Tokens: " + BeastTokensAPI.getTokensManger().getTokens(p)));
            BeastTokensAPI.getTokensManger().addTokens(p, 100);
            p.sendMessage(Utils.cC("&7[&6AFTER ADD&7] &bPlayer Tokens: " + BeastTokensAPI.getTokensManger().getTokens(p)));
            p.sendMessage(Utils.cC("&7[&6BEFORE REMOVE&7] &bPlayer Tokens: " + BeastTokensAPI.getTokensManger().getTokens(p)));
            BeastTokensAPI.getTokensManger().removeTokens(p, 50);
            p.sendMessage(Utils.cC("&7[&6AFTER REMOVE&7] &bPlayer Tokens: " + BeastTokensAPI.getTokensManger().getTokens(p)));
            p.sendMessage(Utils.cC("&7[&6BEFORE SET&7] &bPlayer Tokens: " + BeastTokensAPI.getTokensManger().getTokens(p)));
            BeastTokensAPI.getTokensManger().setTokens(p, 25);
            p.sendMessage(Utils.cC("&7[&6AFTER SET&7] &bPlayer Tokens: " + BeastTokensAPI.getTokensManger().getTokens(p)));
            return true;
        }
        if (args[0].equalsIgnoreCase("update")) {
            Utils.sendUpdate(p);
            return true;
        }
        if (args[0].equalsIgnoreCase("gendocs")) {

        }
        return true;
    }
}
