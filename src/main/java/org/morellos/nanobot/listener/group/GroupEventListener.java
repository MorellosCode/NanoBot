package org.morellos.nanobot.listener.group;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.contact.MemberPermission;
import net.mamoe.mirai.contact.NormalMemberKt;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.events.*;
import org.jetbrains.annotations.NotNull;
import org.morellos.nanobot.exception.NanoException;
import org.morellos.nanobot.listener.BaseListener;
import org.morellos.nanobot.utils.BotUtils;
import org.springframework.stereotype.Component;

/**
 * 群事件监听器
 *
 * @author Robin Lang
 * @since 2022/5/6
 */
@Slf4j
@Component
public class GroupEventListener extends BaseListener {

    /**
     * 成员主动加入群
     *
     * @param event 成员主动加入群事件监听器
     * @return 监听状态
     */
    @SneakyThrows
    @EventHandler
    public ListeningStatus onMemberActiveJoin(@NotNull MemberJoinEvent.Active event) {
        String sendMsg = StrUtil.format(
                """
                        这里是群管家东云名乃,東雲なの
                        {}你好，欢迎加入[{}]
                        你是第{}位成员，欢迎！"""
                , event.getUser().getNick(), event.getGroup().getName(), event.getGroup().getMembers().size() + 1);
        log.info("当前有新成员主动加群,QQ号:{},昵称:{},当前为第{}位成员", event.getUser().getId(), event.getUser().getNick(), event.getGroup().getMembers().size() + 1);
        event.getGroup().sendMessage(sendMsg);
        return ListeningStatus.LISTENING;
    }

    /**
     * 被邀请加入群
     *
     * @param event 被邀请加入群事件
     * @return 监听状态
     */
    @SneakyThrows
    @EventHandler
    public ListeningStatus onMemberInviteJoin(@NotNull MemberJoinEvent.Invite event) {
        String sendMsg = StrUtil.format(
                """
                        这里是群管家东云名乃,東雲なの
                        {}你好，欢迎通过{}[{}]邀请加入[{}]
                        你是第{}位成员，欢迎！"""
                , event.getUser().getNick(), event.getInvitor().getNick(), event.getInvitor().getId(), event.getGroup().getName(), event.getGroup().getMembers().size() + 1);
        log.info("当前有新成员被邀请加入群,QQ号:{},昵称:{},当前为第{}位成员,邀请人:{}", event.getUser().getId(), event.getUser().getNick(), event.getGroup().getMembers().size() + 1, event.getInvitor());
        event.getGroup().sendMessage(sendMsg);
        return ListeningStatus.LISTENING;
    }

    /**
     * 成员主动退群
     *
     * @param event 成员主动退群事件
     * @return 监听状态
     */
    @SneakyThrows
    @EventHandler
    public ListeningStatus onQuit(@NotNull MemberLeaveEvent.Quit event) {
        String name = StrUtil.isEmpty(event.getMember().getNameCard()) ? event.getMember().getNick() : event.getMember().getNameCard();
        String sendMsg = StrUtil.format(
                """
                        {}[{}],悄咪咪的离开了本群
                        当前消息将在30秒后撤回""", name, event.getMember().getId());
        log.info("{}[{}]于{}，主动退出{}[{}]群",
                name, event.getMember().getId(), DateUtil.now(), event.getGroup().getName(), event.getGroup().getId());
        event.getGroup().sendMessage(sendMsg).recallIn(1000 * 30);
        return ListeningStatus.LISTENING;
    }

    /**
     * 成员被踢退群
     *
     * @param event 成员被踢退群事件
     * @return 监听状态
     */
    @SneakyThrows
    @EventHandler
    public ListeningStatus onKick(@NotNull MemberLeaveEvent.Kick event) {
        String kickedName = StrUtil.isEmpty(event.getMember().getNameCard()) ? event.getMember().getNick() : event.getMember().getNameCard();
        assert event.getOperator() != null;
        String operatorName = StrUtil.isEmpty(event.getOperator().getNameCard()) ? event.getOperator().getNick() : event.getOperator().getNameCard();
        String sendMsg = StrUtil.format(
                """
                        {}[{}]被{}无情的踢出了[{}]
                        当前消息将在30秒后撤回""", kickedName, event.getMember().getId(), operatorName, event.getGroup().getName());
        log.info("{}[{}]于{}，被群管理{}[{}]提出{}[{}]群",
                kickedName, event.getMember().getId(), DateUtil.now(), operatorName, event.getOperator().getId(), event.getGroup().getName(), event.getGroup().getId());
        event.getGroup().sendMessage(sendMsg).recallIn(1000 * 30);
        return ListeningStatus.LISTENING;
    }

    //群员荣誉修改
    @SneakyThrows
    @EventHandler
    public ListeningStatus onMemberHonorChange(@NotNull MemberHonorChangeEvent event) {
        return ListeningStatus.LISTENING;
    }

    /**
     * 群名片修改，用于检测机器人名片被恶意修改
     *
     * @param event 群名片修改事件
     * @return 监听状态
     */
    @SneakyThrows
    @EventHandler
    public ListeningStatus onMemberCardChange(@NotNull MemberCardChangeEvent event) {
        String name = StrUtil.isEmpty(event.getMember().getNameCard()) ? event.getMember().getNick() : event.getMember().getNameCard();
        // 机器人名片被修改
        if (event.getBot().getId() == event.getMember().getId()) {
            if (!BotUtils.getBotName().equals(event.getNew())) {
                event.getMember().setNameCard(BotUtils.getBotName());
                log.warn("{},系统检测到机器人群名片企图被修改,已自动重置", DateUtil.now());
                event.getGroup().sendMessage(StrUtil.format("有人企图黑入{}，未能突破AT立场", BotUtils.getBotName()));
            }
        }
        return ListeningStatus.LISTENING;
    }

    /**
     * 成员权限改变,成员不可能是机器人自己
     *
     * @param event 成员权限改变的事件
     * @return 监听状态
     */
    @SneakyThrows
    @EventHandler
    public ListeningStatus onMemberPermissionChange(@NotNull MemberPermissionChangeEvent event) {
        log.info("{}[{}]权限变动通知:{}[{}]权限由[{}]变更为[{}]", event.getGroup().getName(), event.getGroup().getId(),
                NormalMemberKt.getNameCardOrNick(event.getMember()), event.getMember().getId(),
                getGroupLevelByPermission(event.getOrigin()), getGroupLevelByPermission(event.getNew()));
        return ListeningStatus.LISTENING;
    }

    private String getGroupLevelByPermission(MemberPermission permission) throws NanoException {
        String groupLevel;
        int level = permission.getLevel();
        groupLevel = switch (level) {
            case (0) -> "一般群成员";
            case (1) -> "管理员";
            case (2) -> "群主";
            default -> throw new NanoException("获取群成员权限出现异常");
        };
        return groupLevel;
    }
}
