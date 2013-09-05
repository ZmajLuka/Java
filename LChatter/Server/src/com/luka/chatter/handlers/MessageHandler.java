package com.luka.chatter.handlers;

import com.luka.chatter.Server;
import com.luka.chatter.ServerUser;
import com.luka.chatter.data.event.DataEvent;
import com.luka.chatter.data.impl.MessageData;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 15/08/13
 * Time: 11:17
 * To change this template use File | Settings | File Templates.
 */
public class MessageHandler extends AbstractHandler<MessageData> {
    @Override
    public void handle(DataEvent e, MessageData data) {
        final ServerUser user = Server.users.getUser(e.getConnection());
        final MessageData message = new MessageData(user.getUid().getValue(), String.format("%s : %s" , user.getName().getValue(), data.getValue()));
        Server.users.send(message);
    }
}
