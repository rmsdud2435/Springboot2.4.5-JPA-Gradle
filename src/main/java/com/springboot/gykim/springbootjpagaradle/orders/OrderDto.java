package com.springboot.gykim.springbootjpagaradle.orders;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

public class OrderDto {

	private Long seq;

	private Long userSeq;
	private Long productSeq;
	private Long reviewSeq;

	private String state;

	private String requestMsg;

	private String rejectMsg;

	private LocalDateTime completedAt;

	private LocalDateTime rejectedAt;

	private LocalDateTime createAt;

	public OrderDto(Order source) {
    copyProperties(source, this);

    this.requestMsg = source.getRequestMsg().orElse(null);
    this.rejectMsg = source.getRejectMsg().orElse(null);
  	}

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public Long getUserSeq() {
		return this.userSeq;
	}

	public void setUserSeq(Long userSeq) {
		this.userSeq = userSeq;
	}

	public Long getProductSeq() {
		return this.productSeq;
	}

	public void setProductSeq(Long productSeq) {
		this.productSeq = productSeq;
	}

	public Long getReviewSeq() {
		return this.reviewSeq;
	}

	public void setReviewSeq(Long reviewSeq) {
		this.reviewSeq = reviewSeq;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRequestMsg() {
		return this.requestMsg;
	}

	public void setRequestMsg(String requestMsg) {
		this.requestMsg = requestMsg;
	}

	public String getRejectMsg() {
		return this.rejectMsg;
	}

	public void setRejectMsg(String rejectMsg) {
		this.rejectMsg = rejectMsg;
	}

	public LocalDateTime getCompletedAt() {
		return this.completedAt;
	}

	public void setCompletedAt(LocalDateTime completedAt) {
		this.completedAt = completedAt;
	}

	public LocalDateTime getRejectedAt() {
		return this.rejectedAt;
	}

	public void setRejectedAt(LocalDateTime rejectedAt) {
		this.rejectedAt = rejectedAt;
	}

	public LocalDateTime getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
      .append("seq", seq)
      .append("userSeq", userSeq)
      .append("productSeq", productSeq)
      .append("reviewSeq", reviewSeq)
      .append("state", state)
      .append("requestMsg", requestMsg)
      .append("rejectMsg", rejectMsg)
      .append("completedAt", completedAt)
      .append("createAt", rejectedAt)
      .append("createAt", createAt)
      .toString();
  }

}