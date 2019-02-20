package dd.green;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dd.blue.model.BlueTeam;
import dd.green.model.GreenTeam;
import dd.green.model.GreenTeamRepository;

@Controller
public class GreenTeamController {
	private static final Logger logger = LoggerFactory.getLogger(GreenTeamController.class);

	@Autowired
	GreenTeamRepository repository;

	private String findAll(Model model) {
		logger.trace("findAll()");
		model.addAttribute("data", repository.findAll());
		return "/green/teams";
	}

	@GetMapping("/green/teams")
	public String getAll(Model model) {
		logger.trace("ho chiamato la getAll()");
		return findAll(model);
	}

	private void save(GreenTeam team, Model model) {
		//logger.trace("Saved");
		String message = "";
		try {
			repository.save(team);
		} catch (DataAccessException dae) {
			message = "Non posso inserire " + team.getName() + "perchè già presente";
			if (team.getId() != 0) {
				message += " squadra " + team.getName();
			} else {
				message += " tuo nuovo team";
			}
			
			//logger.error(message);
			model.addAttribute("msg", message);
			
		}
	}

	@GetMapping("/green/team/create")
	public String create( //
			@RequestParam String name, //
			Model model) {
		logger.trace("create()");
		String upperName = name.toUpperCase();
		Iterable<GreenTeam> teams = repository.findAll();
		String message = "";
		for(GreenTeam gt: teams) {
			if (gt.getName().equalsIgnoreCase(upperName)) {
				message = "Il team " + upperName + " è già presente";
				model.addAttribute("msg", message);
				break;
			}
		}
		if(message.equals("")) {
			save(new GreenTeam(upperName), model);
		}
		
		
		return findAll(model);
	}

	@GetMapping("/green/team/rename")
	public String rename( //
			@RequestParam long id, //
			@RequestParam String name, //
			Model model) {
		//logger.trace("rename()");

		String upperName = name.toUpperCase();
		String message = "";
		Optional<GreenTeam> opt = repository.findById(id);
		Iterable<GreenTeam> teams = repository.findAll();
		for (GreenTeam gt : teams) {
			if (gt.getName().equalsIgnoreCase(name)) {
				message = "Il team " + upperName + " è già presente";
				model.addAttribute("msg", message);
				break;
			}
		}
		if (message.equals("")) {
			if (opt.isPresent()) {
				GreenTeam team = opt.get();
				// logger.debug(String.format("Renaming team %s as %s", team.getName(), name));
				team.setName(upperName);
				save(team, model);
			} else {
				message = String.format("Non è possibile salvare la squadra %s ", name);
				// logger.error(message);
				model.addAttribute("msg", message);
			}
		}

		return findAll(model);
	}

	@GetMapping("/green/team/delete")
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
