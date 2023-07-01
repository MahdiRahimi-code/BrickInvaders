public class Bullet {
    public int bulletX;
    public int bulletY;
    public int bulletRadius;
    public int bulletR;
    public int bulletG;
    public int bulletB;

    public Bullet(int bulletX, int bulletY, int bulletRadius, int bulletR, int bulletG, int bulletB) {
        this.bulletB = bulletB;
        this.bulletG = bulletG;
        this.bulletR = bulletR;
        this.bulletX = bulletX;
        this.bulletY = bulletY;
        this.bulletRadius = bulletRadius;
    }

    public int getBulletX() {
        return bulletX;
    }

    public int getBulletY() {
        return bulletY;
    }

    public int getBulletRadius() {
        return bulletRadius;
    }

    public int getBulletR() {
        return bulletR;
    }

    public int getBulletG() {
        return bulletG;
    }

    public int getBulletB() {
        return bulletB;
    }
}