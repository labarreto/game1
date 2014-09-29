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

interface fishTankConst{
    public Random rand = new Random();
    //generates random num
    
    public int WIDTH = 500;
    //setting screen width.
    
    public int HEIGHT = 750;
    //setting screen height
    
    public Color tankColor = new Color(50,150,255);
    //color of ocean. 
    
    public Posn position = new Posn(WIDTH/2, HEIGHT/2);
    public String fileName = new String("/Users/ldbruby95/NetBeansProjects/game1/tankback.png");

    public WorldImage tankImage = new FromFileImage(position, fileName);
    // creating background image from file.
}

class movePoint extends Posn implements fishTankConst {
    movePoint(int x, int y) {
        super(x,y);
    }
    //if x out of bounds to the left, have fish not be able to move anymore to the left
    void moveLeft() {
        if (this.x < 0) {
            this.x = 0;
        } else {
            this.x = this.x;
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
  int y; //want y to be 1/2 of the image pic 
  int lives;
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
