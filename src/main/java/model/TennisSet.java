package model;

import java.util.*;

public class TennisSet {
    private Map<Player, Integer> SetScores = new EnumMap<>(Player.class);
    private Game ongoing;
    private TieBreak tieBreak;
    private Player winner;

    public TennisSet() {
        Arrays.stream(Player.values()).forEach(p -> SetScores.put(p, 0));
        ongoing = new Game();
    }

    public TennisSet(Map<Player, Integer> SetScores, Game ongoing, TieBreak tieBreak, Player winner) {
        this.SetScores = SetScores;
        this.ongoing = ongoing;
        this.tieBreak = tieBreak;
        this.winner = winner;
    }

    public Map<Player, Integer> getSetScores() {
        return Collections.unmodifiableMap(SetScores);
    }

    public Optional<Player> getWinner() {
        return Optional.ofNullable(winner);
    }

    public Game getOngoing() {
        return ongoing;
    }

    public Optional<TieBreak> getTieBreak() {
        return Optional.ofNullable(tieBreak);
    }

    public void mark(Player player) throws GameOverException {
        checkSetOver();
        if (getTieBreak().isPresent()) {
            tieBreak.mark(player);
            if (tieBreak.getWinner().isPresent()) {
                //update the final score of the match
                SetScores.put(player, SetScores.get(player) + 1);
                winner = tieBreak.getWinner().get();
            }
            return;
        }
        //if the last game is finished, we must set up new game
        if (ongoing.getWinner().isPresent()) {
            ongoing = new Game();
        }
        //the player win 1 point for the game
        ongoing.mark(player);
        //if the game has finished
        if (ongoing.getWinner().isPresent()) {
            SetScores.put(player, SetScores.get(player) + 1);
        }
        //active tie-break rule
        if (SetScores.get(player) == 6 && SetScores.get(player.getOpponent()) == 6) {
            tieBreak = new TieBreak();
            return;
        }
        //if the set has finished
        if ((SetScores.get(player) == 6 && SetScores.get(player.getOpponent()) <= 4) || SetScores.get(player) == 7) {
            winner = player;
        }
    }

    private void checkSetOver() throws GameOverException {
        if (getWinner().isPresent()) {
            throw new GameOverException();
        }
    }


}
