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
    int eat;

    int x;
    int y;
    int width;
    int height;
    
    int lives;
    int score;
    
    
    Posn pin;
    WorldImage fish;
    String fishFileName = new String("/Users/ldbruby95/NetBeansProjects/game1/fishy.png");

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

        int diffX = this.x - nourishment.pin.x;
        int diffY = this.y - nourishment.pin.x;
        int distance = (int) Math.sqrt((diffX * diffX) + (diffY * diffY));
        return distance;

    }
    
    public boolean eatHuh(Nourishment nourishment) {
        if (Distance(nourishment) < 5) {
            return true;
        } else {
            return false;
        }
    }
    
    public void poisonedHuh(Nourishment nourishment) {
        if (eatHuh(nourishment) && nourishment.isPoison()) {
            this.lives--;
        } else {
            this.score++;
        }
    }
    
    public boolean isDeadHuh() {
        if (this.lives == 0) {
            return true;
        } else {
            return false;
        }
    }
    


    public Fishy moveFishy(String ke) {  
        //to never have out of bounds
        if (ke.equals("right") && ((this.pin.x + this.width / 2) <= 495)) {
            return new Fishy(new Posn(x + 5, y));
        } else if (ke.equals("left") && ((this.pin.x - this.width / 2) >= 5)) {
            return new Fishy(new Posn(x - 5, y));
        } else if (ke.equals("up") && ((this.pin.y - this.height / 2) >= 5)) {
            return new Fishy(new Posn(x, y - 5));
        } else if (ke.equals("down") && ((this.pin.y + this.height / 2) <= 745)) {
            return new Fishy(new Posn(x, y + 5));
        } else {
            return this;
        }
    }

    public boolean outOfBounds(Fishy fish) {
        //checking if the fish is out of bounds
        if ((fish.pin.x < 495 && fish.pin.x > 5)
                && (fish.pin.y < 745 && fish.pin.y > 5)) {
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

abstract class Nourishment{
    
    Nourishment nourishment;
    Posn pin = nourishment.pin;
    int x = pin.x;
    int y = pin.y;
    abstract boolean isPoison();
    
}

class Food extends Nourishment { // before i had implements Nourishments interface
                                  // cause of common isPoison method.
    
    
    
    //Random rand = new Random();
    //int randNum = rand.nextInt();
    int eat = 5;
    public Posn pin;
    public int width;
    public int height;
    public int r;
    public IColor color = new Yellow();
    public WorldImage foodImage;

    Random rand = new Random();
    int randNum = rand.nextInt();
    int x;
    int y;

    WorldImage food;

    public boolean isPoison() {
        return false;
    }

    public Food(Posn pin) {
        foodImage = new RectangleImage(pin, width, height, color);

    }

    public WorldImage foodImage() {

        return new RectangleImage(this.pin, this.width, this.height, this.color);
    }

}

class Poison extends Nourishment {

    public Posn pin;
    public int width;
    public int height;
    public int r;
    public IColor color = new Red();

    Random rand = new Random();
    int randNum = rand.nextInt();
    int x;
    int y;
    public WorldImage poisonImage;

    public boolean isPoison() {
        return true;
    }

    public Poison(Posn pin) {

        poisonImage = new RectangleImage(pin, width, height, color);
    }

}



public class Game1 extends World {

    int WIDTH = 500;
    int HEIGHT = 750;
    Fishy fishy;
    int x = WIDTH / 2;
    int y = HEIGHT / 2;
    Posn center = new Posn(x, y);
    Random rand = new Random();
    int randNum = rand.nextInt();
    String backFileName = new String("/Users/ldbruby95/NetBeansProjects/game1/tankback.png");
    WorldImage background;

    public Game1(Fishy fishy) {// Food food, Poison poison
        super();
        this.fishy = fishy;
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
        return new OverlayImages(this.fishy.fish, this.background);
    }

    public World onKeyEvent(String ke) {
        if (ke.equals("q")) {
            return this.endOfWorld("Goodbye! See you later!");
        } else {
            return new Game1(fishy.moveFishy(ke));
        }

    }

//    public World onTick() {
//        can't code anything yet because i want this to represent
    //    the falling nourishments blocks. 
//    }
}
