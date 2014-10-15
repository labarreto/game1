/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game1;


import java.util.LinkedList;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class TestTestTest {

    public Fishy fishy;
    ; //nourishments are already at random positions
    public LinkedList nourishments;
    public int rate;
    public int score;
    public int lives;
    public boolean worldEnd;

    Fishy f1 = new Fishy(new Posn(250, 350));
    Fishy f1RIGHT = new Fishy(new Posn(260, 350));
    Fishy f1LEFT = new Fishy(new Posn(240, 350));
    Fishy f1UP = new Fishy(new Posn(250, 340));
    Fishy f1DOWN = new Fishy(new Posn(250, 360));

    Fishy f1RIGHTLEFTUPDOWN = new Fishy(new Posn(250, 350));

    LinkedList yayNora = new LinkedList();

    Fishy f2 = new Fishy(new Posn(495, 350));
    Fishy f2RIGHT = new Fishy(new Posn(495, 350));
    Fishy f2LEFT = new Fishy(new Posn(485, 350));
    Fishy f2UP = new Fishy(new Posn(495, 340));
    Fishy f2DOWN = new Fishy(new Posn(495, 360));
    Fishy f2RIGHTLEFTUPDOWN = new Fishy(new Posn(485, 350)); //doesn't move to the right

    Game1 g1 = new Game1(500, 700, 15, 0, this.f1, yayNora);
    Game1 g1LEFT = new Game1(500, 700, 15, 0, this.f1RIGHT, yayNora);
    Game1 g1RIGHT = new Game1(500, 700, 15, 0, this.f1LEFT, yayNora);
    Game1 g1UP = new Game1(500, 700, 15, 0, this.f1UP, yayNora);
    Game1 g1DOWN = new Game1(500, 700, 15, 0, this.f1DOWN, yayNora);

    public boolean testMoveFish(Tester t) {

        return t.checkExpect(this.f1.moveFishy("right"),
                this.f1RIGHT, "test moveFishy - right " + "\n")
                
                && t.checkExpect(this.f1.moveFishy("left"),
                this.f1LEFT, "test moveFishy - left " + "\n")
                
                && t.checkExpect(this.f1.moveFishy("up"),
                this.f1UP, "test moveFishy - up " + "\n")
                
                && t.checkExpect(this.f1.moveFishy("down"),
                this.f1DOWN, "test moveFishy - down " + "\n");
    }

    public boolean testOnKeyEvent(Tester t) {
        return t.checkExpect(this.g1.onKeyEvent("right"),
                this.g1RIGHT, "test moveFishy - right " + "\n")
                && t.checkExpect(this.g1.onKeyEvent("left"),
                        this.g1LEFT, "test moveFishy - left " + "\n")
                && t.checkExpect(this.g1.onKeyEvent("up"),
                        this.g1UP, "test moveFishy - up " + "\n")
                && t.checkExpect(this.g1.onKeyEvent("down"),
                        this.g1DOWN, "test moveFishy - down " + "\n");

    }
    
    public static void main(String[] args) {
        TestTestTest test = new TestTestTest();
        Tester.runReport(test, false, false);
    }
    

    

    
    
    
    
}
