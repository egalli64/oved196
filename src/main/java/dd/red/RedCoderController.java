





package dd.red;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dd.red.model.RedCoder;

import dd.red.model.RedCoderRepository;
import dd.red.model.RedTeam;
import dd.red.model.RedTeamRepository;

@Controller
public class RedCoderController {
	private static final Logger logger = LoggerFactory.getLogger(RedCoderController.class);

	@Autowired
	RedCoderRepository repository;
	@Autowired
	RedTeamRepository repositoryTeam;

	private String findAll(Model model) {
		logger.trace("findAll()");
		model.addAttribute("data", repository.findAll());
		return "/red/coders";
	}

	private void save(RedCoder team, Model model) {
		logger.trace("save()");
		try {
			repository.save(team);
		} catch (DataAccessException dae) {
			String message = "Can't give name " + team.getName() + " to ";
			if (team.getId() != 0) {
				message += " team " + team.getId();
			} else {
				message += " il tuo cambiamneto";
			}
			logger.error(message);
			model.addAttribute("msg", message);
		}
	}

	@GetMapping("/red/coders")
	public String getCoders(Model model) {
		logger.trace("red getCoders");

		model.addAttribute("data", repository.findAll());
		return "/red/coders";
	}

	@GetMapping("/red/coder/setTeam")
	public String setTeam(Model model, @RequestParam String name, @RequestParam RedTeam test) {

		logger.trace("setTeam()");
		Optional<RedCoder> opt = repository.findByName(name);
		if (opt.isPresent()) {
			RedCoder team = opt.get();
			logger.debug(String.format("Change team %s as %s", team.getName(), test));
			team.setTeam(test);
			save(team, model);

		} else {
			String message = String.format("Can't save team %d: not found", name);
			logger.error(message);
			model.addAttribute("msg", message);
		}

		return findAll(model);

	}
	
	 @GetMapping("/red/coder/createCoder")
	    public String create(@RequestParam long id, @RequestParam String name, @RequestParam RedTeam id_t, Model model) {
	        logger.trace("create()");
	        //RedTeam team = new  RedTeam ("null");
	        if (id_t== null) {
	        	id_t =repositoryTeam.findByName("bench").get();
	        	
	        }
	        

	        save(new RedCoder(id, name, id_t), model);
	        return findAll(model);
	    }
	 
	 @GetMapping("/red/coder/deleteCoder")
	    public String delete( 
	            @RequestParam String name, 
	            Model model) {
	        try {
	        	RedCoder team = new RedCoder();
		            team=repository.findByName(name).get();
		            long a = team.getId();
		            repository.deleteById(a);
	            String message = String.format("la persona  è stata eliminata correttamente", name);
	            System.out.println(message);
	        } catch (DataAccessException dae) {
	            String message = String.format("Can't delete person ", name);
	            logger.error(message);
	            model.addAttribute("msg", message);
	        }

	        return findAll(model);
	        
	    }
	
	

}
	

