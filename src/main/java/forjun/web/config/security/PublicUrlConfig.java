package forjun.web.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
public class PublicUrlConfig {
    
    @Value("${file.upload.resource-url}")
    private String resourceUrl;

    @Bean
    public Map<String, List<String>> publicUrls() {
        return Map.of(
                "GET", List.of("/web/**", "/user/duplicate" , resourceUrl + "**"),
                "POST", List.of("/user", "/user/login")
        );
    }
}
