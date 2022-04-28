package com.uldemy.controllers;

import com.uldemy.Greetings;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/greetings")
public class GreetingController {

    private static final String template = "Hello %s!";

    private final AtomicLong counter = new AtomicLong();

    @GetMapping
    public Greetings greetings(@RequestParam(value = "name", defaultValue = "world") String name){
        return new Greetings(counter.incrementAndGet(), String.format(template, name));
    }

}
