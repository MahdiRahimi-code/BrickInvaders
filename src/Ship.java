import processing.core.PApplet;

public class Ship implements myInterface {
    public static int headX;
    public static int headY;

    public static int leftHandX1;
    public static int leftHandY1;
    public static int leftHandX2;
    public static int leftHandY2;

    public static int rightHandX1;
    public static int rightHandY1;
    public static int rightHandX2;
    public static int rightHandY2;

    public static int leftLegX1;
    public static int leftLegY1;
    public static int leftLegX2;
    public static int leftLegY2;

    public static int rightLegX1;
    public static int rightLegY1;
    public static int rightLegX2;
    public static int rightLegY2;

    public static int bodyX1;
    public static int bodyX2;
    public static int bodyY;
    public static int bodyWidth;
    public static int bodyHeight;

    private int humanR;
    private int humanG;
    private int humanB;

    public Ship(int y, int R, int G, int B) {
        this.bodyY = y;
        this.humanR = R;
        this.humanG = G;
        this.humanB = B;
    }

    public int getHumanR() {
        return humanR;
    }

    public int getHumanG() {
        return humanG;
    }

    public int getHumanB() {
        return humanB;
    }

    public void setHumanR(int humanR) {
        this.humanR = humanR;
    }

    public void setHumanG(int humanG) {
        this.humanG = humanG;
    }

    public void setHumanB(int humanB) {
        this.humanB = humanB;
    }

    @Override
    public void makeAndShowObjects(int x, int y, int width, int height, int R, int G, int B) {
        PApplet mp = Main.processing;
        mp.fill(R, G, B);
        mp.noStroke();
        bodyX1 = x;
        bodyX2 = x + width;
        bodyY = y - 2 * height;
        bodyWidth = width;
        bodyHeight = height;
        mp.rect(bodyX1, bodyY, bodyWidth, bodyHeight);
        // body

        mp.fill(0);
        mp.triangle(bodyX1, bodyY, bodyX2, bodyY, bodyX1 + (width / 2), bodyY - height); // head
        mp.triangle(bodyX1, bodyY + height, bodyX1 + (width / 2), bodyY + height, bodyX1, bodyY + 2 * height); // left
                                                                                                               // leg
        mp.triangle(bodyX2, bodyY + height, bodyX2 - (width / 2), bodyY + height, bodyX2, bodyY + 2 * height); // right
                                                                                                               // leg
        mp.triangle(bodyX1, bodyY, bodyX1, bodyY + (height / 2), bodyX1 - (width / 2), bodyY + (height / 2)); // left
                                                                                                              // hand
        mp.triangle(bodyX2, bodyY, bodyX2, bodyY + (height / 2), bodyX2 + (width / 2), bodyY + (height / 2)); // right
                                                                                                              // hand

        // mp.stroke(R, G, B);
        // mp.strokeWeight(1);
        rightLegX2 = x + (width / 2);
        rightLegX1 = x + (width / 2) + 5;
        rightLegY1 = y;
        rightLegY2 = y - height;
        // mp.line(rightLegX1, rightLegY1, rightLegX2, rightLegY2);
        // //right leg
        leftLegX1 = x + (width / 2) - 5;
        leftLegX2 = x + (width / 2);
        leftLegY1 = y;
        leftLegY2 = y - height;
        // mp.line(leftLegX1, leftLegY1, leftLegX2, leftLegY2);
        // //left leg
        //
        leftHandX1 = x - (width / 2);
        leftHandX2 = x;
        leftHandY1 = y - height;
        leftHandY2 = y - 3 * (height / 2);
        // mp.line(leftHandX1, leftHandY1, leftHandX2, leftHandY2);
        // //left hand
        rightHandX1 = x + 3 * (width / 2);
        rightHandX2 = x + width;
        rightHandY1 = y - height;
        rightHandY2 = y - 3 * (height / 2);
        // mp.line(rightHandX1, rightHandY1, rightHandX2, rightHandY2);
        // //right leg
        //
        // mp.noStroke();
        headX = bodyX1 + (width / 2);
        headY = bodyY - height + 5;
        // mp.circle(headX, headY, 10);
        // //head
    }
}