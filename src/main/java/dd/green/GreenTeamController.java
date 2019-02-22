package dd.green;

import java.util.Optional;
import java.util.Set;

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
			String message = "Squadra " + team.getName() + " inserita con successo";
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
				message = "Non puoi! La squadra " + upperName + " è già presente";
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
				message = "La squadra " + upperName + " è già presente";
				model.addAttribute("msg", message);
				break;
			} else if (t.getName().equalsIgnoreCase("NUOVI")) {
				message = "La squadra NUOVI non può essere modificata";
				model.addAttribute("msg", message);
				break;
			}
		}
		if (message.equals("")) {
			if (opt.isPresent()) {
				GreenTeam team = opt.get();
				logger.debug(String.format("Squadra rinominata da %s a %s", team.getName(), name));
				team.setName(upperName);
				save(team, model);
			} else {
				message = String.format("Non è possibile rinominare la squadra %s perchè è già presente",
						name.toLowerCase());
				// logger.error(message);
				model.addAttribute("msg", message);
			}
		}
		model.addAttribute("data", repository.findAll());
		return findAll(model);
	}

	@GetMapping("/green/teams/delete")
	public String delete(@RequestParam long id, Model model, String name) {

		Optional<GreenTeam> opt = repository.findById(id);

		if (opt.get().getName().equalsIgnoreCase("NUOVI")) {
			String message = "La squadra NUOVI non può essere eliminata";
			model.addAttribute("msg", message);
		} else {

			try {
				String message = String.format("La squadra " + opt.get().getName() + " è stata eliminata");
				model.addAttribute("msg", message);
				repository.deleteById(id);
			} catch (DataAccessException dae) {
				String message = String.format(
						"Non puoi eliminare la squadra selezionata. Devi prima eliminare le persone della squadra selezionata");
				logger.error(message);
				model.addAttribute("msg", message);
			}
		}
		return findAll(model);
	}
}
