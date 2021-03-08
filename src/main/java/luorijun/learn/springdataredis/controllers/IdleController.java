package luorijun.learn.springdataredis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class IdleController {

    @GetMapping

    @ResponseBody
    public String idle() {
        return "Idle Page";
    }
}
