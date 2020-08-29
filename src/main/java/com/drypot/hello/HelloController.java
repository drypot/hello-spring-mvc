package com.drypot.hello;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class HelloController {

    private static final String template = "Hello, %s!";

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Message {
        private String msg;
        private String value;
    }

    @GetMapping("/hello-param")
    public Message helloParam(@RequestParam(value="name", defaultValue = "") String name) {
        return new Message("hello", name);
    }

    @GetMapping(value = {"/hello-path", "/hello-path/{name}"})
    public Message helloPath(@PathVariable("name") Optional<String> name) {
        return new Message("hello", name.orElse(""));
    }

}
