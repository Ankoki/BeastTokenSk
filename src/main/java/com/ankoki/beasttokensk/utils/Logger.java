package com.ankoki.beasttokensk.utils;

import com.ankoki.beasttokensk.BeastTokenSk;

public class Logger {

    private BeastTokenSk plugin;

    public Logger(BeastTokenSk plugin) {
        this.plugin = plugin;
    }

    public void log(String message) {
        plugin.getServer().getLogger().info(Utils.cC("&7[&6BTSK&7] &7" + message));
    }

    public void logError(String message) {
        BeastTokenSk.plugin().getServer().getLogger().severe(Utils.cC(message));
    }

    public void dependencyNotFound(String dependency) {
        logError("&c## Fatal Error with BeastTokenSk");
        logError("&c##");
        logError("&c## " + dependency + " was not found");
        logError("&c## Are you sure you have it enabled?");
        logError("&c## Check previous logs for " + dependency + " errors");
        logError("&c##");
        logError("&c## Disabling this plugin...");
    }

    public void outdatedDependency(String dependency, String minVersion) {
        logError("&c## Fatal Error with BeastTokenSk");
        logError("&c## ");
        logError("&c## " + dependency + " is outdated!");
        logError("&c## " + minVersion + " is the minimum version you need");
        logError("&c## for this addon to work.");
        logError("&c##");
        logError("&c## Disabling this plugin...");
    }
}
