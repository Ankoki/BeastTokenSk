package com.ankoki.beasttokensk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.mraxetv.beasttokens.BeastTokensAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

//need to figure out the remove and add, as it doesnt change
public class ExprPlayerTokens extends SimpleExpression {

    static {
        Skript.registerExpression(ExprPlayerTokens.class, Double.class, ExpressionType.COMBINED, "(%player%'s [beast][ ]token[s]|[beast][ ]token[']s of %player%)");
    }

    private Expression<Player> player;

    public boolean isSingle() {
        return true;
    }

    public Class getReturnType() {
        return Number.class;
    }

    @Nullable
    protected Number[] get(Event event) {
        Player p = player.getSingle(event);
        if (p != null) {
            return new Number[] {BeastTokensAPI.getTokensManger().getTokens(p)};
        }
        return null;
    }

    public String toString(@Nullable Event event, boolean b) {
        return null;
    }

    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        player = (Expression<Player>) expressions[0];
        return true;
    }

    @Override
    public void change(@NotNull Event event, Object[] delta, Changer.ChangeMode mode) {
        Player p = player.getSingle(event);
        double d = delta == null ? 0 : ((Number) delta[0]).doubleValue();
        if (p == null) {
            return;
        }
        switch (mode) {
            case SET:
            case DELETE:
            case RESET:
                BeastTokensAPI.getTokensManger().setTokens(p, d);
                break;
            case ADD:
                BeastTokensAPI.getTokensManger().addTokens(p, d);
                break;
            case REMOVE:
                BeastTokensAPI.getTokensManger().removeTokens(p, d);
                break;
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.REMOVE_ALL) {
            return null;
        }
        return CollectionUtils.array(Number.class);
    }
}
