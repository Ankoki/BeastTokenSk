package com.ankoki.beasttokensk;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import ch.njol.skript.util.Version;
import com.ankoki.beasttokensk.commands.BTSKCMD;
import com.ankoki.beasttokensk.elements.conditions.CondHasMaxTokens;
import com.ankoki.beasttokensk.elements.events.EvtMythicMobTokenDrop;
import com.ankoki.beasttokensk.elements.expressions.ExprMaxTokens;
import com.ankoki.beasttokensk.utils.Logger;
import com.ankoki.beasttokensk.utils.Utils;
import me.mraxetv.beasttokens.api.events.tokendrops.mobs.BTMythicMobTokenDropEvent;
import org.bstats.bukkit.Metrics;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

import static ch.njol.skript.conditions.base.PropertyCondition.register;

public class BeastTokenSk extends JavaPlugin {

    private static BeastTokenSk pl;
    private PluginManager pm;
    private SkriptAddon addon;
    private Logger logger;

    @Override
    public void onEnable() {
        long start = System.currentTimeMillis();
        pl = this;
        logger = new Logger(pl);
        logger.log("&aEnabling BeastTokenSk...");
        pm = this.getServer().getPluginManager();
        PluginDescriptionFile desc = getDescription();

        /*
        Dependency checking
        Softdepend on dependencies because i want to control the error handling
         */
        if (pm.getPlugin("BeastTokens") == null) {
            logger.dependencyNotFound("BeastTokens");
            pm.disablePlugin(this);
            return;
        } else if (!(pm.getPlugin("BeastTokens").isEnabled())) {
            logger.dependencyNotFound("BeastTokens");
            pm.disablePlugin(this);
            return;
        }

        if (new Version(pm.getPlugin("BeastTokens").getDescription().getVersion()).isSmallerThan(new Version("3.9.8"))) {
            logger.outdatedDependency("BeastTokens", "3.9.8");
            pm.disablePlugin(this);
            return;
        }

        if (pm.getPlugin("MythicMobs") != null) {
            if (pm.getPlugin("MythicMobs").isEnabled()) {
                mythicMobIntegration();
                logger.log("&bMythicMobs integration is enabled!");
            } else {
                logger.log("&cMythicMobs not found, integration is disabled.");
            }
        } else {
            logger.log("&cMythicMobs not found, integration is disabled.");
        }

        if (pm.getPlugin("Skript") != null) {
            addon = Skript.registerAddon(this);
            skRegister();
        } else {
            logger.dependencyNotFound("Skript");
            pm.disablePlugin(this);
            return;
        }

        logger.log("&aHooking into bStats...");
        if (classExists("org.bstats.bukkit.Metrics")) {
            int pluginId = 9477;
            Metrics metrics = new Metrics(this, pluginId);
            logger.log("&abStats was found! Thank you for helping us monitor our usage across servers!");
        } else {
            logger.log("&abStats was not found. Please consider allowing us to moniter our usage across servers!");
        }

        this.getServer().getPluginCommand("btsk").setExecutor(new BTSKCMD());

        logger.log(String.format(Utils.cC("&aSuccessfully enabled BeastTokenSk v%s&a in &b%.2f seconds"), desc.getVersion(), (float) System.currentTimeMillis() - start));
        logger.log("&aIf you find any issues, please report on the issue tracker, which can be found at &bhttps://github.com/ankoki-dev/BeastTokenSk/issues");
    }

    public static BeastTokenSk plugin() {
        return pl;
    }

    public void skRegister() {
        try {
            addon.loadClasses("com.ankoki.beasttokensk.elements.events");
            addon.loadClasses("com.ankoki.beasttokensk.elements.expressions");
            addon.loadClasses("com.ankoki.beasttokens.elements.conditions");
            register(CondHasMaxTokens.class, PropertyCondition.PropertyType.HAVE, "[the ]max[imum] [beast[ ]]tokens", "players");
            logger.log("&bcom.ankoki.beasttokensk.elements &ahas been registered!");
        } catch (IOException ex) {
            ex.printStackTrace();
            pm.disablePlugin(this);
        }
    }

    public void mythicMobIntegration() {
        logger.log("&bMythicMobs was found! MythicMobs integration is being enabled...");
        Skript.registerEvent("Mythic Event Drop", EvtMythicMobTokenDrop.class, BTMythicMobTokenDropEvent.class, "mythic (mob[[']s]|monster[[']s]) [beast[ ]]token[[']s] drop");
        EventValues.registerEventValue(BTMythicMobTokenDropEvent.class, Player.class, new Getter<Player, BTMythicMobTokenDropEvent>() {
            @Nullable
            @Override
            public Player get(BTMythicMobTokenDropEvent btMythicMobTokenDropEvent) {
                return btMythicMobTokenDropEvent.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(BTMythicMobTokenDropEvent.class, Location.class, new Getter<Location, BTMythicMobTokenDropEvent>() {
            @Nullable
            @Override
            public Location get(BTMythicMobTokenDropEvent btMythicMobTokenDropEvent) {
                return btMythicMobTokenDropEvent.getPlayer().getLocation();
            }
        }, 0);
        EventValues.registerEventValue(BTMythicMobTokenDropEvent.class, World.class, new Getter<World, BTMythicMobTokenDropEvent>() {
            @Nullable
            @Override
            public World get(BTMythicMobTokenDropEvent btMythicMobTokenDropEvent) {
                return btMythicMobTokenDropEvent.getPlayer().getLocation().getWorld();
            }
        }, 0);
    }

    public static boolean classExists(String classExists) {
        try {
            Class.forName(classExists);
            return true;
        } catch(ClassNotFoundException ex) {
            return false;
        }
    }
}
