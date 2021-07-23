package com.springboot.gykim.springbootjpagaradle.orders;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.springboot.gykim.springbootjpagaradle.configures.web.Pageable;

import static com.springboot.gykim.springbootjpagaradle.utils.DateTimeUtils.dateTimeOf;
import static java.util.Optional.ofNullable;

import java.time.LocalDateTime;

@Repository
public class JdbcOrderRepository implements OrderRepository {

  private final JdbcTemplate jdbcTemplate;

  public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Optional<Order> findById(Long orderSeq){
    List<Order> results = jdbcTemplate.query(
      "SELECT A.*, B.seq as review_sequence, B.product_seq as review_product_seq, B.content, B.create_at as review_create_at FROM orders A LEFT OUTER JOIN reviews B ON A.review_seq = B.seq WHERE A.seq = ?",
      mapper,
      orderSeq
    );

    return ofNullable(results.isEmpty() ? null : results.get(0));
  }

  @Override
  public List<Order> findAll(Pageable page) {
    return jdbcTemplate.query(
      "SELECT R1.* FROM (SELECT A.*, B.seq as review_sequence, B.product_seq as review_product_seq, B.content, B.create_at as review_create_at FROM orders A LEFT OUTER JOIN reviews B ON A.review_seq = B.seq ORDER BY seq DESC) R1 LIMIT ? OFFSET ?",
      mapper,
      page.getSize(),
      page.getOffset()
    );
  }

  @Override
  public int accept(Long id) {
    return jdbcTemplate.update(
      "UPDATE orders SET  state='ACCEPTED' WHERE seq = ? AND state = 'REQUESTED'",
      id
    );
  }

  @Override
  public int shipping(Long id) {
    return jdbcTemplate.update(
      "UPDATE orders SET  state='SHIPPING' WHERE seq = ? AND state = 'ACCEPTED'",
      id
    );
  }

  @Override
  public int complete(Long id, LocalDateTime createAt) {
    return jdbcTemplate.update(
      "UPDATE orders SET state='COMPLETED', completed_at= ? WHERE seq = ? AND state = 'SHIPPING'",
      createAt,
      id
    );
  }

  @Override
  public int reject(Long id, String rejectMsg, LocalDateTime rejectedAt) {
    return jdbcTemplate.update(
      "UPDATE orders SET state='REJECTED', reject_msg = ?, rejected_at= ? WHERE seq = ? AND state = 'REQUESTED'",
      rejectMsg,
      rejectedAt,
      id
    );
  }

  @Deprecated
  @Override
  public ReviewDto review(Long userSeq, Long orderSeq, ReviewDto reviewDto) {

    Long productSeq = jdbcTemplate.queryForObject(
      "SELECT PRODUCT_SEQ FROM ORDERS WHERE SEQ = ?", new Object[]{orderSeq}, Long.class
    );

    jdbcTemplate.update(
      "INSERT INTO REVIEWS(USER_SEQ, PRODUCT_SEQ, CONTENT, CREATE_AT) VALUES(?,?,?,?);",
      userSeq,
      productSeq,
      reviewDto.getContent(),
      reviewDto.getCreateAt()
    );

    jdbcTemplate.update(
      "UPDATE PRODUCTS SET REVIEW_COUNT = IFNULL(REVIEW_COUNT,0)+1 WHERE SEQ = ?",
      productSeq
    );
    ;

    Long seq = jdbcTemplate.queryForObject("SELECT SEQ FROM REVIEWS WHERE PRODUCT_SEQ = ?", new Object[]{productSeq}, Long.class);
    
    return new ReviewDto(seq, productSeq, reviewDto.getContent(), reviewDto.getCreateAt());
  }

  static RowMapper<Order> mapper = (rs, rowNum) ->
    new Order.Builder()
      .seq(rs.getLong("seq"))
      .userSeq(rs.getLong("user_seq"))
      .productId(rs.getLong("product_seq"))
      .reviewSeq(rs.getLong("review_seq"))
      .state(rs.getString("state"))
      .requestMessage(rs.getString("request_msg"))
      .rejectMessage(rs.getString("reject_msg"))
      .completedAt(dateTimeOf(rs.getTimestamp("completed_at")))
      .rejectedAt(dateTimeOf(rs.getTimestamp("rejected_at")))
      .createAt(dateTimeOf(rs.getTimestamp("create_at")))
      .review(rs.getLong("review_sequence"), rs.getLong("review_product_seq"), rs.getString("content"), dateTimeOf(rs.getTimestamp("review_create_at")))
      .build();

}