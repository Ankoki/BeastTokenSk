package com.ankoki.beasttokensk.elements.events;

import ch.njol.skript.doc.*;
import ch.njol.skript.lang.util.SimpleEvent;
import org.bukkit.event.Event;

/* Mythic Mob Token Drop
 * Since 1.0
 */
@RequiredPlugins({"BeastTokens", "MythicMobs"})
public class EvtMythicMobTokenDrop extends SimpleEvent {

    @Override
    public boolean check(Event e) {
        return true;
    }
}