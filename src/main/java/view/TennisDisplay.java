package view;

import controller.Display;
import controller.Service;
import model.GameOverException;
import model.GameScore;
import model.Match;
import model.Player;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static model.Player.PLAYER_ONE;
import static model.Player.PLAYER_TWO;

public class TennisDisplay implements Display {
    private static final Map<String, Player> CONTROLLER = new HashMap<>();

    static {
        CONTROLLER.put("1", PLAYER_ONE);
        CONTROLLER.put("2", PLAYER_TWO);
    }

    private final Service service;
    private final PrintWriter printer;
    private final BufferedReader reader;
    private boolean ended = false;

    public TennisDisplay(Service service, Reader input, Writer output) {
        this.service = Objects.requireNonNull(service);
        this.reader = new BufferedReader(Objects.requireNonNull(input));
        this.printer = new PrintWriter(output, true);
        this.service.register(this);
    }

    public void begin() throws IOException, GameOverException {
        while (!ended) {
            String player = waitingPoint();
            mark(player);
        }
    }

    private String waitingPoint() throws IOException {
        printer.print("Who has won point (answer only 1 or 2) ? :   ");
        printer.flush();
        return reader.readLine().trim();
    }

    private void mark(String player) throws GameOverException {
        if (CONTROLLER.containsKey(player)) {
            this.service.mark(CONTROLLER.get(player));
        }
    }

    @Override
    public void winPoint(Player player, Match match) {
        // player won point
        printer.println(String.format("%s win 1 point", player));
        Map<Player, GameScore> gameScores = match.getCurrentGame().getScores();
        // display game score
        printer.println(String.format("Game: %s - %s", gameScores.get(PLAYER_ONE), gameScores.get(PLAYER_TWO)));
        Map<Player, Integer> SetScores = match.getCurrentSet().getSetScores();
        // display set score
        printer.println(String.format("Set: %s - %s", SetScores.get(PLAYER_ONE), SetScores.get(PLAYER_TWO)));
        // display tie-break score
        if (match.getTieBreak().isPresent()) {
            Map<Player, Integer> tieBreakScores = match.getTieBreak().get().getScores();
            printer.println(String.format("Tie Break: %s - %s", tieBreakScores.get(PLAYER_ONE), tieBreakScores.get(PLAYER_TWO)));
        }
        // display game winner
        boolean unFinishedTieBreak = !match.getTieBreak().isPresent() ||
                (
                        Arrays.stream(Player.values())
                                .map(p -> match.getTieBreak().get().getScores().get(p))
                                .allMatch(score -> score == 0) &&
                                !match.getTieBreak().get().getWinner().isPresent()
                );
        if (match.getCurrentGame().getWinner().isPresent() && unFinishedTieBreak) {
            printer.println(String.format("%s won the game", match.getCurrentGame().getWinner().get()));
        }
        // display tie-break winner
        if (match.getTieBreak().isPresent() && match.getTieBreak().get().getWinner().isPresent()) {
            printer.println(String.format("%s won the tie break", match.getTieBreak().get().getWinner().get()));
        }
        // display set winner
        if (match.getCurrentSet().getWinner().isPresent()) {
            printer.println(String.format("%s won the set", match.getCurrentSet().getWinner().get()));
        }
        // display match winner
        if (match.getWinner().isPresent()) {
            printer.println(String.format("%s won the match", match.getWinner().get()));
        }
        printer.println();
        ended = match.getWinner().isPresent();
    }


}
