package org.morellos.nanobot.selector;

import net.mamoe.mirai.message.data.MessageChain;
import org.morellos.nanobot.command.Command;
import org.morellos.nanobot.command.CommonMessageCommand;
import org.morellos.nanobot.core.NanoBot;
import org.morellos.nanobot.utils.CommandUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 通用指令选择器
 *
 * @author Robin Lang
 * @since 2022/6/30
 */
@Component
public class CommonCmdSelector {

    @Resource
    private Map<String, CommonMessageCommand> commonCmdMap;

    public Command getCommand(MessageChain messageChain) {
        String key = CommandUtils.getCommandKey(messageChain);
        return commonCmdMap.get(NanoBot.cmdMapping.get(key));
    }
}
