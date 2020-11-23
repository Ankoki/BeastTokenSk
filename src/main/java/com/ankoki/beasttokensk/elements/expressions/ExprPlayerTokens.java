package com.ankoki.beasttokensk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
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

@Name("Player Tokens")
@Description("Gets a players BeastTokens.")
@Examples({"on death:" +
        "\tremove 100 from player's tokens",
        "\tsend \"You have lost 100 tokens!\""})
@Since("1.0")
@RequiredPlugins("BeastTokens")
public class ExprPlayerTokens extends SimpleExpression<Double> {

    static {
        Skript.registerExpression(ExprPlayerTokens.class, Double.class, ExpressionType.COMBINED, "(%player%'s [beast][ ]token[s]|[beast][ ]token[']s of %player%)");
    }

    private Expression<Player> player;

    @Override
    public boolean init(Expression<?>[] exprs, int i, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        player = (Expression<Player>) exprs[0];
        return true;
    }

    @Nullable
    protected Double[] get(Event event) {
        Player p = player.getSingle(event);
        if (p != null) {
            return new Double[] {BeastTokensAPI.getTokensManager().getTokens(p)};
        }
        return null;
    }

    public boolean isSingle() {
        return true;
    }

    public Class getReturnType() {
        return Double.class;
    }

    public String toString(@Nullable Event event, boolean b) {
        return "player's tokens";
    }

    @Override
    public Class<?>[] acceptChange(final @NotNull ChangeMode mode) {
        if (mode == ChangeMode.REMOVE_ALL) {
            return null;
        }
        return CollectionUtils.array(Number.class);
    }

    @Override
    public void change(@NotNull Event event, Object[] delta, ChangeMode mode) {
        assert player != null;
        Player p = player.getSingle(event);
        double d = delta == null ? 0 : ((Number) delta[0]).doubleValue();
        if (p != null) {
            switch (mode) {
                case SET:
                case DELETE:
                case RESET:
                    BeastTokensAPI.getTokensManager().setTokens(p, d);
                    break;
                case ADD:
                    BeastTokensAPI.getTokensManager().addTokens(p, d);
                    break;
                case REMOVE:
                    BeastTokensAPI.getTokensManager().removeTokens(p, d);
                    break;
                case REMOVE_ALL:
                    assert false;
                    break;
            }
        }
    }
}
