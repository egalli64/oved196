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

import dd.green.model.GreenRole;
import dd.green.model.GreenRoleRepository;

@Controller
public class GreenRoleController {
	private static final Logger logger = LoggerFactory.getLogger(GreenRoleController.class);

	@Autowired
	GreenRoleRepository repository;

	@GetMapping("/green/roles")
	public String getRoles(Model model) {
		logger.trace("green getRoles");

		model.addAttribute("data", repository.findAll());
		return "/green/roles";
	}

	private String findAll(Model model) {
		logger.trace("findAll()");
		model.addAttribute("data", repository.findAll());
		return "/green/roles";
	}

	private void save(GreenRole roles, Model model) {
		logger.trace("Saved");
		try {
			repository.save(roles);
			String message = "Ruolo " + roles.getName() + " inserito con successo";
			model.addAttribute("msg", message);
		} catch (DataAccessException dae) {
			String message = "Non puoi! " + roles.getName() + " già esistente";
			logger.error(message);
			model.addAttribute("msg", message);
		}
	}

	@GetMapping("/green/roles/change_role")
	public String renameRole(@RequestParam long id_role, @RequestParam String role_name, Model model) {

		String message = "";
		Optional<GreenRole> opt = repository.findById(id_role);
		Iterable<GreenRole> roles = repository.findAll();
		for (GreenRole t : roles) {
			if (t.getName().equalsIgnoreCase(role_name)) {
				message = "Il team " + role_name + " è già presente";
				model.addAttribute("msg", message);
				break;
			}

		}
		if (message.equals("")) {
			if (opt.isPresent()) {
				GreenRole role = opt.get();
				logger.debug(String.format("Renaming role %s as %s", role.getName(), role_name));
				role.setName(role_name.toUpperCase());
				save(role, model);
			} else {
				message = String.format("Non è possibile inserire il ruolo %s perchè è già presente",
						role_name.toLowerCase());
				// logger.error(message);
				model.addAttribute("msg", message);
			}
		}
		model.addAttribute("data", repository.findAll());
		return findAll(model);
	}

	@GetMapping("/green/roles/add_role")
	public String addRole(@RequestParam String role_name, Model model) {

		Iterable<GreenRole> roles = repository.findAll();
		String message = "";
		for (GreenRole gt : roles) {
			if (gt.getName().equalsIgnoreCase(role_name)) {
				message = "Non puoi! Il ruolo  " + role_name + " è già presente";
				model.addAttribute("msg", message);
				break;
			}
		}
		if (message.equals("")) {
			save(new GreenRole(role_name), model);
		}

		return findAll(model);
	}

	@GetMapping("/green/roles/remove_role")
	public String removeRole(@RequestParam long id_role, Model model) {

		try {
			repository.deleteById(id_role);
		} catch (DataAccessException dae) {
			String message = String.format("Non puoi eliminare il ruolo selezionato. Elimina prima le persone associate del ruolo selezionato");
			logger.error(message);
			model.addAttribute("msg", message);
		}

		return findAll(model);
	}
	

}
