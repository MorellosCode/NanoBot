package org.morellos.nanobot.utils;


import java.util.List;

/**
 * @author Robin Lang
 * @since 2021/6/4
 */
public class BotUtils {
    /**
     * 获取机器人名
     *
     * @return 机器人名
     */
    public static String getBotName() {
        return PropertiesUtils.getBotName();
    }

    /**
     * 获取配置群集合
     *
     * @return 群ID集合
     */
    public static List<Long> getGroupIds() {
        return PropertiesUtils.getBotGroups();
    }
}
