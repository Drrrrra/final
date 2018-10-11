package app.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

import app.model.AlertService;

@Controller
public class WebSocketController extends TextWebSocketHandler {
	@Autowired
	Gson gson;
	
	@Autowired
	AlertService service;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// 클라이언트측에서 WebSocket 객체를 생성해서 연결이 될 때.
		service.addSocket(session);
		
		System.out.println("afterConnectionEstablished ..\n"+ session );
		Map data = new HashMap();
		data.put("mode", "welcome");
		data.put("count", service.size());

		service.sendAll(data);
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// 클라이언트측에서 사용중인 WebSocket 이 종료 될때.
		System.out.println("afterConnectionClosed");
		service.removeSocket(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 클라이언트측에서 WebSocket 객체를 통해 데이터를 전송했을때.
		String payload = message.getPayload();
		System.out.println("handleTextMessage : " + payload);
		Map data = new HashMap();
			data.put("mode", "login");
			data.put("actor", "윤형호");
		TextMessage msg = new TextMessage(gson.toJson(data));
		session.sendMessage(msg);
	}
}
