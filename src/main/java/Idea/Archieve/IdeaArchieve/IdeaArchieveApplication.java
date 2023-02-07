package Idea.Archieve.IdeaArchieve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties
@ConfigurationProperties
@EnableJpaRepositories
public class IdeaArchieveApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdeaArchieveApplication.class, args);
	}

}
