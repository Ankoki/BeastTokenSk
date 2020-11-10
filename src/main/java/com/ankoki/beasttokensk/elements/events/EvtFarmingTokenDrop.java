package com.ankoki.beasttokensk.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import me.mraxetv.beasttokens.api.events.tokendrops.blocks.BTBlockTokenDropEvent;
import me.mraxetv.beasttokens.api.events.tokendrops.farming.BTFarmingTokenDropEvent;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Farming Token Drop")
@Description("Fired when a crop drops BeastTokens")
@Examples({"on farming token drop:" +
        "    if player has permission \"tokens.gain\":",
        "        send \"You broke %event-block% and recieved %event-tokens% tokens\"",
        "        stop",
        "    cancel event",
        "    send \"You can't earn tokens until you have unlocked this!\""})
@Since("1.0")
@RequiredPlugins("BeastTokens")
public class EvtFarmingTokenDrop extends SimpleEvent {

    static {
        Skript.registerEvent("Farming Token Drop", EvtFarmingTokenDrop.class, BTFarmingTokenDropEvent.class, "[(beasttokens|beast tokens)] (farm[ing]|crop[s]) token[s] (drop|dropping)");
        EventValues.registerEventValue(BTFarmingTokenDropEvent.class, Player.class, new Getter<Player, BTFarmingTokenDropEvent>() {
            @Override
            public Player get(BTFarmingTokenDropEvent e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(BTFarmingTokenDropEvent.class, Block.class, new Getter<Block, BTFarmingTokenDropEvent>() {
            @Override
            public Block get(BTFarmingTokenDropEvent e) {
                return e.getBlock();
            }
        }, 0);
        EventValues.registerEventValue(BTFarmingTokenDropEvent.class, Location.class, new Getter<Location, BTFarmingTokenDropEvent>() {
            @Override
            public Location get(BTFarmingTokenDropEvent e) {
                return e.getBlock().getLocation();
            }
        }, 0);
        EventValues.registerEventValue(BTFarmingTokenDropEvent.class, World.class, new Getter<World, BTFarmingTokenDropEvent>() {
            @Nullable
            @Override
            public World get(BTFarmingTokenDropEvent e) {
                return e.getBlock().getLocation().getWorld();
            }
        }, 0);
    }

    @Override
    public boolean check(Event e) {
        return true;
    }
}
