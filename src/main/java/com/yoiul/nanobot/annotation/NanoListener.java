package com.yoiul.nanobot.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 监听器注解，使用在类上标明当前类为监听器类，由统一处理器进行注册
 *
 * @author Robin Lang
 * @since 2022/5/6
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Component
public @interface NanoListener {
}
