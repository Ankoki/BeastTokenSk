package com.ankoki.beasttokensk.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.mraxetv.beasttokens.BeastTokensAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EffTokenSlip extends Effect {

    static {
        Skript.registerEffect(EffTokenSlip.class, "give %player% %int% [beast]token slip[[']s] worth %number% named %string%");
    }

    private Expression<Player> player;
    private Expression<Number> tokens;
    private Expression<String> name;
    private Expression<Integer> amount;

    @Override
    public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.player = (Expression<Player>) exprs[0];
        this.amount = (Expression<Integer>) exprs[1];
        this.tokens = (Expression<Number>) exprs[2];
        this.name = (Expression<String>) exprs[3];
        return true;
    }

    @Override
    protected void execute(Event e) {
        if (player == null || tokens == null || amount == null || name == null) return;
        for (Player user : player.getAll(e)) {
            user.getInventory().addItem(BeastTokensAPI.getTokensManager().getTokenNote(name.getSingle(e), (Double) tokens.getSingle(e), amount.getSingle(e), false));
        }
    }

    @Nullable
    @Override
    public String toString(Event event, boolean b) {
        return "token slip";
    }
}
