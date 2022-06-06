package org.morellos.nanobot.bot.listener.friend;

import cn.hutool.core.util.StrUtil;
import org.morellos.nanobot.annotation.NanoListener;
import org.morellos.nanobot.bot.core.infrastructure.NanoBotProperties;
import org.morellos.nanobot.bot.listener.BaseListener;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.events.NewFriendRequestEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * 好友事件监听器
 *
 * @author Robin Lang
 * @since 2022/5/6
 */
@Slf4j
@NanoListener
public class FriendEventListener extends BaseListener {

    private final NanoBotProperties nanoBotProperties;

    public FriendEventListener(NanoBotProperties nanoBotProperties) {
        this.nanoBotProperties = nanoBotProperties;
    }

    /**
     * 新好友请求,暂时不进行自动同意操作
     *
     * @param event 一个账号请求添加机器人为好友的事件
     * @return 监听状态
     */
    @SneakyThrows
    @EventHandler
    public ListeningStatus onNewFriendRequest(@NotNull NewFriendRequestEvent event) {
        String sendMsg = StrUtil.format(
                """
                        当前有一个新请求添加好友
                        请求人:{}
                        昵称or群名片:{}
                        申请信息:{}
                        来自群(群号为0为其他途径请求):{}
                        暂不提供自动同意功能，请打开PC QQ执行操作"""
                , event.getFromId(), event.getFromNick(), event.getMessage(), event.getFromGroupId()
        );
        Objects.requireNonNull(event.getBot().getFriend(nanoBotProperties.getAdmin())).sendMessage(sendMsg);
        return ListeningStatus.LISTENING;
    }
}
