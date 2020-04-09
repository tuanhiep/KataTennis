package model;

import java.util.*;

public class TieBreak {
    private Map<Player, Integer> tieBreakScores = new EnumMap<>(Player.class);
    private Player winner;

    public TieBreak() {
        Arrays.stream(Player.values()).forEach(player -> tieBreakScores.put(player, 0));
    }

    public TieBreak(Map<Player, Integer> tieBreakScores, Player winner) {
        this.tieBreakScores = tieBreakScores;
        this.winner = winner;
    }

    public Map<Player, Integer> getScores() {
        return Collections.unmodifiableMap(tieBreakScores);
    }

    public Optional<Player> getWinner() {
        return Optional.ofNullable(winner);
    }

    void mark(Player player) throws GameOverException {
        checkTieBreakOver();
        tieBreakScores.put(player, tieBreakScores.get(player) + 1);
        if (tieBreakScores.get(player) >= 7 && ((tieBreakScores.get(player) - tieBreakScores.get(player.getOpponent())) >= 2)) {
            Arrays.stream(Player.values()).forEach(p -> tieBreakScores.put(p, 0));
            winner = player;
        }
    }

    /**
     * check Tie-Break is over
     * @throws GameOverException
     */
    private void checkTieBreakOver() throws GameOverException {
        if (getWinner().isPresent()) {
            throw new GameOverException();
        }
    }


}
