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

import dd.blue.model.BlueTeamRepository;
import dd.green.model.GreenTeam;
import dd.red.model.RedTeam;
import dd.red.model.RedTeamRepository;

@Controller
public class RedTeamController {
	private static final Logger logger = LoggerFactory.getLogger(RedTeamController.class);

	@Autowired
	RedTeamRepository repository;
	
	 private String findAll(Model model) {
	        logger.trace("findAll()");
	        model.addAttribute("data", repository.findAll());
	        return "/red/teams";
	    }

	 @GetMapping("/red/teams")
	    public String getAll(Model model) {
	        logger.trace("getAll()");
	        return findAll(model);
	    }
	 
	 private void save(RedTeam team, Model model) {
	        logger.trace("save()");
	        try {
	            repository.save(team);
	        } catch (DataAccessException dae) {
	            String message = "Can't give name " + team.getName() + " to ";
	            if (team.getId() != 0) {
	                message += " team " + team.getId();
	            } else {
	                message += " your new team";
	            }
	            logger.error(message);
	            model.addAttribute("msg", message);
	        }
	    }
	 
	 @GetMapping("/red/team/create")
	    public String create( //
	            @RequestParam String name, //
	            Model model) {
	        logger.trace("create()");

	        save(new RedTeam(name), model);
	        return findAll(model);
	    }
	 
	 @GetMapping("/red/team/rename")
	    public String rename( //
	            @RequestParam long id, //
	            @RequestParam String name, //
	            Model model) {
	        logger.trace("rename()");

	        Optional<RedTeam> opt = repository.findById(id);
	        if (opt.isPresent()) {
	            RedTeam team = opt.get();
	            logger.debug(String.format("Renaming team %s as %s", team.getName(), name));
	            team.setName(name);
	            save(team, model);
	        } else {
	            String message = String.format("Can't save team %d: not found", id);
	            logger.error(message);
	            model.addAttribute("msg", message);
	        }

	        return findAll(model);
	    }
	 
	 @GetMapping("/red/team/delete")
	    public String delete( //
	            @RequestParam long id, //
	            Model model) {
	        try {
	            repository.deleteById(id);
	        } catch (DataAccessException dae) {
	            String message = String.format("Can't delete team %d", id);
	            logger.error(message);
	            model.addAttribute("msg", message);
	        }

	        return findAll(model);
	    }
}
