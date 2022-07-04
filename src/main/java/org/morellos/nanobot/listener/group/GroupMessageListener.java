package org.morellos.nanobot.listener.group;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.GroupTempMessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import org.jetbrains.annotations.NotNull;
import org.morellos.nanobot.command.Command;
import org.morellos.nanobot.listener.BaseListener;
import org.morellos.nanobot.selector.GroupCmdSelector;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * 群消息监听器
 *
 * @author Robin Lang
 * @since 2022/5/6
 */
@Slf4j
@Component
public class GroupMessageListener extends BaseListener {

    @Resource
    private GroupCmdSelector groupCmdSelector;

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
        MessageChain messageChain = event.getMessage();
        log.info("群消息处理器,接收消息,id:{},nick:{},message:{}", event.getSender().getId(), event.getSender().getNick(), messageChain);
        // 指令头校验
        Command command = groupCmdSelector.getCommand(event.getMessage());
        if (messageNotStartWithCommandHead(messageChain) || ObjectUtils.isEmpty(command)) {
            return ListeningStatus.LISTENING;
        }
        // 获取对应指令执行
        command.execute(event);
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
