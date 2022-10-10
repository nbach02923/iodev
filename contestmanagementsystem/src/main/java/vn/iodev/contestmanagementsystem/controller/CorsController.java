package vn.iodev.contestmanagementsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CorsController {
    @GetMapping("/cms/cors")
    public String cors() {
        return "Successful calling CORS!!";
    }
}
