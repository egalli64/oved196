package dd.red;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	private void save(RedCoder coder, Model model) {
		logger.trace("save()");
		try {
			repositoryCoder.save(coder);
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
    public String create( @RequestParam String name, @RequestParam String role, Model model) {
        logger.trace("create()");
        RedRole r= repositoryRole.findByName(role).get();
        RedCoder p = repositoryCoder.findByName(name).get();
        p.getRoles().add(r);
        save(p, model);
        
     
        return findAll(model);
    }
	
	@GetMapping("/red/role/setRole")
    public String setCoder( @RequestParam String name, @RequestParam String role, @RequestParam String roleNew,Model model) {
		logger.trace("modify()");
		RedRole r= repositoryRole.findByName(role).get();
		RedRole rn= repositoryRole.findByName(roleNew).get();
		RedCoder p = repositoryCoder.findByName(name).get();
		
		//if (p.getRoles().contains(r)) {
			p.getRoles().remove(r);
			p.getRoles().add(rn);
		//}
		save(p, model);
		return findAll(model);
	}
	
	@GetMapping("/red/role/deleteRole")
    public String deleteRole( @RequestParam String name, @RequestParam String role, Model model) {
        logger.trace("delete()");
        RedRole r= repositoryRole.findByName(role).get();
        RedCoder p = repositoryCoder.findByName(name).get();
//        if(p.getRoles().contains(r)) {
        	logger.trace("deleteif()");
        p.getRoles().remove(r);
//        }
        save(p, model);
     
        return findAll(model);
    }
	
	

}



	



