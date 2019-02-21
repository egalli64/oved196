package dd.blue;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dd.blue.model.BlueCoder;
import dd.blue.model.BlueCoderRepository;
import dd.blue.model.BlueRoleRepository;
import dd.blue.model.BlueTeam;
import dd.blue.model.BlueTeamRepository;

@Controller
public class BlueTeamController {
	// private static final Logger logger =
	// LoggerFactory.getLogger(BlueTeamController.class);

	@Autowired
	BlueTeamRepository teamRepo;
	@Autowired
	BlueCoderRepository coderRepo;
	@Autowired
	BlueRoleRepository roleRepo;

//    private String findAll(Model model) {
//        //logger.trace("findAll()");
//        model.addAttribute("teams", teamRepo.findAll());
//        return "/blue/teams";
//    }
	@GetMapping("/blue/settings")
	public String settings(Model model) {
		// logger.trace("getAll()");
		model.addAttribute("coders", coderRepo.findAll());
		model.addAttribute("teams", teamRepo.findAll());
		return "/blue/settings";
	}
	
	@GetMapping("/blue/teams/orderby")
	public String orderBy(@RequestParam String by, Model model) {

		List<BlueCoder> coders;

		switch (by) {
		case "firstname":
			coders = coderRepo.findAllByOrderByFirstname();
			break;

		case "lastname":
			coders = coderRepo.findAllByOrderByLastname();
			break;

		case "Team":
		coders = coderRepo.findAllByOrderByTeam();
		break;

//	case "Role":
//			coders = coderRepo.findAllOrderByRole();
//			break;

		default:
			coders = coderRepo.findAllByOrderByIdCoder();
		}
		
		model.addAttribute("coders", coders);
		model.addAttribute("teams", teamRepo.findAll());
		return "/blue/teams";
	}

	private void save(BlueTeam team, Model model) {
		// logger.trace("save()");
		try {
			teamRepo.save(team);
		} catch (DataAccessException dae) {
			String message = "Can't give name " + team.getName() + " to ";
			if (team.getId() != 0) {
				message += " team " + team.getId();
			} else {
				message += " your new team";
			}
			// logger.error(message);
			model.addAttribute("msg", message);
		}
	}

	
	@GetMapping("/blue/teams/orderby")
	public String orderBy(@RequestParam String by, Model model) {

		List<BlueCoder> coders;

		switch (by) {
		case "firstname":
			coders = coderRepo.findAllByOrderByFirstname();
			break;

		case "lastname":
			coders = coderRepo.findAllByOrderByLastname();
			break;

		case "Team":
		coders = coderRepo.findAllByOrderByTeam();
		break;

		default:
			coders = coderRepo.findAllByOrderByIdCoder();
		}
		
		model.addAttribute("coders", coders);
		model.addAttribute("teams", teamRepo.findAll());
		model.addAttribute("roles", roleRepo.findAll());
		return "/blue/teams";
	}
	
	
	@GetMapping("/blue/settings/create")
	public String create(@RequestParam String name, Model model) {
		// logger.trace("create()");
		String newname = name.toUpperCase();
		Iterable<BlueTeam> teams = teamRepo.findAll();
		String message = "";
		for (BlueTeam t : teams) {
			if (t.getName().equalsIgnoreCase(newname)) {
				message = "Il team " + newname + " è già presente!!";
				model.addAttribute("msg", message);
				break;
			}
		}
		if (message.equals("")) {
			save(new BlueTeam(newname), model);
		}

		return settings(model);

	}

	@GetMapping("/blue/settings/rename")
	public String rename(@RequestParam Integer id, @RequestParam String name, Model model) {
		// logger.trace("rename()");
		if (id == 0) {
			String message = "Attenzione seleziona tutti i campi!";
			model.addAttribute("msg", message);
		} else {
			String newname = name.toUpperCase();
			String message = "";
			Optional<BlueTeam> opt = teamRepo.findById(id);
			Iterable<BlueTeam> teams = teamRepo.findAll();
			for (BlueTeam t : teams) {
				if (t.getName().equalsIgnoreCase(name)) {
					message = "Il team " + newname + " è già presente!!";
					model.addAttribute("msg", message);
					break;
				}
			}
			if (message.equals("")) {
				if (opt.isPresent()) {
					BlueTeam team = opt.get();
					// logger.debug(String.format("Renaming team %s as %s", team.getName(), name));
					team.setName(newname);
					save(team, model);
				} else {
					message = String.format("Can't save team %d: not found", id);
					// logger.error(message);
					model.addAttribute("msg", message);
				}
			}
		}
		return settings(model);

	}

	@GetMapping("/blue/settings/delete")
	public String delete(@RequestParam Integer id, Model model) {
		if (id == 0) {
			String message = "Attenzione! Selezionare un team.";
			model.addAttribute("msg", message);
			return settings(model);
		} 
		
		try {
			teamRepo.deleteById(id);
		} catch (DataAccessException dae) {
			String message = String.format("Can't delete team %d", id);
			// logger.error(message);
			model.addAttribute("msg", message);
		}
		
		return settings(model);
	}
}
