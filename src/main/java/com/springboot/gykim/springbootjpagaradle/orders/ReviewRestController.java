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

@RestController
@RequestMapping("api/orders")
public class ReviewRestController {
  // TODO review 메소드 구현이 필요합니다.
  private final OrderService orderService;

  private final Jwt jwt;

  private final AuthenticationManager authenticationManager;

  public ReviewRestController(Jwt jwt, AuthenticationManager authenticationManager, OrderService orderService) {
    this.jwt = jwt;
    this.authenticationManager = authenticationManager;
    this.orderService = orderService;
  }

  @PostMapping(path = "{id}/review")
  public ApiResult<ReviewDto> review(@AuthenticationPrincipal JwtAuthentication authentication, @PathVariable Long id, @RequestBody ReviewDto reviewDto) {

    return success(
      orderService.review(authentication.id, id, reviewDto).map(ReviewDto::new);
    );
  }
}