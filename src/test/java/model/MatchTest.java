package model;

import org.junit.Test;

import static model.MatchFactory.*;

public class MatchTest {

    @Test
    public void thePlayerWhoWinsTheSetWinTheMatch() throws GameOverException {
        Match match = initializeMatch(initializeSet(6, 0, null, initializeGame(GameScore.FORTY, GameScore.ZERO, null)));

        match.mark(Player.PLAYER_ONE);

        myAssertion.assertGameScores(match, GameScore.ZERO, GameScore.ZERO);
        myAssertion.assertGameWon(match, Player.PLAYER_ONE);
        myAssertion.assertNoTieBreak(match);
        myAssertion.assertSetScores(match, 7, 0);
        myAssertion.assertSetWon(match, Player.PLAYER_ONE);
        myAssertion.assertMatchWon(match, Player.PLAYER_ONE);

    }

    @Test
    public void thePlayerWhoWinsTheTieBreakWinTheMatch() throws GameOverException {
        Match match = initializeMatch(initializeSet(6, 6, null, initializeGame(GameScore.ZERO, GameScore.ZERO, Player.PLAYER_ONE), initializeTieBreak(6, 5, null)));

        match.mark(Player.PLAYER_ONE);

        myAssertion.assertGameScores(match, GameScore.ZERO, GameScore.ZERO);
        myAssertion.assertGameWon(match, Player.PLAYER_ONE);
        myAssertion.assertTieBreakScores(match, 0, 0);
        myAssertion.assertTieBreakWon(match, Player.PLAYER_ONE);
        myAssertion.assertSetScores(match, 7, 6);
        myAssertion.assertSetWon(match, Player.PLAYER_ONE);
        myAssertion.assertMatchWon(match, Player.PLAYER_ONE);

    }

}