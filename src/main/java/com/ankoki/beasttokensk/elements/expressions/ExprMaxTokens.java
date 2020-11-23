package com.ankoki.beasttokensk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.mraxetv.beasttokens.BeastTokensAPI;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Max Tokens")
@Description("The max tokens set in the config.")
@Examples({"command /max:",
        "\ttrigger:",
        "\t\tsend \"Max Tokens: %max tokens%\""})
@Since("1.0")
public class ExprMaxTokens extends SimpleExpression<Double> {

    static {
        Skript.registerExpression(ExprMaxTokens.class, Double.class, ExpressionType.SIMPLE, "(max[imum][ poss[ible]]|total poss[ible]) [beast[ ]]token[s]");
    }

    @Nullable
    @Override
    protected Double[] get(Event event) {
        return new Double[] {BeastTokensAPI.getTokensManager().getMaxTokens()};
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
        return null;
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        return false;
    }
}
