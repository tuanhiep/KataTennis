package model;

import org.junit.Test;

import static model.MatchFactory.initializeTieBreak;
import static model.Player.PLAYER_ONE;
import static model.Player.PLAYER_TWO;
import static model.myAssertion.*;

public class TieBreakTest {
    @Test
    public void testUpdateScore() throws GameOverException {
        final int max = 20;
        TieBreak tieBreak = initializeTieBreak(1, 0, null);
        for (int i = 1; i <= max; i++) {
            tieBreak.mark(PLAYER_TWO);
            tieBreak.mark(PLAYER_ONE);
            assertTieBreakScores(tieBreak, i + 1, i);
            assertTieBreakNotWon(tieBreak);
        }
    }

    @Test
    public void testWinTieBreak() throws GameOverException {
        TieBreak tieBreak = initializeTieBreak(6, 6, null);
        tieBreak.mark(PLAYER_ONE);
        assertTieBreakScores(tieBreak, 7, 6);
        assertTieBreakNotWon(tieBreak);
        tieBreak.mark(PLAYER_TWO);
        tieBreak.mark(PLAYER_TWO);
        assertTieBreakScores(tieBreak, 7, 8);
        assertTieBreakNotWon(tieBreak);
        tieBreak.mark(PLAYER_TWO);
        assertTieBreakScores(tieBreak, 0, 0);
        assertTieBreakWon(tieBreak, PLAYER_TWO);
    }

    @Test(expected = GameOverException.class)
    public void testTieBreakOver() throws GameOverException {
        TieBreak tieBreak = initializeTieBreak(0, 0, PLAYER_ONE);
        tieBreak.mark(PLAYER_ONE);
    }
}