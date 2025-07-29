package org.example.javaconf;

import org.example.javaconf.components.Body;
import org.example.javaconf.config.Javaconf;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClassForJavaConf {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Javaconf.class);

        Body body1 = context.getBean(Body.class);
        System.out.println(body1);
        Body body2 = context.getBean(Body.class);
        System.out.println(body1 == body2);

        //context.close();

    }
}
