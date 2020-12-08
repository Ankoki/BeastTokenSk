package com.ankoki.beasttokensk.elements.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.*;
import org.bukkit.entity.Player;

import me.mraxetv.beasttokens.BeastTokensAPI;

@Name("Player Has Max Tokens")
@Description("Returns if the player has the maximum amount of BeastTokens")
@Examples({"command /redeem:",
        "\tcooldown: 1 day",
        "\ttrigger:",
        "\t\tif player has the max amount of tokens:",
        "\t\t\tsend \"Sorry! You cannot redeem any tokens!\"",
        "\t\t\tcancel cooldown",
        "\t\t\tstop",
        "\t\tsend \"You have redeemed your daily tokens!",
        "\t\tadd 100 to player's tokens"})
@Since("1.0")
@RequiredPlugins("BeastTokens")
public class CondHasMaxTokens extends PropertyCondition<Player> {


    @Override
    public boolean check(Player player) {
        return BeastTokensAPI.getTokensManager().hasMaxTokens(player);
    }

    @Override
    protected PropertyType getPropertyType() {
        return PropertyType.HAVE;
    }

    @Override
    protected String getPropertyName() {
        return "max beast tokens";
    }

}
