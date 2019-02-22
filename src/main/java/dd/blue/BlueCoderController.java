package dd.blue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
import dd.blue.model.BlueCoder;
import dd.blue.model.BlueCoderRepository;
import dd.blue.model.BlueRole;
import dd.blue.model.BlueRoleRepository;
import dd.blue.model.BlueTeam;
import dd.blue.model.BlueTeamRepository;

@Controller
public class BlueCoderController {

	String ok = "Operazione conclusa con successo :)";

	private static final Logger logger = LoggerFactory.getLogger(BlueCoderController.class);

	@Autowired
	BlueCoderRepository coderRepo;
	@Autowired
	BlueRoleRepository roleRepo;
	@Autowired
	BlueTeamRepository teamRepo;

	@GetMapping("/blue/teams")
	public String getAll(Model model) {

		Iterable<BlueTeam> ttt = teamRepo.findAllByOrderByName();
		model.addAttribute("teams", ttt);
		
		Iterator<BlueTeam> tt = ttt.iterator();
		
		List<List<BlueCoder>> ris = new ArrayList<>();
		
		while(tt.hasNext()) {
			List<BlueCoder> sut = coderRepo.findByTeam_NameOrderByLastnameAscFirstnameAsc(tt.next().getName());
			ris.add(sut);
		}
		
		model.addAttribute("sizez", ris.size());
		
		model.addAttribute("coders", ris);
		
		return "/blue/teams";
	}

	@GetMapping("/blue/coders")
	public String coders(Model model) {
		// logger.trace("getAll()");
		model.addAttribute("coders", coderRepo.findAll());
		model.addAttribute("teams", teamRepo.findAll());
		model.addAttribute("roles", roleRepo.findAll());
		return "/blue/coders";
	}

	private void save(BlueCoder coder, Model model) {
		try {
			coderRepo.save(coder);
		} catch (DataAccessException dae) {
			String message = "Can't give name " + coder.getFirstname() + coder.getLastname() + " to ";
			if (coder.getIdCoder() != 0) {
				message += " coder " + coder.getIdCoder();
			} else {
				message += " your new coder";
			}
			model.addAttribute("msg", message);
		}
	}

