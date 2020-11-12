package kevins.fun.blog.mongo.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
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
