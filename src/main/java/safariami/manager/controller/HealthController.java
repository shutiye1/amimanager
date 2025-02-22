package safariami.manager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class HealthController {
   // private static final Logger log = LoggerFactory.getLogger(HealthController.class);

    @SuppressWarnings("rawtypes")
	@RequestMapping(path = "/healthy", method = POST)
    public ResponseEntity Health() {
        return ResponseEntity.ok().build();
    };



}
