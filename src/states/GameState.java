package states;

import javafx.scene.canvas.GraphicsContext;
import util.KeyHandler;
import util.MouseHandler;

public abstract class GameState {
    private final GameStateManager gsm;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public abstract void update();

    public abstract void input(MouseHandler mouse, KeyHandler key);

    public abstract void render(GraphicsContext gc);

}
