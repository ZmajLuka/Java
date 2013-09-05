package com.luka.chatter;

import com.luka.chatter.data.Data;

import java.util.LinkedList;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 15/08/13
 * Time: 11:14
 * To change this template use File | Settings | File Templates.
 */
public class ServerUsers extends LinkedList<ServerUser> {

    public ServerUser getUser(final Connection connection) {
        for(final ServerUser user : this) {
            if(user.connection.equals(connection)) {
                return user;
            }
        }
        return null;
    }

    public void send(final Data data) {
        for(final ServerUser user : this) {
            user.send(data);
        }
    }

}
