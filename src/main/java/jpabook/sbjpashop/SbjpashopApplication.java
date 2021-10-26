package jpabook.sbjpashop;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SbjpashopApplication {

	public static void main(String[] args) {

		Hello hello = new Hello();
		hello.setData("hello");
		String data = hello.getData();
		System.out.println(data);

		SpringApplication.run(SbjpashopApplication.class, args);
	}

	/**
	 * 엔티티를 그대로 보내지 않으면 필요없는 기능!!
	 * 엔티티를 그대로 보내지 말기 꼭!
	 * simple controller 를 위한.. ㅜ
	 * */
	@Bean
	Hibernate5Module hibernate5Module() {
		return new Hibernate5Module();
	}

}
