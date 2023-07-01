public class Boss extends Block {

    public Boss(int x, int y, int r, int g, int b) {
        super(x, y, r, g, b);
        blockX = x;
        blockY = y;
        blockWidth = 100;
        blockHeight = 100;
        blockResistance = 100;
    }

    public void takeDamage(int damage) {
        blockResistance -= damage;
        if (blockResistance <= 0) {
            Main.gameWon = true;
            Main.bossFight = false;
            Main.gameWon = true;
        }
    }

}