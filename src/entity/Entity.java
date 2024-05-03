package entity;

import graphics.Animation;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import util.*;

public abstract class Entity {
    protected boolean isMoveUp, isMoveDown, isMoveLeft, isMoveRight, fallen;
    protected Animation animation;
    protected Sprite sprite;
    protected Vector2f position;
    protected int size;
    protected EntityDirection currentDirection;
    protected AnimationType currentAnimation;
    protected AABB hitBoundary;
    protected AABB boundary;
    protected float dx;
    protected float dy;
    protected float maxSpeed = 5f;
    protected float acceleration = 4f;
    protected float deceleration = 0.5f;
    protected TileCollision tileCollision;
    protected boolean isAttack;

    public boolean isXCollision= false;

    public boolean isAttack() {
        return isAttack;
    }

    public void setAttack(boolean attack) {
        isAttack = attack;
    }

    public boolean isXCollision() {
        return isXCollision;
    }

    public void setXCollision(boolean XCollision) {
        isXCollision = XCollision;
    }

    public boolean isYCollision() {
        return isYCollision;
    }

    public void setYCollision(boolean YCollision) {
        isYCollision = YCollision;
    }

    public boolean isYCollision= false;


    public Entity(Sprite sprite, Vector2f origin, int size) {
        this.setSprite(sprite);
        this.setPosition(origin);
        this.setSize(size);
        this.setBoundary(new AABB(origin, size, size));
        this.setHitBoundary(new AABB(origin, size, size));
        this.hitBoundary.setXOffset((float) size /2);
        this.setAnimation(new Animation());
        this.setCurrentDirection(EntityDirection.RIGHT);
        this.setAnimation(AnimationType.MOVE_RIGHT, sprite.getSpriteArray(EntityDirection.RIGHT.ordinal()), 10);
        this.setTileCollision(new TileCollision(this));
    }

    public util.TileCollision getTileCollision() {
        return this.tileCollision;
    }

    public void setTileCollision(util.TileCollision tileCollision) {
        this.tileCollision = tileCollision;
    }

    public void setAnimation(AnimationType animationType, Image[] frames, int delay) {
        this.setCurrentAnimation(animationType);
        this.animation.setFrames(frames);
        this.animation.setUpdatesPerFrameChange(delay);
    }

    public void setAnimation(AnimationType animationType, Image frame, int delay) {
        this.currentAnimation = animationType;
        this.animation.setFrames(frame);
        this.animation.setUpdatesPerFrameChange(delay);
    }

    public void setHitBoxDirection() {
        switch (currentDirection) {
            case UP:
                this.hitBoundary.setYOffset((float) -size / 2);
                this.hitBoundary.setXOffset(0);
                break;
            case DOWN:
                this.hitBoundary.setYOffset((float) size / 2);
                this.hitBoundary.setXOffset(0);
                break;
            case LEFT:
                this.hitBoundary.setXOffset((float) -size / 2);
                this.hitBoundary.setYOffset(0);
                break;
            case RIGHT:
                this.hitBoundary.setXOffset((float) size /2);
                this.hitBoundary.setYOffset(0);
                break;
        }
    }

    public void update() {
        this.handleEntityAnimation();
        this.setHitBoxDirection();
        this.animation.update();
    }

    private void handleEntityAnimation() {
        switch (currentDirection) {
            case UP:
                if (currentAnimation != AnimationType.MOVE_UP || animation.getUpdatesPerFrameChange() == -1) {
                    this.setAnimation(AnimationType.MOVE_UP, sprite.getSpriteArray(EntityDirection.UP.ordinal()), 5);
                }
                break;
            case DOWN:
                if (currentAnimation != AnimationType.MOVE_DOWN || animation.getUpdatesPerFrameChange() == -1) {
                    this.setAnimation(AnimationType.MOVE_DOWN,
                                      sprite.getSpriteArray(EntityDirection.DOWN.ordinal()),
                                      5
                    );
                }
                break;
            case RIGHT:
                if (currentAnimation != AnimationType.MOVE_RIGHT || animation.getUpdatesPerFrameChange() == -1) {
                    this.setAnimation(AnimationType.MOVE_RIGHT,
                                      sprite.getSpriteArray(EntityDirection.RIGHT.ordinal()),
                                      5
                    );
                }
                break;
            case LEFT:
                if (currentAnimation != AnimationType.MOVE_LEFT || animation.getUpdatesPerFrameChange() == -1) {
                    this.setAnimation(AnimationType.MOVE_LEFT,
                                      sprite.getSpriteArray(EntityDirection.LEFT.ordinal()),
                                      5
                    );
                }
                break;
            case FALLEN:
                if (currentAnimation != AnimationType.FALLING || animation.getUpdatesPerFrameChange() == -1) {
                    this.setAnimation(AnimationType.FALLING,
                                      sprite.getSpriteArray(EntityDirection.FALLEN.ordinal()),
                                      15
                    );
                }
                break;
            default:
                this.setAnimation(currentAnimation, sprite.getSpriteArray(currentDirection.ordinal(), 0), -1);
                break;
        }
    }

    public void input(KeyHandler key, MouseHandler mouse) {
    }

    public abstract void render(GraphicsContext gc);

    public int getSize() {
        return size;
    }

    public void setSize(int i) {
        this.size = i;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public boolean isMoveUp() {
        return isMoveUp;
    }

    public void setMoveUp(boolean moveUp) {
        this.isMoveUp = moveUp;
    }

    public boolean isMoveDown() {
        return isMoveDown;
    }

    public void setMoveDown(boolean moveDown) {
        this.isMoveDown = moveDown;
    }

    public boolean isMoveLeft() {
        return isMoveLeft;
    }

    public void setMoveLeft(boolean moveLeft) {
        this.isMoveLeft = moveLeft;
    }

    public boolean isMoveRight() {
        return isMoveRight;
    }

    public void setMoveRight(boolean moveRight) {
        this.isMoveRight = moveRight;
    }

    public boolean isFallen() {
        return fallen;
    }

    public void setFallen(boolean fallen) {
        this.fallen = fallen;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public EntityDirection getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(EntityDirection currentDirection) {
        this.currentDirection = currentDirection;
    }

    public AnimationType getCurrentAnimation() {
        return currentAnimation;
    }

    public void setCurrentAnimation(AnimationType currentAnimation) {
        this.currentAnimation = currentAnimation;
    }

    public AABB getHitBoundary() {
        return hitBoundary;
    }

    public void setHitBoundary(AABB hitBoundary) {
        this.hitBoundary = hitBoundary;
    }

    public AABB getBoundary() {
        return boundary;
    }

    public void setBoundary(AABB boundary) {
        this.boundary = boundary;
    }

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public float getDeceleration() {
        return deceleration;
    }

    public void setDeceleration(float deceleration) {
        this.deceleration = deceleration;
    }

    public enum EntityDirection {
        RIGHT,
        LEFT,
        DOWN,
        UP,
        FALLEN
    }

    public enum AnimationType {
        IDLE,
        MOVE_UP,
        MOVE_DOWN,
        MOVE_RIGHT,
        MOVE_LEFT,
        FALLING,
        ATTACK
    }

}
