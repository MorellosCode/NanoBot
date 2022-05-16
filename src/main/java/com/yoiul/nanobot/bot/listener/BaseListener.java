package com.yoiul.nanobot.bot.listener;

import com.yoiul.nanobot.utils.CommandUtils;
import kotlin.coroutines.CoroutineContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.message.data.MessageChain;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.ObjectUtils;

import static com.yoiul.nanobot.utils.CommandUtils.IMAGE_HEAD;

/**
 * 基础监听器，所有监听器应当继承此类
 *
 * @author Robin Lang
 * @since 2022/5/6
 */
@Slf4j
public abstract class BaseListener extends SimpleListenerHost {

    @SneakyThrows
    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        log.error(context.toString(), exception);
    }

    public boolean messageNotStartWithCommandHead(MessageChain messageChain) {
        String message = messageChain.contentToString();
        if (ObjectUtils.isEmpty(message)) {
            return true;
        }
        for (String image : IMAGE_HEAD) {
            if (image.equals(message)) {
                return false;
            }
        }
        return !message.startsWith(CommandUtils.getCommandHead());
    }
}
