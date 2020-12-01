package com.ankoki.beasttokensk.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import me.mraxetv.beasttokens.api.events.tokendrops.mobs.BTMobTokenDropEvent;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

/* Mob Token Drop
 * Since 1.0
 */
public class EvtMobTokenDrop extends SimpleEvent {

    static {
        Skript.registerEvent("Mob Token Drop", EvtMobTokenDrop.class, BTMobTokenDropEvent.class, "[beast[ ]token[[']s]] (mob[s]|monster[s]) [beast[ ]]token[s] (drop|dropping)");
        EventValues.registerEventValue(BTMobTokenDropEvent.class, Player.class, new Getter<Player, BTMobTokenDropEvent>() {
            @Override
            public Player get(BTMobTokenDropEvent e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(BTMobTokenDropEvent.class, Entity.class, new Getter<Entity, BTMobTokenDropEvent>() {
            @Override
            public Entity get(BTMobTokenDropEvent e) {
                return e.getMobType();
            }
        }, 0);
        EventValues.registerEventValue(BTMobTokenDropEvent.class, Location.class, new Getter<Location, BTMobTokenDropEvent>() {
            @Override
            public Location get(BTMobTokenDropEvent e) {
                return e.getPlayer().getLocation();
            }
        }, 0);
        EventValues.registerEventValue(BTMobTokenDropEvent.class, World.class, new Getter<World, BTMobTokenDropEvent>() {
            @Nullable
            @Override
            public World get(BTMobTokenDropEvent e) {
                return e.getPlayer().getLocation().getWorld();
            }
        }, 0);
    }
}
