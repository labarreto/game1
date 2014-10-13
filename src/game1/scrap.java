///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package game1;
//
//import java.util.Random;
//import javalib.colors.IColor;
//import javalib.colors.Red;
//import javalib.colors.Yellow;
//import javalib.worldimages.Posn;
//import javalib.worldimages.RectangleImage;
//import javalib.worldimages.WorldImage;
//
///**
// *
// * @author ldbruby95
// */
//public class scrap {
//    
//}
//
//
//interface Nourishments {
//    boolean isPoison();
//    WorldImage nourishImage();
//    void nourishMove();
//    void set(int x, int y);
//}
//
//class Food implements Nourishments {
// 
//    public IColor color = new Yellow();
//    WorldImage foodImage;
//    int width;
//    int height;
//    int rate = Utility.randInt(0,15);
//    Posn pin;
//
//    public Food(Posn pin) {
//        this.pin = pin;
//        foodImage = new RectangleImage(pin, width, height, color);
//    }
//
//    public boolean isPoison() {
//        return false;
//    }
//
//    public WorldImage nourishImage() {
//        this.pin.x = Utility.randInt(0, 500);
//        return new RectangleImage(this.pin, this.width, this.height, this.color);
//    }
//    
//    public void nourishMove() {
//        this.pin.y = (this.pin.y + rate) % 300;
//    }
//
//    public void set(int x, int y) {
//        x = Utility.randInt(5, 495);
//        y = 0;
//        this.pin = new Posn(x, y);
//    }
//
//}
//
//class Poison implements Nourishments {
//
//    public IColor color = new Red();
//    WorldImage foodImage;
//    int width;
//    int height;
//    int rate = Utility.randInt(0,15);
//    Posn pin;
//
//    public Poison(Posn pin) {
//        this.pin = pin;
//        foodImage = new RectangleImage(pin, width, height, color);
//    }
//
//    public boolean isPoison() {
//        return true;
//    }
//
//    public WorldImage nourishImage() {
//        this.pin.x = Utility.randInt(0, 500);
//        return new RectangleImage(this.pin, this.width, this.height, this.color);
//    }
//    
//    public void nourishMove() {
//        this.pin.y = (this.pin.y + rate) % 300;
//    }
//
//    public void set(int x, int y) {
//        x = Utility.randInt(5, 495);
//        y = 0;
//        this.pin = new Posn(x, y);
//    }
//
//}
//
//
//
//abstract class Nourishment {
//
//    Fishy fishy;
//    Nourishment nourishment;
//    Posn pin = nourishment.pin;
//    int x = pin.x;
//    int y = pin.y;
//    int rate = randInt(1, 10);
//    public int width;
//    public int height;
////    
////    Random rand = new Random();
////    int randNum = rand.nextInt();
//
//    abstract boolean isPoison();
//    abstract WorldImage nourishImage();
//
//    public Nourishment() {
//        this.width = 10;
//        this.height = 10;
//    }
//
//    public void nourishMove() {
//        this.pin.y = (this.pin.y + rate) % 300;
//    }
//
//    public static boolean coinToss() {
//        Random randomInts = new Random();
//        int scene = randomInts.nextInt();
//        Math.abs(scene);
//        int remainder = scene % 2;
//        return (remainder == 1);
//    }
//
//    public static int randInt(int min, int max) {
//
//        Random rand = new Random();
//        int randomNum = rand.nextInt((max - min) + 1) + min;
//        return randomNum;
//    }
//
//    public Nourishment generateRandomNourish() {
//        x = randInt(0, 500);// random x value;
//        y = 0;
//        Posn pos = new Posn(x, y);
//        if (coinToss()) {
//            nourishment = new Food(pos);
//
//        } else {
//            nourishment = new Poison(pos);
//        }
//        return nourishment;
//    }
//    
//
//
//    public void set(int x, int y) {
//             x = randInt(5, 495);
//            this.pin = new Posn(x, y);
//    }
//
//    class Food extends Nourishment { // before i had implements Nourishments interface
//
//        public IColor color = new Yellow();
//        public WorldImage foodImage;
//        WorldImage food;
//
//        public boolean isPoison() {
//            return false;
//        }
//
//        public Food(Posn pin) {
//            foodImage = new RectangleImage(pin, width, height, color);
//
//        }
//
//        public WorldImage nourishImage() {
//            this.pin.x = randInt(0, 500);
//            return new RectangleImage(this.pin, this.width, this.height, this.color);
//        }
//
//
//
//    }
//
//    class Poison extends Nourishment {
//
//        public int r;
//        public IColor color = new Red();
//        public WorldImage poisonImage;
//
//        public boolean isPoison() {
//            return true;
//        }
//
//        public Poison(Posn pin) {
//
//            poisonImage = new RectangleImage(pin, width, height, color);
//        }
//
//        public WorldImage nourishImage() {
//            return new RectangleImage(this.pin, this.width, this.height, this.color);
//        }
//
//    }