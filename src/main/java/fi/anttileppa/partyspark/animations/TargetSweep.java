package fi.anttileppa.partyspark.animations;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;
import java.util.TimerTask;

import fi.anttileppa.partyspark.screen.Pixel;
import fi.anttileppa.partyspark.screen.Screen;

public class TargetSweep extends TimerTask {
  
  private int hy = 0;
  private int hx = 0;
  
  @Override
  public void run() {
    Set<Pixel> pixels = new HashSet<>();
    
    hy = (hy + 1) % Screen.CHAINS;
    if (hy == 0 || hy == Screen.CHAINS / 2) {
      hx = (hx + 1) % Screen.LEDS;
    }
    
    for (int y = 0; y < Screen.CHAINS; y++) {
      for (int x = 0; x < Screen.LEDS; x++) {
        int r = 0;
        int g = 0;
        int b = 0;
        int a = 255;
        
        if (hy == y) {
          r = 128;
        } else if (hy == y - 1) {
          r = 100;
        } else if (hy == y + 1) {
          r = 100;
        }
        
        if (hx == x) {
          g = 128;
        } else if (hx == x - 1) {
          g = 100;
        } else if (hx == x + 1) {
          g = 100;
        }
        
        if (hy == y && hx == x) {
          b = 255;
        }
        
        pixels.add(new Pixel(x, y, new Color(r, g, b, a)));
      }  
    }
    
    Screen.setPixels(pixels);
  }

}