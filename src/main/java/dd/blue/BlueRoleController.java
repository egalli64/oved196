package dd.blue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dd.blue.model.BlueRoleRepository;

@Controller
public class BlueRoleController {
	private static final Logger logger = LoggerFactory.getLogger(BlueRoleController.class);

	@Autowired
	BlueRoleRepository repository;

	@GetMapping("/blue/roles")
	public String getRoles(Model model) {
		logger.trace("blue getRoles");
		model.addAttribute("data", repository.findAll());
		return "/blue/roles";
	}
}
