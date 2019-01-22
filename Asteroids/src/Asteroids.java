import java.util.ArrayList;
import processing.core.PApplet;
public class Asteroids {
  float xPos;
  float yPos;
  float width;
  float height;
  float xDir = ((float) (Math.random() * 3));
  float yDir = ((float) (Math.random() * 3));
  float maxX;
  float maxY;
  float minX;
  float minY;
  float speed = 2;
  float life = 255;
  float red = 0;
  float green = 0;
  float blue = 0;
  ArrayList<ParticleSystem> mySystems;
  boolean beat = true;
  ScoreKeeper score;
  // Asteroids constructor
  Asteroids(float x, float y, float w, float h, float inMaxX, float inMaxY,
  float inMinX, float inMinY, float l, ScoreKeeper scoreKeeper) {
    xPos = x;
    yPos = y;
    width = w;
    height = h;
    maxX = inMaxX;
    maxY = inMaxY;
    minX = inMinX;
    minY = inMinY;
    life = l;
    score = scoreKeeper;
    mySystems = new ArrayList<ParticleSystem>();
  }// end Asteroids constructor
  //alternate Asteroids constructor
  Asteroids(float x, float y, float w, float h, float inMaxX, float inMaxY,
  float inMinX, float inMinY, float l) {
    xPos = x;
    yPos = y;
    width = w;
    height = h;
    maxX = inMaxX;
    maxY = inMaxY;
    minX = inMinX;
    minY = inMinY;
    life = l;
    mySystems = new ArrayList<ParticleSystem>();
  }// end Asteroids constructor
  // Asteroids display method
  void display(PApplet proc) {
    proc.fill(red, green, blue, life);
    proc.stroke(red, green, blue, life);
    proc.ellipse(xPos, yPos, width, height);
    for (ParticleSystem system : mySystems) {
      system.display(proc);
      system.update();
    } // end for statement
    for(ParticleSystem system:mySystems){
      if(system.dead()){
        beat = false;
      }//end if statement
    }// end for statement
  }// end Asteroids display method
  // Asteroids move method
  void move(PApplet proc) {
    xPos = xPos + xDir * speed;
    yPos = yPos + yDir * speed;
    if (xPos <= minX || xPos >= maxX) {
      xPos = 0;
    } // if statement
    if (yPos <= minY || yPos >= maxY) {
      yPos = 0;
    } // if statement
  }// end Asteroids move method
  void update() {
    xPos = xPos + xDir;
    yPos = yPos + yDir;
  }// end update method
  // getXPos method
  float getXPos() {
    return xPos;
  }// end getXPos
  // getYPos method
  float getYPos() {
    return yPos;
  }// end getYPos
  // getWidth method
  float getWidth() {
    return width;
  }// end getWidth
  // getHeight method
  float getHeight() {
    return height;
  }// end getHeight
  float getLife() {
    return life;
  }//end getLife()
  void explode(PApplet proc) {
    if(life != 0){
      mySystems.add(new ParticleSystem (xPos,yPos,300));
      score.setScore(score.getScore() + 1);
      life = 0;
    }//end if statement
  }// end explode method
  boolean dead() {
    if (life <= 0 && beat == false) {
      return true;
    } else {
      return false;
    } // end if−else statement
  }// end boolean dead
}// end Asteroids class