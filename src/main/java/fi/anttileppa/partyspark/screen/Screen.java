package fi.anttileppa.partyspark.screen;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Screen {

  @SuppressWarnings("unused")
  private static Logger logger = LoggerFactory.getLogger(Screen.class);

  public static final Integer LEDS = 25;
  public static final Integer CHAINS = 20;
  private static List<List<Color>> pixels;
  private static Set<ScreenListener> listeners = new HashSet<>();
  
  private Screen() {
  }

  public static Color getColor(int x, int y) {
    return pixels.get(y).get(x);
  }
  
  public static void setPixel(int x, int y, Color color) {
    setPixel(new Pixel(x, y, color));
  }
  
  public static void setPixel(Pixel pixel) {
    setPixels(Collections.singleton(pixel));
  }
  
  public static void setPixels(Set<Pixel> newPixels) {
    Set<Pixel> changedPixels = getChangedPixels(newPixels);
    
    for (Pixel changedPixel : changedPixels) {
      pixels.get(changedPixel.getY()).set(changedPixel.getX(), changedPixel.getColor());
    }
    
    triggerChangeSet(changedPixels);
  }
  
  public static void addListener(ScreenListener listener) {
    listeners.add(listener);
  }
  
  private static void triggerChangeSet(Set<Pixel> changedPixels) {
    List<ScreenChange> changes = changedPixels.stream()
      .map(ScreenChange::new)
      .collect(Collectors.toList());
    
    for (ScreenListener listener : listeners) {
      listener.onChange(changes);
    }
  }

  private static Set<Pixel> getChangedPixels(Set<Pixel> pixels) {
    Set<Pixel> result = new HashSet<>(pixels.size());
    
    for (Pixel pixel : pixels) {
      if (!pixel.getColor().equals(getColor(pixel.getX(), pixel.getY()))) {
        result.add(pixel);
      }
    }
    
    return result;
  }

  public static List<List<Color>> getPixels() {
    return pixels;
  }
  
  private static List<Color> createEmptyChain() {
    List<Color> result = new ArrayList<>(LEDS);
    
    for (int x = 0; x < LEDS; x++) {
      result.add(Color.BLACK);
    }
    
    return result;
  }
  
  static {
    pixels = new ArrayList<>(CHAINS);
    for (int y = 0; y < CHAINS; y++) {
      pixels.add(createEmptyChain());
    }
  }

}
