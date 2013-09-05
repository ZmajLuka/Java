package com.luka.chatter.handler;

import com.luka.chatter.Client;
import com.luka.chatter.data.impl.JoinData;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 15/08/13
 * Time: 11:50
 * To change this template use File | Settings | File Templates.
 */
public class JoinHandler extends AbstractHandler<JoinData> {

    @Override
    public void handle(JoinData data) {
        Client.getUsers().add(data.getValue());
    }
}
