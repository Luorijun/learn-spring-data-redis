package luorijun.learn.springdataredis.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import luorijun.learn.springdataredis.services.SecKillService;

@RestController
@RequestMapping("/api/sec-kill")
public class SecKillRestController {

    private final SecKillService service;

    public SecKillRestController(SecKillService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<String> idle() {
        return ResponseEntity.ok("可用");
    }

    @PostMapping
    public ResponseEntity<String> secKill(@RequestParam int goods) {
        var message = "秒杀成功，已经创建订单：";
        try {
            service.secKillWithCacheAndAsyncOrder(goods);
        } catch (RuntimeException exception) {
            message = "秒杀失败：" + exception.getMessage();
        }

        return ResponseEntity.ok(message);
    }
}
