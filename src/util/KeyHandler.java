package util;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

public class KeyHandler implements EventHandler<KeyEvent> {
    public boolean isUpPressed;
    public boolean isDownPressed;
    public boolean isLeftPressed;
    public boolean isRightPressed;
    public boolean isEscapePressed;

    public KeyHandler(Canvas canvas) {
        canvas.setFocusTraversable(true); // Ensure the canvas can receive keyboard focus
        canvas.setOnKeyPressed(this);     // Set this class as the key pressed event handler
        canvas.setOnKeyReleased(this);    // Set this class as the key released event handler
    }

    public boolean allKeysReleased() {
        return !isUpPressed && !isDownPressed && !isLeftPressed && !isRightPressed;
    }

    public boolean isUpPressed() {
        return isUpPressed;
    }

    public void setUpPressed(boolean upPressed) {
        this.isUpPressed = upPressed;
    }

    public boolean isDownPressed() {
        return isDownPressed;
    }

    public void setDownPressed(boolean downPressed) {
        this.isDownPressed = downPressed;
    }

    public boolean isLeftPressed() {
        return isLeftPressed;
    }

    public void setLeftPressed(boolean leftPressed) {
        this.isLeftPressed = leftPressed;
    }

    public boolean isRightPressed() {
        return isRightPressed;
    }

    public void setRightPressed(boolean rightPressed) {
        this.isRightPressed = rightPressed;
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getEventType().getName()) {
            case "KEY_PRESSED":
                switch (event.getCode()) {
                    case W:
                        isUpPressed = true;
                        break;
                    case S:
                        isDownPressed = true;
                        break;
                    case A:
                        isLeftPressed = true;
                        break;
                    case D:
                        isRightPressed = true;
                        break;
                }
                break;
            case "KEY_RELEASED":
                switch (event.getCode()) {
                    case W:
                        isUpPressed = false;
                        break;
                    case S:
                        isDownPressed = false;
                        break;
                    case A:
                        isLeftPressed = false;
                        break;
                    case D:
                        isRightPressed = false;
                        break;

                    case ESCAPE:
                        isEscapePressed = true;
                        break;
                }
                break;
        }
    }

}