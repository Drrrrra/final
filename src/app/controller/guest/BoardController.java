package app.controller.guest;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

	@RequestMapping("/draw.do")
	public String drawHandler(Map map) {
		map.put("uri", "board");
		return "guest.board";
	}
}
