package com.example.emsbackend.config.database.redis;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class UploadRepositoryConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        // Keys in Redis are typically strings. Serializing keys ensures that they are consistently encoded and decoded when stored and retrieved.
        template.setKeySerializer(new StringRedisSerializer());
        // Values in Redis can be of various types (strings, lists, sets, hashes). Serializing values ensures that complex data types (like Java objects) are converted into a
        // storable format and can be correctly reconstructed when retrieved.
        // GenericJackson2JsonRedisSerializer: Useful for storing complex Java objects as JSON.
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
}
