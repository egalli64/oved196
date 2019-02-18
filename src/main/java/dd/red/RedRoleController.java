package dd.red;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import dd.red.model.RedRoleRepository;

@Controller
public class RedRoleController {
	private static final Logger logger = LoggerFactory.getLogger(RedRoleController.class);

	@Autowired
	RedRoleRepository repository;

	@GetMapping("/red/roles")
	public String getRoles(Model model) {
		logger.trace("red getRoles");
		
		model.addAttribute("data", repository.findAll());
		return "/red/roles";
	}
}




