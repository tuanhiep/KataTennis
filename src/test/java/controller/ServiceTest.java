package controller;

import model.*;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class ServiceTest {
    @Test
    public void testService() throws GameOverException {
        Match match = new Match();
        Service service = new Service(match);
        Display display = Mockito.mock(Display.class);
        service.register(display);
        service.mark(Player.PLAYER_ONE);
        ArgumentCaptor<Player> playerCaptor = ArgumentCaptor.forClass(Player.class);
        ArgumentCaptor<Match> matchCaptor = ArgumentCaptor.forClass(Match.class);
        verify(display).winPoint(playerCaptor.capture(), matchCaptor.capture());
        Assert.assertEquals(Player.PLAYER_ONE, playerCaptor.getValue());
        assertEquals(match, matchCaptor.getValue());
        myAssertion.assertGameScores(matchCaptor.getValue(), GameScore.FIFTEEN, GameScore.ZERO);
    }
}