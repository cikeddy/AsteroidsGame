import java.util.ArrayList;
import processing.core.PApplet;
public class Ship {
  float centerX;
  float centerY;
  float xDir;
  float yDir;
  int red = 0;
  int green = 0;
  int blue = 0;
  int type = 255;
  float angle = 0;
  ArrayList<ParticleSystem> mySystems;
  boolean dead=false;
  boolean hit = false;
  //ship constructor;
  Ship (float x, float y, float xd, float yd, int t, float a){
    centerX = x;
    centerY = y;
    xDir = xd;
    yDir = yd;
    type = t;
    angle = a;
    mySystems = new ArrayList<ParticleSystem>();
  }// end ship constructor
  void display(PApplet proc) {
    proc.pushMatrix();
    proc.fill(red, green, blue, type);
    proc.stroke(red, green, blue, type);
    proc.translate(centerX,centerY);
    proc.rotate(angle);
    proc.triangle(- 50, 0, 0,- 50, 50, 0);
    proc.popMatrix();
    proc.stroke(0,0,0,200);
    proc.fill(0,0,0,255);
    for(ParticleSystem system: mySystems){
      system.display(proc);
      system.update();
    }//end for statement
    if(type == 0){
      dead = true;
    }//end if statement
  }//end display method
  void explode() {
    type = type - 15;
    mySystems.add(new ParticleSystem (centerX,centerY,100));
  }//end explode method
  //move method
  void move(float amount){
    centerY = centerY + amount;
  }//end move method
  //turn method
  void turn(float amount) {
    angle = angle + amount;
  }//end turn method
  //getXPos method
  float getXPos() {
    return centerX;
  }//end getXPos
  //getYPos method
  float getYPos() {
    return centerY;
  }//end getYPos
  //getAngle method
  float getAngle() {
    return angle;
  }//end getAngle
  //getHeight
  float getHeight() {
    return (float)Math.sin(angle);
  }//end getHeight
  //getWidth
  float getWidth() {
    return (float)Math.cos(angle);
  }//end getWidth
  //getType
  float getType() {
    return type;
  }//end getType
  //getHit
  boolean getHit() {
    return hit;
  }//end getHit
  //getDead
  boolean getDead() {
    return dead;
  }//end getDead()
}//end Ship class