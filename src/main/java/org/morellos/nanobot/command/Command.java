package org.morellos.nanobot.command;

import net.mamoe.mirai.event.Event;
import org.springframework.beans.factory.InitializingBean;

/**
 * 指令统一接口
 *
 * @author Robin Lang
 * @since 2022/5/6
 */
public interface Command<T extends Event> extends InitializingBean {
    /**
     * 指令执行方法
     *
     * @param event 事件
     */
    void execute(T event);
}
