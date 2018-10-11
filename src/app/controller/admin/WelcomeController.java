package app.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import app.model.EmployeeRepository;

@Controller
public class WelcomeController {

	@Autowired
	EmployeeRepository er;
	
	@GetMapping("/index.do")
	public String indexHandle() {
		return "index";
	}
	
	@PostMapping("/login.do")
	public String loginHandle(WebRequest wr) {
		
		String id = (String)wr.getParameter("inputId");
		String pass = (String)wr.getParameter("inputPass");
		
		Map map = new HashMap<>();
		map.put("id", id);
		map.put("pass",pass);
		
		Map get = er.getAccount(map);
		
		if(get != null) {
			return "redirect : /index.do";
		}else {
			return "home";
		}
	}
}