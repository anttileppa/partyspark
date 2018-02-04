package fi.anttileppa.partyspark.websocket;

import java.awt.Color;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fi.anttileppa.partyspark.screen.ScreenChange;
import fi.anttileppa.partyspark.screen.ScreenListener;

public class WebSocketScreenListener implements ScreenListener {
  
  private static Logger logger = LoggerFactory.getLogger(WebSocketScreenListener.class);

  @Override
  public void onChange(Set<ScreenChange> changes) {
    
    try {
      ScreenWebSocketSessions.sendMessage(new ObjectMapper().writeValueAsString(toChanges(changes)));
    } catch (JsonProcessingException e) {
      logger.error("Message serialization failed", e);
    }
  }

  private List<int[]> toChanges(Set<ScreenChange> changes) {
    return changes.stream()
      .map(this::toChanges)
      .collect(Collectors.toList());
  }
  
  private int[] toChanges(ScreenChange change) {
    return toChanges(change.getPixel().getX(), change.getPixel().getY(), change.getPixel().getColor());
  }

  private int[] toChanges(int x, int y, Color color) {
    return toChanges(x, y, color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
  }
  
  private int[] toChanges(int x, int y, int r, int g, int b, int a) {
    if (a < 255) {
      return new int[] { x, y, r, g, b, a };
    } else {
      return new int[] { x, y, r, g, b };
    }
  }

}
