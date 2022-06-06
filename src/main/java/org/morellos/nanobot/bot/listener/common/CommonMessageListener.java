package org.morellos.nanobot.bot.listener.common;

import org.morellos.nanobot.annotation.NanoListener;
import org.morellos.nanobot.bot.core.infrastructure.NanoBot;
import org.morellos.nanobot.bot.listener.BaseListener;
import org.morellos.nanobot.utils.CommandUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.event.events.StrangerMessageEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.ObjectUtils;


/**
 * 通用消息处理器
 *
 * @author Robin Lang
 * @since 2022/5/6
 */
@Slf4j
@NanoListener
public class CommonMessageListener extends BaseListener {

    /**
     * 通用消息处理器
     *
     * @param event 通用消息事件
     * @return 监听状态
     */
    @SneakyThrows
    @EventHandler
    @SuppressWarnings("unchecked")
    public ListeningStatus onCommonMessage(@NotNull MessageEvent event) {
        log.debug("通用消息处理器,接收消息,id:{},nick:{},message:{}", event.getSender().getId(), event.getSender().getNick(), event.getMessage());
        // 指令头校验
        if (messageNotStartWithCommandHead(event.getMessage())) {
            return ListeningStatus.LISTENING;
        }
        // 获取对应指令执行
        String commandKey = CommandUtils.getCommandKey(event.getMessage());
        if (ObjectUtils.isEmpty(NanoBot.commonCommands.get(commandKey))) {
            return ListeningStatus.LISTENING;
        }
        NanoBot.commonCommands.get(commandKey).execute(event);
        return ListeningStatus.LISTENING;
    }

    /**
     * 陌生人消息处理器
     *
     * @param event 陌生人消息事件
     * @return 监听状态
     */
    @SneakyThrows
    @EventHandler
    public ListeningStatus onStrangerMessage(@NotNull StrangerMessageEvent event) {
        log.info("陌生人消息处理器,接收消息,id:{},nick:{},message:{}", event.getSender().getId(), event.getSender().getNick(), event.getMessage());
        return ListeningStatus.LISTENING;
    }
}