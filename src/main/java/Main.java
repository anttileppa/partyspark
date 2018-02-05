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
  private static Map<String, Class<? extends Animation>> ANIMATIONS = new HashMap<>();
  private static Timer timer = null;

  public static void main(String[] args) {
    logger.info("Starting PartySpark...");
    
    exception(Exception.class, (exception, request, response) -> {
      exception.printStackTrace();
    });
    
    webSocket("/screenSocket", ScreenWebSocketHandler.class);
    
    Screen.addListener(new WebSocketScreenListener());    
    
    port(3000);
    staticFiles.location("/public");
     
    get("/", (request, response) -> { 
      String animation = request.queryParams("animation");
      
      if (animation != null) {
        activateAnimation(animation);
      }
      
      Map<String, Object> indexData = new HashMap<>();
      indexData.put("leds", Screen.LEDS);
      indexData.put("chains", Screen.CHAINS);
      return new ModelAndView(indexData, "index"); 
    }, new JadeTemplateEngine());
    
  }
  
  private static void activateAnimation(String name) {
    Animation animation = null;
    
    try {
      animation = ANIMATIONS.get(name).newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      logger.error("Failed to initialize animation", animation);
    }

    if (timer != null) {
      timer.cancel();
      timer = null;
    }
    
    if (animation != null) {
      timer = new Timer();
      timer.schedule(animation, 0, 50);
    }
  }
  
  private static void registerAnimation(String name, Class<? extends Animation> animationClass) {
    ANIMATIONS.put(name, animationClass);
  }
  
  static {
    registerAnimation("chaos", Chaos.class);
    registerAnimation("targetsweep", TargetSweep.class);
    registerAnimation("mordor", Mordor.class);
  }

}