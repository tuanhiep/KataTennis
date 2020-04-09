package controller;

import model.GameOverException;
import model.Match;
import model.Player;

import java.util.Objects;

public class Service {
    private Display display;
    private Match match;

    public Service(Match match) {
        this.match = Objects.requireNonNull(match);
        this.display = (w, m) -> {
        };
    }

    public void register(Display display) {
        this.display = Objects.requireNonNull(display);
    }

    public void mark(Player player) throws GameOverException {
        this.match.mark(player);
        this.display.winPoint(player, match);
    }
}
