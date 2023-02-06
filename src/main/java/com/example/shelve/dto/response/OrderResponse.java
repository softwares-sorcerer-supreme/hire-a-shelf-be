package com.example.shelve.dto.response;

import com.example.shelve.entities.OrderDetail;
import com.example.shelve.entities.Retailer;

import java.sql.Date;
import java.util.Set;

public class OrderResponse {

    private long id;

    private Date date;

    private Set<OrderDetail> orderDetails;

    private Retailer retailer;

}
