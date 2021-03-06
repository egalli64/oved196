





package dd.red;


import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
public class RedCoderController {
	private static final Logger logger = LoggerFactory.getLogger(RedCoderController.class);
//	private static final String CODER = "IT_PROG";
	
	
	@Autowired
	RedCoderRepository repository;
	@Autowired
	RedTeamRepository repositoryTeam;
	@Autowired
	RedRoleRepository repositoryRole;
	

	private String findAll(Model model) {
		logger.trace("findAll()");
		model.addAttribute("data", repository.findAll());
		model.addAttribute("team", repositoryTeam.findAll());
		model.addAttribute("role", repositoryRole.findAll());
		return "/red/coders";
	}

	private void save(RedCoder team, Model model) {
		logger.trace("save()");
		try {
			repository.save(team);
		} catch (DataAccessException dae) {
			String message = " You Can't add  " + team.getName() + "  ";
			if (team.getId() != 0) {
				message += "  " + team.getId();
			} else {
				message += " this name already exist";
			}
			logger.error(message);
			model.addAttribute("msg", message);
		}
	}
	

	@GetMapping("/red/coder/createRole")
    public String createRole( @RequestParam String name, @RequestParam String role, Model model) {
        logger.trace("create()");
        RedRole r= repositoryRole.findByName(role).get();
        RedCoder p = repository.findByName(name).get();
        p.getRoles().add(r);
        save(p, model);
        
     
        return findAll(model);
    }
	
//	@GetMapping("/sortedStClerk")
 //   public String sortedcORDERS(Model model) {
//        logger.debug("Sorted employees");

//        List<RedCoder> result = repository.findByJobId("ST_CLERK", Sort.by(Direction.ASC, "name"));

//        model.addAttribute("employees", result);
 //       model.addAttribute("msg", " st_clerks sorted by last name");
//        return "employees";
//    }
	
	@GetMapping("/red/coders")
	public String getCoders(Model model) {
		logger.trace("red getCoders");

		return findAll(model);
	}

	@GetMapping("/red/coder/setTeam")
	public String setTeam(Model model, @RequestParam String name, @RequestParam String test) {

		logger.trace("setTeam()");
		Optional<RedCoder> opt = repository.findByName(name);
		if (opt.isPresent()) {
			RedCoder team = opt.get();
			logger.debug(String.format("Change team ", team.getName(), test));
			RedTeam team1 = new RedTeam();
					team1 = repositoryTeam.findByName(test).get();
					long a = team1.getId();
			team.setTeam(team1);
			save(team, model);

		} else {
			String message = String.format("You can't save: ", name + "not found");
			logger.error(message);
			model.addAttribute("msg", message);
		}

		return findAll(model);

	}
	
	 @GetMapping("/red/coder/createCoder")
	    public String create( @RequestParam String name, @RequestParam String id_t, Model model) {
	        logger.trace("create()");
	        //RedTeam team = new  RedTeam ("null");
	        
	        RedTeam team = repositoryTeam.findByName(id_t).get();
	        save(new RedCoder(name, team), model);
	        return findAll(model);
	    }
	 
	 @GetMapping("/red/coder/deleteCoder")
	    public String delete( 
	            @RequestParam String name, 
	            Model model) {
	        try {
	        	RedCoder team = new RedCoder();
		            team=repository.findByName(name).get();
		            long a = team.getId();
		            repository.deleteById(a);
	            String message = String.format("The coder's delete executed with success", name);
	            System.out.println(message);
	        } catch (DataAccessException dae) {
	            String message = String.format("You can't delete the coder ", name);
	            logger.error(message);
	            model.addAttribute("msg", message);
	        }

	        return findAll(model);
	        
	    }
		@GetMapping("/red/coder/deleteRole")
	    public String deleteRole( @RequestParam String name, @RequestParam String role, Model model) {
	        logger.trace("delete()");
	        RedRole r= repositoryRole.findByName(role).get();
	        RedCoder p = repository.findByName(name).get();
	        Set<RedRole> rol = p.getRoles();
	        Iterator<RedRole> iterator = rol.iterator();
	 //       if(!p.getRoles().contains(r)) {
//	        	logger.trace("deleteif()");
	        int size = p.getRoles().size();
	        if (size > 1) {
				while (iterator.hasNext()) {
					if (iterator.next().getId() == r.getId()) { // c'è
						iterator.remove();
						repository.save(p);
						break;
					}
				}
			}
	  //      p.getRoles().remove(r);
	 //       }
	        save(p, model);
	     
	        return findAll(model);
	    }
		@GetMapping("/red/coder/orderByName")
		public String orderByName( Model model) {
			model.addAttribute("data", repository.findAllByOrderByName());
			model.addAttribute("team", repositoryTeam.findAll());
			model.addAttribute("role", repositoryRole.findAll());
			return "/red/coders";
		}
		
		@GetMapping("/red/coder/orderByTeam")
		public String orderByteam( Model model) {
			model.addAttribute("data", repository.findAllByOrderByTeam());
			model.addAttribute("team", repositoryTeam.findAll());
			model.addAttribute("role", repositoryRole.findAll());
			return "/red/coders";
		}
		
		@GetMapping("/red/coder/orderByRole")
		public String orderByrole( Model model) {
			model.addAttribute("data", repository.findAllByOrderByRoles());
			model.addAttribute("team", repositoryTeam.findAll());
			model.addAttribute("role", repositoryRole.findAll());
			return "/red/coders";
		}
		
		/* @GetMapping("/sortedStClerk")
	    public String sortedEmployees(Model model) {
	        logger.debug("Sorted employees");

	        List<Employee> result = repo.findByJobId("ST_CLERK", Sort.by(Direction.ASC, "lastName"));

	        model.addAttribute("employees", result);
	        model.addAttribute("msg", " st_clerks sorted by last name");
	        return "employees";
	    }*/

	 
	 
	
	

}
	
