package app.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/index.do")
	public String indexHandle(HttpSession session, Map map) {
	

	
		return "redirect : /index.do";
	}
}