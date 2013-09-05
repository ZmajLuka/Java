package com.luka.chatter.handler;

import com.luka.chatter.Client;
import com.luka.chatter.data.impl.MoveData;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 15/08/13
 * Time: 19:46
 * To change this template use File | Settings | File Templates.
 */
public class MoveHandler extends AbstractHandler<MoveData> {


    @Override
    public void handle(MoveData data) {
        if(Client.getUser().getUid().getValue().equals(data.getUid())) {
           Client.getUser().getPoint().setValue(data.getValue());
        } else {
            Client.getUsers().getUser(data.getUid()).getPoint().setValue(data.getValue());
        }
    }
}
