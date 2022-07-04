package org.morellos.nanobot.core;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;
import org.morellos.nanobot.logger.NanoBotLogger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * NanoBot配置类
 *
 * @author Robin Lang
 * @since 2022/5/6
 */
@Slf4j
@Component
@EnableConfigurationProperties(NanoBotProperties.class)
public class NanoBot implements InitializingBean {

    /**
     * 指令别名映射集合
     */
    public static Map<String, String> cmdMapping = new HashMap<>();

    /**
     * 机器人
     */
    @Getter
    private Bot bot;


    private final NanoBotProperties nanoBotProperties;

    public NanoBot(NanoBotProperties nanoBotProperties) {
        this.nanoBotProperties = nanoBotProperties;
    }

    /**
     * 添加指令别名映射
     * @param command 映射目标命令bean名称
     * @param alias 指令别名列表
     */
    public static void addCommandMapping(String command, String... alias) {
        for (String alia : alias) {
            cmdMapping.put(alia, command);
        }
    }

    /**
     * Bot配置
     *
     * @return Bot配置
     */
    public BotConfiguration requestBotConfiguration() {
        BotConfiguration configuration = new BotConfiguration();
        configuration.setProtocol(nanoBotProperties.getProtocol());
        configuration.fileBasedDeviceInfo(nanoBotProperties.getDeviceInfo());
        if (nanoBotProperties.isBotLog()) {
            configuration.setBotLoggerSupplier(o -> new NanoBotLogger(log));
        }
        if (nanoBotProperties.isNetworkLog()) {
            configuration.setNetworkLoggerSupplier(o -> new NanoBotLogger(log));
        }
        return configuration;
    }

    /**
     * 注册Bot
     */
    @Override
    public void afterPropertiesSet() {
        log.info("NanoBot starting......");
        this.bot = BotFactory.INSTANCE.newBot(
                nanoBotProperties.getAccount(),
                nanoBotProperties.getPassword(),
                requestBotConfiguration());
    }
}
