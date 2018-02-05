package fi.anttileppa.partyspark.websocket;

import java.awt.Color;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fi.anttileppa.partyspark.screen.Pixel;
import fi.anttileppa.partyspark.screen.ScreenChange;
import fi.anttileppa.partyspark.screen.ScreenListener;

public class WebSocketScreenListener implements ScreenListener {
  
  private static Logger logger = LoggerFactory.getLogger(WebSocketScreenListener.class);

  @Override
  public void onChange(List<ScreenChange> changes) {
    ScreenWebSocketSessions.sendMessage(toChanges(changes));
  }

  private String toChanges(List<ScreenChange> changes) {
    char[] result = new char[5 * changes.size()];
    
    for (int i = 0, l = changes.size(); i < l; i++) {
      ScreenChange screenChange = changes.get(i);
      Pixel pixel = screenChange.getPixel();
      Color color = pixel.getColor();
      int i5 = i * 5;
      
      result[0 + i5] = (char) pixel.getX();
      result[1 + i5] = (char) pixel.getY();
      result[2 + i5] = (char) color.getRed();
      result[3 + i5] = (char) color.getGreen();
      result[4 + i5] = (char) color.getBlue();
    }

    return new String(result);
  }

}
