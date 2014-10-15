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

/**
 *
 * @author ldbruby95
 */
public class Fishy {

    Posn pin;
    String fishFileName;
    WorldImage fish;
    int width;
    int height;


    public Fishy() {//){) {
        this.pin = new Posn(250, 350);
        fishFileName = "/Users/ldbruby95/NetBeansProjects/game1/fishy.png";
        fish = new FromFileImage(pin, fishFileName);
        this.width = fish.getWidth();
        this.height = fish.getHeight();
    }

    public WorldImage fishImage() {
        return fish.getMovedTo(pin);
    }

    public Fishy moveFishy(String ke) {
        int outBoundsRight = 500;
        int outBoundsLeft = 0;
        int outBoundsUp = 0;
        int outBoundsDown = 700;

        if (ke.equals("right") && ((this.pin.x + 10) <= outBoundsRight)) {
            this.pin = new Posn(this.pin.x + 10, this.pin.y);
            return new Fishy(pin);
        } else if (ke.equals("left") && ((this.pin.x - 10) >= outBoundsLeft)) {
            this.pin = new Posn(this.pin.x - 10, this.pin.y);
            return new Fishy(pin);
        } else if (ke.equals("up") && ((this.pin.y - 10) >= outBoundsUp)) {
            this.pin = new Posn(this.pin.x, this.pin.y - 10);
            return new Fishy(pin);
        } else if (ke.equals("down") && ((this.pin.y + 10) <= outBoundsDown)) {
            this.pin = new Posn(this.pin.x, this.pin.y + 10);
            return new Fishy(pin);
        }
        return this;
    }
    
        public Fishy(Posn pin) {//){) { //method for tests
        this.pin = new Posn(250, 350);
        fishFileName = "/Users/ldbruby95/NetBeansProjects/game1/fishy.png";
        fish = new FromFileImage(pin, fishFileName);
        this.width = fish.getWidth();
        this.height = fish.getHeight();
    }
    
    
}
