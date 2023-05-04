package ru.job4j.kitchen;

import liquibase.integration.spring.SpringLiquibase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
@Slf4j
public class KitchenApplication {
	private static final String URL_JOB4J_KITCHEN = "http://localhost:8083/kitchens";

	/**
	 * Создание SpringLiquibase
	 *
	 * @param ds DataSource
	 * @return SpringLiquibase
	 */
	@Bean
	public SpringLiquibase liquibase(DataSource ds) {
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setChangeLog("classpath:db/changelog/liquibase-changeLog.xml");
		liquibase.setDataSource(ds);
		return liquibase;
	}

	public static void main(String[] args) {
		SpringApplication.run(KitchenApplication.class, args);
		log.info("GO TO {}", URL_JOB4J_KITCHEN);
	}
}
