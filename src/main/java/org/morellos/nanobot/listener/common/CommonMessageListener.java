package org.morellos.nanobot.listener.common;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.event.events.StrangerMessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import org.jetbrains.annotations.NotNull;
import org.morellos.nanobot.command.Command;
import org.morellos.nanobot.listener.BaseListener;
import org.morellos.nanobot.selector.CommonCmdSelector;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;


/**
 * 通用消息处理器
 *
 * @author Robin Lang
 * @since 2022/5/6
 */
@Slf4j
@Component
public class CommonMessageListener extends BaseListener {

    @Resource
    private CommonCmdSelector commonCmdSelector;

    /**
     * 通用消息处理器
     *
     * @param event 通用消息事件
     * @return 监听状态
     */
    @SuppressWarnings("unchecked")
    @SneakyThrows
    @EventHandler
    public ListeningStatus onCommonMessage(@NotNull MessageEvent event) {
        MessageChain messageChain = event.getMessage();
        log.debug("通用消息处理器,接收消息,id:{},nick:{},message:{}", event.getSender().getId(), event.getSender().getNick(), event.getMessage());
        // 指令头校验
        Command command = commonCmdSelector.getCommand(event.getMessage());
        if (messageNotStartWithCommandHead(messageChain) || ObjectUtils.isEmpty(command)) {
            return ListeningStatus.LISTENING;
        }
        // 获取对应指令执行
        command.execute(event);
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