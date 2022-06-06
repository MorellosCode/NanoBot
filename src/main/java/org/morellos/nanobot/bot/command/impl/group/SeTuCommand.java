package org.morellos.nanobot.bot.command.impl.group;

import org.morellos.nanobot.annotation.NanoCommand;
import org.morellos.nanobot.bot.command.GroupMessageCommand;
import org.morellos.nanobot.enums.CommandType;
import lombok.SneakyThrows;
import net.mamoe.mirai.event.events.GroupMessageEvent;

/**
 * 瑟图指令
 *
 * @author Robin Lang
 * @since 2022/5/6
 */
@NanoCommand(type = CommandType.GROUP, command = "st", alias = {"瑟图", "瑟图来", "色图", "色图来"},
        image = "[mirai:image:{B407F708-A2C6-A506-3420-98DF7CAC4A57}.jpg]")
public class SeTuCommand extends GroupMessageCommand {


    @SneakyThrows
    @Override
    public void execute(GroupMessageEvent event) {
        event.getSubject().sendMessage("ST模块响应参数");
    }

}
