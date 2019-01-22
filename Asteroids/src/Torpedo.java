import processing.core.PApplet;

public class Torpedo {
  float xPos;
  float yPos;
  float type;
  float angle = 0;
  //Torpedo constructor
  Torpedo (float x, float y, int t, float a) {
    xPos = x;
    yPos = y;
    type = t;
    angle = a;
  }//end Torpedo constructor
  //Torpedo display method
  void display (PApplet proc) {
    proc.pushMatrix();
    proc.translate(xPos, yPos);
    proc.fill(type);
    proc.stroke(type);
    proc.rotate(angle);
    proc.ellipse(0, 0,4,10);
    proc.popMatrix();
  }//end Torpedo display method
  //Torpedo move method
  void move (float amountX, float amountY) {
    yPos = amountY;
    xPos = amountX;
  }//end Torpedo move method
  //Torpedo shoot method
  void shoot () {
    xPos = xPos + (float)Math.cos(angle - Math.PI/2) * 10;
    yPos = yPos - (float)Math.sin(angle + Math.PI/2) * 10;
  }// end Torpedo shoot method
  //getXPos method
  float getXPos() {
    return xPos;
  }//end getXPos
  //getYPos method
  float getYPos() {
    return yPos;
  }//end getYPos
  //getWidth method
  float getWidth() {
    return 4;
  }//end getWidth
  //getHeight method
  float getHeight() {
    return 10;
  }//end getHeight
  //turn method
  void turn(float amount) {
    angle = angle + amount;
  }//end turn method
}//end Torpedo class