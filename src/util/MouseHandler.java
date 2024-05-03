package util;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class MouseHandler implements EventHandler<MouseEvent> {

    public boolean isLeftPressed;
    public boolean isRightPressed;
    public MouseHandler(Canvas canvas) {
        canvas.setFocusTraversable(true); // Ensure the canvas can receive mouse focus
        canvas.setOnMouseClicked(this);     // Set this class as the mouse clicked event handler
        canvas.setOnMouseDragged(this);     // Set this class as the mouse dragged event handler
        canvas.setOnMousePressed(this);
        canvas.setOnMouseReleased(this);
    }

    public boolean isLeftPressed() {
        return isLeftPressed;
    }

    public void setLeftPressed(boolean leftPressed) {
        isLeftPressed = leftPressed;
    }

    public boolean isRightPressed() {
        return isRightPressed;
    }

    public void setRightPressed(boolean rightPressed) {
        isRightPressed = rightPressed;
    }

    @Override
    public void handle(MouseEvent event) {
        // Handle mouse events
                MouseButton button = event.getButton();
        switch (event.getEventType().getName()) {
            case "MOUSE_PRESSED":
                if (button == MouseButton.PRIMARY) {
                    setLeftPressed(true);
                    System.out.println("Left button clicked at: " + event.getX() + ", " + event.getY());
                } else if (button == MouseButton.SECONDARY) {
                    setRightPressed(true);
                    System.out.println("Right button clicked at: " + event.getX() + ", " + event.getY());
                } else if (button == MouseButton.MIDDLE) {
                    System.out.println("Middle button clicked at: " + event.getX() + ", " + event.getY());
                }


                break;
            case "MOUSE_RELEASED":
                if (button == MouseButton.PRIMARY) {
                    setLeftPressed(false);
                    System.out.println("Left button released at: " + event.getX() + ", " + event.getY());
                } else if (button == MouseButton.SECONDARY) {
                    setRightPressed(false);
                    System.out.println("Right button released at: " + event.getX() + ", " + event.getY());
                } else if (button == MouseButton.MIDDLE) {
                    System.out.println("Middle button released at: " + event.getX() + ", " + event.getY());
                }
            case "MOUSE_DRAGGED":
                System.out.println("Mouse dragged to: " + event.getX() + ", " + event.getY());
                break;
            default:
                break;
        }
    }

}