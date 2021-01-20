package task.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import task.message.Message;

@Configuration
@RequiredArgsConstructor
@EnableRedisRepositories
public class RedisConfig {
    @Value("${spring.redis.database}")
    private Integer redisDatabase;
    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private Integer redisPort;
    @Value("${spring.redis.password}")
    private String redisPassword;
    @Value("${spring.redis.timeout}")
    private Long redisTimeout;

    @Bean
    public LettuceConnectionFactory factory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHost, redisPort);
        redisStandaloneConfiguration.setDatabase(redisDatabase);
        redisStandaloneConfiguration.setPassword(redisPassword);
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);
        lettuceConnectionFactory.setTimeout(redisTimeout);
        return lettuceConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Message> redisTemplate() {
        RedisTemplate<String, Message> template = new RedisTemplate<>();
        template.setConnectionFactory(factory());
        return template;
    }
}
