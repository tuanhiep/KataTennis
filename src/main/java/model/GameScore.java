package model;

public enum GameScore {
    ZERO("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    DEUCE("DEUCE"),
    ADVANTAGE("ADVANTAGE");

    private String label;

    GameScore(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
