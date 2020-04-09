package model;

import org.junit.Test;

import static model.MatchFactory.*;
import static model.Player.PLAYER_ONE;
import static model.Player.PLAYER_TWO;
import static model.myAssertion.*;

public class TennisSetTest {
    @Test
    public void testBeginning() {
        TennisSet set = new TennisSet();
        myAssertion.assertSetScores(set, 0, 0);
        myAssertion.assertSetNotWon(set);
    }

    @Test
    public void testWinGame1() throws GameOverException {
        TennisSet set = initializeSet(0, 0, null, initializeGame(GameScore.FORTY, GameScore.ZERO, null));
        set.mark(PLAYER_ONE);
        myAssertion.assertSetScores(set, 1, 0);
        myAssertion.assertSetNotWon(set);
    }

    @Test
    public void testWinGame2() throws GameOverException {
        TennisSet set = initializeSet(1, 0, null, initializeGame(GameScore.FORTY, GameScore.ZERO, null));
        set.mark(PLAYER_ONE);
        myAssertion.assertSetScores(set, 2, 0);
        myAssertion.assertSetNotWon(set);
    }

    @Test
    public void testWinGame3() throws GameOverException {
        TennisSet set = initializeSet(2, 0, null, initializeGame(GameScore.FORTY, GameScore.ZERO, null));
        set.mark(PLAYER_ONE);
        myAssertion.assertSetScores(set, 3, 0);
        myAssertion.assertSetNotWon(set);
    }

    @Test
    public void testWinGame4() throws GameOverException {
        TennisSet set = initializeSet(3, 0, null, initializeGame(GameScore.FORTY, GameScore.ZERO, null));
        set.mark(PLAYER_ONE);
        myAssertion.assertSetScores(set, 4, 0);
        myAssertion.assertSetNotWon(set);
    }

    @Test
    public void testWinGame5() throws GameOverException {
        TennisSet set = initializeSet(4, 0, null, initializeGame(GameScore.FORTY, GameScore.ZERO, null));
        set.mark(PLAYER_ONE);
        myAssertion.assertSetScores(set, 5, 0);
        myAssertion.assertSetNotWon(set);
    }

    @Test
    public void testWinSet3() throws GameOverException {
        TennisSet set = initializeSet(5, 5, null, initializeGame(GameScore.FORTY, GameScore.ZERO, null));
        set.mark(PLAYER_ONE);
        myAssertion.assertSetScores(set, 6, 5);
        myAssertion.assertSetNotWon(set);
    }

    @Test
    public void testWinSet1() throws GameOverException {
        for (int score = 1; score <= 4; score++) {
            TennisSet set = initializeSet(5, score, null, initializeGame(GameScore.FORTY, GameScore.ZERO, null));
            set.mark(PLAYER_ONE);
            myAssertion.assertSetScores(set, 6, score);
            assertSetWon(set, PLAYER_ONE);
        }
    }


    @Test
    public void testWinSet2() throws GameOverException {
        TennisSet set = initializeSet(6, 5, null, initializeGame(GameScore.FORTY, GameScore.ZERO, null));
        set.mark(PLAYER_ONE);
        myAssertion.assertSetScores(set, 7, 5);
        assertSetWon(set, PLAYER_ONE);
    }

    @Test(expected = GameOverException.class)
    public void testGameOver() throws GameOverException {
        TennisSet set = initializeSet(6, 0, PLAYER_ONE, initializeGame(GameScore.ZERO, GameScore.ZERO, PLAYER_ONE));
        set.mark(PLAYER_ONE);
    }

    @Test
    public void testTieBreakActive() throws GameOverException {
        TennisSet set = initializeSet(6, 5, null, initializeGame(GameScore.ZERO, GameScore.FORTY, null));
        set.mark(PLAYER_TWO);
        myAssertion.assertSetScores(set, 6, 6);
        myAssertion.assertSetNotWon(set);
        myAssertion.assertTieBreakScores(set, 0, 0);
        myAssertion.assertTieBreakNotWon(set);
    }

    @Test
    public void testWinFinalSet() throws GameOverException {
        TennisSet set = initializeSet(6, 6, null, initializeGame(GameScore.ZERO, GameScore.ZERO, PLAYER_ONE), initializeTieBreak(6, 5, null));
        set.mark(PLAYER_ONE);
        myAssertion.assertTieBreakScores(set, 0, 0);
        assertTieBreakWon(set, PLAYER_ONE);
        myAssertion.assertSetScores(set, 7, 6);
        assertSetWon(set, PLAYER_ONE);
    }
}