package com.yoiul.nanobot.bot.command;

import net.mamoe.mirai.event.Event;

/**
 * 指令统一接口
 *
 * @author Robin Lang
 * @since 2022/5/6
 */
public interface Command<T extends Event> {

    /**
     * 指令执行方法
     *
     * @param event 事件
     */
    void execute(T event);
}
