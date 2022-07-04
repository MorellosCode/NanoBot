package org.morellos.nanobot.core;

import org.morellos.nanobot.listener.BaseListener;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Bot处理器，在项目启动完成后进行Bot相关操作
 *
 * @author Robin Lang
 * @since 2022/5/6
 */
@Slf4j
@Component
public class BotProcessor implements ApplicationListener<ApplicationReadyEvent> {

    @Resource
    private NanoBot nanoBot;

    @Resource
    private List<BaseListener> listenerList;

    @Override
    public void onApplicationEvent(@NotNull ApplicationReadyEvent event) {
        nanoBot.afterPropertiesSet();
        Bot bot = nanoBot.getBot();
        // 批量注册监听指令
        for (BaseListener listener : listenerList) {
            bot.getEventChannel().registerListenerHost(listener);
        }
        // 机器人登录
        bot.login();
        log.info("NanoBot initialization complete.");
    }
}