package fi.anttileppa.partyspark.animations;
import java.awt.Color;
import java.util.HashSet;
import java.util.Set;
import java.util.TimerTask;

import fi.anttileppa.partyspark.screen.Pixel;
import fi.anttileppa.partyspark.screen.Screen;

public class Chaos extends TimerTask {
  
  @Override
  public void run() {
    Set<Pixel> pixels = new HashSet<>();
    
    for (int y = 0; y < Screen.CHAINS; y++) {
      for (int x = 0; x < Screen.LEDS; x++) {
        int r = getRandomComponent();
        int g = getRandomComponent();
        int b = getRandomComponent();
        int a = 255;
        
        pixels.add(new Pixel(x, y, new Color(r, g, b, a)));
      }  
    }
    
    Screen.setPixels(pixels);
  }
  

  private int getRandomComponent() {
    return Math.round((float) Math.random() * 255f);
  }
  
}