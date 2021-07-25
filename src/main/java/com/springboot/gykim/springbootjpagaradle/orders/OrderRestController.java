package com.springboot.gykim.springbootjpagaradle.orders;

import com.fasterxml.jackson.databind.JsonNode;
import com.springboot.gykim.springbootjpagaradle.configures.web.Pageable;
import com.springboot.gykim.springbootjpagaradle.errors.NotFoundException;
import com.springboot.gykim.springbootjpagaradle.security.Jwt;
import com.springboot.gykim.springbootjpagaradle.security.JwtAuthentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.springboot.gykim.springbootjpagaradle.utils.ApiUtils.ApiResult;
import static com.springboot.gykim.springbootjpagaradle.utils.ApiUtils.success;
import java.util.List;

import static java.util.stream.Collectors.toList;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/orders")
public class OrderRestController {
  // TODO findAll, findById, accept, reject, shipping, complete 메소드 구현이 필요합니다.
  private final OrderService orderService;

  public OrderRestController(Jwt jwt, AuthenticationManager authenticationManager, OrderService orderService) {
    this.orderService = orderService;
  }

  // JwtAuthenticationTokenFilter 에서 JWT 값을 통해 사용자를 인증한다.
  // 사용자 인증이 정상으로 완료됐다면 @AuthenticationPrincipal 어노테이션을 사용하여 인증된 사용자 정보(JwtAuthentication)에 접근할 수 있다.
  @GetMapping(path = "{id}")
  public ApiResult<OrderDto> findById( @AuthenticationPrincipal JwtAuthentication authentication, @PathVariable Long id) {
    return success(
      orderService.findById(id)
        .map(OrderDto::new)
        .orElseThrow(() -> new NotFoundException("Could not found order for " + id))
    );
  }

  @GetMapping
  public ApiResult<List<OrderDto>> findAll(@AuthenticationPrincipal JwtAuthentication authentication, Pageable page) {
    return success(
      orderService.findAll(page).stream()
        .map(OrderDto::new)
        .collect(toList())
    );
  }

  @PatchMapping(value="{id}/accept")
  public ApiResult<Boolean> accept(@AuthenticationPrincipal JwtAuthentication authentication, @PathVariable Long id ) {
      //TODO: process POST request
      return orderService.accept(id) == 1? success(true): success(false);
  }
  
  @PatchMapping(value="{id}/shipping")
  public ApiResult<Boolean> shipping(@AuthenticationPrincipal JwtAuthentication authentication, @PathVariable Long id ) {
      //TODO: process POST request
      return orderService.shipping(id) == 1? success(true): success(false);
  }

  @PatchMapping(value="{id}/complete")
  public ApiResult<Boolean> complete(@AuthenticationPrincipal JwtAuthentication authentication, @PathVariable Long id ) {
      //TODO: process POST request
      return orderService.complete(id) == 1? success(true): success(false);
  }

  @PatchMapping(value="{id}/reject")
  public ApiResult<Boolean> reject(@AuthenticationPrincipal JwtAuthentication authentication, @PathVariable Long id, @RequestBody(required = false) JsonNode jsonNode) {
    //TODO: process POST request
    if(jsonNode == null){
      throw new IllegalArgumentException("Request parameter not found");
    }

    String message = jsonNode.get("message").asText();
    return orderService.reject(id, message) == 1? success(true): success(false);
  }
}