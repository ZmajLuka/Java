package com.luka.chatter.handler;

import com.luka.chatter.Client;
import com.luka.chatter.data.impl.ColorData;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 16/08/13
 * Time: 12:10
 * To change this template use File | Settings | File Templates.
 */
public class ColorHandler extends AbstractHandler<ColorData> {

    @Override
    public void handle(ColorData data) {
        if(Client.getUser().getUid().getValue().equals(data.getUid())) {
            Client.getUser().getColor().setValue(data.getValue());
        } else {
            Client.getUsers().getUser(data.getUid()).getColor().setValue(data.getValue());
        }
    }
}
