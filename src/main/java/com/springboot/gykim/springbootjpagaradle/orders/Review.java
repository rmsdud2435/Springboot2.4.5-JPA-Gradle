package com.springboot.gykim.springbootjpagaradle.orders;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Getter
@Setter
@ToString
public class Review {
    
    private final Long seq;

    private Long productId;

    private String content;

    private final LocalDateTime createAt;

    public Review(Long productId, String content) {
        this(null, productId, content, null);
    }

    public Review(Long seq, Long productId, String content, LocalDateTime createAt) {
        this.seq = seq;
        this.productId = productId;
        this.content = content;
        this.createAt = defaultIfNull(createAt, now());
      }
}
