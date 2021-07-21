package com.springboot.gykim.springbootjpagaradle.orders;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import com.springboot.gykim.springbootjpagaradle.configures.web.Pageable;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.time.LocalDateTime.now;

@Service
public class OrderService {

  private final OrderRepository orderRepository;

  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Transactional(readOnly = true)
  public Optional<Order> findById(Long orderSeq) {
    checkNotNull(orderSeq, "orderSeq must be provided");

    return OrderRepository.findById(orderSeq);
  }

  public ReviewDto review(Long userSeq, Long orderSeq, ReviewDto reviewDto) {
    //checkNotNull(reviewDto.getProductId(), "productId must be provided");
    reviewDto.setCreateAt(now());
    return orderRepository.review(userSeq, orderSeq, reviewDto);
  }

  @Transactional(readOnly = true)
  public List<Order> findAll(Pageable page) {
    return orderRepository.findAll(page);
  }
}