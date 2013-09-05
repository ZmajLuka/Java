package com.luka.chat.server.data;

import com.luka.chat.server.Server;
import com.luka.chat.server.handlers.AbstractHandler;

/**
 * Please do not use maliciously but for educational purposes
 * Created with IntelliJ IDEA.
 * User: Luka
 * Date: 05/09/13
 * Time: 19:40
 * To change this template use File | Settings | File Templates.
 */
public class DataEvent {

    private final byte[] bytes;
    private final byte opcode;

    public DataEvent(final byte opcode, final byte[] bytes) {
         this.bytes = bytes;
         this.opcode = opcode;
    }

    public void handle() {
        Server.handlers.get(opcode).handle(bytes);
    }

}
