/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game1;

import tester.*;
import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldimages.*;
import java.util.*;
import java.awt.Color;

class Fishy {

    int x;
    int y;
    int width;
    int height;

    int lives;
    int score;

    Posn pin;
    WorldImage fish;
    String fishFileName = new String("/Users/ldbruby95/NetBeansProjects/game1/fishy.png");
    int outBoundsRight = 500 - ((this.pin.x + this.width/2));
    int outBoundsLeft = 0 + ((this.pin.x - this.width/2));
    int outBoundsUp = 0 + ((this.pin.y = this.height/2));
    int outBoundsDown = 750 - ((this.pin.y +this.height/2));

    public Fishy(Posn pin) {
        this.pin = pin;
        this.x = pin.x;
        this.y = pin.y;
        this.lives = 3; //initial with 3 lives
        this.score = 0; //initial score 0
        fish = new FromFileImage(pin, fishFileName);
        this.width = fish.getWidth();
        this.height = fish.getHeight();

    }

    public int Distance(Nourishment nourishment) {
        //method to tell the distance between the fish and the nourishment item
        //to be used to tell if fish is eating object
        int diffX = this.x - nourishment.pin.x;
        int diffY = this.y - nourishment.pin.x;
        int distance = (int) Math.sqrt((diffX * diffX) + (diffY * diffY));
        return distance;

    }

    public boolean eatHuh(Nourishment nourishment) {
        if (Distance(nourishment) < this.width/2) {
            //if the distance of the fish to the nourishment is less than
            //half of the width of the fish, return true. 
            return true;
        } else {
            //else return false
            return false;
        }
    }

    public void poisonedHuh(Nourishment nourishment) {
        if (eatHuh(nourishment) && nourishment.isPoison()) {
            //if fish is eating nourishment, and the nourishment is poison,
            //decrease amount of lives
            this.lives--;
        } else {
            //else, would be if nourishment is food. increase score
            this.score++;
        }
    }

    public boolean isDeadHuh() {
        //if fish has no more lives, fishy dies. 
        if (this.lives == 0) {
            return true;
        } else {
            return false;
        }
    }
    
//    public boolean isWinnerHuh(){
//        if (this.score == 100) {
//            return true;
//        } else {
//        return false;
//    }
//    }
    

    public Fishy moveFishy(String ke) {
        //to never have out of bounds
//        int outBoundsRight = 500 - ((this.pin.x + this.width/2));
//        int outBoundsLeft = 0 + ((this.pin.x - this.width/2));
//        int outBoundsUp = 0 + ((this.pin.y = this.height/2));
//        int outBoundsDown = 750 - ((this.pin.y +this.height/2));
        if (ke.equals("right") && ((this.pin.x + this.width / 2) < outBoundsRight)) {
            // if the fish moves to the right, AND the right most point of fish image is not out of bounds,
            return new Fishy(new Posn(x + 1, y));
            // move fish to the right. 
        } else if (ke.equals("left") && ((this.pin.x - this.width / 2) > outBoundsLeft)) {
            return new Fishy(new Posn(x - 1, y));
        } else if (ke.equals("up") && ((this.pin.y - this.height / 2) > outBoundsUp)) {
            return new Fishy(new Posn(x, y - 1));
        } else if (ke.equals("down") && ((this.pin.y + this.height / 2) < outBoundsDown)) {
            return new Fishy(new Posn(x, y + 1));
        } else {
            return this;
        }
    }

    public boolean outOfBounds(Fishy fish) {
        //checking if the fish is out of bounds
        if ((fish.pin.x < outBoundsRight && fish.pin.x > outBoundsLeft)
                && (fish.pin.y < outBoundsDown && fish.pin.y > outBoundsUp)) {
            return false;
        } else {
            return true;
        }
    }

    //setting fish
    public void setFishy(int x, int y) {
        this.pin = new Posn(x, y);

    }

}

//interface Nourishments {
//  JK, NOT USING THIS. 
//    public boolean isPoison();
//}


abstract class Nourishment {

    Nourishment nourishment;
    Posn pin = nourishment.pin;
    int x = pin.x;
    int y = pin.y;
    int rate = randInt(1,10);
    
    Random rand = new Random();
    int randNum = rand.nextInt();

    abstract boolean isPoison();
    abstract WorldImage nourishImage();

    public void nourishMove() {
        this.pin.y = (this.pin.y + rate) % 500;
    }
    //set() --> void. sets position 
    //x -> random 
    public static boolean coinToss() {
        Random randomInts = new Random();
        int scene = randomInts.nextInt();
        Math.abs(scene);
        int remainder = scene % 2;
        return (remainder == 1);
    }

