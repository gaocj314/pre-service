package com.yonyou.cap.preservicegs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan //开启对监听器@WebListener注解支持
public class PreServiceGsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PreServiceGsApplication.class, args);
    }

}
