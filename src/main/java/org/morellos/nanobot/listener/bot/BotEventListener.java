package org.morellos.nanobot.listener.bot;

import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.events.*;
import org.jetbrains.annotations.NotNull;
import org.morellos.nanobot.listener.BaseListener;
import org.springframework.stereotype.Component;

/**
 * Bot相关事件监听器
 *
 * @author Robin Lang
 * @since 2022/5/6
 */
@Slf4j
@Component
public class BotEventListener extends BaseListener {


    @EventHandler
    public ListeningStatus onBotOnline(@NotNull BotOnlineEvent event) {
        log.info("Bot登录完成, 好友列表, 群组列表初始化完成");
        return ListeningStatus.LISTENING;
    }

    @EventHandler
    public ListeningStatus onBotOffline(@NotNull BotOfflineEvent event) {
        log.info("Bot主动离线");
        return ListeningStatus.LISTENING;
    }

    @EventHandler
    public ListeningStatus onBotReLogin(@NotNull BotReloginEvent event) {
        log.info("Bot主动或被动重新登录");
        return ListeningStatus.LISTENING;
    }

    @EventHandler
    public ListeningStatus onBotAvatarChanged(@NotNull BotAvatarChangedEvent event) {
        log.info("Bot头像被修改->头像链接:{}", event.getBot().getAvatarUrl());
        return ListeningStatus.LISTENING;
    }

    @EventHandler
    public ListeningStatus onBotNickChanged(@NotNull BotNickChangedEvent event) {
        log.info("Bot昵称改变为->{}", event.getBot().getNick());
        return ListeningStatus.LISTENING;
    }
}