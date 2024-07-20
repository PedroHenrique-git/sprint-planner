package com.sprintplanner.planner;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Entrypoint {
    @GetMapping("/")
    public ResponseEntity<Map<String, String>> index() {
        return ResponseEntity.ok().body(Map.of("message", "it works 👌"));
    }

    @GetMapping("/*")
    public ResponseEntity<Map<String, String>> notFound() {
        return ResponseEntity.ok().body(Map.of("message", "route not found 🥲"));
    }
}
