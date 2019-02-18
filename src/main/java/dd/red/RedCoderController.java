package dd.red;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



import dd.red.model.RedCoder;


import dd.red.model.RedCoderRepository;
import dd.red.model.RedTeam;

@Controller
public class RedCoderController {
	private static final Logger logger = LoggerFactory.getLogger(RedCoderController.class);

	@Autowired
	RedCoderRepository repository;

	@GetMapping("/red/coders")
	public String getCoders(Model model) {
		logger.trace("red getCoders");
		
		model.addAttribute("data", repository.findAll());
		return "/red/coders";
	}	
	
	@GetMapping("/red/setTeam")
	public String setTeam (Model model, @RequestParam long id, @RequestParam RedTeam team) {
		String data;
		if ( repository.existsById(id)) {
			Optional<RedCoder> coder = repository.findById(id);
			RedCoder test = coder.get();
			test.setTeam(team);
		
			
			//model.addAttribute("data", repository.findAll());
			data = test.toString();
			return data;
			
		} else {
			
			data= "la persona legata a quell'id non esiste";
			return data;
		}
		
		
		
		
	}
}
