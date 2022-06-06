package org.morellos.nanobot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * 启动主类
 *
 * @author Robin Lang
 * @since 2022/5/6
 */
@EnableScheduling
@SpringBootApplication
public class NanoBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(NanoBotApplication.class, args);
    }
}
