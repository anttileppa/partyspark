package fi.anttileppa.partyspark.animations;
import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import fi.anttileppa.partyspark.screen.Pixel;
import fi.anttileppa.partyspark.screen.Screen;

public class Mordor extends Animation {

  @Override
  public void run() {
    Set<Pixel> pixels = new HashSet<>();
    
    int twinkleY = getRandomRange(0, Screen.CHAINS);
    int twinkleX = getRandomRange(0, Screen.LEDS);
    
    for (int y = 0; y < Screen.CHAINS; y++) {
      for (int x = 0; x < Screen.LEDS; x++) {
        
        int rand = 0;
        
        if (twinkleY == y && twinkleX == x) {
          rand = getRandomRange(-200, 200);
        } else {
          rand = getRandomRange(-20, 20);
        }
        
        Color color = Screen.getColor(x, y);
        
        int r = color.getRed();
        int g = color.getGreen();
        int b = 0;
        
        r = r + rand;
        g = g + rand;
        
        if (r > 255) {
          r = 255;
        }
        
        if (r < 170) {
         r = 170;
        }
        
        if (g > 80) {
          g = 80;
        }
        
        if (g < 0) {
         g = 0;
        }

        pixels.add(new Pixel(x, y, new Color(r, g, b)));
      }
    }
    
    Screen.setPixels(pixels);
  }
  
  
}