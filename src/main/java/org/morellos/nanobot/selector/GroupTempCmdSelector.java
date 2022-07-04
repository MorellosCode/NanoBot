package org.morellos.nanobot.selector;

import net.mamoe.mirai.message.data.MessageChain;
import org.morellos.nanobot.command.Command;
import org.morellos.nanobot.command.GroupTempMessageCommand;
import org.morellos.nanobot.core.NanoBot;
import org.morellos.nanobot.utils.CommandUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 临时群指令选择器
 *
 * @author Robin Lang
 * @since 2022/6/30
 */
@Component
public class GroupTempCmdSelector {

    @Resource
    private Map<String, GroupTempMessageCommand> groupTempCmdMap;

    public Command getCommand(MessageChain messageChain) {
        String key = CommandUtils.getCommandKey(messageChain);
        return groupTempCmdMap.get(NanoBot.cmdMapping.get(key));
    }
}
