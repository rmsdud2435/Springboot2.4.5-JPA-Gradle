package com.springboot.gykim.springbootjpagaradle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @ComponentScan과 @EnableAutoConfiguration을 통하여 @Service, @Repository, @Controller나 다른 Bean 객체들을 생성한다.
 * 일반 Spring에선 xml에서 <context:component-scan>, <bean> 등의 태그를 통해 등록했다면 SpringBoot에선 @SpringBootApplication으로 Bean 등록 끝
 * TMI: 읽어들일 수 있는 Bean은 spring-boot-autoconfigure.jar의 META-INF의 spring.factories에 작성한다.
 */
@SpringBootApplication
public class SpringbootJpaGaradleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaGaradleApplication.class, args);
	}

}
