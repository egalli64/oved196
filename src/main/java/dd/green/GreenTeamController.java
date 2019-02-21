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
		logger.trace("Saved");
		try {
			repository.save(team);
			String message = "Team " + team.getName() + " inserito con successo";
			model.addAttribute("msg", message);
		} catch (DataAccessException dae) {
			String message = "Non puoi! " + team.getName() + " già esistente";
			logger.error(message);
			model.addAttribute("msg", message);
		}
	}

	@GetMapping("/green/teams/create")
	public String create( //
			@RequestParam String name, //
			Model model) {
		logger.trace("create()");
		String upperName = name.toUpperCase();
		Iterable<GreenTeam> teams = repository.findAll();
		String message = "";
		for (GreenTeam gt : teams) {
			if (gt.getName().equalsIgnoreCase(upperName)) {
				message = "Non puoi! Il team  " + upperName + " è già presente";
				model.addAttribute("msg", message);
				break;
			}
		}
		if (message.equals("")) {
			save(new GreenTeam(upperName), model);
			
		}
						
		return findAll(model);
	}

	@GetMapping("/green/teams/rename")
	public String rename( //
			@RequestParam long id_team, //
			@RequestParam String name, //
			Model model) {
		logger.trace("rename()");

		String upperName = name.toUpperCase();
		String message = "";
		Optional<GreenTeam> opt = repository.findById(id_team);
		Iterable<GreenTeam> teams = repository.findAll();
		for (GreenTeam t : teams) {
			if (t.getName().equalsIgnoreCase(name)) {
				message = "Il team " + upperName + " è già presente";
				model.addAttribute("msg", message);
				break;
			}
		}
		if (message.equals("")) {
			if (opt.isPresent()) {
				GreenTeam team = opt.get();
				logger.debug(String.format("Renaming team %s as %s", team.getName(), name));
				team.setName(upperName);
				save(team, model);
			} else {
				message = String.format("Non è possibile inserire il team %s perchè è già presente", name.toLowerCase());
				// logger.error(message);
				model.addAttribute("msg", message);
			}
		}
		model.addAttribute("data", repository.findAll());
		return findAll(model);
	}
		

	@GetMapping("/green/teams/delete")
	public String delete( //
			@RequestParam long id, //
			Model model) {
		try {
			repository.deleteById(id);
		} catch (DataAccessException dae) {
			String message = String.format("Non puoi eliminare il team selezionato");
			logger.error(message);
			model.addAttribute("msg", message);
		}

		return findAll(model);
	}
}
