package org.morellos.nanobot.listener.friend;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import org.jetbrains.annotations.NotNull;
import org.morellos.nanobot.listener.BaseListener;
import org.springframework.stereotype.Component;

/**
 * 好友消息监听器
 *
 * @author Robin Lang
 * @since 2022/5/6
 */
@Slf4j
@Component
public class FriendMessageListener extends BaseListener {

    /**
     * 好友消息处理器
     *
     * @param event 好友消息事件
     * @return 监听状态
     */
    @SneakyThrows
    @EventHandler
    public ListeningStatus onFriendMessage(@NotNull FriendMessageEvent event) {
        log.info("好友消息处理器,接收消息,id:{},nick:{},message:{}", event.getSender().getId(), event.getSender().getNick(), event.getMessage());
        return ListeningStatus.LISTENING;
    }
}
