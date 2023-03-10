package com.example.shelve.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailRequest {

    private HashMap<Long, Integer> productSales;

}
