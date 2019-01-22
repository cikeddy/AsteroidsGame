import processing.core.PApplet;
public class Button {
  float xPos;
  float yPos;
  float width;
  float height;
  String buttonName;
  Button (float x, float y, float w, float h, String name){
    xPos = x;
    yPos = y;
    width = w;
    height = h;
    buttonName = name;
  }//end Button constructor
  void display(PApplet proc) {
    proc.fill(0);
    proc.textSize(12);
    proc.rect(xPos, yPos, width, height);
    proc.fill(255);
    proc.text("" + buttonName,xPos + width/8, yPos + height/2);
    proc.textSize(28);
  }//end display()
  boolean withinButton(float x, float y) {
    if(x >= xPos && x <= xPos + width && y >= yPos && y <= yPos + height) {
      return true;
    } else {
      return false;
    }// end if−else statement
  }//end withinButton()
}//end Button class