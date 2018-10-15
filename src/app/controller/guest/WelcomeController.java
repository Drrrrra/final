package app.controller.guest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.request.WebRequest;

import app.model.EmployeeRepository;
import app.service.SocketService;


@Controller
public class WelcomeController {
	Map<String, HttpSession> sessions;
	
	public WelcomeController() {
		sessions = new HashMap<>();
	}
	
	
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	SocketService socketService;
	
	@RequestMapping("/index.do")
	public String indexHandle(@SessionAttribute(required=false) String auth, Map attr) {
		if(auth == null) {
			return "index";
		}else {
			return "guest.home";
		}
	}
	
	@RequestMapping("/login.do")
	public String loginHandle(WebRequest webRequest, @RequestParam Map p, HttpSession session) {
		int cnt = employeeRepository.checkEmployee(p);
		if(cnt > 0) {
			// 중복로그인 막기 =======================================
			String id =(String)p.get("id");			
			if(sessions.containsKey(id)) {
				Map map = new HashMap<>();
					map.put("mode", "overlap");
				socketService.sendOne(map,id);
				sessions.get(id).invalidate();
			}
			sessions.put(id, session);
			
			//========================================================
			Map one = employeeRepository.getEmployee(id);
			webRequest.setAttribute("userId", id, WebRequest.SCOPE_SESSION);
			webRequest.setAttribute("user", one, WebRequest.SCOPE_SESSION);
			webRequest.setAttribute("auth", "on", WebRequest.SCOPE_SESSION);
			
			
			Map msg = new HashMap<>();
				msg.put("mode", "login");
				msg.put("actor", one);
//				msg.put("mode", "overlap");
			socketService.sendAll(msg);
			// socketService.sendOne(msg, "em1000");
		}
		return "redirect:/";	// redirect:/index.do
	}
		
	@RequestMapping("/change.do")
	public String changePassHandle(WebRequest wr, @RequestParam Map rp) {
		
		return "";
	}
}
	