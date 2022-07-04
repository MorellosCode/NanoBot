package org.morellos.nanobot.command.impl.group;

import lombok.SneakyThrows;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.morellos.nanobot.command.GroupMessageCommand;
import org.morellos.nanobot.core.NanoBot;
import org.springframework.stereotype.Component;

/**
 * 瑟图指令
 *
 * @author Robin Lang
 * @since 2022/5/6
 */
@Component("st")
public class SeTuCommand extends GroupMessageCommand {


    @SneakyThrows
    @Override
    public void execute(GroupMessageEvent event) {
        event.getSubject().sendMessage("ST模块响应参数");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        NanoBot.addCommandMapping("st", "st", "瑟图", "瑟图来", "色图", "色图来", "[mirai:image:{B407F708-A2C6-A506-3420-98DF7CAC4A57}.jpg]");
    }
}
