package forjun.web.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
public class PublicUrlConfig {

    @Bean
    public Map<String, List<String>> publicUrls() {
        return Map.of(
                "GET", List.of("/web/**", "/user/duplicate"),
                "POST", List.of("/user", "/user/login")
        );
    }
}
