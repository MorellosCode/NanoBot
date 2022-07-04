package org.morellos.nanobot.selector;

import net.mamoe.mirai.message.data.MessageChain;
import org.morellos.nanobot.command.Command;
import org.morellos.nanobot.command.GroupMessageCommand;
import org.morellos.nanobot.utils.CommandUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 群指令选择器
 *
 * @author Robin Lang
 * @since 2022/6/30
 */
@Component
public class GroupCmdSelector {

    @Resource
    private Map<String, GroupMessageCommand> groupCmdMap;

    public Command getCommand(MessageChain messageChain) {
        String key = CommandUtils.getCommandKey(messageChain);
        return groupCmdMap.get(key);
    }
}
