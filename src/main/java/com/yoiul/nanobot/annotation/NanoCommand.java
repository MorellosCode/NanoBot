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
    /**
     * 指令类型
     */
    CommandType type();
    /**
     * 指令名
     */
    String command();
    /**
     * 别名指令数组
     */
    String[] alias() default {};

    /**
     * 图片指令，例：[mirai:image:{xxx}.jpg]
     */
    String image() default "";
}
