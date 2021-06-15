package com.springboot.gykim.springbootjpagaradle.configures;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.springboot.gykim.springbootjpagaradle.security.Jwt;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Configuration: 내부에 있는 @Bean 어노테이션을 인식하여 Bean 객체로 만들어주는 환경구성 클래스라는 의미. 
 * 즉, @SpringBootApplication에서 등록되지 않은 사용자가 만든 Bean 객체를 등록해줄때 사용.
 * 
 * @Bean: 해당 객체가 Bean 객체임을 알려주는 어노테이션
 * 
 * Jackson2ObjectMapperBuilderCustomizer: 우리가 원하는 대로 JSON 응답값을 변경하고 싶을때 사용. 일반적으로 날짜
 */
@Configuration
public class ServiceConfigure {

  @Bean
  public Jwt jwt(JwtTokenConfigure jwtTokenConfigure) {
    return new Jwt(jwtTokenConfigure.getIssuer(), jwtTokenConfigure.getClientSecret(), jwtTokenConfigure.getExpirySeconds());
  }

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
    return builder -> {
      AfterburnerModule abm = new AfterburnerModule();
      JavaTimeModule jtm = new JavaTimeModule();
      jtm.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
      jtm.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
      jtm.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
      jtm.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

      builder.visibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
      builder.visibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE);
      builder.visibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

      builder.serializationInclusion(JsonInclude.Include.ALWAYS);
      builder.modulesToInstall(abm, jtm);
    };
  }

}