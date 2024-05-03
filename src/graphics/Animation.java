package graphics;

import javafx.scene.image.Image;

public class Animation {
    private Image[] frames;
    private int currentFrame;
    private int totalFrames;
    private int updateCount;
    private int updatesPerFrameChange;
    private int timesPlayed;

    public Animation(Image[] frames) {
        timesPlayed = 0;
        setFrames(frames);
    }

    public Animation() {
        timesPlayed = 0;
    }

    public Image[] getFrames() {
        return frames;
    }

    public void setFrames(Image[] frames) {
        this.frames = frames;
        currentFrame = 0;
        updateCount = 0;
        timesPlayed = 0;
        updatesPerFrameChange = 2;
        totalFrames = frames.length;
    }

    public void setFrames(Image frame) {
        Image[] frames = {frame};
        setFrames(frames);
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public int getTotalFrames() {
        return totalFrames;
    }

    public void setTotalFrames(int i) {
        totalFrames = i;
    }

    public int getTimesPlayed() {
        return timesPlayed;
    }

    public void setTimesPlayed(int timesPlayed) {
        this.timesPlayed = timesPlayed;
    }

    public void update() {
        if (updatesPerFrameChange == -1) {
            return;
        }
        updateCount++;
        if (updateCount == updatesPerFrameChange) {
            currentFrame++;
            updateCount = 0;
        }
        if (currentFrame == totalFrames) {
            currentFrame = 0;
            timesPlayed++;
        }
    }

    public int getUpdatesPerFrameChange() {
        return updatesPerFrameChange;
    }

    public void setUpdatesPerFrameChange(int i) {
        updatesPerFrameChange = i;
    }

    public int getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(int updateCount) {
        this.updateCount = updateCount;
    }

    public int getFrame() {
        return currentFrame;
    }

    public void setFrame(int i) {
        currentFrame = i;
    }

    public Image getCurrentAnimationFrame() {
        return frames[currentFrame];
    }

    public boolean hasPlayedOnce() {
        return timesPlayed > 0;
    }

    public boolean hasPlayed(int i) {
        return timesPlayed == i;
    }

}
