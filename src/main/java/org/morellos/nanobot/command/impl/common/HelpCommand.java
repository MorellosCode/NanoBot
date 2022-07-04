package org.morellos.nanobot.command.impl.common;

import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.events.MessageEvent;
import org.morellos.nanobot.command.CommonMessageCommand;
import org.morellos.nanobot.core.NanoBot;
import org.springframework.stereotype.Component;

/**
 * 帮助指令，获取help.txt内容
 *
 * @author Robin Lang
 * @since 2022/5/6
 */
@Slf4j
@Component("help")
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

    @Override
    public void afterPropertiesSet() throws Exception {
        NanoBot.addCommandMapping("help", "help", "帮助", "HELP");
    }
}
