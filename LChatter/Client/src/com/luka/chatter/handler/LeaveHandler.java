package com.luka.chatter.handler;

import com.luka.chatter.Client;
import com.luka.chatter.data.impl.LeaveData;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 15/08/13
 * Time: 11:51
 * To change this template use File | Settings | File Templates.
 */
public class LeaveHandler extends AbstractHandler<LeaveData> {
    @Override
    public void handle(LeaveData data) {
        Client.getUsers().remove(data.getValue());
    }
}
