package com.springboot.gykim.springbootjpagaradle.orders;

import java.time.LocalDateTime;

import static com.google.common.base.Preconditions.checkArgument;
import static java.time.LocalDateTime.now;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class Review {
    
    private final Long seq;

    private Long productId;

    private String content;

    private final LocalDateTime createAt;

    public Review(Long productId, String content) {
        this(null, productId, content, null);
    }

    public Review(Long seq, Long productId, String content, LocalDateTime createAt) {
        checkArgument(isNotEmpty(content), "content must be provided");
        checkArgument(
            content.length() >= 1 && content.length() <= 1000,
            "name length must be between 1 and 1000 characters"
        );
    
        this.seq = seq;
        this.productId = productId;
        this.content = content;
        this.createAt = defaultIfNull(createAt, now());
      }
}
