package com.example.demo.apiv1;

import com.example.demo.service.serviceimpl.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderItemV1 {

    private final OrderService orderService;

    @PostMapping("")
    public CompletableFuture<String> addOrder(Long n) {
        orderService.save(n);
        return null;
    }
}
