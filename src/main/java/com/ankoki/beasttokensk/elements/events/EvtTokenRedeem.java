package com.ankoki.beasttokensk.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import ch.njol.skript.doc.*;
import me.mraxetv.beasttokens.api.events.BTokenRedeemEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Name("Token Redeem")
@Description("Fired when a player uses an item to redeem BeastTokens.")
@Examples({"on token redeem:",
        "\tif event-item is paper:",
        "\t\tsend \"You used a paper withdraw slip!\"",
        "\telse:",
        "\t\tcancel event",
        "\t\tsend \"You have used an illegal slip!\""})
@Since("1.0")
@RequiredPlugins("BeastTokens")
public class EvtTokenRedeem extends SimpleEvent {

    static {
        Skript.registerEvent("Token Drop", EvtTokenRedeem.class, BTokenRedeemEvent.class, "[beast[ ]token[[']s]] token[[']s] redeem");
        EventValues.registerEventValue(BTokenRedeemEvent.class, Player.class, new Getter<Player, BTokenRedeemEvent>() {
            @Override
            public Player get(BTokenRedeemEvent e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(BTokenRedeemEvent.class, ItemStack.class, new Getter<ItemStack, BTokenRedeemEvent>() {
            @Override
            public ItemStack get(BTokenRedeemEvent e) {
                return e.getItem();
            }
        }, 0);
        EventValues.registerEventValue(BTokenRedeemEvent.class, Boolean.class, new Getter<Boolean, BTokenRedeemEvent>() {
            @Override
            public Boolean get(BTokenRedeemEvent e) {
                return e.inOffHand();
            }
        }, 0);
    }
}
