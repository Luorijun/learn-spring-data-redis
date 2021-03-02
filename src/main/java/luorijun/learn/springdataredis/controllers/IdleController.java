package luorijun.learn.springdataredis.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IdleController {

    @GetMapping
    public String idle() {
        return "Hello World";
    }
}
