package states;

import game.GameLauncher;
import javafx.scene.canvas.GraphicsContext;
import util.KeyHandler;
import util.MouseHandler;
import util.Vector2f;

import java.util.ArrayList;

public class GameStateManager {
    public static Vector2f mapDimensions;
    private final ArrayList<GameState> gameStates;

    public GameStateManager() {
        mapDimensions = new Vector2f(GameLauncher.WIDTH, GameLauncher.HEIGHT);
        Vector2f.setWorldCoordinates(mapDimensions.vectorCoordinateX, mapDimensions.vectorCoordinateY);
        gameStates = new ArrayList<>();
        gameStates.add(new PlayState(this));
    }

    public static Vector2f getMapDimensions() {
        return mapDimensions;
    }

    public static void setMapDimensions(Vector2f mapDimensions) {
        GameStateManager.mapDimensions = mapDimensions;
    }

    public ArrayList<GameState> getGameStates() {
        return gameStates;
    }

    public void pop(int state) {
        gameStates.remove(state);
    }

    public void add(int state) {
        if (state == STATES.PLAY_STATE.ordinal()) {
            gameStates.add(new PlayState(this));
        }
        if (state == STATES.MENU_STATE.ordinal()) {
            gameStates.add(new MenuState(this));
        }
        if (state == STATES.PAUSE_STATE.ordinal()) {
            gameStates.add(new PauseState(this));
        }
        if (state == STATES.GAME_OVER_STATE.ordinal()) {
            gameStates.add(new GameOverState(this));
        }
    }

    public void addAndPop(int state) {
        pop(0);
        add(state);
    }

    public void update() {
        Vector2f.setWorldCoordinates(mapDimensions.vectorCoordinateX, mapDimensions.vectorCoordinateY);
        for (GameState state : gameStates) {
            state.update();
        }
    }

    public void input(MouseHandler mouse, KeyHandler key) {
        for (GameState state : gameStates) {
            state.input(mouse, key);
        }

        if (key.isEscapePressed) {
            System.exit(0);
        }
    }

    public void render(GraphicsContext gc) {
        for (GameState state : gameStates) {
            state.render(gc);
        }
    }

    public enum STATES {
        PLAY_STATE,
        MENU_STATE,
        PAUSE_STATE,
        GAME_OVER_STATE
    }

}
