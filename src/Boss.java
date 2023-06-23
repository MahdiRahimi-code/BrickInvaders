public class Boss extends Block {
    private int hp;

    public Boss(int x, int y, int r, int g, int b, int hp) {
        super(x, y, r, g, b);
        this.hp = hp;
        blockX = x;
        blockY = y;
        blockWidth = 250;
        blockHeight = 250;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            Main.gameWon = true;
            Main.bossFight = false;
            Main.gameWon = true;
        }
    }

    public void attack() {
        // add boss attack logic here
    }

}
