package br.ufrn.imd;

import java.awt.*;

public class Brick {
    protected int x;
    protected int y;
    private final int brickSize;

    public Brick(int x, int y, int brickSize) {
        this.x = x;
        this.y = y;
        this.brickSize = brickSize;
    }

    public void draw(Graphics g, Color color) {
        g.setColor(color);
        g.fillRect(x * brickSize, y * brickSize, brickSize, brickSize);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean sameSpace(Brick other) {
        return (this.y == other.y) && (this.x == other.x);
    }

    public void move(int speedX, int speedY) {
        this.x += speedX;
        this.y += speedY;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}