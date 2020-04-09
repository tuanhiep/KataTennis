package model;

import java.util.Objects;
import java.util.Optional;

public class Match {

    private TennisSet currentSet = new TennisSet();

    public Match() {
    }

    Match(TennisSet set) {
        this.currentSet = Objects.requireNonNull(set);
    }

    public void mark(Player player) throws GameOverException {
        this.currentSet.mark(player);
    }

    public TennisSet getCurrentSet() {
        return this.currentSet;
    }

    public Game getCurrentGame() {
        return this.currentSet.getOngoing();
    }

    public Optional<TieBreak> getTieBreak() {
        return currentSet.getTieBreak();
    }

    public Optional<Player> getWinner() {
        return currentSet.getWinner();
    }

}
