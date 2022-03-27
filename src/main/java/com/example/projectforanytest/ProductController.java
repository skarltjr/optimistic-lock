package com.example.projectforanytest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/test")
    public void test() {
        productService.test();
    }
    @PostMapping("/test2")
    public void test2() {
        productService.test2();
    }
}
