package com.sparta.springboot.controller;

import com.sparta.springboot.dto.ProductMypriceRequestDto;
import com.sparta.springboot.dto.ProductRequestDto;
import com.sparta.springboot.entity.Product;
import com.sparta.springboot.repository.ProductRepository;
import com.sparta.springboot.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class ProductRestController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    // 등록된 전체 상품 목록 조회
    @GetMapping("/api/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    // 신규 상품 등록
    @PostMapping("/api/products")
    public Product createProduct(@RequestBody ProductRequestDto requestDto) {
        Product product = new Product(requestDto);
        productRepository.save(product);
        return product;
    }

    //최저가 변경 -made by hj
    @PutMapping("/api/products/{id}")
    public Optional<Product> updatePrice(@RequestParam Long id, @RequestBody ProductMypriceRequestDto requestDto) {
        return productService.updatePrice(id, requestDto);
    }

}