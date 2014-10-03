/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game1;

import tester.*;
import javalib.impworld.*;
import javalib.colors.*;
import javalib.worldimages.*;
import java.util.*;
import java.awt.Color;

interface fishTankConst {

    public Random rand = new Random();
    //generates random num
    public int randNum = rand.nextInt();

    public int WIDTH = 500;
    //setting screen width.

    public int HEIGHT = 750;
    //setting screen height

    public Color tankColor = new Color(50, 150, 255);
    //color of ocean. 

    public Posn position = new Posn(WIDTH / 2, HEIGHT / 2);
    public String fileName = new String("/Users/ldbruby95/NetBeansProjects/game1/tankback.png");

    public WorldImage tankImage = new FromFileImage(position, fileName);
    // creating background image from file.
}

//cart Pt stands for cartesian point. 
class cartPt extends Posn implements fishTankConst {

    cartPt(int x, int y) {
        super(x, y);
    }

    //if x out of bounds to the left, have fish not be able to move anymore to the left
    void moveLeft(int i) {
        if (this.x - i < 0) {
            this.x = WIDTH;
        } else {
            this.x = this.x - i;
        }
    }

    //if y out of bounds to the right, have fish not be able to move anymore to the right
    void moveRight() {
        if (this.x > WIDTH) {
            this.x = WIDTH;
        } else {
            this.x = this.x;
        }
    }

}

class Fishy implements fishTankConst {

    int x; //want y to be 1/2 of the image pic 
    int lives;

    Fishy(int y, int lives) {
        this.x = x;
        this.lives = lives;
    }

    cartPt sharkPos() {
        return new cartPt(20, this.x);
    }

    void move(String keyEvent) {
        if (keyEvent.equals("up")) {
            this.x = this.x - 3;
        } else if (keyEvent.equals("down")) {
            this.x = this.x + 3;
        }
    }

    WorldImage fishyImage() {
        return new FromFileImage(this.sharkPos(), "/Users/ldbruby95/NetBeansProjects/game1/fish.png");
    }

    void poison() {
        this.lives = this.lives - 1;
    }
    
    boolean isDeadHuh() {
        return this.lives <=0;
    }
    
    void eating() {
        this.lives = this.lives;
        //this might need to take in a food thing
    }
}



class Food implements fishTankConst {
    cartPt pt;
    int size;
    String name;
    
    Food(cartPt p, int size, String name) {
        this.pt = pt;
        this.size = size;
        this.name = name;
    }
    
    void start (int y, int size) {
        if (randNum <= WIDTH) {
            this.pt.x = randNum;
        }
               
        this.pt.y= 0;
        this.size = size;
    }
    
    void moveStart () {
        this.start(this.pt.y, this.size);
    }
    void moveFood() {
        //move food down
    }
    
    

}

class Poison implements fishTankConst {

}

/**
 *
 * @author Laura Barreto
 */
public class Game1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

}
