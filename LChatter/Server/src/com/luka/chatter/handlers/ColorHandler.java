package com.luka.chatter.handlers;

import com.luka.chatter.Server;
import com.luka.chatter.ServerUser;
import com.luka.chatter.data.event.DataEvent;
import com.luka.chatter.data.impl.ColorData;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 16/08/13
 * Time: 12:07
 * To change this template use File | Settings | File Templates.
 */
public class ColorHandler extends AbstractHandler<ColorData> {
    @Override
    public void handle(DataEvent e, ColorData data) {
        ServerUser user = Server.users.getUser(e.getConnection());
        user.getColor().setValue(data.getValue());
        Server.users.send(new ColorData(user.getUid().getValue(), user.getColor().getValue()));
    }
}
