package com.ankoki.beasttokensk;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.ankoki.beasttokensk.commands.BTSKCMD;
import com.ankoki.beasttokensk.elements.events.EvtMythicMobTokenDrop;
import com.ankoki.beasttokensk.utils.Utils;
import me.mraxetv.beasttokens.api.events.tokendrops.mobs.BTMythicMobTokenDropEvent;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class BeastTokenSk extends JavaPlugin {

    private static BeastTokenSk pl;
    private PluginManager pm;
    private SkriptAddon addon;

    @Override
    public void onEnable() {
        long start = System.currentTimeMillis();
        pl = this;
        Utils.log("&aEnabling BeastTokenSk...");
        pm = this.getServer().getPluginManager();
        PluginDescriptionFile desc = getDescription();

        /*
        Dependency checking
        Softdepend on dependencies because i wanna control the messages out
         */
        if (pm.getPlugin("BeastTokens") == null) {
            Utils.dependencyNotFound("BeastTokens", this);
            return;
        }

        if (pm.getPlugin("MythicMobs") != null) {
            mythicMobIntegration();
            Utils.log("&bMythicMobs integration is enabled!");
        }

        if (pm.getPlugin("Skript") != null) {
            addon = Skript.registerAddon(this);
            skRegister();
        } else {
            Utils.dependencyNotFound("Skript", this);
            return;
        }

        this.getServer().getPluginCommand("btsk").setExecutor(new BTSKCMD());

        Utils.log(String.format(Utils.cC("&aSuccessfully enabled BeastTokenSk v%s&a in &b%.2f seconds"), desc.getVersion(), (float) System.currentTimeMillis() - start));
    }

    public static BeastTokenSk plugin() {
        return pl;
    }

    public void skRegister() {
        try {
            addon.loadClasses("com.ankoki.beasttokensk.elements.events");
            addon.loadClasses("com.ankoki.beasttokensk.elements.expressions");
            Utils.log("&bcom.ankoki.beasttokensk.elements &ahas been registered!");
        } catch (IOException ex) {
            ex.printStackTrace();
            pm.disablePlugin(this);
        }
    }

    public void mythicMobIntegration() {
        Utils.log("&bMythicMobs was found! MythicMobs integration is being enabled...");
        Skript.registerEvent("Mythic Event Drop", EvtMythicMobTokenDrop.class, BTMythicMobTokenDropEvent.class, "mythic (mob[s]|monster[s]) token[s] drop");
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
}
