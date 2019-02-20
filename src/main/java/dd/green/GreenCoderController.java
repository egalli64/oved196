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
import dd.green.model.GreenTeam;


@Controller
public class GreenCoderController {
	private static final Logger logger = LoggerFactory.getLogger(GreenCoderController.class);

	@Autowired
	GreenCoderRepository repository;

    private String findAll(Model model) {
        logger.trace("findAll()");
        model.addAttribute("data", repository.findAll());
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
		model.addAttribute("data", repository.findAll());
		return "/green/coders";
	}
	
    private void save(GreenCoder coder, Model model) {
        logger.trace("save()");
        try {
            repository.save(coder);
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

    @GetMapping("/green/coders/create_role")
    public String create_role( //
    		@RequestParam String name, //
    		@RequestParam long id, //
    		@RequestParam GreenTeam team, //
            Model model) {
        logger.trace("create()");

        save(new GreenCoder(id,name,team), model);
        return findAll(model);
    }

    @GetMapping("/green/coders/rename_role")
    public String rename_role( //
            @RequestParam long id, //
            @RequestParam String name, //
            Model model) {
        logger.trace("rename()");

        Optional<GreenCoder> opt = repository.findById(id);
        if (opt.isPresent()) {
        	GreenCoder coder = opt.get();
            logger.debug(String.format("Renaming coder %s as %s", coder.getName(), name));
            coder.setName(name);
            save(coder, model);
        } else {
            String message = String.format("Can't save coder %d: not found", id);
            logger.error(message);
            model.addAttribute("msg", message);
        }

        return findAll(model);
    }
    
    @GetMapping("/green/coders/rename")
    public String change_team( //
            @RequestParam long id, //
            @RequestParam String name, //
            Model model) {
        logger.trace("rename()");

        Optional<GreenCoder> opt = repository.findByName(name);
        if (opt.isPresent()) {
        	GreenCoder coder = opt.get();
            logger.debug(String.format("Changed team %s as %s", coder.getName(), name));
            coder.setName(name);
            save(coder, model);
        } else {
            String message = String.format("Can't change team %d: not found", name);
            logger.error(message);
            model.addAttribute("msg", message);
        }

        return findAll(model);
    }

    @GetMapping("/green/coders/delete_role")
    public String delete_role( //
            @RequestParam long id, //
            Model model) {
        try {
            repository.deleteById(id);
        } catch (DataAccessException dae) {
            String message = String.format("Can't delete coder %d", id);
            logger.error(message);
            model.addAttribute("msg", message);
        }

        return findAll(model);
}

}