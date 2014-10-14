package game1;

import tester.*;
import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldimages.*;
import java.util.*;
import java.awt.Color;

class Fishy {

    int lives = 3;
    int score = 0;

    Posn pin;
    int x;
    int y;

    String fishFileName = new String("/Users/ldbruby95/NetBeansProjects/game1/fishy.png");
    WorldImage fish;
    Nourishment nourishment;
    int width = fish.getWidth();
    int height = fish.getHeight();
    int outBoundsRight = 500 - ((this.pin.x + this.width / 2));
    int outBoundsLeft = 0 + ((this.pin.x - this.width / 2));
    int outBoundsUp = 0 + ((this.pin.y = this.height / 2));
    int outBoundsDown = 750 - ((this.pin.y + this.height / 2));

    public Fishy(Posn pin) {
        this.pin = pin;
        this.x = pin.x;
        this.y = pin.y;
        this.width = fish.getWidth();
        this.height = fish.getHeight();
        //        this.lives = 3; //initial with 3 lives
//        this.score = 0; //initial score 0

    }

    public WorldImage fishImage() {
        fish = new FromFileImage(pin, fishFileName);
        return fish;
    }

    public int Distance(Nourishment nourishment) {
        this.nourishment = nourishment;

        //method to tell the distance between the fish and the nourishment item
        //to be used to tell if fish is eating object
        int diffX = this.x - nourishment.posn.x;
        int diffY = this.y - nourishment.posn.y;
        int distance = (int) Math.sqrt((diffX * diffX) + (diffY * diffY));
        return distance;

    }

    public boolean eatHuh(Nourishment nourishment) {
        if (Distance(nourishment) < this.width / 2) {
            //if the distance of the fish to the nourishment is less than
            //half of the width of the fish, return true. 
            return true;
        } else {
            //else return false
            return false;
        }
    }

    public boolean poisonedHuh(Nourishment nourishment) {
        if (eatHuh(nourishment) && nourishment.isPoison()) {
            //if fish is eating nourishment, and the nourishment is poison,
            //decrease amount of lives
            
            this.lives--;
            return true;
            
        } else {
            //else, would be if nourishment is food. increase score
            this.score++;
            return false;
        }
    }

    public boolean isDeadHuh() {
        //if fish has no more lives, fishy dies. 
        if (this.lives == 0) {
            return true;
        } else {
            return false;
        }
    }


    public Fishy moveFishy(String ke) {

        if (ke.equals("right") && ((this.pin.x + this.width / 2) < outBoundsRight)) {
            // if the fish moves to the right, AND the right most point of fish image is not out of bounds,
            return new Fishy(new Posn(x + 1, y));
            // move fish to the right. 
        } else if (ke.equals("left") && ((this.pin.x - this.width / 2) > outBoundsLeft)) {
            return new Fishy(new Posn(x - 1, y));
        } else if (ke.equals("up") && ((this.pin.y - this.height / 2) > outBoundsUp)) {
            return new Fishy(new Posn(x, y - 1));
        } else if (ke.equals("down") && ((this.pin.y + this.height / 2) < outBoundsDown)) {
            return new Fishy(new Posn(x, y + 1));
        } else {
            return this;
        }
    }

