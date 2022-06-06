package org.morellos.nanobot.bot.command.impl.common;

import org.morellos.nanobot.annotation.NanoCommand;
import org.morellos.nanobot.bot.command.CommonMessageCommand;
import org.morellos.nanobot.enums.CommandType;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.events.MessageEvent;

/**
 * 帮助指令，获取help.txt内容
 *
 * @author Robin Lang
 * @since 2022/5/6
 */
@Slf4j
@NanoCommand(type = CommandType.COMMON, command = "帮助", alias = {"help"})
public class HelpCommand extends CommonMessageCommand {

    @Override
    public void execute(MessageEvent event) {
        String sendMsg = """
                你好，这里是东云名乃提供的帮助文档，才不是什么机器人呢
                ----指令列表----
                [.help][.帮助]打开帮助列表
                [.st][.瑟图来]瑟图模块，支持[瑟图来.jpg]图片指令调用""";
        event.getSubject().sendMessage(sendMsg);
    }
}
