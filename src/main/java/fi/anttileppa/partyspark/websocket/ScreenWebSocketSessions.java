package fi.anttileppa.partyspark.websocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.jetty.websocket.api.Session;

public class ScreenWebSocketSessions {

  private static Map<Session, Boolean> sessions = new ConcurrentHashMap<>();

  public static void addSession(Session session) {
    sessions.put(session, Boolean.TRUE);
  }
  
  public static void removeSession(Session session) {
    sessions.remove(session);
  }
  
  public static void sendMessage(String message) {
    sessions.keySet().stream().filter(Session::isOpen).forEach(session -> {
      sendMessage(session, message);
    });
  }
  
  public static void sendMessage(Session session, String message) {
    try {
      session.getRemote().sendStringByFuture(message);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
}
