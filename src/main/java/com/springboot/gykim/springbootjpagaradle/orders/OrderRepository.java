package com.springboot.gykim.springbootjpagaradle.orders;

import java.util.List;

import com.springboot.gykim.springbootjpagaradle.configures.web.Pageable;

public interface OrderRepository {

  List<Order> findAll(Pageable page);

  ReviewDto review(Long userSeq, Long productSeq, ReviewDto reviewDto);

  Order findById(Long orderSeq);
}