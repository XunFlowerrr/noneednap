package entity;

import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import util.AABB;
import util.Vector2f;

public class Enemy extends Entity {
    private static final float MARGIN_OF_ERROR = 1f;
    private AABB sensingRange;
    private int sensingRadius;

    public Enemy(Sprite sprite, Vector2f origin, int size) {
        super(sprite, origin, size);
        this.setAcceleration(1f);
        this.setMaxSpeed(2f);
        this.setSensingRadius(350);
        this.boundary.setBoundingBoxWidth(42);
        this.boundary.setBoundingBoxHeight(20);
        this.boundary.setXOffset(11);
        this.boundary.setYOffset(40);
        this.sensingRange = new AABB(new Vector2f(origin.getVectorCoordinateX() + (float) size / 2 - (float) sensingRadius / 2,
                                                  origin.getVectorCoordinateY() + (float) size / 2 - (float) sensingRadius / 2
        ), sensingRadius);
    }

    public util.AABB getSensingRange() {
        return sensingRange;
    }

    public void setSensingRange(util.AABB sensingRange) {
        this.sensingRange = sensingRange;
    }

    public int getSensingRadius() {
        return sensingRadius;
    }

    public void setSensingRadius(int sensingRadius) {
        this.sensingRadius = sensingRadius;
    }

    public void update(Player player) {
        super.update();
        move(player);
        if (!this.getTileCollision().isEntityCollidingWithTile(this.getDx() , 0)){
            this.getSensingRange().getBoundingBoxPosition().setVectorCoordinateX(this.getSensingRange().getBoundingBoxPosition().getVectorCoordinateX() + this.getDx());
            this.getPosition().setVectorCoordinateX(this.getPosition().getVectorCoordinateX() + this.getDx());
        }

        if (!this.getTileCollision().isEntityCollidingWithTile(0, this.getDy())){
            this.getSensingRange().getBoundingBoxPosition().setVectorCoordinateY(this.getSensingRange().getBoundingBoxPosition().getVectorCoordinateY() + this.getDy());
            this.getPosition().setVectorCoordinateY(this.getPosition().getVectorCoordinateY() + this.getDy());
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setStroke(Color.RED);
        gc.setLineWidth(2);
        gc.strokeRect((int) this.position.getWorldVar().getVectorCoordinateX() + this.boundary.getXOffset(),
                      (int) this.position.getWorldVar().getVectorCoordinateY() + this.boundary.getYOffset(),
                      this.boundary.getBoundingBoxWidth(),
                      this.boundary.getBoundingBoxHeight()
        );
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);
        gc.strokeOval(this.sensingRange.getBoundingBoxPosition().getWorldVar().getVectorCoordinateX(),
                      this.sensingRange.getBoundingBoxPosition().getWorldVar().getVectorCoordinateY(),
                      this.sensingRadius,
                      this.sensingRadius
        );
        gc.drawImage(this.animation.getCurrentAnimationFrame(),
                     (int) this.position.getWorldVar().getVectorCoordinateX(),
                     (int) this.position.getWorldVar().getVectorCoordinateY(),
                     this.size,
                     this.size
        );
    }

    public void move(Player player) {
        if (this.sensingRange.isCollidingWithCircularBoundingBox(player.getBoundary())) {
//            System.out.println("Player is in range");
            if (this.getPosition().getVectorCoordinateY() > player.getPosition().getVectorCoordinateY() + MARGIN_OF_ERROR) {
                this.setDy(this.dy - this.acceleration);
                this.setMoveUp(true);
                this.setMoveDown(false);
                this.currentDirection = EntityDirection.UP;
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
            if (this.getPosition().getVectorCoordinateY() < player.getPosition().getVectorCoordinateY() - MARGIN_OF_ERROR) {
                this.setDy(this.dy + this.acceleration);
                this.setMoveDown(true);
                this.setMoveUp(false);
                this.currentDirection = EntityDirection.DOWN;
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
            if (this.getPosition().getVectorCoordinateX() > player.getPosition().getVectorCoordinateX() + MARGIN_OF_ERROR) {
                this.setDx(this.dx - this.acceleration);
                this.setMoveLeft(true);
                this.setMoveRight(false);
                this.currentDirection = EntityDirection.LEFT;

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
            if (this.getPosition().getVectorCoordinateX() < player.getPosition().getVectorCoordinateX() - MARGIN_OF_ERROR) {
                this.setDx(this.dx + this.acceleration);
                this.setMoveRight(true);
                this.setMoveLeft(false);
                this.currentDirection = EntityDirection.RIGHT;
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
        } else {
            this.setMoveUp(false);
            this.setMoveDown(false);
            this.setMoveLeft(false);
            this.setMoveRight(false);
            this.setCurrentAnimation(AnimationType.IDLE);

            this.setDx(0);
            this.setDy(0);
        }
    }

}
