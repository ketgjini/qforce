package nl.qnh.qforce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"nl.qnh.qforce.repository.ApiAnalyticsRepository"})
public class QforceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QforceApplication.class, args);
	}

}
