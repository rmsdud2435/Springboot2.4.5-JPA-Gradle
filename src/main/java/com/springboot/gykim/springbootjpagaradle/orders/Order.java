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
public class Order {

  private Long seq;

  private Long userSeq;

  private Long productId;

  private Long reviewSeq;

  private String state;

  private String requestMessage;

  private String rejectMessage;

  private LocalDateTime completedAt;

  private LocalDateTime rejectedAt;

  private LocalDateTime createAt;

  private Review review;

  public Order(Long seq, Long userSeq, Long productId, Long reviewSeq, String state, String requestMessage, String rejectMessage, LocalDateTime completedAt, LocalDateTime rejectedAt, LocalDateTime createAt, Review review) {
    this.seq = seq;
    this.userSeq = userSeq;
    this.productId = productId;
    this.reviewSeq = reviewSeq;
    this.state = state;
    this.requestMessage = requestMessage;
    this.rejectMessage = rejectMessage;
    this.completedAt = completedAt;
    this.rejectedAt = rejectedAt;
    this.createAt = defaultIfNull(createAt, now());
    this.review = review;
  }

  public Optional<String> getRequestMessage() {
    return ofNullable(requestMessage);
  }

  public Optional<String> getRejectMassage() {
    return ofNullable(rejectMessage);
  }

  public Optional<Review> getReview() {
    return ofNullable(review);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Order order = (Order) o;
    return Objects.equals(seq, order.seq);
  }

  @Override
  public int hashCode() {
    return Objects.hash(seq);
  }

  static public class Builder {
    private Long seq;
    private Long userSeq;
    private Long productId;
    private Long reviewSeq;
    private String state;
    private String requestMessage;
    private String rejectMessage;
    private LocalDateTime completedAt;
    private LocalDateTime rejectedAt;
    private LocalDateTime createAt;
    private Review review;

    public Builder() {/*empty*/}

    public Builder(Order order) {
      this.seq = order.seq;
      this.userSeq = order.userSeq;
      this.productId = order.productId;
      this.reviewSeq = order.reviewSeq;
      this.state = order.state;
      this.requestMessage = order.requestMessage;
      this.rejectMessage = order.rejectMessage;
      this.completedAt = order.completedAt;
      this.rejectedAt = order.rejectedAt;
      this.createAt = order.createAt;
      this.review = order.review;
    }   

    public Builder seq(Long seq) {
      this.seq = seq;
      return this;
    }

    public Builder userSeq(Long userSeq) {
      this.userSeq = userSeq;
      return this;
    }

    public Builder productId(Long productId) {
      this.productId = productId;
      return this;
    }

    public Builder reviewSeq(Long reviewSeq) {
      this.reviewSeq = reviewSeq;
      return this;
    }
        
    public Builder state(String state) {
      this.state = state;
      return this;
    }

    public Builder requestMessage(String requestMessage) {
      this.requestMessage = requestMessage;
      return this;
    }

    public Builder rejectMessage(String rejectMessage) {
      this.rejectMessage = rejectMessage;
      return this;
    }

    public Builder completedAt(LocalDateTime completedAt) {
      this.completedAt = completedAt;
      return this;
    }

    public Builder rejectedAt(LocalDateTime rejectedAt) {
      this.rejectedAt = rejectedAt;
      return this;
    }

    public Builder createAt(LocalDateTime createAt) {
      this.createAt = createAt;
      return this;
    }

    public Builder review(Long seq, Long productId, String content,  LocalDateTime createAt) {
      if(seq == null || seq == 0){
        return this;
      }else{
        this.review = new Review(seq, productId, content, createAt);
        return this;
      }
    }
    
    public Order build() {
      return new Order(
        seq,
        userSeq,
        productId,
        reviewSeq,
        state,
        requestMessage,
        rejectMessage,
        completedAt,
        rejectedAt,
        createAt,
        review
      );
    }
  }
}