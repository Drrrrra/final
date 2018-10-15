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
	SocketService service;
	
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

		System.out.println("got = " + got);

		Map a = gson.fromJson(got, Map.class);

		Map user = (Map) session.getAttributes().get("user");
		System.out.println("user = " + user);

		String name = (String) user.get("NAME");
		String dName = (String) user.get("DNAME");
		String pName = (String) user.get("PNAME");

		System.out.println("# : " + name + "/" + dName + "/" + pName);

		a.put("name", name);
		a.put("dName", dName);
		a.put("pName", pName);

		String jmap = gson.toJson(a);
		System.out.println("jmap =" + jmap);

		for (int i = 0; i < sockets.size(); i++) {
			try {
				TextMessage msg = new TextMessage(gson.toJson(jmap));
				sockets.get(i).sendMessage(msg);			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	

	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sockets.remove(session);
	}

}
