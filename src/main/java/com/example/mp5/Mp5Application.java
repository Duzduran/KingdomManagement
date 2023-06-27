package com.example.mp5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@SpringBootApplication
public class Mp5Application {

    public static void main(String[] args) {
        SpringApplication.run(Mp5Application.class, args);
    }

    @Controller
    @RequestMapping("/")
    class HomeController {

        @GetMapping
        public RedirectView redirectToHome() {
            return new RedirectView("/home");
        }
    }

}
