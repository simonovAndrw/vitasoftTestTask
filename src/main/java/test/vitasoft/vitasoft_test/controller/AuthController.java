package test.vitasoft.vitasoft_test.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @PostMapping("/auth")
    public String authenticate() {
        return null;
    }
}
