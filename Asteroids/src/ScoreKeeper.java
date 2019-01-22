import processing.core.PApplet;
public class ScoreKeeper {
  float xPos;
  float yPos;
  int score = 0;
  //ScoreKeeper constructor
  ScoreKeeper (float x, float y){
    xPos = x;
    yPos = y;
  }//end constructor
  void display(PApplet proc) {
    proc.text("Score: " + score, xPos, yPos);
  }//end display method
  //getScore
  int getScore() {
    return score;
  }//end getScore()
  //setScore
  void setScore(int newScore) {
    score = newScore;
  }//end setScore
}//end ScoreKeeper class
