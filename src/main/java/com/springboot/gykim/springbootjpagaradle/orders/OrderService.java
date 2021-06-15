package com.springboot.gykim.springbootjpagaradle.orders;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import com.springboot.gykim.springbootjpagaradle.configures.web.Pageable;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class OrderService {

  private final OrderRepository orderRepository;

  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public void review(ReviewDto reviewDto) {
    checkNotNull(reviewDto.getProductId(), "productId must be provided");

    return orderRepository.review(reviewDto);
  }

  @Transactional(readOnly = true)
  public List<Order> findAll(Pageable page) {
    return orderRepository.findAll(page);
  }
}