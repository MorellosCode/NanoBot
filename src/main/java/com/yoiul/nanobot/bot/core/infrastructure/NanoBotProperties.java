package com.yoiul.nanobot.bot.core.infrastructure;

import lombok.Data;
import net.mamoe.mirai.utils.BotConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * NanoBot配置参数
 *
 * @author Robin Lang
 * @since 2022/5/6
 */
@Data
@Component
@ConfigurationProperties("nano.bot")
public class NanoBotProperties {
    private long account = 123;
    private String password = "123";
    private long admin = 123;
    private BotConfiguration.MiraiProtocol protocol = BotConfiguration.MiraiProtocol.ANDROID_PHONE;
    private boolean botLog = true;
    private boolean networkLog = true;
    private String deviceInfo = "nanobot.json";
}
