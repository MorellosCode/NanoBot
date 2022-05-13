package com.yoiul.nanobot.annotation;

import com.yoiul.nanobot.enums.CommandType;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 指令注解，使用在类上标明当前类为指令类，由统一指令处理器进行注册
 *
 * @author Robin Lang
 * @since 2022/5/6
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Component
public @interface NanoCommand {

    CommandType type();

    String command();

    String[] alias() default {};

    String image() default "";
}
