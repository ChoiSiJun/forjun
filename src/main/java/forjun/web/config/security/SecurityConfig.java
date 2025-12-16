package forjun.web.config.security;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    /** 파일 업로드 자원원 리소스 URL */
    @Value("${file.upload.resource-url}")
    private String resourceUrl;

    /** JWT 인증 필터 */
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    /** 공개 URL 설정 */
    private final PublicUrlConfig publicUrlConfig;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .formLogin(formLogin -> formLogin.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .authorizeHttpRequests(auth -> {

                    //파일 업로드 자원원 리소스 URL 허용    
                    auth.requestMatchers(HttpMethod.GET, resourceUrl + "**").permitAll();
                    //OPTIONS 메서드 허용
                    auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
                    //공개 URL 설정 허용
                    publicUrlConfig.publicUrls().forEach((method , urls) -> {
                        auth.requestMatchers(HttpMethod.valueOf(method) , urls.toArray(String[]::new))
                                .permitAll();
                    });

                    auth.anyRequest().authenticated();

                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //JWT 인증 필터 추가
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    //스프링 시큐리티 CORS 설정.
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3001"));  // 허용할 출처
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS" , resourceUrl + "**"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);  // 자격 증명 허용
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
