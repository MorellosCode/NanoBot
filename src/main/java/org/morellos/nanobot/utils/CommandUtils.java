package org.morellos.nanobot.utils;

import org.morellos.nanobot.bot.command.Command;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.message.data.MessageChain;
import org.springframework.util.ObjectUtils;

import java.util.Map;

/**
 * @author Robin Lang
 * @since 2022/5/6
 */
@Slf4j
public class CommandUtils {

    public final static String[] IMAGE_HEAD = {"[动画表情]", "图片"};

    /**
     * 获取指令头
     *
     * @return 指令头，可以配置为[.][#][!]等
     */
    public static String getCommandHead() {
        return PropertiesUtils.getBotHead();
    }

    /**
     * 获取指令对象
     *
     * @param messageChain 消息内容
     * @param commandMap   指令集合
     * @return 指令对象
     */
    public static Command getCommand(MessageChain messageChain, Map<String, Command> commandMap) {
        return commandMap.get(CommandUtils.getCommandKey(messageChain));
    }

    /**
     * 处理消息体，获取指令集key键
     *
     * @return 指令集key
     */
    public static String getCommandKey(MessageChain messageChain) {
        String message = messageChain.contentToString();
        for (String image : IMAGE_HEAD) {
            if (image.equals(message)) {
                return messageChain.serializeToMiraiCode();
            }
        }
        String headMsg = StrUtil.split(message, " ", 0, true, true).get(0);
        String commandHead = getCommandHead();
        if (ObjectUtils.isEmpty(commandHead) && !headMsg.startsWith(commandHead)) {
            return "";
        }
        return headMsg.replaceFirst(commandHead, "");
    }
}
