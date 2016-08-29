package businessLogic.controllers;

import businessLogic.controllers.requestWrappers.EncryptRequest;
import businessLogic.controllers.responseWrappers.EncryptResponse;
import businessLogic.controllers.responseWrappers.TestResponse;
import businessLogic.services.DataPreparation;
import businessLogic.services.TurningGrilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    @RequestMapping(value = "/encrypt", method = RequestMethod.POST)
    public ResponseEntity<EncryptResponse> encrypt(@RequestBody EncryptRequest request) {
        EncryptResponse response = new EncryptResponse();
        int[] message = DataPreparation.fillMessage(request.getMessage());
        HashMap<Integer, int[]> cipherInfo = TurningGrilleService.encrypt(message, request.getDirection());

        // Wrapping masks
        List<int[]> masks = new ArrayList<>();
        masks.add(cipherInfo.get(TurningGrilleService.KEY));
        masks.add(cipherInfo.get(TurningGrilleService.MASK1));
        masks.add(cipherInfo.get(TurningGrilleService.MASK2));
        masks.add(cipherInfo.get(TurningGrilleService.MASK3));

        // Converting from int[] to StringBuilder
        StringBuilder tempCipherMessage = new StringBuilder();
        int[] cipherMessage = cipherInfo.get(TurningGrilleService.CIPHER);
        for (int i = 0; i < cipherMessage.length; i++)
            tempCipherMessage.append(cipherMessage[i]);

        response.setCipherMessage(tempCipherMessage.toString());
        response.setDirection((char) cipherInfo.get(TurningGrilleService.DIRECTION)[0]);
        response.setMasks(masks);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public TurningGrilleService getTurningGrilleService() {
        return turningGrilleService;
    }

    public void setTurningGrilleService(TurningGrilleService turningGrilleService) {
        this.turningGrilleService = turningGrilleService;
    }

}
