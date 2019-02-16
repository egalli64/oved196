package dd.green;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dd.green.model.GreenCoderRepository;

@Controller
public class GreenCoderController {
	private static final Logger logger = LoggerFactory.getLogger(GreenCoderController.class);

	@Autowired
	GreenCoderRepository repository;

	@GetMapping("/green/coders")
	public String getCoders(Model model) {
		logger.trace("green getCoders");
		
		model.addAttribute("data", repository.findAll());
		return "/green/coders";
	}
}
