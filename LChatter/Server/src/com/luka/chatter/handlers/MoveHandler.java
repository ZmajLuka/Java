package com.luka.chatter.handlers;

import com.luka.chatter.Server;
import com.luka.chatter.ServerUser;
import com.luka.chatter.data.event.DataEvent;
import com.luka.chatter.data.impl.MoveData;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 15/08/13
 * Time: 19:43
 * To change this template use File | Settings | File Templates.
 */
public class MoveHandler extends AbstractHandler<MoveData> {
    @Override
    public void handle(DataEvent e, MoveData data) {
        ServerUser user = Server.users.getUser(e.getConnection());
        user.getPoint().setValue(data.getValue());
        Server.users.send(new MoveData(user.getUid().getValue(), user.getPoint().getValue()));


    }
}
