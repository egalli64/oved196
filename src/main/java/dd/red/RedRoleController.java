package dd.red;

import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dd.blue.model.BlueRole;
import dd.red.model.RedCoder;
import dd.red.model.RedCoderRepository;
import dd.red.model.RedRole;
import dd.red.model.RedRoleRepository;
import dd.red.model.RedTeam;
import dd.red.model.RedTeamRepository;

@Controller
public class RedRoleController {
	private static final Logger logger = LoggerFactory.getLogger(RedRoleController.class);

	@Autowired
	RedRoleRepository repositoryRole;
	@Autowired
	RedCoderRepository repositoryCoder;
	
	private String findAll(Model model) {
		logger.trace("findAll()");
		model.addAttribute("role", repositoryRole.findAll());
		model.addAttribute("coder", repositoryCoder.findAll());
		return "/red/roles";
	}
	
	private void save(RedRole coder, Model model) {
		logger.trace("save()");
		try {
			repositoryRole.save(coder);
		} catch (DataAccessException dae) {
			String message = "Can't give name " + coder.getName() + " to ";
			if (coder.getId() != 0) {
				message += " coder " + coder.getId();
			} else {
				message += " il tuo cambiamento";
			}
			logger.error(message);
			model.addAttribute("msg", message);
		}
	}

	@GetMapping("/red/roles")
	public String getRoles(Model model) {
		logger.trace("red getRoles");
		
		
		return findAll(model);
	}
	
	 @GetMapping("/red/role/createRole")
	    public String create( //
	            @RequestParam String nameRole, //
	            Model model) {
	        logger.trace("create()");

	        save(new RedRole( nameRole), model);
	        return findAll(model);
	    }
	
	
	@GetMapping("/red/role/deleteRole")
	public String deleteRole(@RequestParam String nameRole, Model model) {
	try {
		RedRole role = new RedRole();
		role = repositoryRole.findByName(nameRole).get();
		long r = role.getId();
		repositoryRole.deleteById(r);
		String message = String.format("Il ruolo è stato eliminato correttamente", nameRole);
        System.out.println(message);
	}
	catch (DataAccessException dae) {
		String message = String.format("Non si può cancellare il ruolo ", nameRole);
        logger.error(message);
        model.addAttribute("msg", message);
    }
	   return findAll(model);
       
    }
	
}
//	@GetMapping("/red/role/setRole")
//    public String setCoder( @RequestParam String name, @RequestParam String role, @RequestParam String roleNew,Model model) {
////		logger.trace("modify()");
//		RedRole r= repositoryRole.findByName(role).get();
//		RedRole rn= repositoryRole.findByName(roleNew).get();
//		RedCoder p = repositoryCoder.findByName(name).get();
		
		//if (p.getRoles().contains(r)) {
//			p.getRoles().remove(r);
//			p.getRoles().add(rn);
//		//}
//		save(p, model);
//		return findAll(model);
//	}
	
	





	