    public boolean outOfBounds(Fishy fish) {
        //checking if the fish is out of bounds
        if ((fish.pin.x < outBoundsRight && fish.pin.x > outBoundsLeft)
                && (fish.pin.y < outBoundsDown && fish.pin.y > outBoundsUp)) {
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

class Nourishment {
    Nourishment nourishment;
    Posn posn; //one food generated each time. (on each clock tick.
    // so only necessary to have one. 
    int rate;
    int width = 10;
    int height = 10;
    IColor color;
    IColor colorFood = new Yellow();
    IColor colorPoison = new Red();
    
    public Nourishment (Posn pos1, int width, int height, int rate) {
        this.width = width;
        this.height = height;
        this.rate = rate;
        this.posn = new Posn(Utility.randInt(5,495),0);
    }
    
    public Nourishment() {
        posn = (new Posn(Utility.randInt(0,15), 0));
        width = 10;
        height = 10;
        rate = Utility.randInt(0,15);
        if (Utility.coinToss()){
             color = new Yellow();
        } else {
            color = new Red();
        }
    }
    
    
    
    public Nourishment move() {
        Nourishment nourishment = 
                new Nourishment(
                new Posn(this.posn.x, this.posn.y + rate),
                this.width, this.height, this.rate);
        return nourishment;
    }
    
    public WorldImage drawImage() {
        if (Utility.coinToss()){
            //in the case of true, return block that has food color
            //FOOD
            return new RectangleImage(this.posn, this.width,this.height, colorFood);
        } else {
            //false, Poison image
            return new RectangleImage(this.posn, this.width, this.height, colorPoison);
        }
    }
    
    public boolean isPoison() {
        if (Utility.coinToss()) {
            //in the case of true, nourishment is food, thus not poison
            return false;
        } else {
            return true;
        }
    }
}





    public class Game1 extends World {

        int screenWIDTH= 500;
        int screenHEIGHT=750;
        Fishy fishy;
        //Nourishment nourishment;
        int lives;
        int score;
        int x = screenWIDTH / 2;
        int y = screenHEIGHT / 2;
        static Posn center = new Posn(250, 375);
        Random rand = new Random();
        int randNum = rand.nextInt();
        String backFileName = new String("/Users/ldbruby95/NetBeansProjects/game1/tankback.png");
        WorldImage background;
        LinkedList nourishments;

        public Game1(int width, int height, int lives, int score, Fishy fishy, LinkedList<Nourishment> nourishments) {
            this.screenWIDTH = width;
            this.screenHEIGHT = height;
            this.fishy = fishy;
            //this.nourishment = nourishment;
            this.nourishments = nourishments;
            this.lives = lives;
            this.score = score;
//            this.nourishments;
            background = new FromFileImage(center, backFileName);

        }


        public WorldImage makeImage() {
            //overlaying fish image on the background image.
            
            Iterator<Nourishment> yay = nourishments.listIterator(0);
            
            WorldImage world = new OverlayImages(this.fishy.fish,
                            new OverlayImages(
                                    new TextImage(new Posn(400, 20), "Lives:  " + lives, 20, new Black()),
                                    new OverlayImages(
                                            new TextImage(new Posn(400, 40), "Score:  " + score, 20, new Black()), this.background)));
            
            while(yay.hasNext()) {
                world = new OverlayImages(world, yay.next().drawImage());
            }

            return world;
        }

        public World onKeyEvent(String ke) {
            if (ke.equals("q")) {
                return this.endOfWorld("Goodbye! See you later!");
            } else {
                return new Game1(this.screenWIDTH, this.screenHEIGHT, this.lives, this.score, this.fishy, this.nourishments);
            }

        }

        public World onTick() {
            Iterator<Nourishment> yay = nourishments.listIterator(0);
            
            while (yay.hasNext()) {
                yay.next().move();
                //moves through list. 
            }

            yay = nourishments.listIterator(0);
            while (yay.hasNext()) {
                Nourishment listNourishment = yay.next();
                if (fishy.eatHuh(listNourishment) && (!fishy.poisonedHuh(listNourishment))) {
                    score++;
                } else {
                    lives++;
                }
            }
           
           
            return new Game1(this.screenWIDTH, this.screenHEIGHT, this.lives, this.score, this.fishy, this.nourishments);
        }

        public WorldEnd worldEnd() {
            if (fishy.isDeadHuh()) {
                //if fishy is dead, game ends.
                return new WorldEnd(true,
                        //overlay the background with text that displays game over, and the final score
                        new OverlayImages(background,
                                new OverlayImages(new TextImage(new Posn(x, y-30), "GAME OVER", 30, new Black()),
                                        new TextImage(new Posn(x, y), "Final Score: " + score, 20, new Black()))));
            } else {
                //if not dead, just return normal image. 
                return new WorldEnd(false, this.makeImage());
            }
        }
        
        public static void main(String[] args) {
            
            LinkedList yayNora = new LinkedList();
            yayNora.add(new Nourishment());
            yayNora.add(new Nourishment());
            yayNora.add(new Nourishment());
            yayNora.add(new Nourishment());
            
            // public Game1(int width, int height, int lives, int score, 
            //              Fishy fishy, LinkedList<Nourishment> nourishments)
            Game1 game = new Game1(550, 1000, 3, 0, new Fishy(center), yayNora);
            game.bigBang(550, 1000, 0.1);
            
        }
        
        
    }
