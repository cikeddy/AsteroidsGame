public class Score {
  int number;
  String name = "";
  //String constructor
  Score(int num, String nam){
    number = num;
    name = nam;
  }//end Score constructor
  public String toString() {
    return name + " " + number;
  }
  int getNum() {
    return number;
  }//end getNum()
  void setNum(int newNum) {
    number = newNum;
  }//end setNum()
}//end Score class
