package dd.blue;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
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

	private static final Logger logger = LoggerFactory.getLogger(BlueCoderController.class);

	@Autowired
	BlueCoderRepository coderRepo;
	@Autowired
	BlueRoleRepository roleRepo;
	@Autowired
	BlueTeamRepository teamRepo;

	@GetMapping("/blue/teams")
	public String getAll(Model model) {
		// logger.trace("getAll()");
		model.addAttribute("coders", coderRepo.findAll());
		model.addAttribute("teams", teamRepo.findAll());
		model.addAttribute("roles", roleRepo.findAll());
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
			// logger.error(message);
			model.addAttribute("msg", message);
		}
	}

	@GetMapping("/blue/coders/addrole")
	public String addRole(@RequestParam Integer coderid, @RequestParam Integer roleid, Model model) {
		logger.trace("addRole()");
		if (coderid == 0 || roleid == 0) {
			String message = "Attenzione seleziona tutti i campi!";
			model.addAttribute("msg", message);
		} else {
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
				String message = "Attenzione: " + coder.getFirstname() + " " + coder.getLastname()
						+ " ha già il ruolo di " + role.getNomeRole() + "!";
				logger.error(message);
				model.addAttribute("msg", message);
			}
		}
		return coders(model);
	}

	@GetMapping("/blue/coders/removerole")
	public String removeRole(@RequestParam Integer coderid, @RequestParam Integer roleid, Model model) {
		logger.trace("removeRole()");
		if (coderid == 0 || roleid == 0) {
			String message = "Attenzione seleziona tutti i campi!";
			model.addAttribute("msg", message);
		} else {
			BlueCoder coder = (coderRepo.findById(coderid)).get();
			BlueRole role = (roleRepo.findById(roleid)).get();
			Set<BlueRole> codrol = coder.getRole();
			Iterator<BlueRole> itr = codrol.iterator();
			int size = codrol.size();
			if (size > 1) {
				while (itr.hasNext()) {
					if (itr.next().getIdRole() == roleid) { // c'è
						itr.remove();
						coderRepo.save(coder);
						break;
					} else {
						String message = "Attenzione: " + coder.getFirstname() + " " + coder.getLastname()
								+ " non ha il ruolo di " + role.getNomeRole() + "!";
						logger.error(message);
						model.addAttribute("msg", message);
					}
				}
			} else {
				String message = "Attenzione: " + coder.getFirstname() + " " + coder.getLastname()
						+ " deve avere almeno un ruolo!";
				logger.error(message);
				model.addAttribute("msg", message);
			}
		}
		return coders(model);
	}

	@GetMapping("/blue/coders/changeteam")
	public String changeTeam(@RequestParam Integer coderId, @RequestParam Integer teamId, Model model) {
		logger.trace("changeTeam()");
		if (coderId == 0 || teamId == 0) {
			String message = "Attenzione seleziona tutti i campi!";
			model.addAttribute("msg", message);
		} else {
			Optional<BlueCoder> opt = coderRepo.findById(coderId);
			Optional<BlueTeam> team = teamRepo.findById(teamId);

			if (opt.isPresent()) {
				BlueCoder coder = opt.get();
				if (!(coder.getTeam().getId() == teamId)) {
					coder.getTeam().setId(teamId);
					coderRepo.save(coder);
				} else {
					String message = "Attenzione: " + coder.getFirstname() + " " + coder.getLastname()
							+ " è gia nel team " + team.get().getName() + "!";
					logger.error(message);
					model.addAttribute("msg", message);
				}

			} else {
				String message = "Attenzione: id non valido.";
				logger.error(message);
				model.addAttribute("msg", message);
			}
		}

		return coders(model);
	}

	@GetMapping("/blue/coders/addcoder")
	public String addCoder(@RequestParam String firstname, @RequestParam String lastname, @RequestParam Integer teamid,
			@RequestParam Integer roleid, Model model) {
		String newfirstname = firstname.toUpperCase();
		String newlastname = lastname.toUpperCase();

		if (firstname == null || lastname == null || teamid == 0 || roleid == 0) {
			String message = "Attenzione: inserire tutti i campi!";
			logger.error(message);
			model.addAttribute("msg", message);
			return coders(model);
		}
		
//		Iterable<BlueCoder> itcoders = coderRepo.findAll();
//		String message = "";
//		for (BlueCoder itc : itcoders) {
//			if (firstname == itc.getFirstname() && lastname == itc.getLastname()) {
//				message = "Attenzione: il programmatore è già un tuo dipendente!";
//				logger.error(message);
//				model.addAttribute("msg", message);
//				return coders(model);
//			}
//		}
//
//		if (message.equals("")) {
			BlueTeam team = teamRepo.findById(teamid).get();
			BlueRole role = roleRepo.findById(roleid).get();
			Set<BlueRole> roles = new HashSet<BlueRole>();
			roles.add(role);
			save(new BlueCoder(newfirstname, newlastname, team, roles), model);
//		}

		return coders(model);
	}
	
	@GetMapping("/blue/coders/removecoder")
	public String removeCoder(@RequestParam Integer id, Model model) {
		if (id == 0) {
			String message = "Attenzione: inserire tutti i campi!";
			logger.error(message);
			model.addAttribute("msg", message);
			return coders(model);
		}
		coderRepo.deleteById(id);
		return coders(model);
	}

//	@GetMapping("/blue/coders/changerole")
//		public String changeRole(@RequestParam Integer coderid, @RequestParam Integer oldroleid , @RequestParam Integer newroleid, Model model) {
//			logger.trace("changeRole()");
//				if (coderid == 0 || oldroleid == 0 || newroleid == 0) {
//					String message = "Attenzione seleziona tutti i campi!";
//					model.addAttribute("msg", message);
//				} else {
//					
//					BlueCoder coder = coderRepo.findById(coderid).get();
//					BlueRole oldrole = roleRepo.findById(oldroleid).get();
//					BlueRole newrole = roleRepo.findById(newroleid).get();
//					Set<BlueRole> codrol = coder.getRole();
//					boolean existold = false;
//					boolean existnew = false;
//
//					Iterator<BlueRole> itr = codrol.iterator();
//						while (itr.hasNext()) {							
//							if (itr.next().getIdRole() == oldroleid) { // c'è
//								existold = true;
//								break;
//							}
//							if (itr.next().getIdRole() == newroleid) {
//								existnew = true;
//								break;
//							}
//						}
//						if (existold == false) {
//							String message = "Attenzione: " + coder.getFirstname() + " " + coder.getLastname()+ " non ha il ruolo di " + oldrole.getNomeRole() + "!";
//							logger.error(message);
//							model.addAttribute("msg", message);
//							
//						}
//						if (existnew = true) {
//							String message = "Attenzione: " + coder.getFirstname() + " " + coder.getLastname()+ " ha già il ruolo di " + newrole.getNomeRole() + "!";
//							logger.error(message);
//							model.addAttribute("msg", message);
//						}
//						if (existold == true && existnew == false) {
//								codrol.remove(oldrole);
//								codrol.add(newrole);
//								coderRepo.save(coder);
//						}
//					
//				}
//				return coders(model);
//			}
}
