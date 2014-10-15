/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game1;


import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldimages.*;
import java.util.*;
import java.awt.Color;


public class Nourishment {

    Posn posn;
    int rate;
    int width;
    int height;
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
    
    public Nourishment (Posn posn) {
        this.posn = posn;
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
    
        public Nourishment (Posn posn, int r) {
        this.posn = posn;
        width = 10;
        height = 10;
        this.rate = r;
        if (Utility.coinToss()) {
            color = new Yellow();
            isPoison = false;
        } else {
            color = new Red();
            isPoison = true;
        }
    }
    


    public WorldImage nourishImage() {
        WorldImage nourishment = new RectangleImage(this.posn, this.width,
                this.height, this.color);
        return nourishment;
    }

    
    public Nourishment move() {
        posn = new Posn(this.posn.x, this.posn.y + rate %700);
        return new Nourishment(posn);
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
