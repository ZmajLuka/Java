package com.luka.chat.server.handlers;

import com.luka.chat.data.Data;
import com.luka.chat.data.impl.MessageData;
import com.luka.chat.server.Server;

/**
 * Please do not use maliciously but for educational purposes
 * Created with IntelliJ IDEA.
 * User: Luka
 * Date: 05/09/13
 * Time: 19:26
 * To change this template use File | Settings | File Templates.
 */
public class MessageHandler extends AbstractHandler {

    @Override
    public void handle(byte[] bytes) {
        Server.send(new MessageData(new String(bytes).trim()));
    }
}
