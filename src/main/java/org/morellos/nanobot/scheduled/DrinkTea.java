package org.morellos.nanobot.scheduled;

import org.morellos.nanobot.bot.core.infrastructure.NanoBot;
import org.morellos.nanobot.exception.NanoException;
import org.morellos.nanobot.utils.BotUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.contact.ContactList;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.utils.ExternalResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 饮茶小助手，每天下午15点定时执行
 *
 * @author Robin Lang
 * @since 2021/7/1
 */
@Slf4j
@Component
public class DrinkTea {

    private final NanoBot nanoBot;

    public DrinkTea(NanoBot nanoBot) {
        this.nanoBot = nanoBot;
    }

    @SneakyThrows
    @Scheduled(cron = "0 0 15 * * ?", zone = "Asia/Shanghai")
    public void pleaseDrinkTea() {
        InputStream inputStream = new ClassPathResource("/nanobot/DrinkTea.gif").getInputStream();
        List<Long> groupIds = BotUtils.getGroupIds();
        ContactList<Group> groups = nanoBot.getBot().getGroups();
        for (Group group : groups) {
            long id = group.getId();
            if (groupIds.contains(id)) {
                sendImage(inputStream, group);
            }
        }
    }

    private void sendImage(InputStream inputStream, Group group) {
        ExternalResource resource = null;
        try {
            resource = ExternalResource.create(inputStream);
            Image image = group.uploadImage(resource);
            group.sendMessage(image);
        } catch (IOException e) {
            throw new NanoException(e.getMessage());
        }finally {
            try {
                assert resource != null;
                resource.close();
            } catch (IOException e) {
                log.error("ExternalResource.close() failed to execute.");
            }
        }
    }
}
