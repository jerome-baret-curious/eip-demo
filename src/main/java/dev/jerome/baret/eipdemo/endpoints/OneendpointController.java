package dev.jerome.baret.eipdemo.endpoints;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OneendpointController implements OneendpointApi {

    public ResponseEntity<String> all() {
        return ResponseEntity.ok("All");
    }
}
