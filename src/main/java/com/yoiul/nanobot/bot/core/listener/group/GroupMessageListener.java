package com.yoiul.nanobot.bot.core.listener.group;

import com.yoiul.nanobot.annotation.NanoListener;
import com.yoiul.nanobot.bot.core.infrastructure.NanoBot;
import com.yoiul.nanobot.bot.core.listener.BaseListener;
import com.yoiul.nanobot.utils.CommandUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.GroupTempMessageEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.ObjectUtils;

/**
 * 群消息监听器
 *
 * @author Robin Lang
 * @since 2022/5/6
 */
@Slf4j
@NanoListener
public class GroupMessageListener extends BaseListener {


    /**
     * 群消息处理器
     *
     * @param event 群消息事件
     * @return 监听状态
     */
    @SneakyThrows
    @EventHandler
    @SuppressWarnings("unchecked")
    public ListeningStatus onGroupMessage(@NotNull GroupMessageEvent event) {
        log.info("群消息处理器,接收消息,id:{},nick:{},message:{}", event.getSender().getId(), event.getSender().getNick(), event.getMessage());
        // 指令头校验
        if (messageNotStartWithCommandHead(event.getMessage())) {
            return ListeningStatus.LISTENING;
        }
        // 获取对应指令执行
        String commandKey = CommandUtils.getCommandKey(event.getMessage());
        if (ObjectUtils.isEmpty(NanoBot.groupCommands.get(commandKey))) {
            return ListeningStatus.LISTENING;
        }
        NanoBot.groupCommands.get(commandKey).execute(event);
        return ListeningStatus.LISTENING;
    }

    /**
     * 群临时消息处理器
     *
     * @param event 群临时消息事件
     * @return 监听状态
     */
    @SneakyThrows
    @EventHandler
    public ListeningStatus onGroupTempMessage(@NotNull GroupTempMessageEvent event) {
        log.info("群临时消息处理器,接收消息,id:{},nick:{},message:{}", event.getSender().getId(), event.getSender().getNick(), event.getMessage());
        return ListeningStatus.LISTENING;
    }
}
