package com.yoiul.nanobot.bot.core.infrastructure;

import com.yoiul.nanobot.bot.core.listener.BaseListener;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Bot处理器，在项目启动完成后进行Bot相关操作
 *
 * @author Robin Lang
 * @since 2022/5/6
 */
@Slf4j
@Component
public class BotProcessor implements ApplicationListener<ApplicationReadyEvent> {

    private final NanoBot nanoBot;

    public BotProcessor(NanoBot nanoBot) {
        this.nanoBot = nanoBot;
    }

    @Override
    public void onApplicationEvent(@NotNull ApplicationReadyEvent event) {
        nanoBot.afterPropertiesSet();
        Bot bot = nanoBot.getBot();
        // 批量注册监听指令
        for (BaseListener listener : NanoBot.listeners) {
            bot.getEventChannel().registerListenerHost(listener);
        }
        bot.login();
        log.info("NanoBot initialization complete.");
    }
}