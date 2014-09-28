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

interface fishTank{
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
