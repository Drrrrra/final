package app.controller.guest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

import app.service.SocketService;


@Controller
public class ChatSocketController extends TextWebSocketHandler {
	@Autowired
	Gson gson;
	
	@Autowired
	ChatSocketController chatSocketController;
	@Autowired
	SocketService
	
	List<WebSocketSession> sockets;
	public ChatSocketController() {
		sockets = new ArrayList<>();
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sockets.add(session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		String got = message.getPayload();
		
		WebRequest wr; 
		
		for(int i=0; i<sockets.size(); i++) {
			try { 
		//		String name = (String) sockets.get(i).getAttributes().get("NAME");
		//		String did = (String) sockets.get(i).getAttributes().get("DID");
		//		String pid = (String) sockets.get(i).getAttributes().get("PID");
				Map id = chatSocketController.
				
				wr.setAttribute("name", value, scope);
				Map map = new HashMap<>();
					map.put("name", name);
					map.put("did", did);
					map.put("pid", pid);
				sockets.get(i).sendMessage(message);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sockets.remove(session);
	}

}
