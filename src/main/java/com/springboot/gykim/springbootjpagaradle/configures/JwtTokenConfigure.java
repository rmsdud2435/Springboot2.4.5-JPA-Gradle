package com.springboot.gykim.springbootjpagaradle.configures;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Component: Bean 객체를 생성하는 어노테이션. default는 class명에서 첫글자를 소문자로 하여 객체 생성.
 * xml에서 <Bean class=""/>와 동일한 의미
 * 
 * @ConfigurationProperties: properties 파일에 설정되어 있는 값과 변수 값을 매핑해준다. 즉, properties파일에 jwt.token.header가 설정되어 있으면 해당 값이 header 변수에
 * 그렇다면 jwt.token.header는 어디 있을까? 아직 못찾았다. 프로젝트 실행되면 해당 어노테이션 빼서 실행해보기 및 정리
 */
@Component
@ConfigurationProperties(prefix = "jwt.token")
public class JwtTokenConfigure {

  private String header;

  private String issuer;

  private String clientSecret;

  private int expirySeconds;

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public String getIssuer() {
    return issuer;
  }

  public void setIssuer(String issuer) {
    this.issuer = issuer;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }

  public int getExpirySeconds() {
    return expirySeconds;
  }

  public void setExpirySeconds(int expirySeconds) {
    this.expirySeconds = expirySeconds;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
      .append("header", header)
      .append("issuer", issuer)
      .append("clientSecret", clientSecret)
      .append("expirySeconds", expirySeconds)
      .toString();
  }

}