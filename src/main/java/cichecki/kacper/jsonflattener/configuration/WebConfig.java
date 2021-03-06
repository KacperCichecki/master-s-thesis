package cichecki.kacper.jsonflattener.configuration;

import cichecki.kacper.jsonflattener.mvc.MyHandlerInterceptorAdapter;
import cichecki.kacper.jsonflattener.mvc.RequestResponseLoggingFilter;
import cichecki.kacper.jsonflattener.persistence.listeners.MyModifiedObjectTracker;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("main");
        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/login").setViewName("login");
    }

    // nadpisałem bo podstawowa konfiguracja nie zawiera cacheu
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
        registry.addInterceptor(myHandlerInterceptorAdapter()).addPathPatterns("/profile/**");
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Bean
    public MyHandlerInterceptorAdapter myHandlerInterceptorAdapter() {
        return new MyHandlerInterceptorAdapter(myModifiedObjectTracker());
    }

    @Bean
    public MyModifiedObjectTracker myModifiedObjectTracker() {
        return new MyModifiedObjectTracker();
    }


    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

    @Bean
    public ShallowEtagHeaderFilter shallowEtagHeaderFilter() {
        return new ShallowEtagHeaderFilter();
    }

    @Bean
    public FilterRegistrationBean<RequestResponseLoggingFilter> loggingFilter(){
        FilterRegistrationBean<RequestResponseLoggingFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new RequestResponseLoggingFilter());
        registrationBean.addUrlPatterns("/save-json");
        registrationBean.addUrlPatterns("/about");

        return registrationBean;
    }

}
