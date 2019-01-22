import processing.core.PApplet;
public class Particle {
  float xPos;
  float yPos;
  float xDir;
  float yDir;
  float radius;
  float lifeSpan = 255;
  float red;
  float green;
  float blue;
  //Particle constructor
  Particle (float x, float y, float xd, float yd, float rad, float l, float r, float g, float b)
  {
    xPos = x;
    yPos = y;
    xDir = xd;
    yDir = yd;
    radius = rad;
    lifeSpan = l;
    red = r;
    green = g;
    blue = b;
  }//end Particle constructor
  void update() {
    xPos = xPos + xDir;
    yPos = yPos + yDir;
    lifeSpan = lifeSpan - 5;
  }//end update method
  void display(PApplet proc) {
    proc.stroke(red, green, blue, lifeSpan);
    proc.fill(red, green,blue, lifeSpan);
    proc.ellipse(xPos, yPos, radius*2, radius*2);
  }//end display method
  boolean dead() {
    if (lifeSpan <= 0) {
      return true;
    } else {
      return false;
    }//end if−else statement
  }//end boolean dead
}//end Particle class
