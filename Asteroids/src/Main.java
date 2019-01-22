import java.util.ArrayList;
import java.util.Iterator;
import processing.core.PApplet;

public class Main extends PApplet {
	public static void main(String [] args) {
		PApplet.main("Main");
	}
  boolean homeScreen = true;
  boolean readingName = false;
  boolean playingGame = false;
  boolean gameOver = false;
  boolean gameScores = false;
  boolean viewRules = false;
  String name = "";
  Button viewScores = new Button(350, 430, 130, 50, "View High Scores");
  Button restartGame = new Button(520, 430, 130, 50, "Restart Game");
  Button playGame = new Button(520, 430, 130, 50, "Play Game");
  Button mainScreen = new Button(520, 50, 160, 50, "Return to Main Screen");
  Button rules = new Button(420, 510, 130, 50, "View Rules");
  Ship ship;
  Ship introShip;
  Torpedo torpedo;
  ArrayList<Asteroids> AllAsteroids;
  ArrayList<Asteroids> IntroAsteroids;
  ScoreKeeper theScore = new ScoreKeeper(900, 100);
  Highscores highScores = new Highscores(300, 100);
  
  
  public void settings() {
	  size(1000, 700);
	}
  
  public void setup(){
    textSize(28);
    ship = new Ship(500,350,1,1,255, PI/6);
    introShip = new Ship(500,350,1,1,255, PI/6);
    AllAsteroids = new ArrayList <Asteroids>();
    IntroAsteroids = new ArrayList <Asteroids>();
    while (IntroAsteroids.size() < 3) {
      IntroAsteroids.add(new Asteroids (100,100,50,50,1000,700, 0, 0,255));
    }// end while statement
  }//end setup()
  
  public void draw() {
    background(136,18,176);
    if(homeScreen){
      homeScreen();
    } else if(readingName){
      readingName();
    } else if(viewRules){
      viewRules();
    } else if (playingGame){
      playingGame();
    } else if (gameOver){
      gameOver();
    } else if (gameScores) {
      gameScores();
    }//end if−else statement
    if(homeScreen || readingName || gameOver || gameScores || viewRules){
      for(Asteroids anAsteroid: IntroAsteroids){
        anAsteroid.display(this);
        anAsteroid.move(this);
      }//end for statement
    }//end if statement
  }//end draw
  void homeScreen() {
    textSize(40);
    text("Asteroid Blaster", 360, 200);
    textSize(28);
    playGame.display(this);
    viewScores.display(this);
    rules.display(this);
    introShip.display(this);
  }//end homeScreen()
  void viewRules() {
    text("Rules", 150, 100);
    mainScreen.display(this);
    textSize(20);
    text("− use the left and right arrow keys to rotate your ship" +"\n" + "− use the up and down arrow keys to move your ship" +
    "\n" + "up and down" +
    "\n" + "− you have one torpedo that can be reloaded" +
    "\n" + "−−> press the spacebar to fire it/reload and fire it",
    150, 150);
    textSize(28);
  }//end viewRules()
  void readingName() {
    mainScreen.display(this);
    text("What is your name?", 390, 300);
    text(name, 460, 415);
    textSize(15);
    text("type in your name and press ENTER to play game", 360, 330);
    textSize(28);
  }//end readingName()
  void playingGame() {
    if(!ship.getDead()){
      ship.display(this);
    }//end if statement
    while (AllAsteroids.size() <3) {
      AllAsteroids.add(new Asteroids (-100,100,50,50,1000,700, 0, 0,255, theScore));
    }// end while statement
    theScore.display(this);
    for (Asteroids aAsteroid:AllAsteroids){
      aAsteroid.display(this);
      aAsteroid.move(this);
      if(aAsteroid.getLife() != 0 && twoObjectsCollide (aAsteroid.getXPos(),
      aAsteroid.getYPos(),aAsteroid.getWidth(), aAsteroid.getHeight(),
      ship.getXPos(),ship.getYPos(),
      ship.getWidth(),ship.getHeight())){
        playingGame = false;
        gameOver = true;
        Score playerScore = new Score(theScore.getScore(), name);
        highScores.addHighScores(playerScore);
      }//end if statement
    }//end for statement
    if(torpedo != null){
      torpedo.display(this);
      torpedo.shoot();
      for(Asteroids aAsteroid:AllAsteroids){
        if(twoObjectsCollide (aAsteroid.getXPos(),
        aAsteroid.getYPos(),aAsteroid.getWidth(), aAsteroid.getHeight(),
        torpedo.getXPos(),torpedo.getYPos(),
        torpedo.getWidth(),torpedo.getHeight())){
          aAsteroid.explode(this);
        }//end if statement
      }//end for statement
      Iterator<Asteroids> theAsteroid = AllAsteroids.iterator();
      while(theAsteroid.hasNext()) {
        boolean stillThere = true;
        Asteroids bAsteroid = theAsteroid.next();
        bAsteroid.update();
        if(bAsteroid.dead() && stillThere){
          theAsteroid.remove();
          stillThere = false;
        }//end if statement
      }//end while statement
    }//end if statement
  }//end playingGame()
  void gameOver() {
    text("Game Over", 420, 200);
    viewScores.display(this);
    restartGame.display(this);
    ship.display(this);
    ship.explode();
  }//end gameOver()
  void gameScores() {
    highScores.display(this);
    highScores.sort();
    restartGame.display(this);
    mainScreen.display(this);
  }//end gameOver()
 
