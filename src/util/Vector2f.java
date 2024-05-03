package util;

public class Vector2f {
    public static float worldCoordinateX;
    public static float worldCoordinateY;
    public float vectorCoordinateX;
    public float vectorCoordinateY;

    public Vector2f() {
        vectorCoordinateX = 0;
        vectorCoordinateY = 0;
    }

    public Vector2f(Vector2f vec) {
        new Vector2f(vec.vectorCoordinateX, vec.vectorCoordinateY);
    }

    public static float getWorldCoordinateX() {
        return worldCoordinateX;
    }

    public static void setWorldCoordinateX(float worldCoordinateX) {
        Vector2f.worldCoordinateX = worldCoordinateX;
    }

    public static float getWorldCoordinateY() {
        return worldCoordinateY;
    }

    public static void setWorldCoordinateY(float worldCoordinateY) {
        Vector2f.worldCoordinateY = worldCoordinateY;
    }

    public Vector2f(float vectorCoordinateX, float vectorCoordinateY) {
        this.vectorCoordinateX = vectorCoordinateX;
        this.vectorCoordinateY = vectorCoordinateY;
    }

    public static void setWorldCoordinates(float x, float y) {
        worldCoordinateX = x;
        worldCoordinateY = y;
    }

    public Vector2f getWorldVar() {
        return new Vector2f(vectorCoordinateX - worldCoordinateX, vectorCoordinateY - worldCoordinateY);
    }

    @Override
    public String toString() {
        return vectorCoordinateX/64 + ", " + vectorCoordinateY/64;
    }

    public float getVectorCoordinateX() {
        return vectorCoordinateX;
    }

    public void setVectorCoordinateX(float f) {
        vectorCoordinateX = f;
    }

    public float getVectorCoordinateY() {
        return vectorCoordinateY;
    }

    public void setVectorCoordinateY(float f) {
        vectorCoordinateY = f;
    }



}
