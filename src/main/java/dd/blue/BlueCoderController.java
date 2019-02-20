package dd.blue;

import java.util.Optional;
import java.util.Set;

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
	
	private static final Logger logger = LoggerFactory.getLogger(BlueCoderController.class);

	@Autowired
	BlueCoderRepository coderRepo;
	@Autowired
	BlueRoleRepository roleRepo;
	@Autowired
	BlueTeamRepository teamRepo;
	
	 @GetMapping("/blue/teams")
	    public String getAll(Model model) {
	        //logger.trace("getAll()");
	    model.addAttribute("coders", coderRepo.findAll());
        model.addAttribute("teams", teamRepo.findAll());
        model.addAttribute("roles", roleRepo.findAll());
        return "/blue/teams";	  
     }
	 
	 @GetMapping("/blue/settings")
	    public String settings(Model model) {
	        //logger.trace("getAll()");
	    model.addAttribute("coders", coderRepo.findAll());
	    model.addAttribute("teams", teamRepo.findAll());
	    model.addAttribute("roles", roleRepo.findAll());
	    return "/blue/settings";	  
	 }
	 
	 @GetMapping("/blue/coders")
	    public String coders(Model model) {
	        //logger.trace("getAll()");
	    model.addAttribute("coders", coderRepo.findAll());
	    model.addAttribute("teams", teamRepo.findAll());
	    model.addAttribute("roles", roleRepo.findAll());
	    return "/blue/coders";	  
	 }

	 
	@GetMapping("/blue/coders/addrole")
	public String addRole(@RequestParam Integer coderid, @RequestParam Integer roleid, Model model) {
		logger.trace("addRole()");
        Optional<BlueCoder> opt = coderRepo.findById(coderid);
        Optional<BlueRole> optRole = roleRepo.findById(roleid);
        
		if (opt.isPresent() && optRole.isPresent()) {			
			BlueCoder coder = opt.get();
			BlueRole newrole = optRole.get();
			if (!(coder.getRole().contains(newrole))){
				coder.getRole().add(newrole);
				coderRepo.save(coder);
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
		model.addAttribute("coders", coderRepo.findAll());
		model.addAttribute("roles", roleRepo.findAll());
		return "/blue/coders";
		}
	
	
	@GetMapping("/blue/coders/changerole")
	public String changeRole(@RequestParam Integer coderid, @RequestParam Integer oldroleid , @RequestParam Integer newroleid, Model model) {
		logger.trace("changeRole()");
        Optional<BlueCoder> opt = coderRepo.findById(coderid);
        Optional<BlueRole> oldR = roleRepo.findById(oldroleid);
        Optional<BlueRole> newR = roleRepo.findById(newroleid);

        BlueCoder coder = opt.get();
		Set<BlueRole> coderRoles = coder.getRole();
		BlueRole oldRole = oldR.get();
		BlueRole newRole = newR.get();
//			System.out.println("new " + newRole);
//			if (!(coderRoles.contains(oldRole))) 
//			{
//			String message = "Attenzione: " + coder.getFirstname() + " "+ coder.getLastname() + " non ha il ruolo di " + oldRole.getNomeRole() + "!";
//			logger.error(message);
//            model.addAttribute("msg", message);	
//			}
//			if ((coderRoles.contains(oldRole)) && !(coderRoles.contains(newRole))) {
		coderRoles.add(newRole);
		coderRoles.remove(oldRole);		
		coderRepo.save(coder);
//			}
//			if (coderRoles.contains(oldRole) && (coderRoles.contains(newRole))) {
//				String message = "Attenzione: " + coder.getFirstname() + " "+ coder.getLastname() + " ha già il ruolo di " + newRole.getNomeRole() + "!";
//				logger.error(message);
//	            model.addAttribute("msg", message);
//			}
//			if (!(coderRoles.contains(oldRole)) && (coderRoles.contains(newRole))) {
//				String message = "Attenzione: forse hai invertito i ruoli da modificare!";
//				logger.error(message);
//	            model.addAttribute("msg", message);
//			}
//			
//			
//		} else {
//			String message = "Attenzione: id non valido.";
//			logger.error(message);
//            model.addAttribute("msg", message);
		
		model.addAttribute("coders", coderRepo.findAll());
		model.addAttribute("roles", roleRepo.findAll());
		return "/blue/coders";
	}
	
	@GetMapping("/blue/coders/removerole")
	public String removeRole(@RequestParam Integer coderid, @RequestParam Integer roleid, Model model) {
		logger.trace("removeRole()");
		BlueCoder coder = (coderRepo.findById(coderid)).get();
		BlueRole role =(roleRepo.findById(roleid)).get();
		Set<BlueRole> codrol = coder.getRole();
		if (codrol.contains(role)) 
			{
			codrol.remove(role);
			coderRepo.save(coder);
			} 
		else {
			String message = "Attenzione: " + coder.getFirstname() + " "+ coder.getLastname() + " non ha il ruolo di " + role.getNomeRole() + "!";
			logger.error(message);
	        model.addAttribute("msg", message);
			}	 
		model.addAttribute("coders", coderRepo.findAll());
		model.addAttribute("roles", codrol);
		return "/blue/coders";
		}
	
	@GetMapping("/blue/coders/changeteam")
	public String changeTeam(@RequestParam Integer coderId, @RequestParam Integer teamId, Model model) {
		logger.trace("changeTeam()");
        Optional<BlueCoder> opt = coderRepo.findById(coderId);
        Optional<BlueTeam> team = teamRepo.findById(teamId);

		if (opt.isPresent()) {			
			BlueCoder coder = opt.get();
			if (!(coder.getTeam().getId()==teamId)) 
			{
				coder.getTeam().setId(teamId);
				coderRepo.save(coder);
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
		
		model.addAttribute("teams", teamRepo.findAll());
		model.addAttribute("coders", coderRepo.findAll());
		return "/blue/coders";
		}
	
}
