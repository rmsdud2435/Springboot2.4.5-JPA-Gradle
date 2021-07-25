package com.springboot.gykim.springbootjpagaradle.orders;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReviewDto {
    
    private Long seq;

    private Long productId;

    private String content;

    private LocalDateTime createAt;

}
