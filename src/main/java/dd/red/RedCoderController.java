package dd.red;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dd.green.model.GreenCoderRepository;

@Controller
public class RedCoderController {
	private static final Logger logger = LoggerFactory.getLogger(RedCoderController.class);

	@Autowired
	GreenCoderRepository repository;

	@GetMapping("/red/coders")
	public String getCoders(Model model) {
		logger.trace("red getCoders");
		
		model.addAttribute("data", repository.findAll());
		return "/red/coders";
	}
}
