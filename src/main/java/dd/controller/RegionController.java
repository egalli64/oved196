package dd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dd.model.RegionRepository;

@Controller
public class RegionController {
	@Autowired
	RegionRepository repository;

	@GetMapping("/regions")
	public String getRegions(Model model) {
		model.addAttribute("data", repository.findAll());
		return "regions";
	}
}
