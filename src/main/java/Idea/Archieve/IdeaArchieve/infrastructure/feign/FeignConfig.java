package Idea.Archieve.IdeaArchieve.infrastructure.feign;

import feign.codec.ErrorDecoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@EnableFeignClients(basePackages = "Idea.Archieve.IdeaArchieve.infrastructure.feign")
@Import(FeignClientErrorDecoder.class)
@Configuration
public class FeignConfig {

    @Bean
    @ConditionalOnMissingBean(value = ErrorDecoder.class)
    public FeignClientErrorDecoder commonFeignErrorDecoder() {
        return new FeignClientErrorDecoder();
    }
}

