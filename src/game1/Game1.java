package game1;


import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldimages.*;
import java.util.*;



public class Game1 extends World {

    int screenWIDTH = 500;
    int screenHEIGHT = 750;
    static Fishy fishy;
    int lives;
    int score;
    String backFileName = new String("/Users/ldbruby95/NetBeansProjects/game1/tankback.png");
    WorldImage background;
    LinkedList<Nourishment> nourishments;

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

    public Game1 onTick() {
        Nourishment nourish = new Nourishment();
        if (Utility.coinToss()) {
            nourishments.add(new Nourishment());
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
                if (listNourishment.isPoison) {
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
            world = new OverlayImages(world, yay.next().nourishImage());
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

        Game1 game = new Game1(500, 700, 15, 0, new Fishy(), yayNora);
        game.bigBang(500, 700, 0.125);
    }
    
    

}
