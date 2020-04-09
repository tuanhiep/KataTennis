import controller.Service;
import model.*;
import org.junit.Before;
import org.junit.Test;

public class MainTest {
    private Match match;
    private Service service;

    @Before
    public void setUp() {
        match = new Match();
        service = new Service(match);
    }

    @Test
    public void testSprint1() throws GameOverException {
        // Start the game: 0 - 0
        myAssertion.assertGameScores(match, GameScore.ZERO, GameScore.ZERO);
        myAssertion.assertGameNotWon(match);
        // Player 1 wins 1 point : 15 - 0
        service.mark(Player.PLAYER_ONE);
        myAssertion.assertGameScores(match, GameScore.FIFTEEN, GameScore.ZERO);
        myAssertion.assertGameNotWon(match);
        // Player 1 wins 1 point : 30 - 0
        service.mark(Player.PLAYER_ONE);
        myAssertion.assertGameScores(match, GameScore.THIRTY, GameScore.ZERO);
        myAssertion.assertGameNotWon(match);
        // Player 2 wins 1 point : 30 - 15
        service.mark(Player.PLAYER_TWO);
        myAssertion.assertGameScores(match, GameScore.THIRTY, GameScore.FIFTEEN);
        myAssertion.assertGameNotWon(match);
        // Player 1 wins 1 point : 40 - 15
        service.mark(Player.PLAYER_ONE);
        myAssertion.assertGameScores(match, GameScore.FORTY, GameScore.FIFTEEN);
        myAssertion.assertGameNotWon(match);
        // Player 2 wins 1 point : 40 - 30
        service.mark(Player.PLAYER_TWO);
        myAssertion.assertGameScores(match, GameScore.FORTY, GameScore.THIRTY);
        myAssertion.assertGameNotWon(match);
        // Player 2 wins 1 point : Deuce - Deuce
        service.mark(Player.PLAYER_TWO);
        myAssertion.assertGameScores(match, GameScore.FORTY, GameScore.FORTY);
        myAssertion.assertGameNotWon(match);
        // Player 2 wins 1 point : 40 - Advantage
        service.mark(Player.PLAYER_TWO);
        myAssertion.assertGameScores(match, GameScore.FORTY, GameScore.ADVANTAGE);
        myAssertion.assertGameNotWon(match);
        // Player 1 wins 1 point : Deuce - Deuce
        service.mark(Player.PLAYER_ONE);
        myAssertion.assertGameScores(match, GameScore.DEUCE, GameScore.DEUCE);
        myAssertion.assertGameNotWon(match);
        // Player 1 wins 1 point : Advantage - 40
        service.mark(Player.PLAYER_ONE);
        myAssertion.assertGameScores(match, GameScore.ADVANTAGE, GameScore.FORTY);
        myAssertion.assertGameNotWon(match);
        // Player 1 wins 1 point : 0 - 0 ; Player 1 win the game
        service.mark(Player.PLAYER_ONE);
        myAssertion.assertGameScores(match, GameScore.ZERO, GameScore.ZERO);
        myAssertion.assertGameWon(match, Player.PLAYER_ONE);
    }

