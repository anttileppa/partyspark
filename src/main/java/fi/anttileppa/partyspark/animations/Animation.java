package fi.anttileppa.partyspark.animations;

import java.util.Random;
import java.util.TimerTask;

public abstract class Animation extends TimerTask {

  private static Random random = new Random();

  protected int getRandomRange(int min, int max) {
    return random.nextInt((max - min) + 1) + min;
  }
  
  protected int getRandomComponent() {
    return Math.round((float) Math.random() * 255f);
  }
  
}
