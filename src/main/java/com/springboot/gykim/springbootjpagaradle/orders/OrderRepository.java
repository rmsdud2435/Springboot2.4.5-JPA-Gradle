package com.springboot.gykim.springbootjpagaradle.orders;

import java.util.List;

import com.springboot.gykim.springbootjpagaradle.configures.web.Pageable;

public interface OrderRepository {

  /*
  Optional<Product> findById(long id);
  */

  List<Order> findAll(Pageable page);

  void review(ReviewDto reviewDto);
}