    @Test
    public void testSprint2_userStory1() throws GameOverException {
        // Start the game & the set : 0 - 0 | 0 - 0
        myAssertion.assertGameScores(match, GameScore.ZERO, GameScore.ZERO);
        myAssertion.assertSetScores(match, 0, 0);
        myAssertion.assertGameNotWon(match);
        myAssertion.assertSetNotWon(match);
        // Player 1 wins 1 point : 15 - 0 | 0 - 0
        service.mark(Player.PLAYER_ONE);
        myAssertion.assertGameScores(match, GameScore.FIFTEEN, GameScore.ZERO);
        myAssertion.assertSetScores(match, 0, 0);
        myAssertion.assertGameNotWon(match);
        myAssertion.assertSetNotWon(match);
        // Player 1 wins 1 point : 30 - 0 | 0 - 0
        service.mark(Player.PLAYER_ONE);
        myAssertion.assertGameScores(match, GameScore.THIRTY, GameScore.ZERO);
        myAssertion.assertSetScores(match, 0, 0);
        myAssertion.assertGameNotWon(match);
        myAssertion.assertSetNotWon(match);
        // Player 2 wins 1 point : 30 - 15 | 0 - 0
        service.mark(Player.PLAYER_TWO);
        myAssertion.assertGameScores(match, GameScore.THIRTY, GameScore.FIFTEEN);
        myAssertion.assertSetScores(match, 0, 0);
        myAssertion.assertGameNotWon(match);
        myAssertion.assertSetNotWon(match);
        // Player 1 wins 1 point : 40 - 15 | 0 - 0
        service.mark(Player.PLAYER_ONE);
        myAssertion.assertGameScores(match, GameScore.FORTY, GameScore.FIFTEEN);
        myAssertion.assertSetScores(match, 0, 0);
        myAssertion.assertGameNotWon(match);
        myAssertion.assertSetNotWon(match);
        // Player 1 wins 1 point : 0 - 0 | 1 - 0
        service.mark(Player.PLAYER_ONE);
        myAssertion.assertGameScores(match, GameScore.ZERO, GameScore.ZERO);
        myAssertion.assertSetScores(match, 1, 0);
        myAssertion.assertGameWon(match, Player.PLAYER_ONE);
        myAssertion.assertSetNotWon(match);
        // Player 2 wins 1 game : 0 - 0 | 1 - 1
        markWinGame(Player.PLAYER_TWO);
        myAssertion.assertGameScores(match, GameScore.ZERO, GameScore.ZERO);
        myAssertion.assertSetScores(match, 1, 1);
        myAssertion.assertGameWon(match, Player.PLAYER_TWO);
        myAssertion.assertSetNotWon(match);
        // Player 2 wins 1 game : 0 - 0 | 1 - 2
        markWinGame(Player.PLAYER_TWO);
        myAssertion.assertGameScores(match, GameScore.ZERO, GameScore.ZERO);
        myAssertion.assertSetScores(match, 1, 2);
        myAssertion.assertGameWon(match, Player.PLAYER_TWO);
        myAssertion.assertSetNotWon(match);
        // Player 2 wins 1 game : 0 - 0 | 1 - 3
        markWinGame(Player.PLAYER_TWO);
        myAssertion.assertGameScores(match, GameScore.ZERO, GameScore.ZERO);
        myAssertion.assertSetScores(match, 1, 3);
        myAssertion.assertGameWon(match, Player.PLAYER_TWO);
        myAssertion.assertSetNotWon(match);
        // Player 2 wins 1 game : 0 - 0 | 1 - 4
        markWinGame(Player.PLAYER_TWO);
        myAssertion.assertGameScores(match, GameScore.ZERO, GameScore.ZERO);
        myAssertion.assertSetScores(match, 1, 4);
        myAssertion.assertGameWon(match, Player.PLAYER_TWO);
        myAssertion.assertSetNotWon(match);
        // Player 2 wins 1 game : 0 - 0 | 1 - 5
        markWinGame(Player.PLAYER_TWO);
        myAssertion.assertGameScores(match, GameScore.ZERO, GameScore.ZERO);
        myAssertion.assertSetScores(match, 1, 5);
        myAssertion.assertGameWon(match, Player.PLAYER_TWO);
        myAssertion.assertSetNotWon(match);
        // Player 2 wins 1 game : 0 - 0 | 1 - 6
        markWinGame(Player.PLAYER_TWO);
        myAssertion.assertGameScores(match, GameScore.ZERO, GameScore.ZERO);
        myAssertion.assertSetScores(match, 1, 6);
        myAssertion.assertGameWon(match, Player.PLAYER_TWO);
        myAssertion.assertSetWon(match, Player.PLAYER_TWO);
    }

