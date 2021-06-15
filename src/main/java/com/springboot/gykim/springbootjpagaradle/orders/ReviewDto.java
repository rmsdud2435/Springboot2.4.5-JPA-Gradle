package com.springboot.gykim.springbootjpagaradle.orders;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
@ToString
public class ReviewDto {
    
    private Long seq;

    private Long productId;

    private String content;

    private LocalDateTime createAt;

    public ReviewDto(Review source) {
        copyProperties(source, this);
    }
}
