/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game1;

import java.util.Iterator;
import java.util.LinkedList;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class TestTestTest {

    public Fishy fishy;
    ; //nourishments are already at random positions
    public LinkedList<Nourishment> nourishments;

    public int score;
    public int lives;
    public boolean worldEnd;
    Nourishment nourishment = new Nourishment();
    public int rate = nourishment.rate;

    Fishy f1 = new Fishy(new Posn(250, 350));
    Fishy f1RIGHT = new Fishy(new Posn(260, 350));
    Fishy f1LEFT = new Fishy(new Posn(240, 350));
    Fishy f1UP = new Fishy(new Posn(250, 340));
    Fishy f1DOWN = new Fishy(new Posn(250, 360));

    Fishy f1RIGHTLEFTUPDOWN = new Fishy(new Posn(250, 350));

    LinkedList yayNora = new LinkedList();
    String backFileName = new String("/Users/ldbruby95/NetBeansProjects/game1/tankback.png");
    WorldImage background = new FromFileImage(new Posn(250, 325), backFileName);
    Nourishment n0 = new Nourishment(new Posn(250,0));
    Nourishment n1 = new Nourishment(new Posn(250, 350), 5);
    public int r1 = n1.rate;
    Nourishment n2 = new Nourishment(new Posn(495, 350));
    public int r2 = n2.rate;
    Nourishment n2f = new Nourishment(new Posn(495, 350 + r2));

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

    Game1 g1Pre = new Game1(500, 700, 15, 1, new Fishy(new Posn(nourishment.posn.x, nourishment.posn.y)), yayNora);
    Game1 g1PostS = new Game1(500, 700, 15, 1, this.f1, yayNora); //eats Food
    Game1 g1PostL = new Game1(500, 700, 14, 0, this.f1, yayNora);

    Game1 g2 = new Game1(500, 700, 15, 0, this.f2, yayNora);

    Game1 g2ot = new Game1(500, 700, 15, 0, this.f2, yayNora);
    Game1 g2o1 = new Game1(500, 700, 15, 1, this.f2, yayNora); //if food
    Game1 g2o2 = new Game1(500, 700, 14, 0, this.f2, yayNora); //if poison
    Game1 g2LEFT = new Game1(500, 700, 15, 0, this.f2RIGHT, yayNora);
    Game1 g2RIGHT = new Game1(500, 700, 15, 0, this.f2LEFT, yayNora);
    Game1 g2UP = new Game1(500, 700, 15, 0, this.f2UP, yayNora);
    Game1 g2DOWN = new Game1(500, 700, 15, 0, this.f2DOWN, yayNora);

    Game1 g1we = new Game1(500, 700, 0, 0, this.f1, yayNora);

    public Game1 generateGame() {
        Game1 g1Post;

        if (n1.isPoison) {
            g1Post = new Game1(500, 700, 14, 0, this.f1, yayNora);
            return g1Post;
        } else {
            g1Post = new Game1(500, 700, 15, 1, this.f1, yayNora);
            return g1Post;
        }

    }

    void reset() {

        // examples of data for the Blob class:
        this.f1 = new Fishy(new Posn(250, 350));
        this.f1RIGHT = new Fishy(new Posn(260, 350));
        this.f1LEFT = new Fishy(new Posn(240, 350));
        this.f1UP = new Fishy(new Posn(250, 340));
        this.f1DOWN = new Fishy(new Posn(250, 360));

        this.f2 = new Fishy(new Posn(250, 350));
        this.f2RIGHT = new Fishy(new Posn(260, 350));
        this.f2LEFT = new Fishy(new Posn(240, 350));
        this.f2UP = new Fishy(new Posn(250, 340));
        this.f2DOWN = new Fishy(new Posn(250, 360));
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

    public boolean checkRandInt() {
        int rand = Utility.randInt(0, 500);
        return (rand <= 500 && rand >= 0);
    }

    public boolean testRandom(Tester t) {
        return t.checkExpect(this.checkRandInt(),
                true, "test RandInt")
                && t.checkExpect(this.checkRandInt(),
                        true, "test RandInt")
                && t.checkExpect(this.checkRandInt(),
                        true, "test RandInt")
                && t.checkExpect(this.checkRandInt(),
                        true, "test RandInt")
                && t.checkExpect(this.checkRandInt(),
                        true, "test RandInt");
    }

    public boolean checkCoinToss() {
        boolean coinToss = Utility.coinToss();
        return (coinToss == true || coinToss == false);
    }

    public boolean testCoinToss(Tester t) {
        return t.checkExpect(this.checkCoinToss(),
                true, "test CoinToss")
                && t.checkExpect(this.checkCoinToss(),
                        true, "test CoinToss")
                && t.checkExpect(this.checkCoinToss(),
                        true, "test CoinToss");
    }

    public boolean testBeingEatenHuh(Tester t) {
        return t.checkExpect(this.n1.beingEatenHuh(this.f1),
                true, "test beingEatenHuh ")
                && t.checkExpect(this.n1.beingEatenHuh(this.f2),
                        false, "test beingEatenHuh")
                && t.checkExpect(this.n2.beingEatenHuh(this.f1),
                        false, "test beingEatenHuh ")
                && t.checkExpect(this.n2.beingEatenHuh(this.f2),
                        true, "test beingEatenHuh");
    }

    public boolean checkMoveFishy(Fishy f, String ke) {
        if (f.pin.x <= f.moveFishy(ke).pin.x) {
            return true;
        } else if (f.pin.x >= f.moveFishy(ke).pin.x) {
            return true;
        } else if (f.pin.y >= f.moveFishy(ke).pin.y) {
            return true;
        } else {
            return (f.pin.y <= f.moveFishy(ke).pin.y);
        }

    }
    
    public boolean testMoveFishy(Tester t){
        return t.checkExpect(checkMoveFishy(f1, "right"), true, "test moveFishy - right")
                && t.checkExpect(checkMoveFishy(f1, "left"), true, "test moveFishy - left")
        && t.checkExpect(checkMoveFishy(f1, "up"), true, "test moveFishy - up")
                && t.checkExpect(checkMoveFishy(f1, "down"), true, "test moveFishy 0 down")
                
                &&t.checkExpect(checkMoveFishy(f2, "right"), true, "test moveFishy - right")
                && t.checkExpect(checkMoveFishy(f2, "left"), true, "test moveFishy - left")
        && t.checkExpect(checkMoveFishy(f2, "up"), true, "test moveFishy - up")
                && t.checkExpect(checkMoveFishy(f2, "down"), true, "test moveFishy 0 down")
                
                &&t.checkExpect(checkMoveFishy(f1LEFT, "right"), true, "test moveFishy - right")
                && t.checkExpect(checkMoveFishy(f1LEFT, "left"), true, "test moveFishy - left")
        && t.checkExpect(checkMoveFishy(f1LEFT, "up"), true, "test moveFishy - up")
                && t.checkExpect(checkMoveFishy(f1LEFT, "down"), true, "test moveFishy 0 down");
    }

        
    public boolean checkMoveNourish(Nourishment n) {

        return (n.posn.y < n.move().posn.y);
    }

    public boolean testMoveNourish(Tester t) {
        return t.checkExpect(this.checkMoveNourish(n0),
                true, "test Move Nourish")
                && t.checkExpect(this.checkMoveNourish(n1),
                        true, "test Move Nourish")
                && t.checkExpect(this.checkMoveNourish(n2),
                        true, "test Move Nourish");
    }

    public boolean checkRandRate() {
        int rand = Utility.randInt(3, 15);
        return (rand <= 15 && rand >= 3);
    }

    public boolean testRandomRate(Tester t) {

        return t.checkExpect(this.checkRandRate(),
                true, "test random rate ")
                && t.checkExpect(this.checkRandRate(),
                        true, "test random rate")
                && t.checkExpect(this.checkRandRate(),
                        true, "test random rate");
    }



    public boolean checkOnTick2(Game1 g1) {
        Game1 g1oT = g1.onTick();
        if (g1oT.nourishments.size() < g1.nourishments.size()) {
            if (g1oT.lives < g1.lives || g1oT.score > g1.score) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

//    // nourishment y before tick is less than nourishment y after tick. 
//    public boolean checkOnTick3(Game1 g) {
//        Game1 gg = g.onTick();
//        Iterator<Nourishment> yay = g.nourishments.listIterator(0);
//        Iterator<Nourishment> yay2 = gg.nourishments.listIterator(0);
//        Nourishment n = yay.next();
//        Nourishment nn = yay2.next();
//        for (Nourishment n1 : g.nourishments) {
//            for (Nourishment n2 : gg.nourishments) {
//                if (nn.posn.y < n.posn.y) {
//                    
//            } if (n1.posn.y)
//        }
//        return true;
//    }
//
//    
//    
//    public boolean testOnTick3(Tester t){
//        return t.checkExpect(checkOnTick3(g1),
//                true, "test onTick ")
//                && t.checkExpect(checkOnTick3(g2),
//                        true, "test onTick ");
//    }

    public boolean testOnTick2(Tester t) {
        return t.checkExpect(checkOnTick2(g1),
                true, "test onTick ");
    }

        public boolean checkOnTick1(Game1 g2) {

        return g2.nourishments.size() + 1 >= g2.onTick().nourishments.size();

    }
    public boolean testOnTick1(Tester t) {
        return t.checkExpect(checkOnTick1(g2),
                true, "test onTick ")
                && t.checkExpect(checkOnTick1(g2),
                        true, "test onTick ")
                && t.checkExpect(checkOnTick1(g2),
                        true, "test onTick ")
                && t.checkExpect(checkOnTick1(g2),
                        true, "test onTick ")
                && t.checkExpect(checkOnTick1(g1),
                        true, "test onTick ")
                && t.checkExpect(checkOnTick1(g1),
                        true, "test onTick ")
                && t.checkExpect(checkOnTick1(g1),
                        true, "test onTick ")
                && t.checkExpect(checkOnTick1(g1),
                        true, "test onTick ");
         
    } //if nourishment is added based off of a coin toss, size plus one could be
    //more than or equal to size OnTick. all based off of a coin Toss. 

    public boolean testWorldEnd(Tester t) {
        return t.checkExpect(this.g1we.worldEnds(),
                new WorldEnd(true,
                        new OverlayImages(background,
                                new OverlayImages(new TextImage(new Posn(250, 25),
                                                "Fishy Frenzy!", 20, 1, new Black()),
                                        new OverlayImages(new TextImage(new Posn(250, 300),
                                                        "GAME OVER!!!!", 30, 1, new Black()),
                                                new TextImage(new Posn(250, 400),
                                                        "Final Score:   " + score,
                                                        20, 1, new Black()))))))
                && t.checkExpect(this.g1.worldEnds(),
                        new WorldEnd(false, this.g1.makeImage()));
    }

    public static void main(String[] args) {
        TestTestTest test = new TestTestTest();
        Tester.runReport(test, false, false);
    }

}
