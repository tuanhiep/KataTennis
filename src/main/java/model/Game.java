package model;


import java.util.*;

public class Game {

    private Map<Player, GameScore> scores = new EnumMap<>(Player.class);
    private Player winner;

    public Game() {
        Arrays.stream(Player.values()).forEach(p -> scores.put(p, GameScore.ZERO));
    }

    public Game(Map<Player, GameScore> scores, Player winner) {
        this.scores = Objects.requireNonNull(scores);
        this.winner = winner;
    }

    public Map<Player, GameScore> getScores() {
        return Collections.unmodifiableMap(this.scores);
    }

    public Optional<Player> getWinner() {
        return Optional.ofNullable(winner);
    }

    /**
     * When the player win 1 point
     * @param player
     * @throws GameOverException
     */
    public void mark(Player player) throws GameOverException {
        checkGameOver();
        checkGameState(player);
    }

    /**
     * Update the scores and state of game
     * @param player
     */
    private void checkGameState(Player player) {
        GameScore currentScore = scores.get(player);
        switch (currentScore) {
            case ZERO:
                scores.put(player, GameScore.FIFTEEN);
                break;
            case FIFTEEN:
                scores.put(player, GameScore.THIRTY);
                break;
            case THIRTY:
                scores.put(player, GameScore.FORTY);
                break;
            case FORTY:
                if (scores.get(player.getOpponent()).equals(GameScore.ADVANTAGE)) {
                    Arrays.stream(Player.values()).forEach(p -> scores.put(p, GameScore.DEUCE));
                } else if (scores.get(player.getOpponent()).equals(GameScore.FORTY)) {
                    scores.put(player, GameScore.ADVANTAGE);
                } else {
                    Arrays.stream(Player.values()).forEach(p -> scores.put(p, GameScore.ZERO));
                    winner = player;
                }
                break;
            case DEUCE:
                scores.put(player, GameScore.ADVANTAGE);
                scores.put(player.getOpponent(), GameScore.FORTY);
                break;
            case ADVANTAGE:
                Arrays.stream(Player.values()).forEach(p -> scores.put(p, GameScore.ZERO));
                winner = player;
                break;
        }
    }

    /**
     * Check if game is over
     * @throws GameOverException
     */
    private void checkGameOver() throws GameOverException {
        if (getWinner().isPresent()) {
            throw new GameOverException();
        }
    }
}
