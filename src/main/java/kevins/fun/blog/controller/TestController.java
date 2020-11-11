package kevins.fun.blog.controller;

import kevins.fun.blog.model.Cargo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {
    @GetMapping("/test")
    public ResponseEntity<String> getBlogList() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("test-header", "abc123");
        return ResponseEntity.ok().headers(responseHeaders).body("hello");
    }
}
