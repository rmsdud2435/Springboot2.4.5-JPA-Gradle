package com.springboot.gykim.springbootjpagaradle.products;

import com.springboot.gykim.springbootjpagaradle.errors.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.springboot.gykim.springbootjpagaradle.utils.ApiUtils.ApiResult;
import static com.springboot.gykim.springbootjpagaradle.utils.ApiUtils.success;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("api/products")
public class ProductRestController {

  private final ProductService productService;

  public ProductRestController(ProductService productService) {
    this.productService = productService;
  }

  // FIXME `요건 1` 정의에 맞게 응답 타입 수정이 필요합니다.
  // 수정 내용: return에 success 함수 사용 및 result값에 ApiResult로 수정
  @GetMapping(path = "{id}")
  public ApiResult<ProductDto> findById(@PathVariable Long id) {
    return success(
      productService.findById(id)
        .map(ProductDto::new)
        .orElseThrow(() -> new NotFoundException("Could not found product for " + id))
    );
  }

  // FIXME `요건 1` 정의에 맞게 응답 타입 수정이 필요합니다.
  // 수정 내용: return에 success 함수 사용 및 result값에 ApiResult로 수정
  @GetMapping
  public ApiResult<List<ProductDto>> findAll() {
    return success(
      productService.findAll().stream()
        .map(ProductDto::new)
        .collect(toList())
    );
  }
}