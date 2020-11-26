package com.lzw.meblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.lzw.meblog.mapper")
@SpringBootApplication
public class MeblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeblogApplication.class, args);
    }

}
