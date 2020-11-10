package com.ankoki.beasttokensk.elements.events;

import ch.njol.skript.doc.*;
import ch.njol.skript.lang.util.SimpleEvent;
import org.bukkit.event.Event;


@Name("Mythic Mob Token Drop")
@Description("Fired when a mythic mob drops BeastTokens")
@Examples({"on mythic mob token drop:" +
        "    if player has permission \"tokens.gain\":",
        "        send \"You killed %event-entity% and recieved %event-tokens% tokens\"",
        "        stop",
        "    cancel event",
        "    send \"You can't earn tokens until you have unlocked this feature!\""})
@Since("1.0")
@RequiredPlugins({"BeastTokens", "MythicMobs"})
public class EvtMythicMobTokenDrop extends SimpleEvent {

    @Override
    public boolean check(Event e) {
        return true;
    }
}