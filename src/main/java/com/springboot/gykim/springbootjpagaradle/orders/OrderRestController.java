package com.springboot.gykim.springbootjpagaradle.orders;

import com.springboot.gykim.springbootjpagaradle.configures.web.Pageable;
import com.springboot.gykim.springbootjpagaradle.errors.NotFoundException;
import com.springboot.gykim.springbootjpagaradle.errors.UnauthorizedException;
import com.springboot.gykim.springbootjpagaradle.security.Jwt;
import com.springboot.gykim.springbootjpagaradle.security.JwtAuthentication;
import com.springboot.gykim.springbootjpagaradle.security.JwtAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.springboot.gykim.springbootjpagaradle.utils.ApiUtils.ApiResult;
import static com.springboot.gykim.springbootjpagaradle.utils.ApiUtils.success;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("api/orders")
public class OrderRestController {
  // TODO findAll, findById, accept, reject, shipping, complete 메소드 구현이 필요합니다.
  
  private final Jwt jwt;

  private final AuthenticationManager authenticationManager;

  private final OrderService orderService;

  public OrderRestController(Jwt jwt, AuthenticationManager authenticationManager, OrderService orderService) {
    this.jwt = jwt;
    this.authenticationManager = authenticationManager;
    this.orderService = orderService;
  }

  // JwtAuthenticationTokenFilter 에서 JWT 값을 통해 사용자를 인증한다.
  // 사용자 인증이 정상으로 완료됐다면 @AuthenticationPrincipal 어노테이션을 사용하여 인증된 사용자 정보(JwtAuthentication)에 접근할 수 있다.
  @GetMapping(path = "{id}")
  public ApiResult<OrderDto> findById( @AuthenticationPrincipal JwtAuthentication authentication, @PathVariable Long id) {
    return success(
      orderService.findById(id)
        .map(OrderDto::new)
        .collect(toList())
        .orElseThrow(() -> new NotFoundException("Could not found order for " + id))
    );
  }

  @GetMapping
  public ApiResult<List<OrderDto>> findAll(Pageable page) {
    return success(
      orderService.findAll(page).stream()
        .map(OrderDto::new)
        .collect(toList())
        //.orElseThrow(() -> new NotFoundException("Could nof found Product for " + authentication.id))
    );
  }
}