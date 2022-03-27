package com.example.projectforanytest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void test() {
        Product product = productRepository.findAll().get(0);
        product.orderProduct(1);
    }

    public void test2() {
        Product product = productRepository.findAll().get(1);
        product.orderProduct(1);
    }
}
