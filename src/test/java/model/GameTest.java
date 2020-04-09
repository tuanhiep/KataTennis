package model;

import org.junit.Test;

public class GameTest {
    @Test
    public void testBeginning() {
        Game game = new Game();
        myAssertion.assertGameScores(game, GameScore.ZERO, GameScore.ZERO);
        myAssertion.assertGameNotWon(game);
    }

    @Test
    public void testScoreZeroToFifteen() throws GameOverException {
        Game game = MatchFactory.initializeGame(GameScore.ZERO, GameScore.ZERO, null);
        game.mark(Player.PLAYER_ONE);
        myAssertion.assertGameScores(game, GameScore.FIFTEEN, GameScore.ZERO);
        myAssertion.assertGameNotWon(game);
    }

    @Test
    public void testScoreFifteenToThirty() throws GameOverException {
        Game game = MatchFactory.initializeGame(GameScore.FIFTEEN, GameScore.ZERO, null);
        game.mark(Player.PLAYER_ONE);
        myAssertion.assertGameScores(game, GameScore.THIRTY, GameScore.ZERO);
        myAssertion.assertGameNotWon(game);
    }

    @Test
    public void testScoreThirtyToForty() throws GameOverException {
        Game game = MatchFactory.initializeGame(GameScore.THIRTY, GameScore.ZERO, null);
        game.mark(Player.PLAYER_ONE);
        myAssertion.assertGameScores(game, GameScore.FORTY, GameScore.ZERO);
        myAssertion.assertGameNotWon(game);
    }

    @Test
    public void testScoreFortyToWin() throws GameOverException {
        Game game = MatchFactory.initializeGame(GameScore.FORTY, GameScore.ZERO, null);
        game.mark(Player.PLAYER_ONE);
        myAssertion.assertGameScores(game, GameScore.ZERO, GameScore.ZERO);
        myAssertion.assertGameWon(game, Player.PLAYER_ONE);
    }

    @Test(expected = GameOverException.class)
    public void testGameOver() throws GameOverException {
        Game game = MatchFactory.initializeGame(GameScore.ZERO, GameScore.ZERO, Player.PLAYER_ONE);
        game.mark(Player.PLAYER_ONE);
    }

    @Test
    public void testScoreAdvantage1() throws GameOverException {
        Game game = MatchFactory.initializeGame(GameScore.FORTY, GameScore.FORTY, null);
        game.mark(Player.PLAYER_TWO);
        myAssertion.assertGameScores(game, GameScore.FORTY, GameScore.ADVANTAGE);
        myAssertion.assertGameNotWon(game);
    }

    @Test
    public void testScoreAdvantage2() throws GameOverException {
        Game game = MatchFactory.initializeGame(GameScore.DEUCE, GameScore.DEUCE, null);
        game.mark(Player.PLAYER_ONE);
        myAssertion.assertGameScores(game, GameScore.ADVANTAGE, GameScore.FORTY);
        myAssertion.assertGameNotWon(game);
    }

    @Test
    public void testWinner() throws GameOverException {
        Game game = MatchFactory.initializeGame(GameScore.ADVANTAGE, GameScore.FORTY, null);
        game.mark(Player.PLAYER_ONE);
        myAssertion.assertGameScores(game, GameScore.ZERO, GameScore.ZERO);
        myAssertion.assertGameWon(game, Player.PLAYER_ONE);
    }

    @Test
    public void testDeuce() throws GameOverException {
        Game game = MatchFactory.initializeGame(GameScore.ADVANTAGE, GameScore.FORTY, null);
        game.mark(Player.PLAYER_TWO);
        myAssertion.assertGameScores(game, GameScore.DEUCE, GameScore.DEUCE);
        myAssertion.assertGameNotWon(game);
    }
}