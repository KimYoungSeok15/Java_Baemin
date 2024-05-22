package exercise.filter;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class CustomWebMvcConfigurer extends WebMvcConfigurationSupport {
   @Bean
   public FilterRegistrationBean<Filter> logFilter() {
      FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();

//      bean.setFilter(new LogFilter());
      bean.setOrder(1);
      bean.addUrlPatterns("/*");

      return bean;

   }

   @Bean
   public FilterRegistrationBean<Filter> loginCheckFilter() {
      FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();

//      bean.setFilter(new LoginCheckFilter());
      bean.setOrder(2);
      bean.addUrlPatterns("/*");

      return bean;
   }

   @Override
   protected void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(new MyInterceptor())
            .addPathPatterns("/**");
   }
}
