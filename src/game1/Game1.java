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
    WorldImage fish;
    String fishFileName = new String("/Users/ldbruby95/NetBeansProjects/game1/fishy.png");

    public Fishy(Posn pin) {
//        this.width = width;
//        this.height = height;
        this.x = pin.x;
        this.y = pin.y;
        fish = new FromFileImage(pin, fishFileName);
        this.width = fish.getWidth();
        this.height = fish.getHeight();
    }

    public Fishy moveFishy(String ke) {
        if (ke.equals("right")) {
            return new Fishy(new Posn(x + 5, y));
        } else if (ke.equals("left")) {
            return new Fishy(new Posn(x - 5, y));

        } else if (ke.equals("up")) {
            return new Fishy(new Posn(x, y - 5));
        } else if (ke.equals("down")) {
            return new Fishy(new Posn(x, y + 5));
        } else {
            return this;
        }
    }


}



public class Game1 extends World {

    int WIDTH = 500;
    int HEIGHT = 750;
    Fishy fishy;
    int x = WIDTH/2;
    int y = HEIGHT/2;
    Posn center = new Posn (x,y);
    Random rand = new Random();
    int randNum = rand.nextInt();
    String backFileName = new String("/Users/ldbruby95/NetBeansProjects/game1/tankback.png");
    WorldImage background;

    public Game1(Fishy fishy) {// Food food, Poison poison
        super();
        this.fishy = fishy;
        background = new FromFileImage(center,backFileName);

    }
    
    public WorldImage makeImage() {
        //overlaying fish image on the background image.
        return new OverlayImages(this.fishy.fish,this.background);
    }

    public World onKeyEvent(String ke) {
        if (ke.equals("q")) {
            return this.endOfWorld("Goodbye");
        } else {
            return new Fishy(moveFishy(ke));
        }

    }

    public int randomInt() {
        if (randNum < WIDTH) {
            return randNum;
        } else {
            //if randnUm is bigger than Width,
            return randNum % WIDTH; //return remainder (which will def. be 
        }
    }

}

interface Nourishments { //short for nourishment

    public int width = 10;
    public int height = 10;

}
