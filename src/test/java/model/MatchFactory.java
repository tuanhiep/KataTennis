package model;

import java.util.EnumMap;
import java.util.Map;

public class MatchFactory {
    public static Match initializeMatch(TennisSet set) {
        return new Match(set);
    }

    public static TennisSet initializeSet(int ps1, int ps2, Player winner, Game game) {
        return initializeSet(ps1, ps2, winner, game, null);
    }

    public static TennisSet initializeSet(int ps1, int ps2, Player winner, Game game, TieBreak tieBreak) {
        Map<Player, Integer> scores = new EnumMap<>(Player.class);
        scores.put(Player.PLAYER_ONE, ps1);
        scores.put(Player.PLAYER_TWO, ps2);
        return new TennisSet(scores, game, tieBreak, winner);
    }

    public static Game initializeGame(GameScore ps1, GameScore ps2, Player winner) {
        Map<Player, GameScore> scores = new EnumMap<>(Player.class);
        scores.put(Player.PLAYER_ONE, ps1);
        scores.put(Player.PLAYER_TWO, ps2);
        return new Game(scores, winner);
    }

    public static TieBreak initializeTieBreak(int ps1, int ps2, Player winner) {
        Map<Player, Integer> scores = new EnumMap<>(Player.class);
        scores.put(Player.PLAYER_ONE, ps1);
        scores.put(Player.PLAYER_TWO, ps2);
        return new TieBreak(scores, winner);
    }
}
