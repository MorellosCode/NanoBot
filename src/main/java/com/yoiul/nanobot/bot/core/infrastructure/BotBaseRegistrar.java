package com.yoiul.nanobot.bot.core.infrastructure;

import com.yoiul.nanobot.annotation.NanoCommand;
import com.yoiul.nanobot.annotation.NanoListener;
import com.yoiul.nanobot.bot.command.Command;
import com.yoiul.nanobot.bot.core.listener.BaseListener;
import com.yoiul.nanobot.enums.CommandType;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Map;

/**
 * 机器人基础注册器
 *
 * @author Robin Lang
 * @since 2022/5/6
 */
@Component
public class BotBaseRegistrar implements ApplicationListener<ContextRefreshedEvent> {


    /**
     * 项目启动时扫描注解注册指令及监听器
     *
     * @param event 上下文刷新事件
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {

            Map<String, Object> commandBeans = event.getApplicationContext().getBeansWithAnnotation(NanoCommand.class);
            Map<String, Object> listenerBeans = event.getApplicationContext().getBeansWithAnnotation(NanoListener.class);
            // 扫描所有符合的Command添加到指令集合中
            for (Object bean : commandBeans.values()) {
                if (bean instanceof Command command) {
                    NanoCommand nanoCommand = bean.getClass().getAnnotation(NanoCommand.class);
                    String commandKey = nanoCommand.command();
                    CommandType type = nanoCommand.type();
                    String image = nanoCommand.image();
                    setCommandsMap(command, commandKey, type);
                    // 别名添加到指令集合中
                    String[] commands = nanoCommand.alias();
                    for (String key : commands) {
                        setCommandsMap(command, key, type);
                    }
                    // 图片添加到指令集合中
                    if (!ObjectUtils.isEmpty(image)) {
                        setCommandsMap(command, image, type);
                    }
                }
            }
            // 扫描所有符合的Listener添加到监听器列表
            for (Object bean : listenerBeans.values()) {
                if (bean instanceof BaseListener listener) {
                    NanoBot.listeners.add(listener);
                }
            }
        }
    }

    private void setCommandsMap(Command<?> command, String commandKey, CommandType type) {
        switch (type) {
            case COMMON -> NanoBot.commonCommands.put(commandKey, command);
            case GROUP -> NanoBot.groupCommands.put(commandKey, command);
            case FRIEND -> NanoBot.friendCommands.put(commandKey, command);
            case GROUP_TEMP -> NanoBot.groupTempCommands.put(commandKey, command);
            case STRANGER -> NanoBot.strangerCommands.put(commandKey, command);
        }
    }
}
