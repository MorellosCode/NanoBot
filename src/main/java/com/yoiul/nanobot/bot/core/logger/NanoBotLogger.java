package com.yoiul.nanobot.bot.core.logger;

import kotlin.jvm.internal.Intrinsics;
import net.mamoe.mirai.utils.MiraiLoggerPlatformBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.Objects;


/**
 * NanoBot日志系统，替代mirai原有日志重新实现
 *
 * @author Robin Lang
 * @since 2022/5/6
 */
public class NanoBotLogger extends MiraiLoggerPlatformBase {

    private final String identity;
    private final Logger logger;

    public NanoBotLogger(@NotNull Logger logger) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.logger = logger;
        this.identity = "NanoBot";
    }


    @Nullable
    @Override
    public String getIdentity() {
        return this.identity;
    }

    @Override
    protected void debug0(@Nullable String s, @Nullable Throwable throwable) {
        if (Objects.isNull(throwable)) {
            logger.debug("{}", s);
        } else {
            logger.debug("{},{}", s, throwable);
        }
    }

    @Override
    protected void error0(@Nullable String s, @Nullable Throwable throwable) {
        if (Objects.isNull(throwable)) {
            logger.error("{}", s);
        } else {
            logger.error("{},{}", s, throwable);
        }
    }

    @Override
    protected void info0(@Nullable String s, @Nullable Throwable throwable) {
        if (Objects.isNull(throwable)) {
            logger.info("{}", s);
        } else {
            logger.info("{},{}", s, throwable);
        }
    }

    @Override
    protected void verbose0(@Nullable String s, @Nullable Throwable throwable) {
        if (Objects.isNull(throwable)) {
            logger.trace("{}", s);
        } else {
            logger.trace("{},{}", s, throwable);
        }
    }

    @Override
    protected void warning0(@Nullable String s, @Nullable Throwable throwable) {
        if (Objects.isNull(throwable)) {
            logger.warn("{}", s);
        } 
    }
}
