package entity;

import game.GameLauncher;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import states.PlayState;
import util.KeyHandler;
import util.MouseHandler;
import util.Vector2f;

public class Player extends Entity {
    public Player(Sprite sprite, Vector2f origin, int size) {
        super(sprite, origin, size);
        this.boundary.setBoundingBoxHeight(42);
        this.boundary.setBoundingBoxWidth(42);
        this.boundary.setXOffset(11);
        this.boundary.setYOffset(22);
    }

    private void move() {
        if (this.isMoveUp) {
            this.setDy(this.dy - this.acceleration);
            if (this.dy < -maxSpeed) {
                this.setDy(-maxSpeed);
            }
        } else {
            if (this.dy < 0) {
                this.setDy(this.dy + this.deceleration);
                if (this.dy > 0) {
                    this.setDy(0);
                }
            }
        }
        if (this.isMoveDown) {
            this.setDy(this.dy + this.acceleration);
            if (this.dy > maxSpeed) {
                this.setDy(maxSpeed);
            }
        } else {
            if (this.dy > 0) {
                this.setDy(this.dy - this.deceleration);
                if (this.dy < 0) {
                    this.setDy(0);
                }
            }
        }
        if (this.isMoveLeft) {
            this.setDx(this.dx - this.acceleration);
            if (this.dx < -maxSpeed) {
                this.setDx(-maxSpeed);
            }
        } else {
            if (this.dx < 0) {
                this.setDx(this.dx + this.deceleration);
                if (this.dx > 0) {
                    this.setDx(0);
                }
            }
        }
        if (this.isMoveRight) {
            this.setDx(this.dx + this.acceleration);
            if (this.dx > maxSpeed) {
                this.setDx(maxSpeed);
            }
        } else {
            if (this.dx > 0) {
                this.setDx(this.dx - this.deceleration);
                if (this.dx < 0) {
                    this.setDx(0);
                }
            }
        }
    }

    private void resetPlayerPosition() {
        System.out.println("Resting player position");
        position.setVectorCoordinateX((float) GameLauncher.WIDTH / 2 - 32);
        PlayState.getMapDimensions().setVectorCoordinateX(0);
        position.setVectorCoordinateY((float) GameLauncher.HEIGHT / 2 - 32);
        PlayState.getMapDimensions().setVectorCoordinateY(0);
    }

    public void update(Enemy enemy) {
//        System.out.println((int)this.getPosition().vectorCoordinateX + " " + (int)this.getPosition().vectorCoordinateY);
        super.update();
        if (!fallen) {
            move();

            if (this.boundary.isBoundingBoxColliding(enemy.getBoundary()) && this.isAttack) {
                System.out.println("Player hit ab enemy");
            }
            if (!tileCollision.isEntityCollidingWithTile(dx, 0)) {
                PlayState.mapDimensions.vectorCoordinateX += dx;
                position.vectorCoordinateX += dx;
                setXCollision(false);
            } else {
                setXCollision(true);
            }
            if (!tileCollision.isEntityCollidingWithTile(0, dy)) {
                PlayState.mapDimensions.vectorCoordinateY += dy;
                position.vectorCoordinateY += dy;
                setYCollision(false);
            }else {
                setYCollision(true);
            }
        } else {
            setXCollision(false);
            setYCollision(false);
            if (animation.hasPlayedOnce()) {
                resetPlayerPosition();
                fallen = false;
            }
        }
//        System.out.println("dx: " + dx + " dy: " + dy);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setStroke(Color.GREEN);
        gc.setLineWidth(2);
        gc.strokeRect((int) position.getWorldVar().vectorCoordinateX + this.boundary.getXOffset(),
                      (int) position.getWorldVar().vectorCoordinateY + this.boundary.getYOffset(),
                      this.boundary.getBoundingBoxWidth(),
                      this.boundary.getBoundingBoxHeight()
        );
        if (this.isAttack) {
            gc.setStroke(Color.RED);
            gc.setLineWidth(2);
            gc.strokeRect((int) this.getHitBoundary().getBoundingBoxPosition().getWorldVar().getVectorCoordinateX() + this.getHitBoundary().getXOffset(),
                          (int) this.getHitBoundary().getBoundingBoxPosition().getWorldVar().getVectorCoordinateY() + this.getHitBoundary().getYOffset(),
                          this.getHitBoundary().getBoundingBoxWidth(),
                          this.getHitBoundary().getBoundingBoxHeight()
            );
        }
        gc.drawImage(animation.getCurrentAnimationFrame(),
                     (int) (position.getWorldVar().vectorCoordinateX),
                     (int) (position.getWorldVar().vectorCoordinateY),
                     size,
                     size
        );
    }

    public void input(MouseHandler mouse, KeyHandler key) {
        if (!fallen) {
            if (key.isUpPressed) {
                this.currentDirection = EntityDirection.UP;
                this.isMoveUp = true;
            } else {
                this.isMoveUp = false;
            }
            if (key.isDownPressed) {
                this.currentDirection = EntityDirection.DOWN;
                this.isMoveDown = true;
            } else {
                this.isMoveDown = false;
            }
            if (key.isLeftPressed) {
                this.currentDirection = EntityDirection.LEFT;
                this.isMoveLeft = true;
            } else {
                this.isMoveLeft = false;
            }
            if (key.isRightPressed) {
                this.currentDirection = EntityDirection.RIGHT;
                this.isMoveRight = true;
            } else {
                this.isMoveRight = false;
            }
            if (key.allKeysReleased()) {
                this.currentAnimation = AnimationType.IDLE;
            }
            if (mouse.isLeftPressed()) {
                this.isAttack = true;
                this.currentAnimation = AnimationType.ATTACK;
            } else {
                this.isAttack = false;
            }
        } else {
            this.currentDirection = EntityDirection.FALLEN;
            this.isAttack = false;
            this.isMoveRight = false;
            this.isMoveLeft = false;
            this.isMoveUp = false;
            this.isMoveDown = false;
        }
    }

}
