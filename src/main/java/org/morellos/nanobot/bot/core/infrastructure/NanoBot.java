package org.morellos.nanobot.bot.core.infrastructure;

import org.morellos.nanobot.bot.command.Command;
import org.morellos.nanobot.bot.listener.BaseListener;
import org.morellos.nanobot.bot.core.logger.NanoBotLogger;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
     * 机器人
     */
    @Getter
    private Bot bot;

    /**
     * 指令集合
     */
    public static Map<String, Command> commonCommands = new HashMap<>();
    public static Map<String, Command> friendCommands = new HashMap<>();
    public static Map<String, Command> groupCommands = new HashMap<>();
    public static Map<String, Command> groupTempCommands = new HashMap<>();
    public static Map<String, Command> strangerCommands = new HashMap<>();

    /**
     * 监听列表
     */
    public static List<BaseListener> listeners = new ArrayList<>();


    private final NanoBotProperties nanoBotProperties;

    public NanoBot(NanoBotProperties nanoBotProperties) {
        this.nanoBotProperties = nanoBotProperties;
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
