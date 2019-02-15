package dd.green;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dd.green.model.GreenTeamRepository;

@Controller
public class GreenTeamController {
	private static final Logger logger = LoggerFactory.getLogger(GreenTeamController.class);

	@Autowired
	GreenTeamRepository repository;

	@GetMapping("/green/teams")
	public String getTeams(Model model) {
		logger.trace("green getTeams");
		
		model.addAttribute("data", repository.findAll());
		return "/green/teams";
	}
}
