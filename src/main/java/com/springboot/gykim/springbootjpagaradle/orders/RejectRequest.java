package com.springboot.gykim.springbootjpagaradle.orders;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Getter
@Setter
@ToString
public class RejectRequest {

  private String message;

/*   public RejectRequest(String message) {
    this.message = message;
  }

  public Optional<String> getMessage() {
    return ofNullable(message);
  } */
}