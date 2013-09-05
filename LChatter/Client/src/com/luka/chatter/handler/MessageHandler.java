package com.luka.chatter.handler;

import com.luka.chatter.Client;
import com.luka.chatter.data.impl.MessageData;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 15/08/13
 * Time: 11:49
 * To change this template use File | Settings | File Templates.
 */
public class MessageHandler extends AbstractHandler<MessageData> {

    @Override
    public void handle(MessageData data) {
        Client.getChatArea().addMessage(data);
    }
}
