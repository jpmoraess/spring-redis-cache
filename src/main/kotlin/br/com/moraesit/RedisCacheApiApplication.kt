package br.com.moraesit

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import java.time.Duration


@SpringBootApplication
@EnableCaching
@Configuration
class RedisCacheApiApplication

fun main(args: Array<String>) {
    runApplication<RedisCacheApiApplication>(*args)
}

@Bean
fun cacheConfiguaration(): RedisCacheConfiguration {
    return RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(60))
            .disableCachingNullValues()
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(GenericJackson2JsonRedisSerializer()))
}

//@Bean
//fun redisCacheManagerBuilderCustomizer(): RedisCacheManagerBuilderCustomizer {
//    return RedisCacheManagerBuilderCustomizer { builder: RedisCacheManagerBuilder ->
//        builder
//                .withCacheConfiguration("customerCache",
//                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5)))
//    }
//}