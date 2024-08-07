# 项目笔记
## 跨域请求配置
定义一个WebConfig配置类，写上下面的代码
```java
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // One way of solving CORS(cross-origin-resource sharing)
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply CORS to all endpoints
                .allowedOrigins("http://127.0.0.1:3000") // URL of the React app
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
```


## 配置多个数据源






## Java常用类
### Optional<T>
`Optional<?>` 可以和`orElse()`一起使用
```java
List<ParameterEntity> parameterEntities = strings.stream()
        .map(elem -> parameterEntityRepository.findById(elem))
        .map(elem -> elem.orElse(null))
        .filter(Objects::nonNull)
        .toList();
```

