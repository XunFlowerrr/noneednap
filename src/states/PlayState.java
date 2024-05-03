package states;

import entity.Enemy;
import entity.Player;
import game.GameLauncher;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import tiles.TileManager;
import util.*;

public class PlayState extends GameState {
    public static Vector2f mapDimensions;
    private final Font font = Font.loadFont(getClass().getResourceAsStream("/font/Kiona-Regular.ttf"), 32);
    private final Player player;
    private final TileManager tileManager;
    private final Camera camera;
    private Enemy enemy;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        camera = new Camera(new AABB(new Vector2f(((float) GameLauncher.WIDTH / 2) - ((float) 800 / 2),
                                                         ((float) GameLauncher.HEIGHT / 2) - ((float) 600 /2)
        ), 800, 600));
        mapDimensions = new Vector2f();
        Vector2f.setWorldCoordinates(mapDimensions.vectorCoordinateX, mapDimensions.vectorCoordinateY);
        tileManager = new TileManager("tile/GrassGround4.xml" , camera);
        enemy = new Enemy(new Sprite("entity/littlegirl.png", 48, 48),
                          new Vector2f(100 + ((float) GameLauncher.WIDTH / 2) - 32,
                                       100 + ((float) GameLauncher.HEIGHT / 2) - 32
                          ),
                          64
        );
        player = new Player(new Sprite("entity/linkformatted.png"),
                            new Vector2f(0 + ((float) GameLauncher.WIDTH / 2) - 32,
                                         0 + ((float) GameLauncher.HEIGHT / 2) - 32
                            ),
                            64
        );
        camera.target(player);
    }

    public static Vector2f getMapDimensions() {
        return mapDimensions;
    }

    public static void setMapDimensions(Vector2f mapDimensions) {
        PlayState.mapDimensions = mapDimensions;
    }

    public Font getFont() {
        return font;
    }

    public Player getPlayer() {
        return player;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void update() {
        player.update(enemy);
        enemy.update(player);
        Vector2f.setWorldCoordinates(mapDimensions.vectorCoordinateX, mapDimensions.vectorCoordinateY);
        camera.update();
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        player.input(mouse, key);
        camera.input(mouse, key);
    }

    @Override
    public void render(GraphicsContext gc) {
        tileManager.renderGround(gc);
        Sprite.drawText(gc, font, "Hello World", new Vector2f(200, 100), 53, Color.WHITE);
        player.render(gc);
        enemy.render(gc);
        tileManager.renderAbove(gc);
        camera.render(gc);
    }

}
