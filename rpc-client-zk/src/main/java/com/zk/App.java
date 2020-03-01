package com.zk;


import com.zk.config.ApplicationConfig;
import com.zk.service.ParmentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App
{
    public static void main( String[] args )
    {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        ParmentService parmentService = applicationContext.getBean(ParmentService.class);



       // ParmentService parmentService = new ParmentService();
        parmentService.subscribeObj();
    }
}
