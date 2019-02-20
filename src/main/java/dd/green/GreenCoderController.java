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
    @GetMapping("/green/coders")
    public String getAll(Model model) {
        logger.trace("getAll()");
        return findAll(model);
    }
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
            String message = "Non posso aggiungere " + coder.getName() + " al ";
            if (coder.getId() != 0) {
                message += " coder " + coder.getId();
            } else {
                message += " il tuo nuovo coder";
            }
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
        Optional<GreenRole> optRole = repositoryRoles.findById(id_team);
        
        if (optCoders.isPresent()) {
        	GreenCoder coder = optCoders.get();
            logger.debug(String.format("Changed team %s as %s", coder.getName(), id_team));
            GreenTeam team = new GreenTeam(id_team);
            coder.setTeam(team);
            save(coder, model);
        } else {
            String message = String.format("Can't change team %d: not found", id_team);
            logger.error(message);
            model.addAttribute("msg", message);
        }

        return findAll(model);
    }

    @GetMapping("/green/coders/create_role")
    public String create( //
    		@RequestParam String name, //
    		@RequestParam long id, //
    		@RequestParam GreenTeam team, //
            Model model) {
        logger.trace("create()");

        save(new GreenCoder(id,name,team), model);
        return findAll(model);
    }

    @GetMapping("/green/coders/rename_role")
    public String rename( //
            @RequestParam long id, //
            @RequestParam String name, //
            Model model) {
        logger.trace("rename()");

        Optional<GreenCoder> optCoders = repositoryCoders.findById(id);
        Optional<GreenRole> optRole = repositoryRoles.findById(id);
        if (optCoders.isPresent()) {
        	GreenCoder coder = optCoders.get();
            logger.debug(String.format("Renaming team %s as %s", coder.getName(), name));
            coder.setName(name);
            save(coder, model);
        } else {
            String message = String.format("Can't save coder %d: not found", id);
            logger.error(message);
            model.addAttribute("msg", message);
        }

        return findAll(model);
    }
    

    @GetMapping("/green/coders/delete_role")
    public String delete( //
            @RequestParam long id, //
            Model model) {
        try {
            repositoryCoders.deleteById(id);
        } catch (DataAccessException dae) {
            String message = String.format("Can't delete coder %d", id);
            logger.error(message);
            model.addAttribute("msg", message);
        }

        return findAll(model);
}

}