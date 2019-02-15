package dd.blue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dd.model.RegionRepository;

@Controller
public class BlueRegionController {
	private static final Logger logger = LoggerFactory.getLogger(BlueRegionController.class);

	@Autowired
	RegionRepository repository;

	@GetMapping("/blue/regions")
	public String getRegions(Model model) {
		logger.info("getRegions");
		
		model.addAttribute("data", repository.findAll());
		return "/blue/regions";
	}
}
