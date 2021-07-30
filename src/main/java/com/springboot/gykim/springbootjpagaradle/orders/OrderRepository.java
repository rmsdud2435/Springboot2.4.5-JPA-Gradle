package com.springboot.gykim.springbootjpagaradle.orders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.springboot.gykim.springbootjpagaradle.configures.web.Pageable;

public interface OrderRepository {

  List<Order> findAll(Pageable page);

  Optional<Order> findById(Long orderSeq);

  int accept(Long id);
  
  int shipping(Long id);

  int complete(Long id, LocalDateTime createAt);

  int reject(Long id, String rejectMsg, LocalDateTime rejectedAt);

  Optional<Order> review(Long userSeq, Long productSeq, ReviewDto reviewDto);
}