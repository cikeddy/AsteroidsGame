import java.util.ArrayList;
import processing.core.PApplet;
public class Highscores {
  float xPos;
  float yPos;
  ArrayList<Score> highScores = new ArrayList<Score>();
  //Highscores constructor
  Highscores (float x, float y){
    xPos = x;
    yPos = y;
  }//end Highscores constructor
  void display(PApplet proc) {
    // String writtenScores = highScores.toString();
    proc.text("High Scores", xPos, yPos);
    String result = "";
    for(int index = 0; highScores.size() > index; index++){
      Score theScore = highScores.get(index);
      if(index < 5){
        int rank = index + 1;
        result = result + "\n" + rank + "." + theScore ;
      }//end if statement
    }//end for statement
    proc.text(result, xPos, yPos + 30);
  }//end display
  void addHighScores(Score score){
    highScores.add(score);
  }//addHighScores()
  void sort(){
    for (int start = 0; start < highScores.size(); start ++){
      int indexOfMax = findIndexOfMax(start);
      swap(indexOfMax, start);
    }//end for loop
  }//end sort()
  void swap(int first, int second){
    Score temp = highScores.get(first);
    highScores.set(first, highScores.get(second));
    highScores.set(second, temp);
  }//end swap()
  int findIndexOfMax(int start) {
    int maxSoFar = highScores.get(start).getNum();
    int indexOfMax = start;
    for(int index = start + 1; index < this.highScores.size(); index++){
      if(highScores.get(index).getNum() > maxSoFar){
        maxSoFar = highScores.get(index).getNum();
        indexOfMax = index;
      }//end if statement
    }//end for
    return indexOfMax;
  }//end findIndexOfMin()
}//end Highscores class