    public static int randInt(int min, int max) {

        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public void set() {
        x = randInt(0, 500);// random x value;
        y = 0;
        Posn pos = new Posn(x, y);

        if (coinToss()) {
            nourishment = new Food(pos);
        } else {
            nourishment = new Poison(pos);
        }

        //random x value;
        // y always starts at 0. 
        //  this.pin = new Posn(x,y);
    }

}

class Food extends Nourishment { // before i had implements Nourishments interface
    // cause of common isPoison method.

    //Random rand = new Random();
    //int randNum = rand.nextInt();
   // public Posn pin;
    public int width;
    public int height;
    public IColor color = new Yellow();
    public WorldImage foodImage;

    //int x;
   // int y;

    WorldImage food;

    public boolean isPoison() {
        return false;
    }

    public Food(Posn pin) {
        foodImage = new RectangleImage(pin, width, height, color);

    }

    public WorldImage nourishImage() {
        this.pin.x = randInt(0,500);
        return new RectangleImage(this.pin, this.width, this.height, this.color);
    }

    public void setFood(int x, int y) {
        x = randNum;
        this.pin = new Posn(x, y);
    }

}

class Poison extends Nourishment {

    
//    public Posn pin;
//    Random rand = new Random();
//    int randNum = rand.nextInt();
//    int x;
//    int y;
    
    public int width;
    public int height;
    public int r;
    public IColor color = new Red();
    public WorldImage poisonImage;

    public boolean isPoison() {
        return true;
    }

    public Poison(Posn pin) {

        poisonImage = new RectangleImage(pin, width, height, color);
    }
    
    public WorldImage nourishImage() {
        return new RectangleImage(this.pin, this.width, this.height, this.color);
    }

    public void setPoison(int x, int y) {
        x = randNum;
        this.pin = new Posn(x, y);
    }

}

public class Game1 extends World {

    static int WIDTH = 500;
    static int HEIGHT = 750;
    static Fishy fishy;
    static Nourishment nourishment;
    int lives;
    int score;
    int x = WIDTH / 2;
    int y = HEIGHT / 2;
    Posn center = new Posn(x, y);
    Random rand = new Random();
    int randNum = rand.nextInt();
    String backFileName = new String("/Users/ldbruby95/NetBeansProjects/game1/tankback.png");
    WorldImage background;
    LinkedList nourishments;

    Game1(int width, int height, Fishy fishy, Nourishment nourishment) {// Food food, Poison poison
        super();
        this.fishy = fishy;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.nourishment = nourishment;
        background = new FromFileImage(center, backFileName);

    }

    public int randomInt() {
        if (randNum < WIDTH) {
            return randNum;
        } else {
            //if randnUm is bigger than Width,
            return randNum % WIDTH; //return remainder (which will def. be 
        }
    }

    public WorldImage makeImage() {
        //overlaying fish image on the background image.
        WorldImage world = new OverlayImages(this.fishy.fish,
                new OverlayImages(this.nourishment.nourishImage(), //want this to represent changing food
                        new OverlayImages(
                                new TextImage(new Posn(400, 20), "Lives:  " + lives, 20, new Black()),
                                new OverlayImages(
                                        new TextImage(new Posn(400, 40), "Score:  " + score, 20, new Black()), this.background))));

        return world;
    }

    public World onKeyEvent(String ke) {
        if (ke.equals("q")) {
            return this.endOfWorld("Goodbye! See you later!");
        } else {
            return new Game1(WIDTH, HEIGHT, fishy.moveFishy(ke), nourishment);
        }

    }
    
    public World onTick() {
        nourishment.set();
        nourishment.pin.y = nourishment.pin.y--;
        return new Game1(WIDTH, HEIGHT, fishy, nourishment);
    }
    
    public WorldEnd worldEnd() {
        if (fishy.isDeadHuh()) {
            return new WorldEnd(true, 
            new OverlayImages(background, 
            new OverlayImages(new TextImage(new Posn(WIDTH/2, HEIGHT/2 - 30), "GAME OVER", 30, new Black()),
            new TextImage(new Posn(WIDTH/2, HEIGHT/2), "Final Score: " + score, 20, new Black()))));
        } else {
            return new WorldEnd(false, this.makeImage());
        }
    }
    
    public static void main(String[] args) {
        Game1 game = new Game1(WIDTH, HEIGHT, fishy, nourishment);
        
        game.bigBang(WIDTH, HEIGHT,0.5);
    }
    
    
    
    
    
    

}
