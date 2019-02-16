package dd.green;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dd.green.model.GreenRoleRepository;

@Controller
public class GreenRoleController {
	private static final Logger logger = LoggerFactory.getLogger(GreenRoleController.class);

	@Autowired
	GreenRoleRepository repository;

	@GetMapping("/green/roles")
	public String getRoles(Model model) {
		logger.trace("green getRoles");
		
		model.addAttribute("data", repository.findAll());
		return "/green/roles";
	}
}
