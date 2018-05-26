package hu.bme.aut.webshop.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /*@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }*/

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addRedirectViewController("/","/home");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/403").setViewName("error/403");
        registry.addViewController("/orders").setViewName("orders");
        registry.addViewController("/addItem").setViewName("addItem");
        registry.addViewController("/shoppingCart").setViewName("shoppingCart");
        registry.addViewController("/account").setViewName("account");
    }
}
