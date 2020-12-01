package com.ankoki.beasttokensk.elements.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import org.bukkit.entity.Player;

import me.mraxetv.beasttokens.BeastTokensAPI;

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
