package com.ankoki.beasttokensk.elements.expressions;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.mraxetv.beasttokens.api.events.BTokenRedeemEvent;
import me.mraxetv.beasttokens.api.events.tokendrops.blocks.BTBlockTokenDropEvent;
import me.mraxetv.beasttokens.api.events.tokendrops.farming.BTFarmingTokenDropEvent;
import me.mraxetv.beasttokens.api.events.tokendrops.mobs.BTMobTokenDropEvent;
import me.mraxetv.beasttokens.api.events.tokendrops.mobs.BTMythicMobTokenDropEvent;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Tokens")
@Description("The tokens in an event.")
@Examples({"on block token drop:",
        "\tcancel event",
        "\tset {_tokens} to event-tokens",
        "\tadd ({_tokens} + 1) to player's tokens"})
@Since("1.0")
@Events("block token drop")
public class ExprTokens extends SimpleExpression<Double> {

    static {
        Skript.registerExpression(ExprTokens.class, Double.class, ExpressionType.SIMPLE, "[event-][ ]token[s]");
    }

    @Nullable
    @Override
    protected Double[] get(Event e) {
        return new Double[]{getTokens(e)};
    }

    private static Double getTokens(final Event e) {
        if (e == null) {
            return null;
        }
        if (e instanceof BTBlockTokenDropEvent) {
            return ((BTBlockTokenDropEvent) e).getTokens();
        }
        if (e instanceof BTFarmingTokenDropEvent) {
            return ((BTFarmingTokenDropEvent) e).getTokens();
        }
        if (e instanceof BTMythicMobTokenDropEvent) {
            return ((BTMythicMobTokenDropEvent) e).getTokens();
        }
        if (e instanceof BTMobTokenDropEvent) {
            return ((BTMobTokenDropEvent) e).getTokens();
        }
        if (e instanceof BTokenRedeemEvent) {
            return ((BTokenRedeemEvent) e).getTokens();
        }
        return null;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Double> getReturnType() {
        return Double.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        if (event instanceof BTBlockTokenDropEvent) {
            return Double.toString(((BTBlockTokenDropEvent) event).getTokens());
        }
        if (event instanceof BTFarmingTokenDropEvent) {
            return Double.toString(((BTFarmingTokenDropEvent) event).getTokens());
        }
        if (event instanceof BTMythicMobTokenDropEvent) {
            return Double.toString(((BTMythicMobTokenDropEvent) event).getTokens());
        }
        if (event instanceof BTMobTokenDropEvent) {
            return Double.toString(((BTMobTokenDropEvent) event).getTokens());
        }
        if (event instanceof BTokenRedeemEvent) {
            return Double.toString(((BTokenRedeemEvent) event).getTokens());
        }
        return null;
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        if (!ScriptLoader.isCurrentEvent(BTBlockTokenDropEvent.class) && (!ScriptLoader.isCurrentEvent(BTokenRedeemEvent.class)) && !ScriptLoader.isCurrentEvent(BTFarmingTokenDropEvent.class) && !ScriptLoader.isCurrentEvent(BTMythicMobTokenDropEvent.class) && !ScriptLoader.isCurrentEvent(BTMobTokenDropEvent.class)) {
            Skript.error("Cannot use 'tokens' outside of a token event", ErrorQuality.SEMANTIC_ERROR);
            return false;
        }
        return true;
    }

    @Override
    public void change(@NotNull Event event, Object[] delta, Changer.ChangeMode mode) {
        double d = delta == null ? 0 : ((Number) delta[0]).doubleValue();
        if (event instanceof BTBlockTokenDropEvent) {
            double tokens = ((BTBlockTokenDropEvent) event).getTokens();
            switch (mode) {
                case DELETE:
                case RESET:
                    ((BTBlockTokenDropEvent) event).setTokens(0);
                    break;
                case SET:
                    ((BTBlockTokenDropEvent) event).setTokens(d);
                    break;
                case ADD:
                    ((BTBlockTokenDropEvent) event).setTokens(tokens + d);
                    break;
                case REMOVE:
                    ((BTBlockTokenDropEvent) event).setTokens(tokens - d);
                    break;
            }
        }
        if (event instanceof BTFarmingTokenDropEvent) {
            double tokens = ((BTFarmingTokenDropEvent) event).getTokens();
            switch (mode) {
                case DELETE:
                case RESET:
                    ((BTFarmingTokenDropEvent) event).setTokens(0);
                    break;
                case SET:
                    ((BTFarmingTokenDropEvent) event).setTokens(d);
                    break;
                case ADD:
                    ((BTFarmingTokenDropEvent) event).setTokens(tokens + d);
                    break;
                case REMOVE:
                    ((BTFarmingTokenDropEvent) event).setTokens(tokens - d);
                    break;
            }
        }
        if (event instanceof BTMythicMobTokenDropEvent) {
            double tokens = ((BTMythicMobTokenDropEvent) event).getTokens();
            switch (mode) {
                case DELETE:
                case RESET:
                    ((BTMythicMobTokenDropEvent) event).setTokens(0);
                    break;
                case SET:
                    ((BTMythicMobTokenDropEvent) event).setTokens(d);
                    break;
                case ADD:
                    ((BTMythicMobTokenDropEvent) event).setTokens(tokens + d);
                    break;
                case REMOVE:
                    ((BTMythicMobTokenDropEvent) event).setTokens(tokens - d);
                    break;
            }
        }
        if (event instanceof BTMobTokenDropEvent) {
            double tokens = ((BTMobTokenDropEvent) event).getTokens();
            switch (mode) {
                case DELETE:
                case RESET:
                    ((BTMobTokenDropEvent) event).setTokens(0);
                    break;
                case SET:
                    ((BTMobTokenDropEvent) event).setTokens(d);
                    break;
                case ADD:
                    ((BTMobTokenDropEvent) event).setTokens(tokens + d);
                    break;
                case REMOVE:
                    ((BTMobTokenDropEvent) event).setTokens(tokens - d);
                    break;
            }
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.@NotNull ChangeMode mode) {
        if (ScriptLoader.isCurrentEvent(BTokenRedeemEvent.class)) {
            return null;
        }
        else if (mode == Changer.ChangeMode.REMOVE_ALL) {
            return null;
        }
        return CollectionUtils.array(Number.class);
    }
}
