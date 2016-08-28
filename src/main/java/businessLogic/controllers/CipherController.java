package businessLogic.controllers;

import businessLogic.controllers.responseWrappers.TestResponse;
import businessLogic.services.TurningGrilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by oscar on 28/08/16.
 */

@RestController
@RequestMapping(value = "/cipher")
public class CipherController {

    @Autowired
    private TurningGrilleService turningGrilleService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<TestResponse> test() {
        return new ResponseEntity<>(new TestResponse(new int[] {1, 2, 3}, "YEAH"), HttpStatus.OK);
    }

    public TurningGrilleService getTurningGrilleService() {
        return turningGrilleService;
    }

    public void setTurningGrilleService(TurningGrilleService turningGrilleService) {
        this.turningGrilleService = turningGrilleService;
    }
}
