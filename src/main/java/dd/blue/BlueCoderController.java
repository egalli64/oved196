package dd.blue;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	private static final Logger logger = LoggerFactory.getLogger(BlueTeamController.class);

	@Autowired
	BlueCoderRepository repository;
	BlueRoleRepository roleRepo;
	BlueTeamRepository teamRepo;
	
	private String findAll (Model model) {
		logger.trace("findAll()");
        model.addAttribute("data", repository.findAll());
        return "/blue/coders";
	}

	@GetMapping("/blue/coders/adderole")
	public String addRole(@RequestParam long coderid, @RequestParam long roleid, Model model) {
		logger.trace("addRole()");
        Optional<BlueCoder> opt = repository.findById(coderid);
        Optional<BlueRole> optRole = roleRepo.findById(roleid);
        
		if (opt.isPresent() && optRole.isPresent()) {			
			BlueCoder coder = opt.get();
			BlueRole newrole = optRole.get();
			if (!(coder.getRole().contains(newrole))){
				coder.getRole().add(newrole);
				repository.save(coder);
			} else {
				String message = "Attenzione: " + coder.getFirstname() + " "+ coder.getLastname() + " ha già il ruolo di " + newrole.getNomeRole()  + "!";
				logger.error(message);
	            model.addAttribute("msg", message);
			}
		} else {
				String message = "Attenzione: id o ruolo non validi.";
				logger.error(message);
	            model.addAttribute("msg", message);
			}
		 return findAll(model);
		}
	
	
	@GetMapping("/blue/coders/changerole")
	public String changeRole(@RequestParam long coderid, @RequestParam long newroleid, @RequestParam long oldroleid, Model model) {
		logger.trace("changeRole()");
        Optional<BlueCoder> opt = repository.findById(coderid);
        Optional<BlueRole> oldRole = roleRepo.findById(oldroleid);
        Optional<BlueRole> newRole = roleRepo.findById(newroleid);

		if (opt.isPresent() ) {			
			BlueCoder coder = opt.get();
			if (!(coder.getRole().contains(newRole.get()))
				&& coder.getRole().contains(oldRole.get())) 
			{
				coder.getRole().add(newRole.get());
				coder.getRole().remove(oldRole.get());
				repository.save(coder);
			} 
			else if (coder.getRole().contains(newRole.get())) {
				String message = "Attenzione: " + coder.getFirstname() + " "+ coder.getLastname() + " ha già il ruolo di " + newRole.get().getNomeRole() + "!";
				logger.error(message);
	            model.addAttribute("msg", message);
			}
			else {
				String message = "Attenzione: " + coder.getFirstname() + " "+ coder.getLastname() + " non ha il ruolo di " + newRole.get().getNomeRole() + "!";
				logger.error(message);
	            model.addAttribute("msg", message);
			}
		
		} else {
			String message = "Attenzione: id o ruolo non validi.";
			logger.error(message);
            model.addAttribute("msg", message);
		}
	 return findAll(model);
	}
	
	@GetMapping("/blue/coders/removerole")
	public String removeRole(@RequestParam long coderid, @RequestParam long roleid, Model model) {
		logger.trace("removeRole()");
        Optional<BlueCoder> opt = repository.findById(coderid);
        Optional<BlueRole> role = roleRepo.findById(roleid);

		if (opt.isPresent()) {			
			BlueCoder coder = opt.get();
			if (!(coder.getRole().contains(role.get()))) 
			{
				coder.getRole().remove(role.get());
				repository.save(coder);
			} 
			else {
				String message = "Attenzione: " + coder.getFirstname() + " "+ coder.getLastname() + " non ha il ruolo di " + role.get().getNomeRole() + "!";
				logger.error(message);
	            model.addAttribute("msg", message);
			}
		
		} else {
			String message = "Attenzione: id o ruolo non validi.";
			logger.error(message);
            model.addAttribute("msg", message);
		}
	 return findAll(model);
	}
	
	@GetMapping("/blue/coders/changeteam")
	public String changeTeam(@RequestParam long coderid, @RequestParam long teamId, Model model) {
		logger.trace("changeTeam()");
        Optional<BlueCoder> opt = repository.findById(coderid);
        Optional<BlueTeam> team = teamRepo.findById(teamId);

		if (opt.isPresent()) {			
			BlueCoder coder = opt.get();
			if (!(coder.getIdTeam()==teamId)) 
			{
				coder.setIdTeam(teamId);
				repository.save(coder);
			} 
			else {
				String message = "Attenzione: " + coder.getFirstname() + " "+ coder.getLastname() + " è gia nel team " + team.get().getName() + "!";
				logger.error(message);
	            model.addAttribute("msg", message);
			}
		
		} else {
			String message = "Attenzione: id non valido.";
			logger.error(message);
            model.addAttribute("msg", message);
		}
	 return findAll(model);
	}
	
}
