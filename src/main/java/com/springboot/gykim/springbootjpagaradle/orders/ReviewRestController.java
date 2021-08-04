package com.springboot.gykim.springbootjpagaradle.orders;

import com.springboot.gykim.springbootjpagaradle.security.Jwt;
import com.springboot.gykim.springbootjpagaradle.security.JwtAuthentication;
import com.springboot.gykim.springbootjpagaradle.utils.ApiUtils.ApiResult;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.springboot.gykim.springbootjpagaradle.utils.ApiUtils.success;

@RestController
@RequestMapping("api/orders")
public class ReviewRestController {
  // TODO review 메소드 구현이 필요합니다.
  private final OrderService orderService;

  public ReviewRestController(Jwt jwt, AuthenticationManager authenticationManager, OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping(path = "{id}/review")
  public ApiResult<Review> review(@AuthenticationPrincipal JwtAuthentication authentication, @PathVariable Long id, @RequestBody ReviewDto reviewDto) {

    if(reviewDto.getContent() == null || "".equals(reviewDto.getContent())){
      throw new IllegalArgumentException("Content must be provide");
    }

    if(reviewDto.getContent().length() > 1000){
      throw new IllegalArgumentException("Content must be at most 1000");
    }

    return success(
      orderService.review(authentication.id, id, reviewDto)
    );
  }
}