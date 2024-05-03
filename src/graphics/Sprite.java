package graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import util.Vector2f;

import java.util.ArrayList;

public class Sprite {
    private final int TILE_SIZE = 32;
    public int width;
    public int height;
    private Image spriteSheetImage = null;
    private Image[][] spritesInSheet;
    private int spriteWidthInSheet;
    private int spriteHeightInSheet;

    public Sprite(String file) {
        System.out.println("Loading: " + file + "...");
        spriteSheetImage = new Image(file);
        width = TILE_SIZE;
        height = TILE_SIZE;
        spriteWidthInSheet = (int) (spriteSheetImage.getWidth() / width);
        spriteHeightInSheet = (int) (spriteSheetImage.getHeight() / height);
        loadSpriteArray();
    }

    public Sprite(String file, int width, int height) {
        System.out.println("Loading: " + file + "...");
        spriteSheetImage = new Image(file);
        this.width = width;
        this.height = height;
        spriteWidthInSheet = (int) (spriteSheetImage.getWidth() / width);
        spriteHeightInSheet = (int) (spriteSheetImage.getHeight() / height);
        loadSpriteArray();
    }

    public static void drawArray(GraphicsContext gc,
                                 ArrayList<Image> img,
                                 Vector2f pos,
                                 int width,
                                 int height,
                                 int xOffset,
                                 int yOffset) {
        float x = pos.vectorCoordinateX;
        float y = pos.vectorCoordinateY;
        for (int i = 0; i < img.size(); i++) {
            if (img.get(i) != null) {
                gc.drawImage(img.get(i), x, y);
            }
            x += xOffset;
            if (x >= width) {
                x = pos.vectorCoordinateX;
                y += yOffset;
            }
        }
    }

    public static void drawText(GraphicsContext gc, Font font, String word, Vector2f pos) {
        gc.setFont(font);
        gc.setFill(Color.BLACK);
        gc.fillText(word, pos.vectorCoordinateX, pos.vectorCoordinateY);
    }

    public static void drawText(GraphicsContext gc, Font font, String word, Vector2f pos, int size) {
        Font newFont = new Font(font.getName(), size);
        gc.setFont(newFont);
        gc.setFill(Color.BLACK);
        gc.fillText(word, pos.vectorCoordinateX, pos.vectorCoordinateY);
    }

    public static void drawText(GraphicsContext gc, Font font, String word, Vector2f pos, Color color) {
        gc.setFont(font);
        gc.setFill(color);
        gc.fillText(word, pos.vectorCoordinateX, pos.vectorCoordinateY);
    }

    public static void drawText(GraphicsContext gc, Font font, String word, Vector2f pos, int size, Color color) {
        Font newFont = new Font(font.getName(), size);
        gc.setFont(newFont);
        gc.setFill(color);
        gc.fillText(word, pos.vectorCoordinateX, pos.vectorCoordinateY);
    }

    public int getTILE_SIZE() {
        return TILE_SIZE;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int i) {
        width = i;
        spriteWidthInSheet = (int) (spriteSheetImage.getWidth() / width);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int i) {
        height = i;
        spriteHeightInSheet = (int) (spriteSheetImage.getHeight() / height);
    }

    public int getSpriteHeightInSheet() {
        return spriteHeightInSheet;
    }

    public void setSpriteHeightInSheet(int spriteHeightInSheet) {
        this.spriteHeightInSheet = spriteHeightInSheet;
    }

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    private void loadSpriteArray() {
        spritesInSheet = new Image[spriteHeightInSheet][spriteWidthInSheet];
        for (int y = 0; y < spriteHeightInSheet; y++) {
            for (int x = 0; x < spriteWidthInSheet; x++) {
                spritesInSheet[y][x] = getSpriteImageFromSheet(x, y);
            }
        }
    }

    public Image getSpriteSheetImage() {
        return spriteSheetImage;
    }

    public void setSpriteSheetImage(Image spriteSheetImage) {
        this.spriteSheetImage = spriteSheetImage;
    }

    public WritableImage getSpriteImageFromSheet(int x, int y) {
        // Check if the sprite sheet is loaded
        if (spriteSheetImage == null) {
            System.out.println("Sprite sheet is not loaded.");
            return null;
        }
        // Calculate the coordinates of the sprite within the sprite sheet
        int xPos = x * width;
        int yPos = y * height;
        // Create a new image for the sprite
//        WritableImage sprite = new WritableImage(width, height);
//        byte[] buffer = new byte[width * height * 4]; // Assuming 4 bytes per pixel
//        SPRITE_SHEET.getPixelReader().getPixels(xPos, yPos, width, height, PixelFormat.getByteBgraInstance(), buffer, 0, width * 4);
//        sprite.getPixelWriter().setPixels(0, 0, width, height, PixelFormat.getByteBgraInstance(), buffer, 0, width * 4);
//        return sprite;
        return new WritableImage(spriteSheetImage.getPixelReader(), xPos, yPos, width, height);
    }

    public Image getSpriteArray(int x, int y) {
        return spritesInSheet[x][y];
    }

    public Image[] getSpriteArray(int x) {
        return spritesInSheet[x];
    }

    public Image[][] getSpritesInSheet() {
        return spritesInSheet;
    }

    public void setSpritesInSheet(Image[][] spritesInSheet) {
        this.spritesInSheet = spritesInSheet;
    }

    public int getSpriteWidthInSheet() {
        return spriteWidthInSheet;
    }

    public void setSpriteWidthInSheet(int spriteWidthInSheet) {
        this.spriteWidthInSheet = spriteWidthInSheet;
    }

}