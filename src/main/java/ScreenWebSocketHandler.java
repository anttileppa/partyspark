import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import fi.anttileppa.partyspark.websocket.ScreenWebSocketSessions;

@WebSocket
public class ScreenWebSocketHandler {
  
  @OnWebSocketConnect
  public void onConnect(Session session) throws Exception {
    ScreenWebSocketSessions.addSession(session);
  }

  @OnWebSocketClose
  public void onClose(Session session, int statusCode, String reason) {
    ScreenWebSocketSessions.removeSession(session);
  }

  @OnWebSocketMessage
  public void onMessage(Session user, String message) {
  }
  
}
