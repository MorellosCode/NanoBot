package com.yoiul.nanobot.utils;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Robin Lang
 * @since 2022/5/6
 */
@Component
public class PropertiesUtils {

    @Value("${nano.bot.name:Nano}")
    private String name;
    @Value("${nano.bot.head:.}")
    private String head;
    @Value("${nano.bot.groups:123}")
    private String groups;

    private static String botName;
    private static String botHead;
    private static List<Long> botGroups;

    @PostConstruct
    public void setBotName() {
        botName = this.name;
    }

    public static String getBotName() {
        return botName;
    }

    @PostConstruct
    public void setBotHead() {
        botHead = this.head;
    }

    public static String getBotHead() {
        return botHead;
    }

    @PostConstruct
    public void setBotGroups() {
        List<String> split = StrUtil.split(this.groups, ",", true, true);
        botGroups = split.stream().map(Long::parseLong).collect(Collectors.toList());
    }

    public static List<Long> getBotGroups() {
        return botGroups;
    }
}