	@GetMapping("/blue/coders/orderby")
	public String orderBy(@RequestParam String by, Model model) {
		logger.trace("orderBy()");

		List<BlueCoder> coders;

		switch (by) {
		case "firstname":
			coders = coderRepo.findAllByOrderByFirstnameAscLastnameAsc();
			break;

		case "lastname":
			coders = coderRepo.findAllByOrderByLastnameAscFirstnameAsc();
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
		model.addAttribute("roles", roleRepo.findAll());
		return "/blue/coders";
	}

	@GetMapping("/blue/coders/addrole")
	public String addRole(@RequestParam Integer coderid, @RequestParam Integer roleid, Model model) {
		if (coderid == 0) {
			String message = "Attenzione! Selezionare un coder.";
			model.addAttribute("msg", message);
			return coders(model);
		}

		logger.trace("addRole()");
		BlueCoder coder = (coderRepo.findById(coderid)).get();
		BlueRole role = (roleRepo.findById(roleid)).get();
		Set<BlueRole> codrol = coder.getRole();
		boolean check = false;

		for (BlueRole r : codrol) {
			if (r.getIdRole() == roleid) { // c'è
				check = true;
				break;
			}
		}
		if (check == false) {
			codrol.add(role);
			coderRepo.save(coder);
		} else {
			String message = "Attenzione: " + coder.getFirstname() + " " + coder.getLastname() + " ha già il ruolo di "
					+ role.getNomeRole() + "!";
			logger.error(message);
			model.addAttribute("msg", message);
			return coders(model);
		}
		model.addAttribute("msg", ok);
		return coders(model);
	}

	@GetMapping("/blue/coders/removerole")
	public String removeRole(@RequestParam Integer coderid, @RequestParam Integer roleid, Model model) {
		if (coderid == 0) {
			String message = "Attenzione! Selezionare un coder.";
			model.addAttribute("msg", message);
			return coders(model);
		}

		logger.trace("removeRole()");
		BlueCoder coder = (coderRepo.findById(coderid)).get();
		BlueRole role = (roleRepo.findById(roleid)).get();
		Set<BlueRole> codrol = coder.getRole();
		Iterator<BlueRole> itr = codrol.iterator();
		Iterator<BlueRole> rti = codrol.iterator();
		
		int tot = codrol.size();
		int sum = 0;
		
		while(rti.hasNext()) {
			if (!(rti.next().getIdRole() == roleid)) {
				sum++;
			}
		}
		
		if (sum == tot) {
			String message = "Attenzione: " + coder.getFirstname() + " " + coder.getLastname() + " non ha il ruolo di "
				+ role.getNomeRole() + "!";
			logger.error(message);
			model.addAttribute("msg", message);
			return coders(model);
		}
			
		if (codrol.size() == 1) {
			String message = "Attenzione: " + coder.getFirstname() + " " + coder.getLastname()
					+ " deve avere almeno un ruolo!";
			logger.error(message);
			model.addAttribute("msg", message);
			return coders(model);
		}


		while (itr.hasNext()) {
			if (itr.next().getIdRole() == roleid) { // c'è
				itr.remove();
				coderRepo.save(coder);
				model.addAttribute("msg", ok);
				return coders(model);
			}
		}
		
		
		return coders(model);
	}

	@GetMapping("/blue/coders/changeteam")
	public String changeTeam(@RequestParam Integer coderId, @RequestParam Integer teamId, Model model) {
		if (coderId == 0) {
			String message = "Attenzione! Selezionare un coder.";
			model.addAttribute("msg", message);
			return coders(model);
		}

		logger.trace("changeTeam()");

		Optional<BlueCoder> opt = coderRepo.findById(coderId);
		Optional<BlueTeam> team = teamRepo.findById(teamId);
		BlueCoder coder = opt.get();

		if (!(coder.getTeam().getId() == teamId)) {
			coder.getTeam().setId(teamId);
			coderRepo.save(coder);
		} else {
			String message = "Attenzione: " + coder.getFirstname() + " " + coder.getLastname() + " è gia nel team "
					+ team.get().getName() + "!";
			logger.error(message);
			model.addAttribute("msg", message);
			return coders(model);
		}
		model.addAttribute("msg", ok);
		return coders(model);
	}

	@GetMapping("/blue/coders/addcoder")
	public String addCoder(@RequestParam String firstname, @RequestParam String lastname, @RequestParam Integer teamid,
			@RequestParam Integer roleid, Model model) {
		String newfirstname = firstname.toUpperCase();
		String newlastname = lastname.toUpperCase();
		Iterable<BlueCoder> itc = coderRepo.findAll();
		for (BlueCoder c : itc) {
			if (c.getFirstname().equalsIgnoreCase(firstname) && c.getLastname().equalsIgnoreCase(lastname)) {
				String message = "Attenzione: " + c.getFirstname() + " " + c.getLastname() + " è gia staffato!";
				logger.error(message);
				model.addAttribute("msg", message);
				return coders(model);
			}
		}
		BlueTeam team = teamRepo.findById(teamid).get();
		BlueRole role = roleRepo.findById(roleid).get();
		Set<BlueRole> roles = new HashSet<BlueRole>();
		roles.add(role);
		save(new BlueCoder(newfirstname, newlastname, team, roles), model);
		model.addAttribute("msg", ok);
		return coders(model);
	}

	@GetMapping("/blue/coders/removecoder")
	public String removeCoder(@RequestParam Integer id, Model model) {

		if (id == 0) {
			String message = "Attenzione! Selezionare un coder.";
			model.addAttribute("msg", message);
			return coders(model);
		}

		coderRepo.deleteById(id);
		model.addAttribute("msg", ok);
		return coders(model);
	}

}
