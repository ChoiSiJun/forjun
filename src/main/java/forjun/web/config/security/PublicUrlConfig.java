package forjun.web.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/** 공개 URL 설정 */
@Configuration
public class PublicUrlConfig {
    
    @Bean
    public Map<String, List<String>> publicUrls() {
        return Map.of(
                "GET", List.of("/api/v1/public-**", "api/v1/user/duplicate"),
                "POST", List.of("api/v1/user", "api/v1/user/login")
        );
    }
}
