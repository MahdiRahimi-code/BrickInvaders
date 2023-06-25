import processing.core.PApplet;
import processing.core.PImage;

import java.sql.*;
import java.util.ArrayList;
import ddf.minim.*;

public class Main extends PApplet {
    PImage backgroundImage;
    private boolean gameEnded = false;
    public static int bestScore;
    public static PApplet processing;
    public static ArrayList<Block> blocks = new ArrayList<>();
    public static ArrayList<Bullet> bullets = new ArrayList<>();
    public int score = 0;
    private Block collidedBlock;
    private Bullet collidedBullet;
    public static boolean bossFight = false;
    private static Boss fboss;
    public static boolean gameWon = false;
    private int livePercentage = 100;

    Minim minim;
    AudioPlayer music;

    public static void main(String[] args) {
        PApplet.main("Main", args);
    }

    @Override
    public void setup() {
        processing = this;
        Block.makeBlocks();

        // Load the music file and start playing it
        minim = new Minim(this);
        // music = minim.loadFile("C:\\Users\\AceR\\Desktop\\Java\\BrickInvaders\\04
        // Comptine d'un autre ete, l'apres.mp3");
        // music.loop();

        bestScore = getBestScore();
    }

    @Override
    public void settings() {
        size(400, 700);
    }

    @Override
    public void draw() {

        background(0);
        keyPressed();
        moveBlocks();
        moveBullets();
        stroke(225);
        strokeWeight(1);
        line(0, 600, 440, 600); // game line
        noStroke();

        for (Block b : blocks) {
            showBlock(b.getBlockX(), b.getBlockY(), b);
        }

        if (mousePressed) {
            shoot();
        }

        for (Bullet bullet : bullets) {
            showBullet(bullet.bulletX, bullet.bulletY, bullet);
        }

        if (isBulletCollided()) {
            bullets.remove(collidedBullet);
            collidedBlock.setBlockResistance(collidedBlock.getBlockResistance() - 1);
            if (collidedBlock.getBlockResistance() == 0) {
                blocks.remove(collidedBlock);
                score++;
            }
        }

        if (isShipCollided()) {
            if (livePercentage > 0) {
                background(232, 9, 9);
                livePercentage -= 1;
            } else {
                delay(1000);
                gameLost();
                if (score > bestScore) {
                    setBestScore(score);
                }

                // Stop the music when the game is lost
                music.pause();

                return;
            }
        }

        Ship ship = new Ship(600, 255, 0, 0);
        ship.makeAndShowObjects(mouseX, 600, 20, 30, 255, 0, 0);

        // if (blocks.isEmpty()) {
        // // All blocks have been removed - end the game
        // GameWon();
        //
        // setBestScore(score);
        //
        // // Stop the music when the game is won
        // music.pause();
        //
        // gameEnded = true;
        // return ;
        // }

        if (!gameEnded) {
            // update score display
            fill(255);
            textSize(20);
            text("Score : " + score, 100, 100);
            text("Life : " + livePercentage + "%", 100, 70);
        }

        if (blocks.size() == 0 && !bossFight) {
            delay(2000);
            Boss boss = new Boss(20, -60, 255, 0, 0);
            blocks.add(boss);
            fboss = boss;
            bossFight = true;
        }

        if (bossFight && blocks.size() == 0) {
            gameEnded = true;
            gameWon = true;
        }

        if (gameWon) {
            GameWon();

            setBestScore(score);

            // Stop the music when the game is won
            music.pause();

            gameEnded = true;

            return;
        }

    }

    public void moveBlocks() {
        if (!bossFight) {
            for (int i = blocks.size() - 1; i >= 0; i--) {
                Block b = blocks.get(i);

                // Move the block downwards
                b.setBlockY(b.getBlockY() + 2);

                // Check if the block has gone off the bottom of the screen
                if (b.getBlockY() > 600) {
                    // Remove the block from the ArrayList
                    blocks.remove(i);
                    score++;
                }
            }
        } else {
            fboss.blockY = fboss.blockY + 1;
            if (fboss.blockY > 700) {
                score += 50;
                gameEnded = true;
            }
        }
    }

    public static void moveBullets() {
        for (Bullet b : bullets) {
            b.bulletY = (b.bulletY - 5);
        }
    }

    public void showBullet(int x, int y, Bullet b) {
        fill(b.bulletR, b.bulletG, b.bulletB);
        noStroke();
        circle(x, y, b.bulletRadius);
    }

