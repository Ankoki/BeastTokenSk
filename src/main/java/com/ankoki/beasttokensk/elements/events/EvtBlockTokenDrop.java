package com.ankoki.beasttokensk.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import me.mraxetv.beasttokens.api.events.tokendrops.blocks.BTBlockTokenDropEvent;
import org.jetbrains.annotations.Nullable;

/* Block Token Drop
 * Since 1.0
 */
public class EvtBlockTokenDrop extends SimpleEvent {

    static {
        Skript.registerEvent("Block Token Drop", EvtBlockTokenDrop.class, BTBlockTokenDropEvent.class, "[beast[ ]token[[']s]] block [beast[ ]]token[s] (drop|dropping)");
        EventValues.registerEventValue(BTBlockTokenDropEvent.class, Player.class, new Getter<Player, BTBlockTokenDropEvent>() {
            @Override
            public Player get(BTBlockTokenDropEvent e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(BTBlockTokenDropEvent.class, Block.class, new Getter<Block, BTBlockTokenDropEvent>() {
            @Override
            public Block get(BTBlockTokenDropEvent e) {
                return e.getBlock();
            }
        }, 0);
        EventValues.registerEventValue(BTBlockTokenDropEvent.class, Location.class, new Getter<Location, BTBlockTokenDropEvent>() {
            @Override
            public Location get(BTBlockTokenDropEvent e) {
                return e.getBlock().getLocation();
            }
        }, 0);
        EventValues.registerEventValue(BTBlockTokenDropEvent.class, World.class, new Getter<World, BTBlockTokenDropEvent>() {
            @Nullable
            @Override
            public World get(BTBlockTokenDropEvent btBlockTokenDropEvent) {
                return btBlockTokenDropEvent.getBlock().getLocation().getWorld();
            }
        }, 0);
    }

    @Override
    public boolean check(Event e) {
       return true;
    }
}