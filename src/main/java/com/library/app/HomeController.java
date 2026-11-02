package com.library.app;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    // (a) plain text
    @GetMapping("/")
    public String hello() {
        return "Hello, Library";
    }

    // (b) query parameter: GET /hello?name=Ana
    @GetMapping("/hello")
    public String helloName(
            @RequestParam(defaultValue = "World") String name) {
        return "Hello, " + name;
    }

    // (c) path variable: GET /greet/Ana
    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name) {
        return "Welcome to the library, " + name;
    }

    // (d) JSON: Spring serializes the record automatically
    public record AppInfo(String name, String version) {}

    @GetMapping("/info")
    public AppInfo info() {
        return new AppInfo("library-app", "0.0.1");
    }

    // (e) explicit status codes
    @GetMapping("/books/{id}")
    public ResponseEntity<String> getBook(@PathVariable int id) {
        if (id == 1) {
            return ResponseEntity.ok("Effective Java");
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/books")
    public ResponseEntity<Void> createBook() {
        return ResponseEntity.created(URI.create("/books/1")).build();
    }
}
