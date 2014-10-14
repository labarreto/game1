package game1;

import tester.*;
import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldimages.*;
import java.util.*;
import java.awt.Color;

class Fishy {

    Posn pin;
    String fishFileName;
    WorldImage fish;
    int width;
    int height;
    IColor color;

    public Fishy(Posn pin, IColor color) {//){) {
        this.pin = pin;
        fishFileName = "/Users/ldbruby95/NetBeansProjects/game1/fishy.png";
        fish = new FromFileImage(pin, fishFileName);
        this.width = fish.getWidth();
        this.height = fish.getHeight();
        //System.out.println("width: " + width +"height: " + height);
        //width is 50, height is 40
        this.color = color;
    }

    public WorldImage fishImage() {
        return fish.getMovedTo(pin);
    }

    public void moveFishy(String ke) {
        int outBoundsRight = 500;
        int outBoundsLeft = 0;
        int outBoundsUp = 0;
        int outBoundsDown = 700;

        if (ke.equals("right") && ((this.pin.x + 10) <= outBoundsRight)) {
            this.pin = new Posn(this.pin.x + 10, this.pin.y);
        } else if (ke.equals("left") && ((this.pin.x - 10) >= outBoundsLeft)) {
            this.pin = new Posn(this.pin.x - 10, this.pin.y);
        } else if (ke.equals("up") && ((this.pin.y - 10) >= outBoundsUp)) {
            this.pin = new Posn(this.pin.x, this.pin.y - 10);
        } else if (ke.equals("down") && ((this.pin.y + 10) <= outBoundsDown)) {
            this.pin = new Posn(this.pin.x, this.pin.y + 10);
        }
    }
}

class Nourishment {

    Posn posn;
    int rate;
    int width = 10;
    int height = 10;
    IColor color;
    boolean isPoison;

    public Nourishment() {
        posn = (new Posn(Utility.randInt(0, 500), 0));
        width = 10;
        height = 10;
        rate = Utility.randInt(3, 15);
        if (Utility.coinToss()) {
            color = new Yellow();
            isPoison = false;
        } else {
            color = new Red();
            isPoison = true;
        }
    }

    public WorldImage drawImage() {
        WorldImage nourishment = new RectangleImage(this.posn, this.width, 
                this.height, this.color);
        return nourishment;
    }

    public void move() {
        this.posn.y = (this.posn.y + rate) % 700;
    }

    public boolean isPoison() {
        return isPoison;
    }

    public boolean beingEatenHuh(Fishy fish) {
        int a = this.posn.x;
        int b = fish.pin.x;
        int c = this.posn.y;
        int d = fish.pin.y;

        int halfFishWidth = fish.width / 2;
        int halfFishHeight = fish.height / 2;
        if (Math.abs(a - b) < (halfFishWidth)
                && (Math.abs(c - d) < (halfFishHeight))) {
            return true;
        } else {
            return false;
        }
    }
}

class Game1 extends World {

    int screenWIDTH = 500;
    int screenHEIGHT = 750;
    static Fishy fishy;
    int lives;
    int score;
    String backFileName = new String("/Users/ldbruby95/NetBeansProjects/game1/tankback.png");
    WorldImage background;
    LinkedList nourishments;

    public Game1(int width, int height, int lives, int score, Fishy fishy, 
            LinkedList<Nourishment> nourishments) {
        this.screenWIDTH = width;
        this.screenHEIGHT = height;
        this.fishy = fishy;
        this.nourishments = nourishments;
        this.lives = lives;
        this.score = score;
        background = new FromFileImage(new Posn(250, 325), backFileName);
    }

    public World onKeyEvent(String ke) {
        fishy.moveFishy(ke);
        return new Game1(this.screenWIDTH, this.screenHEIGHT, this.lives, 
                this.score, fishy, this.nourishments);
    }

    public World onTick() {
        Nourishment nourish = new Nourishment();
        if (Utility.coinToss()) {
            nourishments.add(new Nourishment());
            if (!nourish.isPoison) {
                nourishments.add(nourish);
            }

        }

        Iterator<Nourishment> yay = nourishments.listIterator(0);

        while (yay.hasNext()) {
            yay.next().move();
            //moves through list. 
        }

        yay = nourishments.listIterator(0);

        while (yay.hasNext()) { //while yay still has next, 
            //(should always be true until world end
            Nourishment listNourishment = yay.next();
            if (listNourishment.beingEatenHuh(fishy)) {
                if (listNourishment.isPoison()) {
                    lives--;
                } else {
                    score++;
                }
                //anytime one of the blocks is touched, it disappears. 
                yay.remove();
            }
        }
        return new Game1(this.screenWIDTH, this.screenHEIGHT, this.lives, 
                this.score, this.fishy, this.nourishments);
    }

    public WorldImage makeImage() {

        Iterator<Nourishment> yay = nourishments.listIterator(0);
        WorldImage world = new OverlayImages(this.background,
                new OverlayImages(
                        new TextImage(new Posn(400, 20), "Lives:  " + lives, 
                                20, new Black()),
                        new OverlayImages(
                                new TextImage(new Posn(400, 40), "Score:  " 
                                        + score, 20, new Black()), fishy.fishImage())));
        while (yay.hasNext()) {
            world = new OverlayImages(world, yay.next().drawImage());
        }

        return world;
    }

    public WorldEnd worldEnds() {
        if (lives < 1) {
            return new WorldEnd(true,
                    new OverlayImages(background,
                            new OverlayImages(new TextImage(new Posn(250, 25), 
                                    "Fishy Frenzy!", 20, 1, new Black()),
                                    new OverlayImages(new TextImage(new Posn(250, 300),
                                            "GAME OVER!!!!", 30, 1, new Black()),
                                            new TextImage(new Posn(250, 400), 
                                                    "Final Score:   " + score, 
                                                    20, 1, new Black())))));
        } else {
            return new WorldEnd(false, this.makeImage());
        }
    }

    public static void main(String[] args) {

        LinkedList yayNora = new LinkedList();

        yayNora.add(new Nourishment());

        Game1 game = new Game1(500, 700, 15, 0, new Fishy(new Posn(250, 375), 
                new White()), yayNora);
        game.bigBang(500, 700, 0.125);
    }

}
