package net.hydramc.domination.handlers;

public enum GameState {

    WAITTING, IN_GAME, LOADING, OFF;

    private static GameState state;

    public static GameState getState() {
        return state;
    }

    public static boolean isState(GameState state) {
        return GameState.state == state;
    }

    public static void setState(GameState state) {
        GameState.state = state;
    }

}