package com.example.projectforanytest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@AutoConfigureMockMvc
class ProductTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository.save(new Product(1L, 1, 0)); // 재고가 1개인 product 생성
        productRepository.save(new Product(2L, 100, 0)); // 재고가 100개인 product 생성
    }

    @Test
    void test() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 2; i++) {
            executorService.execute(() -> {
                productService.test();
                latch.countDown();
            });
        }
        Thread.sleep(10000);
        assertTrue(productRepository.findById(1L).get().getStock() == 0);
    }

    @Test
    void test2() throws Exception {

        CountDownLatch latch = new CountDownLatch(2);
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 2; i++) {
            executorService.execute(() -> {
                try {
                    mockMvc.perform(post("/test2"))
                            .andDo(print());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                latch.countDown();
            });
        }
        Thread.sleep(10000);

        int stock = productRepository.findById(2L).get().getStock();
        System.out.println(stock + " hello");
        assertTrue(stock == 99);
    }

}