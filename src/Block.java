import processing.core.PApplet;

public class Block {
    protected int blockX;
    protected int blockY;
    private int blockResistance;
    public static int speedY = -70;
    protected int blockHeight = 60;
    protected int blockWidth = 30;
    private int blockR;
    private int blockG;
    private int blockB;
    public Block (int blockX ,int blockY ,int blockR ,int blockG ,int blockB){
        this.blockR = blockR;
        this.blockG = blockG;
        this.blockB = blockB;
        this.blockX = blockX;
        this.blockY = blockY;
        blockResistance=20;
    }

    public static void makeBlocks() {
        PApplet d = Main.processing;
        int[][] colors = { {255, 0, 195}, {0, 255, 0}, {0, 0, 255}, {255, 255, 0} };
        for (int i=0; i<20; i++) {
            int[] rowColors = new int[4];
            // select a random color for each block in a row
            for (int j=0; j<4; j++) {
                int randomIndex = (int) d.random(colors.length);
                rowColors[j] = randomIndex;
            }
            Main.blocks.add(new Block((int)d.random(70), (int)d.random(speedY-140,speedY),
                    colors[rowColors[0]][0], colors[rowColors[0]][1], colors[rowColors[0]][2]));
            Main.blocks.add(new Block((int)d.random(100,170), (int)d.random(speedY-140,speedY),
                    colors[rowColors[1]][0], colors[rowColors[1]][1], colors[rowColors[1]][2]));
            Main.blocks.add(new Block((int)d.random(200,270), (int)d.random(speedY-140,speedY),
                    colors[rowColors[2]][0], colors[rowColors[2]][1], colors[rowColors[2]][2]));
            Main.blocks.add(new Block((int)d.random(300,370), (int)d.random(speedY-140,speedY),
                    colors[rowColors[3]][0], colors[rowColors[3]][1], colors[rowColors[3]][2]));
            speedY -= 200;
        }
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public int getBlockX() {
        return blockX;
    }

    public int getBlockR() {
        return blockR;
    }

    public int getBlockG() {
        return blockG;
    }

    public int getBlockB() {
        return blockB;
    }

    public int getBlockHeight() {
        return blockHeight;
    }

    public int getBlockWidth() {
        return blockWidth;
    }

    public void setBlockX(int blockX) {
        this.blockX = blockX;
    }

    public void setBlockY(int blockY) {
        this.blockY = blockY;
    }

    public int getBlockY() {
        return blockY;
    }

    public int getBlockResistance() {
        return blockResistance;
    }

    public void setBlockResistance(int blockResistance) {
        this.blockResistance = blockResistance;
    }
}
