package dd.red;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dd.blue.model.BlueTeamRepository;

@Controller
public class RedRegionController {
	private static final Logger logger = LoggerFactory.getLogger(RedRegionController.class);

	@Autowired
	BlueTeamRepository repository;

	@GetMapping("/red/regions")
	public String getRegions(Model model) {
		logger.info("getRegions");
		
		model.addAttribute("data", repository.findAll());
		return "/red/regions";
	}
}