  public void keyReleased() {
  if(key == ' ') {
  torpedo = new Torpedo((float)(ship.getXPos() - Math.sin(ship.getAngle())),
  (float)(ship.getYPos() - Math.cos(ship.getAngle())),
  0, ship.getAngle());
  } //end if statement
  }//end keyReleased method
  
  public void keyPressed() {
    if (readingName) {
      if (key == ENTER) {
        playingGame = true;
        readingName = false;
      } else if (key == (char) 0x0008) {
        if(name.length() > 0){
          name = name.substring(0, name.length() - 1);
        }//end if statement
      } else {
        name = name + key;
      }//end if−else statement
    }//end if statement
    if (key == CODED){
      if(playingGame) {
        if (keyCode == UP){
          ship.move(-10);
        } else if (keyCode == DOWN){
          ship.move(10);
        }else if (keyCode == RIGHT) {
          ship.turn(PI/13);
        }else if (keyCode == LEFT) {
          ship.turn(-PI/13);
        }//end if−else
      }//end if playing Game
      else if(homeScreen){
        if (keyCode == UP){
          introShip.move(-10);
        }else if (keyCode == DOWN){
          introShip.move(10);
        }else if (keyCode == RIGHT) {
          introShip.turn(PI/13);
        }else if (keyCode == LEFT) {
          introShip.turn(-PI/13);
        }//end if else statement
      }//end if homeScreen
    }//end if key == CODED
  }//end keyPressed()
  public void mousePressed() {
    if(homeScreen){
      if(viewScores.withinButton(mouseX, mouseY)){
        homeScreen = false;
        gameScores = true;
      }//end if statement viewScores
      if(rules.withinButton(mouseX, mouseY)){
        homeScreen = false;
        viewRules = true;
      }//end if statement rules
    }//end if homeScreen statement
    if(viewRules){
      if(mainScreen.withinButton(mouseX, mouseY)){
        homeScreen = true;
        viewRules = false;
      }//end if mainScreen statement
    }//end if viewRules statement
    if(gameOver) {
      if(viewScores.withinButton(mouseX, mouseY)){
        gameOver = false;
        gameScores = true;
      }//end if statement viewScores
      if(restartGame.withinButton(mouseX, mouseY)){
        initialze();
      }//end if restartGame statement
    }//end if gameOver statement
    if(homeScreen){
      if(playGame.withinButton(mouseX,mouseY)){
        readingName = true;
        homeScreen = false;
        initialze();
      }//end if statement
    }//end if homeScreen = true
    if(gameScores){
      if(mainScreen.withinButton(mouseX, mouseY)){
        homeScreen = true;
        gameScores = false;
      }//end if statement
      if(restartGame.withinButton(mouseX, mouseY)){
        initialze();
      }//end if restartGame statement
    }//end if gameScores = true
    if(readingName){
      if(mainScreen.withinButton(mouseX, mouseY)){
        homeScreen = true;
        readingName = false;
      }//end if mainScreen statement
    }//end if readingName
  }//end mousePressed()
  boolean twoObjectsCollide (float x1, float y1, float w1, float h1, float x2, float y2, float w2, float
  h2) {
    if(x1 + w1/2 >= x2 && y1 + h1/2 >= y2 && x1 - w1/2 <= x2 && y1 - h1/2 <= y2 ){
      return true;
    } else {
      return false;
    }//end if−else statement
  }//end boolean twoObjectsCollide
  public void initialze() {
    readingName = true;
    playingGame = false;
    gameOver = false;
    gameScores = false;
    name = "";
    ship = new Ship(500,350,1,1,255, PI/6);
    theScore = new ScoreKeeper(500, 100);
    AllAsteroids = new ArrayList <Asteroids>();
    torpedo = new Torpedo((float)(ship.getXPos() - Math.sin(ship.getAngle())),
    (float)(ship.getYPos() - Math.cos(ship.getAngle())),
    0, ship.getAngle());
  }//end initialize()
}//end main class
