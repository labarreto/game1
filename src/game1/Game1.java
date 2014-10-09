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
    Random rand = new Random();
    int randNum = rand.nextInt();
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
        fish = new FromFileImage(pin, fishFileName);
        this.width = fish.getWidth();
        this.height = fish.getHeight();
        this.lives = 3; //initial with 3 lives
        this.score = 0; //initial score 0
    }
    
    public int foodDistance(Food food) {
        
        int diffX = this.x-food.pin.x;
        int diffY = this.y-food.pin.x;
        int distance = (int) Math.sqrt((diffX*diffX) + (diffY*diffY));
        return distance;
       
    }
    
    public int poisonDistance(Poison poison) {
        int diffX = this.x-poison.pin.x;
        int diffY = this.y-poison.pin.y;
        int distance = (int) Math.sqrt((diffX*diffX) + (diffY*diffY));
        return distance;     
    }
    
    public boolean eatHuh() {
        
        return true;
    }
    

    public Fishy moveFishy(String ke) {
        if (ke.equals("right") && ((this.pin.x + this.width / 2) < 500)) {
            return new Fishy(new Posn(x + 5, y));
        } else if (ke.equals("left") && ((this.pin.x - this.width / 2) > 0)) {
            return new Fishy(new Posn(x - 5, y));
        } else if (ke.equals("up") && ((this.pin.y - this.height / 2) > 0)) {
            return new Fishy(new Posn(x, y - 5));
        } else if (ke.equals("down") && ((this.pin.y + this.height / 2) < 750)) {
            return new Fishy(new Posn(x, y + 5));
        } else {
            return this;
        }
    }
    

    //setting fish
    public void setFishy (int x, int y) {
        this.pin = new Posn(x, y);
    }

}
interface Nourishments {

   public boolean isPoison();

}

class Food implements Nourishments{
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
   
   
   public WorldImage foodImage(){
       
       return new RectangleImage(this.pin,this.width,this.height,this.color);
   }
   
   
   
   public boolean eatHuh(){
       //if food of distance 5, fishy eats food, plus 1 point!
       return true;
   }
   
}


class Poison implements Nourishments {
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
    //    the falling food blocks. 
//    }
}


