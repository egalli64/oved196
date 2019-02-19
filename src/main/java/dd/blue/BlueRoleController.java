package dd.blue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dd.blue.model.BlueRoleRepository;

@Controller
public class BlueRoleController {
	private static final Logger logger = LoggerFactory.getLogger(BlueRoleController.class);

	@Autowired
	BlueRoleRepository repository;


}
