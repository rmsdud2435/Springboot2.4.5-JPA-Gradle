package com.springboot.gykim.springbootjpagaradle.orders;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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