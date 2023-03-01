package com.example.shelve.controllers;

import com.example.shelve.dto.request.OrderDetailRequest;
import com.example.shelve.dto.response.OrderDetailResponse;
import com.example.shelve.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/order_detail")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetailResponse> updateOrderDetail(@PathVariable Long id,
                                                                 @RequestBody
                                                                 @Valid OrderDetailRequest orderDetailRequest) {
        return new ResponseEntity<>(orderDetailService.updateOrderDetailService(id, orderDetailRequest), HttpStatus.OK);
    }

}
