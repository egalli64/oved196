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

import dd.green.model.GreenCoder;
import dd.green.model.GreenCoderRepository;
import dd.green.model.GreenRole;
import dd.green.model.GreenRoleRepository;
import dd.green.model.GreenTeam;
import dd.green.model.GreenTeamRepository;

@Controller
public class GreenCoderController {
	private static final Logger logger = LoggerFactory.getLogger(GreenCoderController.class);

	@Autowired
	GreenTeamRepository repositoryTeams;

	@Autowired
	GreenCoderRepository repositoryCoders;

	@Autowired
	GreenRoleRepository repositoryRoles;

	private String findAll(Model model) {
		logger.trace("findAll()");
		model.addAttribute("dataCoders", repositoryCoders.findAll());
		model.addAttribute("dataTeams", repositoryTeams.findAll());
		model.addAttribute("dataRoles", repositoryRoles.findAll());
		return "/green/coders";
	}
	/*
	 * @GetMapping("/green/coders") public String getAll(Model model) {
	 * logger.trace("getAll()"); return findAll(model); }
	 */

	@GetMapping("/green/coders")
	public String getCoders(Model model) {
		logger.trace("green getCoders");
		model.addAttribute("dataCoders", repositoryCoders.findAll());
		model.addAttribute("dataTeams", repositoryTeams.findAll());
		model.addAttribute("dataRoles", repositoryRoles.findAll());
		return "/green/coders";

	}

	private void save(GreenCoder coder, Model model) {
		logger.trace("save()");
		try {
			repositoryCoders.save(coder);
		} catch (DataAccessException dae) {
			String message = "Non puoi! Il ruolo " + coder.getRoles() + "è già esistente. ";
//			if (coder.getId() != 0) {
//				message += " coder " + coder.getId();
//			} else {
//				message += " il tuo nuovo coder";
//			}
			logger.error(message);
			model.addAttribute("msg", message);
		}
	}

	@GetMapping("/green/coders/change_team")
	public String change_team( //
			@RequestParam long id_coder, //
			@RequestParam long id_team, //
			Model model) {
		logger.trace("rename()");

		Optional<GreenCoder> optCoders = repositoryCoders.findById(id_coder);
		// Optional<GreenRole> optRole = repositoryRoles.findById(id_team);

		if (optCoders.isPresent()) {
			GreenCoder coder = optCoders.get();
			logger.debug(String.format("Squadra %s rinominato in %s", coder.getTeam(), id_team));
			GreenTeam team = new GreenTeam(id_team);
			coder.setTeam(team);
			save(coder, model);
			String message = coder.getName() + " " + coder.getSurname() + " è passato nella squadra " + team.getName();
			model.addAttribute("msg", message);
		} else {
			GreenCoder coder = optCoders.get();
			String message = coder.getName() + " " + coder.getSurname() + "è già nella squadra " + coder.getTeam().getName();
			logger.error(message);
			model.addAttribute("msg", message);
		}

		return findAll(model);
	}

	@GetMapping("/green/coders/create_role")
	public String create( //
			@RequestParam long id_coder, 
			@RequestParam long id_role, 
			Model model) {
		logger.trace("create()");

		GreenCoder coder = (repositoryCoders.findById(id_coder)).get();
		GreenRole role = (repositoryRoles.findById(id_role)).get();
		Set<GreenRole> rol_cod = coder.getRoles();
		
		boolean check = false;
		for (GreenRole r : rol_cod) {
			if (r.getId() == id_role) {
				check = true;
				break;
			}
		}
		if (check == false) {
			rol_cod.add(role);
			repositoryCoders.save(coder);
			String message = coder.getRoles() + " creato";
			model.addAttribute("msg", message);
		}

		return findAll(model);
	}

	@GetMapping("/green/coders/delete_role")
	public String delete( //
			@RequestParam long id_coder, //
			@RequestParam long id_role, Model model) {
		logger.trace("delete()");

		GreenCoder coder = (repositoryCoders.findById(id_coder)).get();
//		GreenRole role = (repositoryRoles.findById(id_role)).get();
		Set<GreenRole> rol_cod = coder.getRoles();

		rol_cod.removeIf(r -> r.getId() == id_role);
		repositoryCoders.save(coder);
		String message = "Il ruolo " + coder.getRoles() + "è stato rimosso";
		model.addAttribute("msg", message);

		return findAll(model);
	}

	@GetMapping("/green/coders/add_coder")
	public String addCoder( //
			@RequestParam long id_role, //
			@RequestParam String name, //
			@RequestParam String surname, //
			Model model) {

		logger.trace("create()");

		GreenRole role = (repositoryRoles.findById(id_role)).get();
		Optional<GreenTeam> team = repositoryTeams.findByName("NUOVI");

//		Optional<GreenTeam> team =  repositoryTeams.findById(4L);	

		GreenCoder coder = new GreenCoder(name, surname, team.get(), null);
		save(coder, model);
		
		long b = coder.getId();

		GreenCoder coder2 = (repositoryCoders.findById(b)).get();
		Set<GreenRole> rol_cod = coder2.getRoles();

		rol_cod.add(role);
		repositoryCoders.save(coder2);
		String message = coder2.getName() + " " +  coder2.getSurname() + " inserito con successo";
		model.addAttribute("msg", message);
		return findAll(model);

	}
	
	@GetMapping("/green/coders/delete_coder")
	public String deleteCoder( //
			@RequestParam long id_coder, //
			Model model) {
		
		
		try {
			repositoryCoders.deleteById(id_coder);
			String message = "coder rimosso";
			model.addAttribute("msg", message);
		} catch (DataAccessException dae) {
			String message = String.format("Non puoi eliminare il coder selezionato");
			logger.error(message);
			model.addAttribute("msg", message);
		}

		return findAll(model);
	}
}