    public void showBlock(int x, int y, Block b) {
        int blockwidth = 40 + (b.getBlockResistance()); // calculate the block size based on its resistance level
        int blockLength = 50 + (b.getBlockResistance());
        fill(b.getBlockR(), b.getBlockG(), b.getBlockB());
        noStroke();
        rect(x, y, blockwidth, blockLength);
    }

    public boolean isShipCollided() {
        for (Block b : blocks) {
            // calculate the bounding box for the block
            int blockLeft = b.getBlockX();
            int blockRight = b.getBlockX() + b.getBlockWidth();
            int blockTop = b.getBlockY();
            int blockBottom = b.getBlockY() + b.getBlockHeight();

            // calculate the bounding box for the human
            int shipLeft = mouseX - 40;
            int shipRight = Ship.rightHandX2;
            int shipTop = Ship.headY - 10; // adjust top boundary to account for head
            int shipBottom = Ship.bodyY + Ship.bodyHeight;

            // check for intersection between the two bounding boxes
            if (shipLeft <= blockRight && shipRight >= blockLeft && shipTop <= blockBottom - 4
                    && shipBottom >= blockTop - 4) {
                // collision detected - handle the collision
                return true;
            }

        }

        return false;
    }

    public boolean isBulletCollided() {
        boolean collided = false;
        for (Block b : blocks) {
            int blockLeft = b.getBlockX();
            int blockRight = b.getBlockX() + b.getBlockWidth();
            int blockTop = b.getBlockY();
            int blockBottom = b.getBlockY() + b.getBlockHeight();

            for (Bullet bullet : bullets) {
                int bulletLeft = bullet.bulletX - bullet.bulletRadius;
                int bulletRight = bullet.bulletX + bullet.bulletRadius;
                int bulletTop = bullet.bulletY - bullet.bulletRadius;
                int bulletBottom = bullet.bulletY + bullet.bulletRadius;

                if (bulletLeft <= blockRight && bulletRight >= blockLeft && bulletTop <= blockBottom - 4
                        && bulletBottom >= blockTop - 4) {
                    // collision detected - handle the collision
                    collided = true;
                    collidedBlock = b;
                    collidedBullet = bullet;
                }

                if (bossFight && bullet.getBulletX() >= fboss.getBlockX()
                        && bullet.getBulletX() <= fboss.getBlockX() + fboss.getBlockWidth()
                        && bullet.getBulletY() >= fboss.getBlockY()
                        && bullet.getBulletY() <= fboss.getBlockY() + fboss.getBlockHeight()) {
                    fboss.takeDamage(10);
                    return true;
                }
            }

            if (collided == true) {
                break;
            }

        }

        return collided;
    }

    public void gameLost() {
        background(225);
        fill(255, 0, 0);
        textSize(50);
        textAlign(CENTER);
        text("Game Over", 200, 350);

        fill(0);
        textSize(30);
        text("Your score : " + score, 200, 400);

        fill(255, 0, 0);
        textSize(30);
        text("Best Score : " + bestScore, 200, 440);
    }

    public void keyPressed() {
        if (key == '1') {
            backgroundImage = loadImage("C:\\Users\\AceR\\Desktop\\Java\\BrickInvaders\\image1.jpg");
            background(backgroundImage);
        } else if (key == '2') {
            backgroundImage = loadImage("C:\\Users\\AceR\\Desktop\\Java\\BrickInvaders\\image2.jpg");
            background(backgroundImage);
        } else if (key == '3') {
            backgroundImage = loadImage("C:\\Users\\AceR\\Desktop\\Java\\BrickInvaders\\image3.jpg");
            background(backgroundImage);
        } else if (key == '0') {
            background(0);
        }
    }

    public void GameWon() {
        background(225);
        fill(0, 255, 0);
        textSize(50);
        textAlign(CENTER);
        text("Game Won", 200, 350);

        fill(0);
        textSize(30);
        text("Your score : " + score, 200, 400);
    }

    public static void setBestScore(int score) {
        bestScore = score;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dogeup", "root",
                    "M13831383mR");
            Statement statement = connection.createStatement();
            statement.execute("USE dogeup");

            statement.executeUpdate("DELETE FROM score");

            String sqlQuery = "INSERT INTO score VALUES (?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setInt(1, bestScore);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static int getBestScore() {
        int score = 0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dogeup", "root",
                    "M13831383mR");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM score");
            while (resultSet.next()) {
                score = resultSet.getInt("Highscore");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return score;
    }

    public static void shoot() {
        Bullet bullet = new Bullet(processing.mouseX + 10, 510, 10, 29, 232, 26);
        bullets.add(bullet);
    }

}