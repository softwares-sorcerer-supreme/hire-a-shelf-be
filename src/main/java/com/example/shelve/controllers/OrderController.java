package com.example.shelve.controllers;

import com.example.shelve.dto.request.OrderRequest;
import com.example.shelve.dto.response.OrderResponse;
import com.example.shelve.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrder() {
        return new ResponseEntity<>(orderService.getAllOrder(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createNewOrder(@RequestBody
                                                        @Valid OrderRequest orderRequest) {
        return new ResponseEntity<>(orderService.createNewOrder(orderRequest), HttpStatus.OK);
    }


}
