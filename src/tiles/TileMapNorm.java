package tiles;

import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import tiles.block.Block;
import tiles.block.NormBlock;
import util.Vector2f;

import java.util.ArrayList;
import java.util.TreeMap;

public class TileMapNorm extends TileMap {
    private final ArrayList<Block> blocks;

    public TileMapNorm(String data,
                       TreeMap<Integer, Sprite> tilesets,
                       int width,
                       int height,
                       int tileWidth,
                       int tileHeight,
                       TreeMap<Integer, Integer> tileColumns) {
        blocks = new ArrayList<>();
        String[] tile = data.replaceAll("\\s+", "").split(",");


        int[][] tileMatrix = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                tileMatrix[i][j] = Integer.parseInt(tile[i * width + j]);
                System.out.print(tileMatrix[i][j] + " ");

            }
            System.out.println();
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int temp = tileMatrix[i][j];
                if (temp != 0) {
                    int startIndex = 0;
                    Sprite sprite = null;
                    if (tilesets.floorKey(temp) != null) {
                        startIndex = tilesets.floorKey(temp);
                        sprite = tilesets.get(tilesets.floorKey(temp));
                    } else {
                        startIndex = tilesets.ceilingKey(temp);
                        sprite = tilesets.get(tilesets.ceilingKey(temp));
                    }
                    int tempColumn = tileColumns.floorKey(temp) != null
                            ? tileColumns.get(tileColumns.floorKey(temp))
                            : tileColumns.get(tileColumns.ceilingKey(temp));
                    System.out.println("In TileMapNorm: " + (temp - startIndex));
                    if (temp - startIndex == 1073741287) {
                        System.out.println("In TileMapNorm: " + (temp - startIndex));
                    }
                    blocks.add(new NormBlock(sprite.getSpriteImageFromSheet(((temp - startIndex) % tempColumn),
                            ((temp - startIndex) / tempColumn)
                    ), new Vector2f(j * tileWidth, i  * tileHeight), tileWidth, tileHeight));
                }
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        for (Block block : blocks) {
            block.render(gc);
        }
    }

}