    @Test
    public void testSprint2_userStory2() throws GameOverException {
        markWinGames(Player.PLAYER_ONE, 5);
        markWinGames(Player.PLAYER_TWO, 6);
        markWinPoints(Player.PLAYER_ONE, 3);
        markWinPoints(Player.PLAYER_TWO, 1);
        myAssertion.assertGameScores(match, GameScore.FORTY, GameScore.FIFTEEN);
        myAssertion.assertGameNotWon(match);
        myAssertion.assertSetScores(match, 5, 6);
        myAssertion.assertSetNotWon(match);
        myAssertion.assertNoTieBreak(match);
        myAssertion.assertMatchNotWon(match);
        service.mark(Player.PLAYER_ONE);
        myAssertion.assertGameScores(match, GameScore.ZERO, GameScore.ZERO);
        myAssertion.assertGameWon(match, Player.PLAYER_ONE);
        myAssertion.assertSetScores(match, 6, 6);
        myAssertion.assertSetNotWon(match);
        myAssertion.assertTieBreak(match);
        myAssertion.assertTieBreakScores(match, 0, 0);
        myAssertion.assertTieBreakNotWon(match);
        myAssertion.assertMatchNotWon(match);
        service.mark(Player.PLAYER_ONE);
        myAssertion.assertGameScores(match, GameScore.ZERO, GameScore.ZERO);
        myAssertion.assertGameWon(match, Player.PLAYER_ONE);
        myAssertion.assertSetScores(match, 6, 6);
        myAssertion.assertSetNotWon(match);
        myAssertion.assertTieBreak(match);
        myAssertion.assertTieBreakScores(match, 1, 0);
        myAssertion.assertTieBreakNotWon(match);
        myAssertion.assertMatchNotWon(match);
        service.mark(Player.PLAYER_TWO);
        myAssertion.assertGameScores(match, GameScore.ZERO, GameScore.ZERO);
        myAssertion.assertGameWon(match, Player.PLAYER_ONE);
        myAssertion.assertSetScores(match, 6, 6);
        myAssertion.assertSetNotWon(match);
        myAssertion.assertTieBreak(match);
        myAssertion.assertTieBreakScores(match, 1, 1);
        myAssertion.assertTieBreakNotWon(match);
        myAssertion.assertMatchNotWon(match);
        service.mark(Player.PLAYER_ONE);
        myAssertion.assertGameScores(match, GameScore.ZERO, GameScore.ZERO);
        myAssertion.assertGameWon(match, Player.PLAYER_ONE);
        myAssertion.assertSetScores(match, 6, 6);
        myAssertion.assertSetNotWon(match);
        myAssertion.assertTieBreak(match);
        myAssertion.assertTieBreakScores(match, 2, 1);
        myAssertion.assertTieBreakNotWon(match);
        myAssertion.assertMatchNotWon(match);
        service.mark(Player.PLAYER_ONE);
        myAssertion.assertGameScores(match, GameScore.ZERO, GameScore.ZERO);
        myAssertion.assertGameWon(match, Player.PLAYER_ONE);
        myAssertion.assertSetScores(match, 6, 6);
        myAssertion.assertSetNotWon(match);
        myAssertion.assertTieBreak(match);
        myAssertion.assertTieBreakScores(match, 3, 1);
        myAssertion.assertTieBreakNotWon(match);
        myAssertion.assertMatchNotWon(match);
        service.mark(Player.PLAYER_TWO);
        myAssertion.assertGameScores(match, GameScore.ZERO, GameScore.ZERO);
        myAssertion.assertGameWon(match, Player.PLAYER_ONE);
        myAssertion.assertSetScores(match, 6, 6);
        myAssertion.assertSetNotWon(match);
        myAssertion.assertTieBreak(match);
        myAssertion.assertTieBreakScores(match, 3, 2);
        myAssertion.assertTieBreakNotWon(match);
        myAssertion.assertMatchNotWon(match);
        service.mark(Player.PLAYER_ONE);
        myAssertion.assertGameScores(match, GameScore.ZERO, GameScore.ZERO);
        myAssertion.assertGameWon(match, Player.PLAYER_ONE);
        myAssertion.assertSetScores(match, 6, 6);
        myAssertion.assertSetNotWon(match);
        myAssertion.assertTieBreak(match);
        myAssertion.assertTieBreakScores(match, 4, 2);
        myAssertion.assertTieBreakNotWon(match);
        myAssertion.assertMatchNotWon(match);
        service.mark(Player.PLAYER_ONE);
        myAssertion.assertGameScores(match, GameScore.ZERO, GameScore.ZERO);
        myAssertion.assertGameWon(match, Player.PLAYER_ONE);
        myAssertion.assertSetScores(match, 6, 6);
        myAssertion.assertSetNotWon(match);
        myAssertion.assertTieBreak(match);
        myAssertion.assertTieBreakScores(match, 5, 2);
        myAssertion.assertTieBreakNotWon(match);
        myAssertion.assertMatchNotWon(match);
        service.mark(Player.PLAYER_ONE);
        myAssertion.assertGameScores(match, GameScore.ZERO, GameScore.ZERO);
        myAssertion.assertGameWon(match, Player.PLAYER_ONE);
        myAssertion.assertSetScores(match, 6, 6);
        myAssertion.assertSetNotWon(match);
        myAssertion.assertTieBreak(match);
        myAssertion.assertTieBreakScores(match, 6, 2);
        myAssertion.assertTieBreakNotWon(match);
        myAssertion.assertMatchNotWon(match);
        service.mark(Player.PLAYER_ONE);
        myAssertion.assertGameScores(match, GameScore.ZERO, GameScore.ZERO);
        myAssertion.assertGameWon(match, Player.PLAYER_ONE);
        myAssertion.assertSetScores(match, 7, 6);
        myAssertion.assertSetWon(match, Player.PLAYER_ONE);
        myAssertion.assertTieBreak(match);
        myAssertion.assertTieBreakScores(match, 0, 0);
        myAssertion.assertTieBreakWon(match, Player.PLAYER_ONE);
        myAssertion.assertMatchWon(match, Player.PLAYER_ONE);
    }

    private void markWinGames(Player player, int numberOfGames) throws GameOverException {
        for (int i = 0; i < numberOfGames; i++) {
            markWinGame(player);
        }
    }

    private void markWinGame(Player player) throws GameOverException {
        markWinPoints(player, 4);
    }

    private void markWinPoints(Player player, int numberOfPoints) throws GameOverException {
        for (int i = 0; i < numberOfPoints; i++) {
            service.mark(player);
        }
    }
}