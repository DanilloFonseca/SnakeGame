package br.ufrn.imd;

import java.awt.*;

public class SnakeHead extends Brick {

    public SnakeHead(int x, int y, int brickSize) {
        super(x, y, brickSize);
    }

    public void move(int speedX, int speedY) {
        setX(getX() + speedX);
        setY(getY() + speedY);
    }
}

