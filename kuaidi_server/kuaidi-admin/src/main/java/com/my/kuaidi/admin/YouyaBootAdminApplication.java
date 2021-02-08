package com.my.kuaidi.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.my.kuaidi.core",
        "com.my.kuaidi.service",
        "com.my.kuaidi.admin"
        })
public class YouyaBootAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(YouyaBootAdminApplication.class, args);
        }

}
