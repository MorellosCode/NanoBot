package org.morellos.nanobot.exception;

import java.io.Serial;

/**
 * @author Robin Lang
 * @since 2022/5/6
 */
public class NanoException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 7136916632062399662L;

    public NanoException() {
        super();
    }

    public NanoException(String message) {
        super(message);
    }

    public NanoException(String message, Throwable cause) {
        super(message, cause);
    }
}
