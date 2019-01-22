import java.util.ArrayList;
import java.util.Iterator;
import processing.core.PApplet;
public class ParticleSystem {
  ArrayList <Particle> particleList = new ArrayList<Particle>();
  float xPos;
  float yPos;
  boolean particleSystemDead = false;
  //ParticleSystem constructor
  ParticleSystem(float x, float y, int numOfParticles){
    xPos = x;
    yPos = y;
    while(particleList.size() < numOfParticles) {
      particleList.add(
      new Particle(xPos, yPos,
      (float) Math.random() *2 - 1,
      (float)Math.random() *2 - 1,
      2,255,0, 0,0));
    }//end while statement
  }//end Particle System constructor
  void update() {
    Iterator<Particle> aParticle = particleList.iterator();
    while(aParticle.hasNext()) {
      Particle theParticle = aParticle.next();
      theParticle.update();
      if(theParticle.dead()){
        aParticle.remove();
      }//end if statement
      if(!aParticle.hasNext()) {
        particleSystemDead = true;
      }//end if statement
    }//end while statement
  }//end update method
  void display(PApplet proc) {
    for(Particle aParticle:particleList){
      aParticle.display(proc);
    }//end for statement
  }//end display method
  boolean dead() {
    if (particleList.size()<= 0) {
      return true;
    } else {
      return false;
    }//end if−else statement
  }//end boolean dead
}//end ParticleSystem class