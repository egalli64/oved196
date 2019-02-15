package dd.blue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dd.blue.model.BlueTeamRepository;

@Controller
public class BlueTeamController {
	private static final Logger logger = LoggerFactory.getLogger(BlueTeamController.class);

	@Autowired
	BlueTeamRepository repository;

	@GetMapping("/blue/teams")
	public String getTeams(Model model) {
		logger.info("getTeams");
		
		model.addAttribute("data", repository.findAll());
		return "/blue/teams";
	}
}
