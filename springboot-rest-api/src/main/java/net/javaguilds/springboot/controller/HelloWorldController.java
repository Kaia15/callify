package net.javaguilds.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//whenever we develop restful web services using Spring MVC always use these two annotations
//@Controller
//@ResponseBody
//@RestController internally uses both the annotations above
@RestController
public class HelloWorldController {
    //To create a REST API
    // We create a method and makes that method a REST API with annotation

    //HTTP GET request
    //http://localhost:8080/hello-world
    @GetMapping("/hello-world")
    public String helloWord() {
        return "Hello World!";
    }
}
