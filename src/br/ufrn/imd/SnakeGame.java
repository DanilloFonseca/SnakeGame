package br.ufrn.imd;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {

    private final int boardWidth;
    private final int boardHeight;
    private final int brickSize = 25;
    private final SnakeHead snakeHead;
    private final ArrayList<SnakeBody> snakeBody;
    private final Apple apple;
    private final Timer loop;
    private int speedX;
    private int speedY;
    private boolean gameOver = false;

    public SnakeGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.black);

        snakeHead = new SnakeHead(5, 5, brickSize);
        snakeBody = new ArrayList<>();

        apple = new Apple(brickSize);
        randomizeAppleLocation();

        speedX = 0;
        speedY = 1;

        loop = new Timer(150, this);
        loop.start();
        addKeyListener(this);
        setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        apple.draw(g, Color.RED);
        snakeHead.draw(g, Color.GREEN);

        for (SnakeBody bodyPart : snakeBody) {
            bodyPart.draw(g, Color.GREEN);
        }

        g.setFont(new Font("Times", Font.PLAIN, 25));
        if (gameOver) {
            g.setColor(Color.red);
            g.drawString("HAHAHAHHAHA GAME OVER: " + snakeBody.size(), brickSize - 25, brickSize);
        } else {
            g.drawString("Score: " + snakeBody.size(), brickSize - 25, brickSize);
        }
    }

    private void randomizeAppleLocation() {
        apple.setLocation(new Random().nextInt(boardWidth / brickSize), new Random().nextInt(boardHeight / brickSize));
    }



//--------------problems with checkCollision()--------------

    private void checkCollision() {
        if (snakeHead.sameSpace(apple)) {
            snakeBody.add(new SnakeBody(snakeHead.getX(), snakeHead.getY(), brickSize));
            randomizeAppleLocation();
        }
    
        /* 
        for (SnakeBody bodyPart : snakeBody) {
            if (snakeHead.sameSpace(bodyPart)) {
                gameOver = true;
            }
        }
    
        */

        if (snakeHead.getX() < 0 || snakeHead.getX() >= boardWidth / brickSize ||
                snakeHead.getY() < 0 || snakeHead.getY() >= boardHeight / brickSize) {
            gameOver = true;
        }
    }

//----------------------problems above----------------------------------


    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        checkCollision();
        repaint();

        if (gameOver) {
            loop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        handleKeyPress(e.getKeyCode());
    }

    private void handleKeyPress(int keyCode) {
        if (keyCode == KeyEvent.VK_UP && speedY != 1) {
            speedX = 0;
            speedY = -1;
        } else if (keyCode == KeyEvent.VK_DOWN && speedY != -1) {
            speedX = 0;
            speedY = 1;
        } else if (keyCode == KeyEvent.VK_LEFT && speedX != 1) {
            speedX = -1;
            speedY = 0;
        } else if (keyCode == KeyEvent.VK_RIGHT && speedX != -1) {
            speedX = 1;
            speedY = 0;
        }
    }

    private void move() {
        if (gameOver) {
            return;
        }

        snakeHead.move(speedX, speedY);

        for (int i = snakeBody.size() - 1; i > 0; i--) {
            SnakeBody currentBodyPart = snakeBody.get(i);
            SnakeBody previousBodyPart = snakeBody.get(i - 1);
            currentBodyPart.setX(previousBodyPart.getX());
            currentBodyPart.setY(previousBodyPart.getY());
        }

        if (!snakeBody.isEmpty()) {
            SnakeBody firstBodyPart = snakeBody.get(0);
            firstBodyPart.setX(snakeHead.getX());
            firstBodyPart.setY(snakeHead.getY());
        }
    }



    // These methods will not be used, so there are no necessity to rewrite them!

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

