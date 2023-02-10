package Idea.Archieve.IdeaArchieve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableCaching
@EnableConfigurationProperties
@ConfigurationProperties
@EnableJpaRepositories
public class IdeaArchiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdeaArchiveApplication.class, args);
	}

}
