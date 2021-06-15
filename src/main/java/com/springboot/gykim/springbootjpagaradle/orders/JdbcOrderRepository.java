package com.springboot.gykim.springbootjpagaradle.orders;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.springboot.gykim.springbootjpagaradle.configures.web.Pageable;

import static com.springboot.gykim.springbootjpagaradle.utils.DateTimeUtils.dateTimeOf;
import static java.util.Optional.ofNullable;

@Repository
public class JdbcOrderRepository implements OrderRepository {

  private final JdbcTemplate jdbcTemplate;

  public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void review(ReviewDto reviewDto) {
    jdbcTemplate.query(
      "INSERT INTO REVIEWS(USER_SEQ, PRODUCT_SEQ, CONTENT, CREATE_AT) VALUES(?,?,?,?);",
      mapper,
      reviewDto.getSeq(),
      reviewDto.getProductId(),
      reviewDto.getContent(),
      reviewDto.getCreateAt()
    );
  }
  

  @Override
  public List<Order> findAll(Pageable page) {
    return jdbcTemplate.query(
      "SELECT R1.* FROM (SELECT * FROM orders ORDER BY seq DESC) R1 LIMIT ? OFFSET ?",
      mapper,
      page.getSize(),
      page.getOffset() - 1
    );
  }

  static RowMapper<Order> mapper = (rs, rowNum) ->
    new Order.Builder()
      .seq(rs.getLong("seq"))
      .userSeq(rs.getLong("user_seq"))
      .productSeq(rs.getLong("product_seq"))
      .reviewSeq(rs.getLong("review_seq"))
      .state(rs.getString("state"))
      .requestMsg(rs.getString("request_msg"))
      .rejectMsg(rs.getString("reject_msg"))
      .completedAt(dateTimeOf(rs.getTimestamp("completed_at")))
      .rejectedAt(dateTimeOf(rs.getTimestamp("rejected_at")))
      .createAt(dateTimeOf(rs.getTimestamp("create_at")))
      .build();

}