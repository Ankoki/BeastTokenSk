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
import me.mraxetv.beasttokens.api.events.tokendrops.blocks.BTBlockTokenDropEvent;
import me.mraxetv.beasttokens.api.events.tokendrops.farming.BTFarmingTokenDropEvent;
import me.mraxetv.beasttokens.api.events.tokendrops.mobs.BTMobTokenDropEvent;
import me.mraxetv.beasttokens.api.events.tokendrops.mobs.BTMythicMobTokenDropEvent;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Multiplier")
@Description("The multiplier applied to tokens in an event")
@Examples({"on block token drop:",
        "    set event-multiplier to 5"})
@Since("1.0")
@Events("block token drop")
public class ExprMultiplier extends SimpleExpression<Double> {

    static {
        Skript.registerExpression(ExprMultiplier.class, Double.class, ExpressionType.SIMPLE, "[event-][ ]multiplier[s]");
    }

    @Nullable
    @Override
    protected Double[] get(Event e) {
        return new Double[]{getMultiplier(e)};
    }

    private static Double getMultiplier(final Event e) {
        if (e == null) {
            return null;
        }
        if (e instanceof BTBlockTokenDropEvent) {
            return ((BTBlockTokenDropEvent) e).getMultiplierTokens();
        }
        if (e instanceof BTFarmingTokenDropEvent) {
            return ((BTFarmingTokenDropEvent) e).getMultiplierTokens();
        }
        if (e instanceof BTMythicMobTokenDropEvent) {
            return ((BTMythicMobTokenDropEvent) e).getMultiplierTokens();
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
            return Double.toString(((BTBlockTokenDropEvent) event).getMultiplierTokens());
        }
        if (event instanceof BTFarmingTokenDropEvent) {
            return Double.toString(((BTFarmingTokenDropEvent) event).getMultiplierTokens());
        }
        if (event instanceof BTMythicMobTokenDropEvent) {
            return Double.toString(((BTMythicMobTokenDropEvent) event).getMultiplierTokens());
        }
        if (event instanceof BTMobTokenDropEvent) {
            return Double.toString(((BTMobTokenDropEvent) event).getMultiplierTokens());
        }
        return null;
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        if (!ScriptLoader.isCurrentEvent(BTBlockTokenDropEvent.class) && !ScriptLoader.isCurrentEvent(BTFarmingTokenDropEvent.class) && !ScriptLoader.isCurrentEvent(BTMythicMobTokenDropEvent.class) && !ScriptLoader.isCurrentEvent(BTMobTokenDropEvent.class)) {
            Skript.error("Cannot use 'multiplier' outside of a token drop event", ErrorQuality.SEMANTIC_ERROR);
            return false;
        }
        return true;
    }

    @Override
    public Class<?>[] acceptChange(final Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.REMOVE_ALL) {
            return null;
        }
        return CollectionUtils.array(Number.class);
    }

    @Override
    public void change(@NotNull Event event, Object[] delta, Changer.ChangeMode mode) {
        double d = delta == null ? 0 : ((Number) delta[0]).doubleValue();
        double multipler;
        if (event instanceof BTBlockTokenDropEvent) {
            multipler = ((BTBlockTokenDropEvent) event).getMultiplierTokens();
            switch (mode) {
                case SET:
                case DELETE:
                case RESET:
                    ((BTBlockTokenDropEvent) event).setMultiplierTokens(d);
                    break;
                case ADD:
                    ((BTBlockTokenDropEvent) event).setMultiplierTokens(multipler + d);
                    break;
                case REMOVE:
                    ((BTBlockTokenDropEvent) event).setMultiplierTokens(multipler - d);
                    break;
            }
        }
        if (event instanceof BTFarmingTokenDropEvent) {
            multipler = ((BTFarmingTokenDropEvent) event).getMultiplierTokens();
            switch (mode) {
                case SET:
                case DELETE:
                case RESET:
                    ((BTFarmingTokenDropEvent) event).setMultiplierTokens(d);
                    break;
                case ADD:
                    ((BTFarmingTokenDropEvent) event).setMultiplierTokens(multipler + d);
                    break;
                case REMOVE:
                    ((BTFarmingTokenDropEvent) event).setMultiplierTokens(multipler - d);
                    break;
            }
        }
        if (event instanceof BTMythicMobTokenDropEvent) {
            multipler = ((BTMythicMobTokenDropEvent) event).getMultiplierTokens();
            switch (mode) {
                case SET:
                case DELETE:
                case RESET:
                    ((BTMythicMobTokenDropEvent) event).setMultiplierTokens(d);
                    break;
                case ADD:
                    ((BTMythicMobTokenDropEvent) event).setMultiplierTokens(multipler + d);
                    break;
                case REMOVE:
                    ((BTMythicMobTokenDropEvent) event).setMultiplierTokens(multipler - d);
                    break;
            }
        }
        if (event instanceof BTMobTokenDropEvent) {
            double tokens = ((BTMobTokenDropEvent) event).getTokens();
            switch (mode) {
                case SET:
                case DELETE:
                case RESET:
                    ((BTMobTokenDropEvent) event).setMultiplierTokens(d);
                    break;
                case ADD:
                    ((BTMobTokenDropEvent) event).setMultiplierTokens(tokens + d);
                    break;
                case REMOVE:
                    ((BTMobTokenDropEvent) event).setMultiplierTokens(tokens - d);
                    break;
            }
        }
        else {
            return;
        }

    }
}