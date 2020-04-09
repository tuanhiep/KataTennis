package model;

import static model.Player.PLAYER_ONE;
import static model.Player.PLAYER_TWO;
import static org.junit.Assert.*;

public class myAssertion {
    // Tennis Game
    public static void assertGameScores(Match match, GameScore score1, GameScore score2) {
        assertGameScores(match.getCurrentGame(), score1, score2);
    }

    public static void assertGameNotWon(Match match) {
        assertGameNotWon(match.getCurrentGame());
    }

    public static void assertGameWon(Match match, Player winner) {
        assertGameWon(match.getCurrentGame(), winner);
    }

    public static void assertGameScores(Game game, GameScore score1, GameScore score2) {
        assertEquals(score1, game.getScores().get(PLAYER_ONE));
        assertEquals(score2, game.getScores().get(PLAYER_TWO));
    }

    public static void assertGameNotWon(Game game) {
        assertFalse(game.getWinner().isPresent());
    }

    public static void assertGameWon(Game game, Player winner) {
        assertTrue(game.getWinner().isPresent());
        assertEquals(winner, game.getWinner().get());
    }

    // Tennis Set
    public static void assertSetScores(Match match, int score1, int score2) {
        assertSetScores(match.getCurrentSet(), score1, score2);
    }

    public static void assertSetNotWon(Match match) {
        assertSetNotWon(match.getCurrentSet());
    }

    public static void assertSetWon(Match match, Player winner) {
        assertSetWon(match.getCurrentSet(), winner);
    }

    public static void assertSetScores(TennisSet set, int score1, int score2) {
        assertEquals(score1, set.getSetScores().get(PLAYER_ONE).intValue());
        assertEquals(score2, set.getSetScores().get(PLAYER_TWO).intValue());
    }

    public static void assertSetNotWon(TennisSet set) {
        assertFalse(set.getWinner().isPresent());
    }

    public static void assertSetWon(TennisSet set, Player winner) {
        assertTrue(set.getWinner().isPresent());
        assertEquals(winner, set.getWinner().get());
    }

    // Tie Break
    public static void assertNoTieBreak(Match match) {
        assertFalse(match.getTieBreak().isPresent());
    }

    public static void assertTieBreak(Match match) {
        assertTrue(match.getTieBreak().isPresent());
    }

    public static void assertTieBreakScores(Match match, int score1, int score2) {
        assertTrue(match.getCurrentSet().getTieBreak().isPresent());
        assertTieBreakScores(match.getCurrentSet().getTieBreak().get(), score1, score2);
    }

    public static void assertTieBreakNotWon(Match match) {
        assertTrue(match.getCurrentSet().getTieBreak().isPresent());
        assertTieBreakNotWon(match.getCurrentSet().getTieBreak().get());
    }

    public static void assertTieBreakWon(Match match, Player winner) {
        assertTrue(match.getCurrentSet().getTieBreak().isPresent());
        assertTieBreakWon(match.getCurrentSet().getTieBreak().get(), winner);
    }

    public static void assertTieBreakScores(TennisSet set, int score1, int score2) {
        assertTrue(set.getTieBreak().isPresent());
        assertTieBreakScores(set.getTieBreak().get(), score1, score2);
    }

    public static void assertTieBreakNotWon(TennisSet set) {
        assertTrue(set.getTieBreak().isPresent());
        assertTieBreakNotWon(set.getTieBreak().get());
    }

    public static void assertTieBreakWon(TennisSet set, Player winner) {
        assertTrue(set.getTieBreak().isPresent());
        assertTieBreakWon(set.getTieBreak().get(), winner);
    }

    public static void assertTieBreakScores(TieBreak tieBreak, int playerOneScore, int playerTwoScore) {
        assertEquals(playerOneScore, tieBreak.getScores().get(PLAYER_ONE).intValue());
        assertEquals(playerTwoScore, tieBreak.getScores().get(PLAYER_TWO).intValue());
    }

    public static void assertTieBreakNotWon(TieBreak tieBreak) {
        assertFalse(tieBreak.getWinner().isPresent());
    }

    public static void assertTieBreakWon(TieBreak tieBreak, Player winner) {
        assertTrue(tieBreak.getWinner().isPresent());
        assertEquals(winner, tieBreak.getWinner().get());
    }

    // Match
    public static void assertMatchNotWon(Match match) {
        assertFalse(match.getWinner().isPresent());
    }

    public static void assertMatchWon(Match match, Player winner) {
        assertTrue(match.getWinner().isPresent());
        assertEquals(winner, match.getWinner().get());
    }
}
