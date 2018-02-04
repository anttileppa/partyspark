import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fi.anttileppa.partyspark.animations.*;
import fi.anttileppa.partyspark.screen.Screen;
import fi.anttileppa.partyspark.websocket.WebSocketScreenListener;
import spark.ModelAndView;
import spark.template.jade.JadeTemplateEngine;

public class Main {

  private static Logger logger = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    logger.info("Starting PartySpark...");
    
    exception(Exception.class, (exception, request, response) -> {
      exception.printStackTrace();
    });
    
    webSocket("/screenSocket", ScreenWebSocketHandler.class);
    
    Screen.addListener(new WebSocketScreenListener());    
    
    port(3000);
    staticFiles.location("/public");
    
    Timer timer = new Timer();
    timer.schedule(new TargetSweep(), 0, 50);
    
    get("/", (request, response) -> { 
      Map<String, Object> indexData = new HashMap<>();
      indexData.put("leds", Screen.LEDS);
      indexData.put("chains", Screen.CHAINS);
      return new ModelAndView(indexData, "index"); 
    }, new JadeTemplateEngine());
    
  }

}