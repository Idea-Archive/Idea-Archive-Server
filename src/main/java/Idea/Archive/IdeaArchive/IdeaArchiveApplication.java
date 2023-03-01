package Idea.Archive.IdeaArchive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties
@ConfigurationPropertiesScan
@ComponentScan(basePackages = {"Idea.Archive.IdeaArchive.infrastructure.client"})
@EnableJpaRepositories
public class IdeaArchiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdeaArchiveApplication.class, args);
	}

}
