package luorijun.learn.springdataredis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IdleController {

    @GetMapping
    public String idle() {
        return "index";
    }
}
