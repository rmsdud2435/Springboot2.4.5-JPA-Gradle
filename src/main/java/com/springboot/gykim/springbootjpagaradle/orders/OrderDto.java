package com.springboot.gykim.springbootjpagaradle.orders;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
@ToString
public class OrderDto {

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

	public OrderDto(Order source) {
    copyProperties(source, this);

    this.requestMessage = source.getRequestMessage().orElse(null);
    this.rejectMessage = source.getRejectMassage().orElse(null);
	this.review = source.getReview().orElse(null);

	
  	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("seq", seq)
		.append("userSeq", userSeq)
		.append("productId", productId)
		.append("reviewSeq", reviewSeq)
		.append("state", state)
		.append("requestMessage", requestMessage)
		.append("rejectMessage", rejectMessage)
		.append("completedAt", completedAt)
		.append("rejectedAt", rejectedAt)
		.append("createAt", createAt)
		.append("review", review)
		.toString();
	}

}